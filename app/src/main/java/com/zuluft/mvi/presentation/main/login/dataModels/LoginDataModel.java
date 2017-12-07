package com.zuluft.mvi.presentation.main.login.dataModels;


import com.google.auto.value.AutoValue;

@AutoValue
public abstract class LoginDataModel {

    public abstract String username();

    public abstract String password();

    public static LoginDataModel create(String username, String password) {
        return new AutoValue_LoginDataModel(username, password);
    }
}
