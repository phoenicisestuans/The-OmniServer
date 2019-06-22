package networking;

import com.bahrcode.ship.mainshipview.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Matthew on 3/24/2018.
 *
 * Adapted from here:
 * https://stackoverflow.com/questions/32605711/adding-header-to-all-request-with-retrofit-2
 */

public class RetrofitNetworkManager {

    //region Authentication
    public static <T> T initializeBahrcodeRetrofit(Class<T> interfaceToInstantiate, String websitePath, BahrcodeCookieStore store, Gson gson){

        OkHttpClient client = getBahrcodeHttpClient(new BahrcodeInterceptor(store));

        Retrofit rfit = new Retrofit.Builder()
                .baseUrl(websitePath)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return rfit.create(interfaceToInstantiate);
    }

    public static <T> T initializeBahrcodeRetrofit(Class<T> interfaceToInstantiate, String websitePath, BahrcodeCookieStore store){
        Gson gson = new GsonBuilder()
                .create();
        return initializeBahrcodeRetrofit(interfaceToInstantiate, websitePath, store, gson);
    }

    //endregion

    //region Synchronization

    //Used by internal classes to create the correct type of interface
    public static OkHttpClient getBahrcodeHttpClient(Interceptor interceptor){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        httpClient.addInterceptor(interceptor);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);

        return httpClient.build();

    }

    //endregion

}
