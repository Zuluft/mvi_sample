package com.zuluft.mvi.domain.repository;


import android.support.annotation.NonNull;

import com.zuluft.mvi.domain.models.UserModel;
import com.zuluft.mvi.presentation.main.login.dataModels.LoginDataModel;

import io.reactivex.Observable;

public interface Repository {

    Observable<UserModel> login(@NonNull LoginDataModel loginDataModel);
}
