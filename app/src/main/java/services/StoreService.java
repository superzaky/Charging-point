package services;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.SubpriseAPI;
import model.Store;
import okio.Buffer;
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


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    SubpriseAPI subpriseAPI = retrofit.create(SubpriseAPI.class);

    public void getSubprises() {
        Call<List<Store>> call = subpriseAPI.listStores();
        call.enqueue(new Callback<List<Store>>() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<List<Store>> response, Retrofit retrofit) {
                Iterator it=response.body().iterator();
                while(it.hasNext())
                    System.out.println(((Store)it.next()).getSTREET());

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
