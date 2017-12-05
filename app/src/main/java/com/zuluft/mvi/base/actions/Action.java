package com.zuluft.mvi.base.actions;

import javax.annotation.Nonnull;

public interface Action<T> {

    T newState(@Nonnull T oldState);
}
