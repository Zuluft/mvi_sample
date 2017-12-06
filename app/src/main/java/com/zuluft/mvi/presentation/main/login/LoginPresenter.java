package com.zuluft.mvi.presentation.main.login;


import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.mvi.base.scopes.PerFragment;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@PerFragment
public class LoginPresenter
        extends
        BasePresenter<LoginViewState, LoginView> {

    @Inject
    LoginPresenter() {
    }

    @Nonnull
    @Override
    protected LoginViewState generateInitialState() {
        return LoginViewState.create();
    }

    @Override
    protected void onAttach(boolean firstAttach) {

    }
}
