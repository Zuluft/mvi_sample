package com.zuluft.mvi.base.views;

import javax.annotation.Nonnull;

import io.reactivex.Observable;

public interface BaseView<T> {
    void states(@Nonnull Observable<T> observable);
}
