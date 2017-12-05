package com.zuluft.mvi.app;

import android.app.Activity;

import com.zuluft.mvi.presentation.main.MainComponent;
import com.zuluft.mvi.presentation.main.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainComponent.class
})
public abstract class ActivityBindings {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivity(MainComponent.Builder __);
}
