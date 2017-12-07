package com.zuluft.mvi.presentation.main.login;


import com.zuluft.mvi.base.views.BaseView;
import com.zuluft.mvi.presentation.main.login.dataModels.LoginDataModel;

import io.reactivex.Observable;

public interface LoginView
        extends
        BaseView<LoginViewState> {

    Observable<LoginDataModel> loginClickIntent();
}
