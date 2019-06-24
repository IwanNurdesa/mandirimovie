package co.id.bankmandiri.mandirimovie.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import co.id.bankmandiri.mandirimovie.injection.qualifier.ActivityContext;
import co.id.bankmandiri.mandirimovie.injection.qualifier.ChildFragmentManager;
import co.id.bankmandiri.mandirimovie.injection.qualifier.DefaultFragmentManager;
import co.id.bankmandiri.mandirimovie.injection.scope.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ActivityContext
    Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @DefaultFragmentManager
    FragmentManager provideDefaultFragmentManager() {
        return mFragment.getFragmentManager();
    }

    @Provides
    @PerFragment
    @ChildFragmentManager
    FragmentManager provideChildFragmentManager() {
        return mFragment.getChildFragmentManager();
    }

}

