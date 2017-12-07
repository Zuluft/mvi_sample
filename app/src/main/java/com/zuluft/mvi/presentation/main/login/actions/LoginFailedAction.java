package com.zuluft.mvi.presentation.main.login.actions;


import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.presentation.main.login.LoginViewState;

import javax.annotation.Nonnull;

public class LoginFailedAction
        implements Action<LoginViewState> {

    private final Throwable mThrowable;

    public LoginFailedAction(Throwable throwable) {
        this.mThrowable = throwable;
    }

    @Override
    public LoginViewState newState(@Nonnull LoginViewState oldState) {
        return oldState.toBuilder().state(LoginViewState.LOGIN_FAILED)
                .throwable(mThrowable)
                .build();
    }
}
