package co.id.bankmandiri.mandirimovie.ui.moviedetail;

import java.util.List;

import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.ui.base.MvpView;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface MovieDetailMvpView extends MvpView {

    void onCallGetMovieDetail();

    void onCallGetMovieDetailError(String message);

    void onCallGetMovieDetailSuccess(Movie movie);

    void onCallGetMovieDetailUnknownError(String message);

}
