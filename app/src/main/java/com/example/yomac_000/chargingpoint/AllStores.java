package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.os.Bundle;
import java.util.List;

import model.Store;
import retrofit.Response;
import services.StoreService;

public class AllStores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Response<List<Store>> subprises = new StoreService().getSubprises();
        System.out.println("AllStores (Activity) - subprise " + subprises);
        setContentView(R.layout.activity_all_stores);
    }


}
