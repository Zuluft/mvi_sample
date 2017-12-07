package com.zuluft.mvi.presentation.main.splash;

import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.mvi.base.scopes.PerFragment;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@PerFragment
public class SplashPresenter
        extends
        BasePresenter<SplashViewState, SplashView> {

    @Inject
    SplashPresenter() {
    }

    @Nonnull
    @Override
    protected SplashViewState generateInitialState() {
        return SplashViewState.create();
    }

    @Override
    protected void onAttach(boolean firstAttach) {

    }
}
