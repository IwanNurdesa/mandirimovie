package co.id.bankmandiri.mandirimovie.ui.moviereview;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMovieReviewsResponse;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import co.id.bankmandiri.mandirimovie.ui.base.BasePresenter;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieMvpView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerActivity
public class MovieReviewPresenter extends BasePresenter<MovieReviewMvpView> {

    private final MandiriMovieApi mandiriMovieApi;
    private Call<GetMovieReviewsResponse> mCallGetMovieReviews;

    @Inject
    public MovieReviewPresenter(MandiriMovieApi mandiriMovieApi) {
        this.mandiriMovieApi = mandiriMovieApi;
    }

    @Override
    public void attachView(MovieReviewMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCallGetMovieReviews != null) mCallGetMovieReviews.cancel();
    }

    public void getMovieReviews(int moviewId, String apiKey) {
        checkViewAttached();
        mCallGetMovieReviews = mandiriMovieApi.getMovieReviews(moviewId, apiKey);
        mCallGetMovieReviews.enqueue(new Callback<GetMovieReviewsResponse>() {
            @Override
            public void onResponse(Call<GetMovieReviewsResponse> call, Response<GetMovieReviewsResponse> response) {
                if (isViewAttached())
                    if (response.isSuccessful()) {
                        getMvpView().onCallGetMovieReviewSuccess(response.body(), response.body().getMovieReviews());
                    } else {
                        getMvpView().onCallGetMovieReviewError(response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<GetMovieReviewsResponse> call, Throwable t) {
                if (isViewAttached())
                    getMvpView().onCallGetMovieReviewUnknownError("error");
            }
        });
    }
}

