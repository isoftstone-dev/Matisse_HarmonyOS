package com.example.matisse.slice;

import com.chg.ultimateprovider.UltimateProvider;
import com.example.matisse.ResourceTable;
import com.example.matisse.model.NestedList;
import com.example.matisse.model.UriModel;
import com.example.matisse.utils.ArraySplitUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.Size;
import ohos.media.photokit.metadata.AVStorage;
import ohos.utils.net.Uri;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MatisseAbilitySlice extends AbilitySlice {

    private static final HiLogLabel TAG =new HiLogLabel(HiLog.LOG_APP, 0, "===demo===");
    private ListContainer listContainer;
    List<UriModel> uris=new ArrayList<UriModel>();

    public List getUris() {
        return uris;
    }

    ArraySplitUtil<UriModel> arraySplitUtil=new ArraySplitUtil();
    List<NestedList> nestedLists=new ArrayList<>();
    // 保存最终选中的结果
    public static List<UriModel> selectedSet = new ArrayList<>();
    public void setUris(List<UriModel> uris) {
        this.uris = uris;
    }

    public void setNestedLists(List<NestedList> nestedLists) {
        this.nestedLists = nestedLists;
    }
    public List<UriModel> getSelectedSet() {
        return selectedSet;
    }

    public void setSelectedSet(List<UriModel> selectedSet) {
        this.selectedSet = selectedSet;
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_matisse);
        String[] per={"ohos.permission.READ_USER_STORAGE"};
        requestPermissionsFromUser(per,0);

    }

    @Override
    public void onActive() {
        super.onActive();
        {
            DataAbilityHelper helper = DataAbilityHelper.creator(this);
            try {
                //    int i=0;
                ResultSet resultSet = helper.query(AVStorage.Images.Media.EXTERNAL_DATA_ABILITY_URI, null, null);
                while (resultSet != null && resultSet.goToNextRow()) {
                    int mid = resultSet.getInt(resultSet.getColumnIndexForName(AVStorage.Images.Media.ID));
                    Uri uri = Uri.appendEncodedPathToUri(AVStorage.Images.Media.EXTERNAL_DATA_ABILITY_URI, "" + mid);
                    FileDescriptor fileDescriptor = null;
                    fileDescriptor = helper.openFile(uri, "r");
                    ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                    decodingOptions.desiredSize = new Size(180, 320);
                    ImageSource imageSource = ImageSource.create(fileDescriptor, null);
                    PixelMap pixelMap = imageSource.createThumbnailPixelmap(decodingOptions, true);
                    uris.add(new UriModel(uri, pixelMap));

                }
                if(uris.size()>3) {
                    List<List<UriModel>> newList = arraySplitUtil.splistList(uris, 3);

                    for (List<UriModel> uriModel : newList) {
                        nestedLists.add(new NestedList(uriModel));
                    }
                }else {
                    nestedLists.add(new NestedList(uris));
                }

            } catch (DataAbilityRemoteException | FileNotFoundException e) {
                e.printStackTrace();
            }
            HiLog.warn(TAG, getUris().toString());
            listContainer = (ListContainer) findComponentById(ResourceTable.Id_galley);
            UltimateProvider ultimateProvider=new UltimateProvider(nestedLists,getContext());
            listContainer.setItemProvider(ultimateProvider);

        }
        Button preview=(Button)findComponentById(ResourceTable.Id_preview);
        Button use=(Button)findComponentById(ResourceTable.Id_use);
        preview.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if (selectedSet.size()==0){
                    return;
                }
                else {
                    Intent intent = new Intent();
                    Operation option = new Intent.OperationBuilder()
                            .withDeviceId("")
                            .withBundleName("com.example.matisse")
                            .withAbilityName("com.example.matisse.PreviewAbility")
                            .build();
                    intent.setParam("model", (Serializable) selectedSet);
                    intent.setOperation(option);
                    startAbility(intent);
                }
            }
        });
        use.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if (selectedSet.size()==0){
                    return;
                }
                else {
                    Intent resultIntent = new Intent();
                    resultIntent.setParam("model", (Serializable) selectedSet);
                    // Intent resultIntent = new Intent();
                    setResult(resultIntent);

                }
            }
        });
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
