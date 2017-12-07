package com.zuluft.mvi.base.presenters;

import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.base.views.BaseView;

import javax.annotation.Nonnull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BasePresenter<S, T extends BaseView<S>> {

    protected T mView;
    private S mOldState;
    private final BehaviorSubject<S> mBehaviorSubject = BehaviorSubject.create();
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private boolean mIsFirstAttach = true;

    public final void attach(@Nonnull T view) {
        this.mView = view;
        mView.states(mBehaviorSubject);
        if (mIsFirstAttach) {
            mOldState = generateInitialState();
            mBehaviorSubject.onNext(mOldState);
        }
        onAttach(mIsFirstAttach);
        mIsFirstAttach = false;
    }

    public final void detach(boolean destroy) {
        onDetach();
        if (destroy) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    protected final void registerDisposables(Disposable... disposables) {
        mCompositeDisposable.addAll(disposables);
    }

    @SuppressWarnings("WeakerAccess")
    protected void onDetach() {

    }

    protected final <Q extends Action<S>> void dispatch(@Nonnull Q action) {
        mBehaviorSubject.onNext(action.newState(mOldState));
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull
    protected abstract S generateInitialState();

    protected abstract void onAttach(boolean firstAttach);

}
