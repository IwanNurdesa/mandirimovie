package co.id.bankmandiri.mandirimovie.data.remote;

import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetGenresResponse;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMovieReviewsResponse;
import co.id.bankmandiri.mandirimovie.data.remote.response.GetMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface MandiriMovieApi {

    @GET("genre/movie/list")
    Call<GetGenresResponse> getOffialGenres(@Query("api_key") String apikey);

    @GET("discover/movie")
    Call<GetMoviesResponse> getMovieList(@Query("api_key") String apikey, @Query("with_genres") int genreId);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") int id, @Query("api_key") String apikey);

    @GET("movie/{id}/reviews")
    Call<GetMovieReviewsResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apikey);

    @GET("movie/{id}/trailers")
    Call<Movie> getMovieTrailers(@Query("api_key") String apikey, @Path("id") int id);

}
