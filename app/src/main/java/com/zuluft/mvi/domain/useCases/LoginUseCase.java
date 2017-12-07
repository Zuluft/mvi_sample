package com.zuluft.mvi.domain.useCases;


import android.support.annotation.NonNull;

import com.zuluft.mvi.domain.models.UserModel;
import com.zuluft.mvi.domain.repository.Repository;
import com.zuluft.mvi.domain.useCases.base.BaseUseCase;
import com.zuluft.mvi.presentation.main.login.dataModels.LoginDataModel;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginUseCase
        extends
        BaseUseCase<UserModel, LoginDataModel> {

    @Inject
    LoginUseCase(@NonNull Repository repository) {
        super(repository);
    }

    @Override
    public Observable<UserModel> createObservable(LoginDataModel... args) {
        return mRepository.login(args[0]);
    }
}
