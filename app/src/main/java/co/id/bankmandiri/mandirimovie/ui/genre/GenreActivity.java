package co.id.bankmandiri.mandirimovie.ui.genre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import co.id.bankmandiri.mandirimovie.data.remote.response.ErrorResponse;
import co.id.bankmandiri.mandirimovie.ui.base.BaseActivity;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class GenreActivity extends BaseActivity implements GenreMvpView {

    Toolbar toolbar;
    RecyclerView rvGenre;
    TextView tvError;

    @Inject
    GenreAdapter adapter;

    @Inject
    GenrePresenter genrePresenter;

    private List<Genre> genres;
    private ViewAnimator va;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_genre);
        genrePresenter.attachView(this);
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

        rvGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvGenre.setAdapter(adapter);

        genrePresenter.getGenres(Constant.API_KEY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        genrePresenter.detachView();
    }

    @Override
    public void onCallGetGenres() {
        va.setDisplayedChild(0);
    }

    @Override
    public void onCallGetGenresError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }

    @Override
    public void onCallGetGenresSuccess(List<Genre> genreList) {
        va.setDisplayedChild(1);
        genres = genreList;
        adapter.setGenres(genres);
    }

    @Override
    public void onCallGetGenresUnknownError(String message) {
        va.setDisplayedChild(2);
        tvError.setText(message);
    }
}
