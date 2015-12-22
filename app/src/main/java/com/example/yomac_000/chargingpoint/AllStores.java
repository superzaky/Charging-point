package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;

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
    private ExpandableListView expListView;
    Context context;
    Response<List<Store>> subprises;
    Iterator it;
    List<Store> subprisesList;
    HashMap<Store, List<Store>> subprisesContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stores);
        expListView = (ExpandableListView) findViewById(R.id.store_list);
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
        subprisesContentList = new HashMap<>();
        int i = 0;
        while(it.hasNext()) {
            Store store = (Store) it.next();
            subprisesList.add(store);
            subprisesContentList.put(subprisesList.get(i), subprisesList);
            i++;
        }
    }

    private void setupList() {
        StoreArrayAdapter adapter = new StoreArrayAdapter(getApplicationContext(),
                subprisesList,
                subprisesContentList);
        expListView.setAdapter(adapter);
    }
}
