package co.id.bankmandiri.mandirimovie.ui.movie;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetGenresResponse;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import co.id.bankmandiri.mandirimovie.ui.base.BasePresenter;
import co.id.bankmandiri.mandirimovie.ui.genre.GenreMvpView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerActivity
public class MoviePresenter extends BasePresenter<MovieMvpView> {

    private final MandiriMovieApi mandiriMovieApi;
    private Call<GetMoviesResponse> mCallGetMovies;

    @Inject
    public MoviePresenter(MandiriMovieApi mandiriMovieApi) {
        this.mandiriMovieApi = mandiriMovieApi;
    }

    @Override
    public void attachView(MovieMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCallGetMovies != null) mCallGetMovies.cancel();
    }

    public void getMovies(String apiKey, int genreId) {
        checkViewAttached();
        mCallGetMovies = mandiriMovieApi.getMovieList(apiKey, genreId);
        mCallGetMovies.enqueue(new Callback<GetMoviesResponse>() {
            @Override
            public void onResponse(Call<GetMoviesResponse> call, Response<GetMoviesResponse> response) {
                if (isViewAttached())
                    if (response.isSuccessful()) {
                        getMvpView().onCallGetMoviesSuccess(response.body(), response.body().getMovies());
                    } else {
                        getMvpView().onCallGetMoviesError(response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<GetMoviesResponse> call, Throwable t) {
                if (isViewAttached())
                    getMvpView().onCallGetMoviesUnknownError("error");
            }
        });
    }
}

