package com.zuluft.mvi.domain.repository;


import android.support.annotation.NonNull;

import com.zuluft.mvi.domain.models.UserModel;
import com.zuluft.mvi.presentation.main.login.dataModels.LoginDataModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class RepositoryImpl
        implements
        Repository {
    @Override
    public Observable<UserModel> login(@NonNull final LoginDataModel loginDataModel) {
        return Observable.just(UserModel.create(loginDataModel.username(),
                loginDataModel.password()))
                .map(userModel -> {
                    if (!userModel.username().equals("aaa")) {
                        throw new RuntimeException();
                    }
                    return userModel;
                })
                .delay(10, TimeUnit.SECONDS);
    }
}
