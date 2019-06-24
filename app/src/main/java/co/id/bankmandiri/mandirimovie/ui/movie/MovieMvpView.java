package co.id.bankmandiri.mandirimovie.ui.movie;

import java.util.List;

import co.id.bankmandiri.mandirimovie.data.model.Genre;
import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.ui.base.MvpView;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface MovieMvpView extends MvpView {

    void onCallGetMovies();

    void onCallGetMoviesError(String message);

    void onCallGetMoviesSuccess(GetMoviesResponse response, List<Movie> movieList);

    void onCallGetMoviesUnknownError(String message);

}
