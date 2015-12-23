package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yomac_000 on 22-12-2015.
 */
public class ParentStore {
    private List<Store> childStoresList;

    public ParentStore() {
        childStoresList = new ArrayList<>();
    }

    public ParentStore(List<Store> childStoresList) {
        this.childStoresList = childStoresList;
    }

    public List<Store> getChildStoresList() {
        return childStoresList;
    }

    public void setChildStoresList(List<Store> childStoresList) {
        this.childStoresList = childStoresList;
    }
}
