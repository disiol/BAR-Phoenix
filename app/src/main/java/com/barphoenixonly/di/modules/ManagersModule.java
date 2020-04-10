package com.barphoenixonly.di.modules;

import android.content.Context;

import com.barphoenixonly.manedger.PreferencesManager;
import com.barphoenixonly.manedger.PreferencesManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagersModule {

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context){
        return new PreferencesManagerImpl(context);
    }


}
