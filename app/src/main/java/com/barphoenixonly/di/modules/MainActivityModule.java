package com.barphoenixonly.di.modules;

import com.barphoenixonly.di.scopes.ActivityScope;
import com.barphoenixonly.di.scopes.FragmentScope;
import com.barphoenixonly.routers.main.MainActivityRouter;
import com.barphoenixonly.routers.main.MainActivityRouterImpl;
import com.barphoenixonly.ui.fragments.recorder.view.RecorderFragment;
import com.barphoenixonly.ui.fragments.start.view.StartFragment;
import com.barphoenixonly.ui.fragments.web.view.WebFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {
    @ActivityScope
    @Binds
    MainActivityRouter mainActivityRouter(MainActivityRouterImpl mainRouter);

    @FragmentScope
    @ContributesAndroidInjector
    WebFragment webFragment();

    @FragmentScope
    @ContributesAndroidInjector
    StartFragment logoFragment();

    @FragmentScope
    @ContributesAndroidInjector
    RecorderFragment reecorderFragment();


}
