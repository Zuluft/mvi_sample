package com.zuluft.mvi.presentation.main.splash;

import android.os.Bundle;

import com.zuluft.mvi.R;
import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.base.fragments.BaseFragment;

import javax.annotation.Nonnull;

@LayoutResourceId(R.layout.fragment_splash)
public class SplashFragment
        extends
        BaseFragment<SplashViewState, SplashPresenter>
        implements
        SplashView {

    @Override
    protected void reflectState(@Nonnull SplashViewState state) {

    }

    @Override
    protected void onPresenterReady(@Nonnull final SplashPresenter mPresenter,
                                    final boolean isFirstAttach) {
        mPresenter.attach(this, isFirstAttach);
    }

    @Override
    protected void renderView(Bundle savedInstanceState) {

    }

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
