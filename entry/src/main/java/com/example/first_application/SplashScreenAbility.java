package com.example.first_application;

import com.example.first_application.slice.SplashScreenAbilitySlice;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

/**
 * Splash screen ability
 */
public class SplashScreenAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SplashScreenAbilitySlice.class.getName());
    }
}