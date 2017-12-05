package com.zuluft.mvi.presentation.main.splash;

import com.zuluft.mvi.base.scopes.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent
public interface SplashComponent
        extends
        AndroidInjector<SplashFragment> {

    @Subcomponent.Builder
    abstract class Builder
            extends
            AndroidInjector.Builder<SplashFragment> {

    }
}
