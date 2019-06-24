package co.id.bankmandiri.mandirimovie.ui.moviedetail;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import co.id.bankmandiri.mandirimovie.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerActivity
public class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView> {

    private final MandiriMovieApi mandiriMovieApi;
    private Call<Movie> mCallGetMovieDetail;

    @Inject
    public MovieDetailPresenter(MandiriMovieApi mandiriMovieApi) {
        this.mandiriMovieApi = mandiriMovieApi;
    }

    @Override
    public void attachView(MovieDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCallGetMovieDetail != null) mCallGetMovieDetail.cancel();
    }

    public void getMovieDetail(int id, String apiKey) {
        checkViewAttached();
        mCallGetMovieDetail = mandiriMovieApi.getMovieDetail(id, apiKey);
        mCallGetMovieDetail.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (isViewAttached())
                    if (response.isSuccessful()) {
                        getMvpView().onCallGetMovieDetailSuccess(response.body());
                    } else {
                        getMvpView().onCallGetMovieDetailError(response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                if (isViewAttached())
                    getMvpView().onCallGetMovieDetailUnknownError("error");
            }
        });
    }
}

