package com.example.matisse.viewholder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.matisse.ResourceTable;
import com.example.matisse.model.NestedList;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;

public class AlbumListViewHolder extends ViewHolder<NestedList> {


    private ListContainer listContainer;
    private UltimateProvider ultimateProvider;

    /**
     * 构造方法
     *
     * @param eventTransmissionListener 事件传输对象
     * @param component                 component
     * @param provider                  provider
     */
    public AlbumListViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component, provider);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_nested);
        ultimateProvider = new UltimateProvider(null,getContext());

    }

    @Override
    public void onDataBound() {
        ultimateProvider.setModels(getModel().getData());
        listContainer.setItemProvider(ultimateProvider);
    }

}
