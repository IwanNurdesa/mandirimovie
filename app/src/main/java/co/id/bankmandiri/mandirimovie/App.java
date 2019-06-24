package co.id.bankmandiri.mandirimovie;

import android.app.Application;
import android.content.Context;

import co.id.bankmandiri.mandirimovie.injection.component.AppComponent;
import co.id.bankmandiri.mandirimovie.injection.component.DaggerAppComponent;
import co.id.bankmandiri.mandirimovie.injection.module.AppModule;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class App extends Application {

    private static App sInstance;
    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static App getInstance() {
        return sInstance;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static Context getContext() {
        return sInstance;
    }

}
