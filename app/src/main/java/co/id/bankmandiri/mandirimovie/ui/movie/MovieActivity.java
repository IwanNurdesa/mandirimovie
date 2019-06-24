package co.id.bankmandiri.mandirimovie.ui.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.List;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.Constant;
import co.id.bankmandiri.mandirimovie.R;
import co.id.bankmandiri.mandirimovie.data.model.Genre;
import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.ui.base.BaseActivity;
import co.id.bankmandiri.mandirimovie.ui.genre.GenreAdapter;
import co.id.bankmandiri.mandirimovie.ui.genre.GenreMvpView;
import co.id.bankmandiri.mandirimovie.ui.genre.GenrePresenter;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class MovieActivity extends BaseActivity implements MovieMvpView {

    Toolbar toolbar;
    RecyclerView rvMovie;
    TextView tvError;

    @Inject
    MovieAdapter adapter;

    @Inject
    MoviePresenter moviePresenter;

    private List<Movie> movies;
    private ViewAnimator va;
    private int genreId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_movie);
        moviePresenter.attachView(this);
        va = findViewById(R.id.va);
        toolbar = findViewById(R.id.toolbar);
        rvMovie = findViewById(R.id.rv_movie);
        tvError = findViewById(R.id.tv_error);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Genres");

        Bundle bundle = getIntent().getExtras();
        genreId = bundle.getInt("genre_id");

        rvMovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovie.setAdapter(adapter);

        moviePresenter.getMovies(Constant.API_KEY, genreId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.detachView();
    }

    @Override
    public void onCallGetMovies() {
        va.setDisplayedChild(0);
    }

    @Override
    public void onCallGetMoviesError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }

    @Override
    public void onCallGetMoviesSuccess(GetMoviesResponse response, List<Movie> movieList) {
        va.setDisplayedChild(1);
        movies = movieList;
        adapter.setMovies(movies);
    }

    @Override
    public void onCallGetMoviesUnknownError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }
}
