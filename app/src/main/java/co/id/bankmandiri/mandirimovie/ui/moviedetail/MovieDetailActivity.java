package co.id.bankmandiri.mandirimovie.ui.moviedetail;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.Constant;
import co.id.bankmandiri.mandirimovie.R;
import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import co.id.bankmandiri.mandirimovie.ui.base.BaseActivity;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieAdapter;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieMvpView;
import co.id.bankmandiri.mandirimovie.ui.movie.MoviePresenter;
import co.id.bankmandiri.mandirimovie.ui.moviereview.MovieReviewActivity;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailMvpView {

    Toolbar toolbar;
    ImageView ivPoster;
    TextView tvError;
    TextView tvTitleDetail;
    TextView tvUserReview;
    TextView tvTitleToolbar;

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    private Movie mMovie;
    private ViewAnimator va;
    private int movieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_movie_detail);
        movieDetailPresenter.attachView(this);
        va = findViewById(R.id.va);
        toolbar = findViewById(R.id.toolbar);
        tvError = findViewById(R.id.tv_error);
        tvTitleDetail = findViewById(R.id.tv_title_detail);
        ivPoster = findViewById(R.id.iv_poster_detail);
        tvUserReview = findViewById(R.id.tv_user_reviews);
        tvTitleToolbar = findViewById(R.id.tv_title_toolbar);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Genres");
        tvTitleToolbar.setText("Movie Detail");

        Bundle bundle = getIntent().getExtras();
        movieId = bundle.getInt("movie_id");

        movieDetailPresenter.getMovieDetail(movieId, Constant.API_KEY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailPresenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCallGetMovieDetail() {
        va.setDisplayedChild(0);
    }

    @Override
    public void onCallGetMovieDetailError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }

    @Override
    public void onCallGetMovieDetailSuccess(Movie movie) {
        va.setDisplayedChild(1);

        mMovie = movie;

        tvTitleDetail.setText(mMovie.getTitle());
        Glide.with(this).load(Constant.BASE_IMAGE_URL + movie
                .getPosterPath()).centerCrop().into(ivPoster);
        tvUserReview.setOnClickListener(v -> {
            Intent intent = new Intent(this, MovieReviewActivity.class);
            intent.putExtra("movie_id", mMovie.getId());
            startActivity(intent);
        });

    }

    @Override
    public void onCallGetMovieDetailUnknownError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }
}
