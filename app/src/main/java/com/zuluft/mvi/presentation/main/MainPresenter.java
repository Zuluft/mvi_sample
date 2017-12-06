package com.zuluft.mvi.presentation.main;

import com.orhanobut.logger.Logger;
import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.mvi.base.scopes.PerActivity;
import com.zuluft.mvi.presentation.main.actions.DrawLoginScreenAction;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Observable;

@PerActivity
public class MainPresenter
        extends
        BasePresenter<MainViewState, MainView> {

    @Inject
    MainPresenter() {
        Logger.d("MainPresenter constructor");
    }

    @Nonnull
    @Override
    protected MainViewState generateInitialState() {
        return MainViewState.builder().state(MainViewState.SPLASH_SCREEN).build();
    }

    @Override
    protected void onAttach(boolean firstAttach) {
        if (firstAttach) {
            Observable.just(new DrawLoginScreenAction())
                    .delay(10, TimeUnit.SECONDS)
                    .subscribe(this::dispatch);
        }
    }
}
