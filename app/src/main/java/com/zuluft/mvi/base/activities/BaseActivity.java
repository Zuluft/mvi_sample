package com.zuluft.mvi.base.activities;

import android.os.Bundle;

import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.safeFragmentTransaction.components.SafeFragmentTransactorActivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity<S, T extends BasePresenter<S, ?>>
        extends
        SafeFragmentTransactorActivity {

    private Lazy<T> mLazyPresenter;
    private T mPresenter;
    private CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        LayoutResourceId layoutResourceId = getClass().getAnnotation(LayoutResourceId.class);
        if (layoutResourceId != null) {
            setContentView(layoutResourceId.value());
        }
        renderView(savedInstanceState);
        AndroidInjection.inject(this);
        onPresenterReady(mPresenter, savedInstanceState == null);
    }

    protected abstract void renderView(@Nullable Bundle savedInstanceState);

    protected abstract void onPresenterReady(@Nonnull T presenter, boolean isFirstAttach);

    public final void states(Observable<S> observable) {
        mCompositeDisposable.add(observable.subscribe(this::reflectState));
    }

    protected abstract void reflectState(@Nonnull S state);

    @SuppressWarnings("unchecked")
    @Inject
    final void setPresenter(@Nonnull Lazy<T> lazyPresenter) {
        Object object = getLastCustomNonConfigurationInstance();
        if (object != null) {
            lazyPresenter = (Lazy<T>) object;
        }
        mLazyPresenter = lazyPresenter;
        mPresenter = mLazyPresenter.get();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mLazyPresenter;
    }


    @Override
    protected void onDestroy() {
        mPresenter.detach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }
}
