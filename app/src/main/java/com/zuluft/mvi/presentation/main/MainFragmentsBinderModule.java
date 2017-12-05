package com.zuluft.mvi.presentation.main;


import android.support.v4.app.Fragment;

import com.zuluft.mvi.presentation.main.splash.SplashComponent;
import com.zuluft.mvi.presentation.main.splash.SplashFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        SplashComponent.class
})
public abstract class MainFragmentsBinderModule {

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindMainActivity(SplashComponent.Builder __);
}
