package networking;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.threeten.bp.LocalDateTime;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public class NetworkManager {

    public static final NetworkManager singleton = new NetworkManager();

    private final String url = " https://sstatus.bahrcode.com/api/";

    private final Network network;

    private NetworkManager() {
        network = RetrofitNetworkManager
                .initializeBahrcodeRetrofit(Network.class,
                        url,
                        new BahrcodeCookieStore(),
                        getGson());
    }

    public Network getNetwork(){
        return network;
    }

    private Gson getGson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(UPPER_CAMEL_CASE)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeStringTypeConverter())
                .create();
    }

}
