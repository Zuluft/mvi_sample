package com.zuluft.mvi.presentation.main.login;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class LoginViewState {


    public static LoginViewState create() {
        return new AutoValue_LoginViewState();
    }
}
