package com.example.first_application.slice;

import com.example.first_application.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

/**
 * Hello world ability slice
 */
public class MainAbilitySlice extends AbilitySlice {
    private static final int REQ_CODE_QUERY_WEATHER =11 ;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
    }

    @Override
    public void onActive() {
        super.onActive();
        Button zhihu=(Button) findComponentById(ResourceTable.Id_button_sample);
        zhihu.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
               // Matisse.from(getAbility()).forResult(11);
                Intent intent=new Intent();
                Operation operation=new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withAction("action.pick_pic")
                        .withBundleName("com.example.matisse")
                        .withAbilityName("com.example.matisse.MatisseAbility")
                        .build();
                intent.setOperation(operation);
                startAbilityForResult(intent,0);
            }
        });
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}