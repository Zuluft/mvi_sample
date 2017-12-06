package com.zuluft.mvi.presentation.main.login;


import com.zuluft.mvi.base.scopes.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent
public interface LoginComponent
        extends
        AndroidInjector<LoginFragment> {

    @Subcomponent.Builder
    abstract class Builder
            extends
            AndroidInjector.Builder<LoginFragment> {

    }
}
