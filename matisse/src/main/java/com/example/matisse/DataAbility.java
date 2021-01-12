package com.example.matisse;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.photokit.metadata.AVStorage;
import ohos.utils.PacMap;
import ohos.utils.net.Uri;

import java.io.FileDescriptor;

public class DataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final HiLogLabel TAG =new HiLogLabel(HiLog.LOG_APP, 0, "===demo===");
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "DataAbility onStart");
        DataAbilityHelper helper=DataAbilityHelper.creator(this);
        try {
            ResultSet resultSet=helper.query(AVStorage.Images.Media.EXTERNAL_DATA_ABILITY_URI,null,null);

            while (resultSet!=null&&resultSet.goToNextRow()){
                int mid=resultSet.getInt(resultSet.getColumnIndexForName(AVStorage.Images.Media.ID));
                Uri uri=Uri.appendEncodedPathToUri(AVStorage.Images.Media.EXTERNAL_DATA_ABILITY_URI,""+mid);
                HiLog.warn(TAG,uri+"mid="+mid);
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        return null;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        HiLog.info(LABEL_LOG, "DataAbility insert");
        return 999;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public FileDescriptor openFile(Uri uri, String mode) {
        return null;
    }

    @Override
    public String[] getFileTypes(Uri uri, String mimeTypeFilter) {
        return new String[0];
    }

    @Override
    public PacMap call(String method, String arg, PacMap extras) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}