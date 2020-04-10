package com.barphoenixonly.di.modules;


import com.barphoenixonly.api.Api;
import com.barphoenixonly.interactor.DataStore;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    DataStore provideDataStore(Api api, Gson gson) {
        return new DataStore(api, gson);
    }
}
