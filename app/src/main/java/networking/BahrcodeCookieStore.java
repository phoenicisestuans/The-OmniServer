package networking;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BahrcodeCookieStore {

    public static final String VERIFICATION_TOKEN_KEY = "RequestVerificationToken";
    public static final String COOKIE_KEY = "Cookie";
    public static final String SET_COOKIES_KEY = "Set-Cookie";

    protected static final String delimiter = "; ";
    protected static final String trimmedDelimeter = ";";
    protected String headerVerificationToken = "";
    protected HashMap<String, String> cookieMap = new HashMap<>();

    protected static final int KEY_INDEX = 0;
    protected static final int COOKIE_INDEX = 1;

    protected void store(){

        Log.e("bahrcode.4dxos", "Cookies stored without overriding class");

    }

    public String getHeaderVerificationToken() {
        return headerVerificationToken;
    }

    public void setHeaderVerificationToken(String headerVerificationToken) {

        if(headerVerificationToken != null) {
            this.headerVerificationToken = headerVerificationToken;
            store();
        }

    }

    public String getCookies(){

        List<String> reconstitutedCookies = new ArrayList<>();

        for(String key : cookieMap.keySet()){
            reconstitutedCookies.add(key + "=" + cookieMap.get(key));
        }

        return TextUtils.join(delimiter, reconstitutedCookies);
    }

    public void updateCookie(String cookie){
        DeconstructedCookie decon = deconstruct(cookie);
        if(decon != null) {
            cookieMap.put(decon.key, decon.cookie);
            store();
        }
    }

    public void setCookies(List<String> cookies){
        for(String cookie : cookies){
            DeconstructedCookie structured = deconstruct(cookie);
            if(structured != null)
                cookieMap.put(structured.key, structured.cookie);
        }
        if(cookies.size() > 0)
            store();
    }

    public void clear(){
        cookieMap = new HashMap<>();
    }

    public boolean containsCookie(String cookie){
        DeconstructedCookie updated = deconstruct(cookie);
        if(updated == null) return false;
        return cookieMap.containsKey(updated.key)
                && (cookieMap.get(updated.key).equals(updated.cookie));
    }

    //Returns 2 objects: Key and DeconstructedCookie
    protected DeconstructedCookie deconstruct(String cookie){
        String[] keyAndCookie = cookie.split("=");
        if(keyAndCookie.length >= 2)
            return new DeconstructedCookie(cookie.split("="));
        return null;
    }

    protected class DeconstructedCookie {
        public String key;
        public String cookie;

        public DeconstructedCookie(String[] keyAndCookie){
            key = keyAndCookie[KEY_INDEX];
            cookie = keyAndCookie[COOKIE_INDEX];

            //Case for if there are flags attached to the cookie
            if(keyAndCookie.length > 2){

                StringBuilder concat = new StringBuilder();
                concat.append(cookie);

                for(int i = 2; i < keyAndCookie.length; i++){
                    concat.append("="); //Restoring from split
                    concat.append(keyAndCookie[i]); //Adding in split flag
                }

                cookie = concat.toString();
            }

        }

    }

}
