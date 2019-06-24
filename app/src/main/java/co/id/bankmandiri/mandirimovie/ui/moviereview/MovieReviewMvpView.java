package co.id.bankmandiri.mandirimovie.ui.moviereview;

import java.util.List;

import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.model.MovieReview;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMovieReviewsResponse;
import co.id.bankmandiri.mandirimovie.ui.base.MvpView;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface MovieReviewMvpView extends MvpView {

    void onCallGetMovieReview();

    void onCallGetMovieReviewError(String message);

    void onCallGetMovieReviewSuccess(GetMovieReviewsResponse response, List<MovieReview> movieReviewList);

    void onCallGetMovieReviewUnknownError(String message);

}
