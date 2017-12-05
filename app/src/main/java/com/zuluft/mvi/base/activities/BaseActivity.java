package com.zuluft.mvi.base.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.presenters.BasePresenter;
import com.zuluft.safeFragmentTransaction.components.SafeFragmentTransactorActivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity<S, T extends BasePresenter<S, ?>>
        extends
        SafeFragmentTransactorActivity {

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
        Object nonConfigInstance = getLastCustomNonConfigurationInstance();
        if (nonConfigInstance == null) {
            AndroidInjection.inject(this);
        } else {
            mPresenter = (T) nonConfigInstance;
        }
        onPresenterReady(mPresenter);
    }

    protected abstract void renderView(@Nullable Bundle savedInstanceState);

    protected abstract void onPresenterReady(@Nonnull T presenter);

    public final void states(Observable<S> observable) {
        mCompositeDisposable.add(observable.subscribe(this::reflectState));
    }

    protected abstract void reflectState(@Nonnull S state);

    @Inject
    final void setPresenter(@Nonnull T presenter) {
        mPresenter = presenter;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mPresenter;
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
