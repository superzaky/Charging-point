package api;

import java.util.List;

import model.Store;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by yomac_000 on 16-12-2015.
 */
public interface SubpriseAPI {
    @GET("api/locations/get")
    Call<List<Store>> listStores();
}
