package co.id.bankmandiri.mandirimovie.ui.moviereview;

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
import co.id.bankmandiri.mandirimovie.data.model.MovieReview;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMovieReviewsResponse;
import co.id.bankmandiri.mandirimovie.ui.base.BaseActivity;
import co.id.bankmandiri.mandirimovie.ui.genre.GenrePresenter;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieMvpView;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class MovieReviewActivity extends BaseActivity implements MovieReviewMvpView {

    Toolbar toolbar;
    RecyclerView rvGenre;
    TextView tvError;

    @Inject
    MovieReviewAdapter adapter;

    @Inject
    MovieReviewPresenter movieReviewPresenter;

    private List<MovieReview> movieReviews;
    private ViewAnimator va;
    private int movieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_genre);
        movieReviewPresenter.attachView(this);
        va = findViewById(R.id.va);
        toolbar = findViewById(R.id.toolbar);
        rvGenre = findViewById(R.id.rv_genre);
        tvError = findViewById(R.id.tv_error);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Genres");

        Bundle bundle = getIntent().getExtras();
        movieId = bundle.getInt("movie_id");

        rvGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvGenre.setAdapter(adapter);

        movieReviewPresenter.getMovieReviews(movieId, Constant.API_KEY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieReviewPresenter.detachView();
    }

    @Override
    public void onCallGetMovieReview() {
        va.setDisplayedChild(0);
    }

    @Override
    public void onCallGetMovieReviewError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }

    @Override
    public void onCallGetMovieReviewSuccess(GetMovieReviewsResponse response, List<MovieReview> movieReviews) {
        va.setDisplayedChild(1);
        this.movieReviews = movieReviews;
        adapter.setMoviewReviews(movieReviews);
    }

    @Override
    public void onCallGetMovieReviewUnknownError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }
}
