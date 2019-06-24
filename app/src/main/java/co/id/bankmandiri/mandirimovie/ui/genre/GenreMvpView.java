package co.id.bankmandiri.mandirimovie.ui.genre;

import java.util.List;

import co.id.bankmandiri.mandirimovie.data.model.Genre;
import co.id.bankmandiri.mandirimovie.data.remote.response.ErrorResponse;
import co.id.bankmandiri.mandirimovie.ui.base.MvpView;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface GenreMvpView extends MvpView {

    void onCallGetGenres();

    void onCallGetGenresError(String message);

    void onCallGetGenresSuccess(List<Genre> genreList);

    void onCallGetGenresUnknownError(String message);

}
