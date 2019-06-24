package co.id.bankmandiri.mandirimovie.ui.genre;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.data.remote.response.ErrorResponse;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetGenresResponse;
import co.id.bankmandiri.mandirimovie.injection.scope.PerActivity;
import co.id.bankmandiri.mandirimovie.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@PerActivity
public class GenrePresenter extends BasePresenter<GenreMvpView> {

    private final MandiriMovieApi mandiriMovieApi;
    private Call<GetGenresResponse> mCallGetGenres;

    @Inject
    public GenrePresenter(MandiriMovieApi mandiriMovieApi) {
        this.mandiriMovieApi = mandiriMovieApi;
    }

    @Override
    public void attachView(GenreMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCallGetGenres != null) mCallGetGenres.cancel();
    }

    public void getGenres(String apiKey) {
        checkViewAttached();
        mCallGetGenres = mandiriMovieApi.getOffialGenres(apiKey);
        mCallGetGenres.enqueue(new Callback<GetGenresResponse>() {
            @Override
            public void onResponse(Call<GetGenresResponse> call, Response<GetGenresResponse> response) {
                if (isViewAttached())
                    if (response.isSuccessful()) {
                        getMvpView().onCallGetGenresSuccess(response.body().getGenres());
                    } else {
                        getMvpView().onCallGetGenresError(response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<GetGenresResponse> call, Throwable t) {
                if (isViewAttached())
                    getMvpView().onCallGetGenresUnknownError("error");
            }
        });
    }
}

