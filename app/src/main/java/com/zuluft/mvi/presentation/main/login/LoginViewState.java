package com.zuluft.mvi.presentation.main.login;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

@AutoValue
public abstract class LoginViewState {

    public static final int LOGIN_NOT_STARTED = 0;
    public static final int LOGIN_IN_PROGRESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int LOGIN_SUCCESS = 3;

    public abstract int state();

    @Nullable
    public abstract Throwable throwable();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_LoginViewState.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder state(int state);

        public abstract Builder throwable(Throwable throwable);

        public abstract LoginViewState build();
    }
}
