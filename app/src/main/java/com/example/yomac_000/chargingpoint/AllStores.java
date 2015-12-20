package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import adapters.StoreArrayAdapter;
import model.Store;
import retrofit.Response;
import services.StoreService;

public class AllStores extends Activity {
    private ListView lv;
    Context context;
    Response<List<Store>> subprises;
    Iterator it;
    List<Store> subprisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stores);
        lv = (ListView) findViewById(R.id.store_list);
        try {
            populateList();
            setupList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateList() throws InterruptedException, ExecutionException, IOException {
        subprises = new StoreService().getSubprises();
        it = subprises.body().iterator();
        subprisesList = new ArrayList<>();
        int i = 0;
        while(it.hasNext()) {
            i++;
            Store store = (Store) it.next();
            subprisesList.add(store);
        }
    }

    private void setupList() {
        StoreArrayAdapter adapter = new StoreArrayAdapter(getApplicationContext(),
                R.layout.listview_item_row,
                subprisesList);
        lv.setAdapter(adapter);
    }
}
