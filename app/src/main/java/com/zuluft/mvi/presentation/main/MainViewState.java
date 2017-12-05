package com.zuluft.mvi.presentation.main;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MainViewState {

    public static final int SPLASH_SCREEN = 0;
    public static final int LOGIN_SCREEN = 1;

    public abstract int state();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_MainViewState.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder state(int state);

        public abstract MainViewState build();
    }
}
