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

    private class LongOperation extends AsyncTask<String, Void, Response<List<Store>>> {

        @Override
        protected Response<List<Store>> doInBackground(String... params) {
            System.out.println("doInBackground executed second");
            try {
                Call<List<Store>> call = subpriseAPI.listStores();
                stores=call.execute();
                Iterator it=stores.body().iterator();
//                while(it.hasNext())
//                    System.out.println(((Store)it.next()).getSTREET());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stores;
        }

        @Override
        protected void onPreExecute() {
            //Can't put the call here because this is the main thread
            System.out.println("onPreExecute first");
        }

        @Override
        protected void onPostExecute(Response<List<Store>> result) {
            //Can't put the call here because this is the main thread
            setStores(stores);
        }
    }

    public Response<List<Store>> getStores() {
        return stores;
    }

    public void setStores(Response<List<Store>> stores) {
        this.stores = stores;
    }

}
