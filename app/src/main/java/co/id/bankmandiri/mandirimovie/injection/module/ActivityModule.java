package co.id.bankmandiri.mandirimovie.injection.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import co.id.bankmandiri.mandirimovie.injection.qualifier.ActivityContext;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
