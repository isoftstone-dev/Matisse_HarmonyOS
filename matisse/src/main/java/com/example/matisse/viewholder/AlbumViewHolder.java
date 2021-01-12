package com.example.matisse.viewholder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.matisse.ResourceTable;
import com.example.matisse.model.UriModel;
import com.example.matisse.slice.MatisseAbilitySlice;
import ohos.agp.components.Checkbox;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class AlbumViewHolder extends ViewHolder<UriModel> {
    private static final HiLogLabel TAG =new HiLogLabel(HiLog.LOG_APP, 0, "===demo===");
    private Image icon;
  // ri pvate PixelMap pixelMap;
    private Checkbox checkbox;


    public AlbumViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);
        icon = (Image) findComponentById(ResourceTable.Id_icon);
        checkbox=(Checkbox)findComponentById(ResourceTable.Id_checked);
                checkbox.setChecked(false);
            icon.setClickedListener(new Component.ClickedListener() {
                @Override
                public void onClick(Component component) {
                        checkbox.toggle();
                    //    getModel().getUri()

                }
            });

        checkbox.setCheckedStateChangedListener((view, state) -> {
            if (state) {
               MatisseAbilitySlice.selectedSet.add(getModel());
            }else {
                MatisseAbilitySlice.selectedSet.remove(getModel());
            }
        //    HiLog.warn(TAG, MainAbilitySlice.selectedSet.toString());
        });
    }

    @Override
    public void onDataBound() {
       // name.setText(getModel().getName());
           icon.setPixelMap(getModel().getPixelMap());

    }
}
