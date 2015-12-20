package com.example.yomac_000.chargingpoint;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Store;
import retrofit.Response;
import services.StoreService;

public class AllStores extends Activity {
    private ListView lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stores);
        lv = (ListView) findViewById(R.id.store_list);
        Response<List<Store>> subprises;
        try {
            subprises = new StoreService().getSubprises();
            Iterator it = subprises.body().iterator();
            List<String> subprisesList = new ArrayList<String>();

            int i = 0;
            while(it.hasNext()) {
                i++;
                subprisesList.add((String) it.next());
            }
            StoreArrayAdapter stringArrayAdapter = new StoreArrayAdapter(
                    getApplicationContext(),
                    lv,
                    subprisesList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private class StoreArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StoreArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
