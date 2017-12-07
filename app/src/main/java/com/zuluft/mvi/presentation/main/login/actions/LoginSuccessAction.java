package com.zuluft.mvi.presentation.main.login.actions;


import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.presentation.main.login.LoginViewState;

import javax.annotation.Nonnull;

public class LoginSuccessAction
        implements
        Action<LoginViewState> {
    @Override
    public LoginViewState newState(@Nonnull LoginViewState oldState) {
        return oldState.toBuilder().throwable(null)
                .state(LoginViewState.LOGIN_SUCCESS)
                .build();
    }
}
