package com.zuluft.mvi.presentation.main.splash;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SplashViewState {

    public static SplashViewState create() {
        return new AutoValue_SplashViewState();
    }
}
