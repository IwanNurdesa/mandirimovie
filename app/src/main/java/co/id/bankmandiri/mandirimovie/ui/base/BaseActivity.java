package co.id.bankmandiri.mandirimovie.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import co.id.bankmandiri.mandirimovie.App;
import co.id.bankmandiri.mandirimovie.injection.component.ActivityComponent;
import co.id.bankmandiri.mandirimovie.injection.component.DaggerActivityComponent;
import co.id.bankmandiri.mandirimovie.injection.module.ActivityModule;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }

    protected final ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(App.getAppComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
