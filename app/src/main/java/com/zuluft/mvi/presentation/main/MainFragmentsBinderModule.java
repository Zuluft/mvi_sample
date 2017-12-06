package com.zuluft.mvi.presentation.main;


import android.support.v4.app.Fragment;

import com.zuluft.mvi.presentation.main.login.LoginComponent;
import com.zuluft.mvi.presentation.main.login.LoginFragment;
import com.zuluft.mvi.presentation.main.splash.SplashComponent;
import com.zuluft.mvi.presentation.main.splash.SplashFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        SplashComponent.class,
        LoginComponent.class
})
public abstract class MainFragmentsBinderModule {

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindSplashFragment(SplashComponent.Builder __);


    @Binds
    @IntoMap
    @FragmentKey(LoginFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindLoginFragment(LoginComponent.Builder __);
}
