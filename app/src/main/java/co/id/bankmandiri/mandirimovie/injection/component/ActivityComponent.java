package co.id.bankmandiri.mandirimovie.injection.component;

import android.content.Context;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.injection.module.ActivityModule;
import co.id.bankmandiri.mandirimovie.injection.qualifier.ActivityContext;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import co.id.bankmandiri.mandirimovie.ui.genre.GenreActivity;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieActivity;
import co.id.bankmandiri.mandirimovie.ui.moviedetail.MovieDetailActivity;
import co.id.bankmandiri.mandirimovie.ui.moviereview.MovieReviewActivity;
import dagger.Component;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ActivityContext
    Context activityContext();

    MandiriMovieApi mandiriMovieApi();

    void inject(GenreActivity genreActivity);
    void inject(MovieActivity movieActivity);
    void inject(MovieDetailActivity movieDetailActivity);
    void inject(MovieReviewActivity movieReviewActivity);

}
