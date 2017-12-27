package com.zuluft.mvi.base.presenters;

import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.base.views.BaseView;

import javax.annotation.Nonnull;

import io.reactivex.subjects.BehaviorSubject;

public abstract class BasePresenter<S, T extends BaseView<S>> {

    protected T mView;
    private S mOldState;
    private final BehaviorSubject<S> mBehaviorSubject = BehaviorSubject.create();
    private boolean mIsFirstAttach = true;

    public final void attach(@Nonnull T view) {
        this.mView = view;
        mView.states(mBehaviorSubject);
        if (mIsFirstAttach) {
            mOldState = generateInitialState();
            mBehaviorSubject.onNext(mOldState);
        }
        onAttach();
        if (mIsFirstAttach) {
            onFirstAttach();
        }
        mIsFirstAttach = false;
    }

    public final void detach() {
        onDetach();
        mView = null;
    }

    @SuppressWarnings("WeakerAccess")
    protected void onDetach() {

    }

    @SuppressWarnings("unused")
    public void onDestroy() {

    }

    @SuppressWarnings("unused")
    protected abstract void onFirstAttach();

    protected final <Q extends Action<S>> void dispatch(@Nonnull Q action) {
        mBehaviorSubject.onNext(action.newState(mOldState));
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull
    protected abstract S generateInitialState();

    protected abstract void onAttach();

}
