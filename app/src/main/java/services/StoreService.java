package services;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Response<List<Store>> getSubprises() throws IOException, ExecutionException, InterruptedException {
        LongOperation longOperation = new LongOperation();
        longOperation.execute("");
        stores = longOperation.get();
        return stores;
    }

    private class LongOperation extends AsyncTask<String, Void, Response<List<Store>>> {
        @Override
        protected Response<List<Store>> doInBackground(String... params) {
            try {
                Call<List<Store>> call = subpriseAPI.listStores();
                stores=call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stores;
        }
    }
}
