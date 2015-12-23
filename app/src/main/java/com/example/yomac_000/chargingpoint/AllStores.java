package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import adapters.StoreArrayAdapter;
import model.ParentStore;
import model.Store;
import retrofit.Response;
import services.StoreService;

public class AllStores extends Activity {
    private ExpandableListView expListView;
    Response<List<Store>> subprises;
    Iterator it;
    ParentStore parentStore;
    List<ParentStore> parentStoresList;

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
        parentStore = new ParentStore();
        parentStoresList = new ArrayList<>();
        int i = 0;
        while(it.hasNext()) {
            Store store = (Store) it.next();
            if (store != null) {
                parentStore.getChildStoresList().add(i, store);
            }
            parentStoresList.add(parentStore);
            i++;
        }
    }

    private void setupList() {
        StoreArrayAdapter adapter = new StoreArrayAdapter(getApplicationContext(), parentStoresList);
        expListView.setAdapter(adapter);
    }
}
