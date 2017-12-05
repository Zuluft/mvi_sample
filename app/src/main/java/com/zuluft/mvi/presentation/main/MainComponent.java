package com.zuluft.mvi.presentation.main;

import com.zuluft.mvi.base.scopes.PerActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(modules = {
        MainFragmentsBinderModule.class
})
public interface MainComponent
        extends
        AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder
            extends
            AndroidInjector.Builder<MainActivity> {

    }
}
