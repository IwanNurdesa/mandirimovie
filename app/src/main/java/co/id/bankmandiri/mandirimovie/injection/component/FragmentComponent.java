package co.id.bankmandiri.mandirimovie.injection.component;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.injection.module.FragmentModule;
import co.id.bankmandiri.mandirimovie.injection.scope.PerFragment;
import dagger.Component;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    MandiriMovieApi mandiriMovieApi();
}
