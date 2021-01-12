package com.example.matisse;

import com.example.matisse.slice.MatisseAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MatisseAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MatisseAbilitySlice.class.getName());
        addActionRoute("action.pick_pic", MatisseAbilitySlice.class.getName());
    }

    @Override
    protected void onActive() {
        super.onActive();
        Intent resultIntent = new Intent();
        setResult(0
                , resultIntent);
    }
}
