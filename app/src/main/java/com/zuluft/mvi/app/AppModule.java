package com.zuluft.mvi.app;

import com.zuluft.mvi.domain.repository.Repository;
import com.zuluft.mvi.domain.repository.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Repository provideRepository() {
        return new RepositoryImpl();
    }
}
