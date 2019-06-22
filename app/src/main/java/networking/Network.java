package networking;


import com.bahrcode.ship.mainshipview.ShipInfo;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Network {

    @POST("authentication/login")
    Completable login(@Body LoginRequest loginRequest);

    @GET("Ships")
    Observable<List<ShipInfo>> getShips();

}
