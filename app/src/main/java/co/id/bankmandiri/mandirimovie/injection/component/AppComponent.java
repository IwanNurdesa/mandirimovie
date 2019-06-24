package co.id.bankmandiri.mandirimovie.injection.component;

import android.content.Context;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.injection.module.AppModule;
import co.id.bankmandiri.mandirimovie.injection.module.NetModule;
import co.id.bankmandiri.mandirimovie.injection.qualifier.AppContext;
import co.id.bankmandiri.mandirimovie.injection.scope.PerApplication;
import dagger.Component;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerApplication
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    @AppContext
    Context appContext();

    MandiriMovieApi mandiriMovieApi();
}

