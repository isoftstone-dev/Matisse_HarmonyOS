package com.example.matisse.model;

import com.chg.ultimateprovider.Model;
import com.example.matisse.ResourceTable;
import com.example.matisse.viewholder.AlbumListViewHolder;

import java.util.List;

public class NestedList implements Model {

    private List<UriModel> data;

    public List<UriModel> getData() {
        return data;
    }

    public NestedList(List<UriModel> data) {
        this.data = data;
    }

    public void setData(List<UriModel> data) {
        this.data = data;
    }
    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_nestedlayout;
    }

    @Override
    public Class getHolderClass(int position) {
        return AlbumListViewHolder.class;
    }
}
