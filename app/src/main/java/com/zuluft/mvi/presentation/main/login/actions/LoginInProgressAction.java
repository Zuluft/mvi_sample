package com.zuluft.mvi.presentation.main.login.actions;


import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.presentation.main.login.LoginViewState;

import javax.annotation.Nonnull;

public class LoginInProgressAction
        implements
        Action<LoginViewState> {


    @Override
    public LoginViewState newState(@Nonnull LoginViewState oldState) {
        return oldState.toBuilder().state(LoginViewState.LOGIN_IN_PROGRESS)
                .build();
    }
}
