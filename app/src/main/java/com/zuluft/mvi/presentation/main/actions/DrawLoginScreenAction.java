package com.zuluft.mvi.presentation.main.actions;

import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.presentation.main.MainViewState;

import javax.annotation.Nonnull;

public class DrawLoginScreenAction
        implements
        Action<MainViewState> {

    @Override
    public MainViewState newState(@Nonnull final MainViewState oldState) {
        return oldState.toBuilder().state(MainViewState.LOGIN_SCREEN).build();
    }
}
