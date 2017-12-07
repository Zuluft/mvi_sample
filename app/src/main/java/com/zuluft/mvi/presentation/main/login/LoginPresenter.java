package com.zuluft.mvi.presentation.main.login;


import android.support.annotation.NonNull;

import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.mvi.base.scopes.PerFragment;
import com.zuluft.mvi.domain.useCases.LoginUseCase;
import com.zuluft.mvi.presentation.main.login.actions.LoginFailedAction;
import com.zuluft.mvi.presentation.main.login.actions.LoginInProgressAction;
import com.zuluft.mvi.presentation.main.login.actions.LoginSuccessAction;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@PerFragment
public class LoginPresenter
        extends
        BasePresenter<LoginViewState, LoginView> {

    private final LoginUseCase mLoginUseCase;

    @Inject
    LoginPresenter(@NonNull final LoginUseCase loginUseCase) {
        mLoginUseCase = loginUseCase;
    }

    @Nonnull
    @Override
    protected LoginViewState generateInitialState() {
        return LoginViewState.builder().state(LoginViewState.LOGIN_NOT_STARTED)
                .build();
    }

    @Override
    protected void onAttach(boolean firstAttach) {
        registerDisposables(
                subscribeLoginClickIntent()
        );
    }

    @NonNull
    private Disposable subscribeLoginClickIntent() {
        return mView.loginClickIntent().flatMap(loginDataModel ->
                mLoginUseCase.createObservable(loginDataModel)
                        .map(userModel -> new LoginSuccessAction())
                        .cast(Action.class)
                        .onErrorReturn(LoginFailedAction::new)
                        .startWith(new LoginInProgressAction())
                        .subscribeOn(Schedulers.newThread()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dispatch);
    }
}
