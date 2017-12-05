package com.zuluft.mvi.presentation.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zuluft.mvi.R;
import com.zuluft.mvi.base.activities.BaseActivity;
import com.zuluft.mvi.base.annotations.LayoutResourceId;
import com.zuluft.mvi.presentation.main.splash.SplashFragment;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


@LayoutResourceId(R.layout.activity_main)
public class MainActivity
        extends
        BaseActivity<MainViewState, MainPresenter>
        implements
        MainView,
        HasSupportFragmentInjector {

    public static final String SPLASH_FRAGMENT_TAG = "SPLASH_FRAGMENT_TAG";


    @Inject
    DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;

    @Override
    protected void renderView(@javax.annotation.Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onPresenterReady(@Nonnull final MainPresenter presenter) {
        presenter.attach(this);
    }

    @Override
    protected void reflectState(@Nonnull MainViewState mainViewState) {
        switch (mainViewState.state()) {
            case MainViewState.SPLASH_SCREEN:
                if (getSupportFragmentManager().findFragmentById(R.id.flContent) == null) {
                    getSafeFragmentTransaction().registerFragmentTransition(fragmentManager ->
                            fragmentManager.beginTransaction()
                                    .add(R.id.flContent,
                                            SplashFragment.newInstance(), SPLASH_FRAGMENT_TAG)
                                    .commit());
                }
                break;
            case MainViewState.LOGIN_SCREEN:
                break;
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }
}
