package co.id.bankmandiri.mandirimovie.injection.module;

import android.app.Application;
import android.content.Context;

import co.id.bankmandiri.mandirimovie.injection.qualifier.AppContext;
import co.id.bankmandiri.mandirimovie.injection.scope.PerApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@Module
public class AppModule {
    protected final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    @AppContext
    Context provideAppContext() {
        return mApp;
    }
}

