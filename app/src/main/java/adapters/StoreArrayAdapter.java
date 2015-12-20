package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yomac_000.chargingpoint.R;

import java.util.List;

import model.Store;

/**
 * Created by yomac_000 on 20-12-2015.
 */
public class StoreArrayAdapter extends ArrayAdapter<Store> {

    public StoreArrayAdapter(Context context, int textViewResourceId, List<Store> stores) {
        super(context, textViewResourceId, stores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        if (convertView == null) {
            // inflate your list view here
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_row, parent, false);
        }
        Store store = getItem(position);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(store.getName());
        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);
        txtId.setText("Facebook ID: " + store.getFacebookID());

        return convertView;
    }
}
