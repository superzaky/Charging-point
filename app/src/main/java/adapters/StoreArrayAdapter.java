package adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.yomac_000.chargingpoint.R;

import java.util.HashMap;
import java.util.List;

import model.Store;

/**
 * Created by yomac_000 on 20-12-2015.
 */
public class StoreArrayAdapter extends BaseExpandableListAdapter {
    private List<Store> listDataHeaders;
    private HashMap<Store, List<Store>> listDataChildren;
    private Context context;

    public StoreArrayAdapter(Context context, List<Store> listDataHeaders, HashMap<Store, List<Store>> listDataChildren) {
        this.context = context;
        this.listDataHeaders = listDataHeaders;
        this.listDataChildren = listDataChildren;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChildren.get(this.listDataHeaders.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeaders.get(groupPosition);
    }

    @Override
    public Store getChild(int groupPosition, int childPosition) {
        return this.listDataChildren.get(this.listDataHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Store store = (Store) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView tvID = (TextView) convertView.findViewById(R.id.txtId);
        tvID.setText("Facebook ID: " + store.getFacebookID());
        TextView tvName = (TextView) convertView.findViewById(R.id.txtName);
        tvName.setText(store.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String itemTitle = getChild(childPosition, groupPosition).getFacebookID();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.lblListItem);
        title.setText(itemTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
