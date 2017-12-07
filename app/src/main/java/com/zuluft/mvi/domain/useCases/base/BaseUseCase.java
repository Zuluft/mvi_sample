package com.zuluft.mvi.domain.useCases.base;


import android.support.annotation.NonNull;

import com.zuluft.mvi.domain.repository.Repository;

import io.reactivex.Observable;

public abstract class BaseUseCase<T, P> {

    protected Repository mRepository;

    public BaseUseCase(@NonNull Repository repository) {
        this.mRepository = repository;
    }

    @SuppressWarnings({"unused", "unchecked"})
    public abstract Observable<T> createObservable(P... args);
}
