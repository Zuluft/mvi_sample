package com.zuluft.mvi.domain.models;


import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserModel {

    public abstract String username();

    public abstract String password();

    public static UserModel create(String username, String password) {
        return new AutoValue_UserModel(username, password);
    }
}
