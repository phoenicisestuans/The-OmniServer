package networking;


import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static networking.BahrcodeCookieStore.COOKIE_KEY;
import static networking.BahrcodeCookieStore.SET_COOKIES_KEY;
import static networking.BahrcodeCookieStore.VERIFICATION_TOKEN_KEY;

public class BahrcodeInterceptor implements Interceptor {

    protected final BahrcodeCookieStore store;

    public BahrcodeInterceptor(BahrcodeCookieStore store) {
        this.store = store;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String verificationToken = store.getHeaderVerificationToken();
        String cookie = store.getCookies();

        Request request = chain.request().newBuilder()
                .addHeader(VERIFICATION_TOKEN_KEY, verificationToken)
                .addHeader(COOKIE_KEY, cookie)
                .build();

        Response response = chain.proceed(request);
        setCookies(response);

        //Allows us to intercept html which returns when cookies are invalid
        String header = getContent(response);
        if(headerIsHtml(header)){
            return new Response.Builder()
                    .code(403)
                    .request(request)
                    .protocol(response.protocol())
                    .message(response.message())
                    .body(response.body())
                    .build();
        }

        return response;

    }

    protected void setCookies(Response response) {
        //Store cookie if it is different
        if (response.isSuccessful()) {
            String verificationToken = response.header(VERIFICATION_TOKEN_KEY);
            if(verificationToken != null){
                store.setHeaderVerificationToken(verificationToken);
            }
            List<String> cookies = response.headers(SET_COOKIES_KEY);
            for(String cookie : cookies){
                store.updateCookie(cookie);
            }
        }
    }

    protected String getContent(Response response){
        return response.headers().get("Content-Type");
    }

    protected boolean headerIsHtml(String content){
        if(content == null) return false;
        final String html = "text/html; charset=utf-8";
        return content.equals(html);
    }

}
