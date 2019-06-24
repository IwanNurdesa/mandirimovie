package co.id.bankmandiri.mandirimovie.data.remote.response;

import java.util.List;

import co.id.bankmandiri.mandirimovie.data.model.Genre;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class GetGenresResponse {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
