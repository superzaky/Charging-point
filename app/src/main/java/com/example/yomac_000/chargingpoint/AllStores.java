package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import model.Store;
import services.StoreService;

public class AllStores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_stores);
        new StoreService().getSubprises();

//        for(int i=1; i<11; i++) {
//            System.out.println("Count is: "+ subprises.iterator().next()  );
//        }

    }
}
