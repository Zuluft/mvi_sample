package com.zuluft.mvi.base.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.safeFragmentTransaction.SafeFragmentTransaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment<S, T extends BasePresenter<S, ?>>
        extends
        Fragment {

    private T mPresenter;
    private CompositeDisposable mCompositeDisposable;
    protected View mRootView;
    private SafeFragmentTransaction mSafeFragmentTransaction;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        mSafeFragmentTransaction = SafeFragmentTransaction.createInstance(getLifecycle(),
                getChildFragmentManager());
        getLifecycle().addObserver(mSafeFragmentTransaction);
        mCompositeDisposable = new CompositeDisposable();
        mRootView = createView(inflater, container);
        renderView(savedInstanceState);
        AndroidSupportInjection.inject(this);
        onPresenterReady(mPresenter, savedInstanceState == null);
        return mRootView;
    }

    public final SafeFragmentTransaction getSafeFragmentTransaction() {
        if (this.mSafeFragmentTransaction == null) {
            throw new RuntimeException("getSafeFragmentTransaction() method was called before onCreateView()");
        } else {
            return this.mSafeFragmentTransaction;
        }
    }

    public final void states(Observable<S> observable) {
        mCompositeDisposable.add(observable.subscribe(this::reflectState));
    }

    @Inject
    final void setPresenter(@Nonnull T presenter) {
        mPresenter = presenter;
    }

    protected abstract void reflectState(@Nonnull S state);

    protected abstract void onPresenterReady(@Nonnull T presenter, boolean isFirstAttach);

    protected abstract void renderView(Bundle savedInstanceState);

    protected View createView(@Nonnull LayoutInflater inflater,
                              @Nullable ViewGroup container) {
        View rootView = null;
        LayoutResourceId layoutResourceId = this.getClass().getAnnotation(LayoutResourceId.class);
        if (layoutResourceId != null) {
            rootView = inflater.inflate(layoutResourceId.value(), container, false);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        super.onDestroyView();
    }
}
