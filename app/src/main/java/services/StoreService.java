package services;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import api.SubpriseAPI;
import model.Store;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yomac_000 on 16-12-2015.
 */
public class StoreService {

    public static final String BASE_URL = "http://getairport.com/subprise/";
    Response<List<Store>> stores;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SubpriseAPI subpriseAPI = retrofit.create(SubpriseAPI.class);

    public Response<List<Store>> getSubprises() {
        new LongOperation().execute("");
        System.out.println("getStores (StoreService.java) value "+ getStores());
        return getStores();
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            System.out.println("doInBackground executed second");

            return " doInBackground Executed";
        }

        @Override
        protected void onPreExecute() {
            System.out.println("onPreExecute first");
            Call<List<Store>> call = subpriseAPI.listStores();
            try {
                stores=call.execute();
                Iterator it=stores.body().iterator();
//                while(it.hasNext())
//                    System.out.println("Stores "+((Store)it.next()).getSTREET());
                setStores(stores);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Response<List<Store>> getStores() {
        return stores;
    }

    public void setStores(Response<List<Store>> stores) {
        this.stores = stores;
    }

}
