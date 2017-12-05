package com.zuluft.mvi.base.presenters;

import com.zuluft.mvi.base.actions.Action;
import com.zuluft.mvi.base.views.BaseView;

import javax.annotation.Nonnull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BasePresenter<S, T extends BaseView<S>> {

    private T mView;
    private boolean mWasAttached;
    private S mOldState;
    private final BehaviorSubject<S> mBehaviorSubject = BehaviorSubject.create();
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public final void attach(@Nonnull T view) {
        this.mView = view;
        mView.states(mBehaviorSubject);
        if (!mWasAttached) {
            mOldState = generateInitialState();
            mBehaviorSubject.onNext(mOldState);
        }
        onAttach(!mWasAttached);
        mWasAttached = true;

    }

    public final void detach() {
        onDetach();
        mCompositeDisposable.dispose();
        mView = null;
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
