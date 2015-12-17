package services;

import android.annotation.TargetApi;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.SubpriseAPI;
import model.Store;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yomac_000 on 16-12-2015.
 */
public class StoreService {

    public static final String BASE_URL = "http://getairport.com/subprise/";
    List<Store> stores;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SubpriseAPI subpriseAPI = retrofit.create(SubpriseAPI.class);

    public List<Store> getSubprises() {
        Call<List<Store>> call = subpriseAPI.listStores();
        call.enqueue(new Callback<List<Store>>() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<List<Store>> response, Retrofit retrofit) {
                Iterator it=response.body().iterator();
                List<Store> stores = new ArrayList<Store>();
                while(it.hasNext())
                    stores.add((Store)it.next());
                    //System.out.println(((Store)it.next()).getSTREET());
                System.out.println("OnResponse (StoreService.java) - stores "+stores);
                setStores(stores);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        System.out.println("getStores (StoreService.java) value "+ getStores());
        return getStores();
    }
}
