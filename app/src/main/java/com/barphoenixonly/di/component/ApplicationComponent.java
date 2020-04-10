package com.barphoenixonly.di.component;

import android.content.Context;

import com.barphoenixonly.App;
import com.barphoenixonly.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        ApplicationComponent build();
    }

    void inject(App app);
}
