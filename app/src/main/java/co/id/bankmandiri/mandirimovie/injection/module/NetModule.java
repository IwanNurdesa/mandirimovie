package co.id.bankmandiri.mandirimovie.injection.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import co.id.bankmandiri.mandirimovie.BuildConfig;
import co.id.bankmandiri.mandirimovie.Constant;
import co.id.bankmandiri.mandirimovie.data.remote.MandiriMovieApi;
import co.id.bankmandiri.mandirimovie.injection.scope.PerApplication;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
@Module
public class NetModule {

    @Provides
    @PerApplication
    static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @PerApplication
    static HttpUrl provideHttpUrl() {
        return HttpUrl.parse(Constant.BASE_URL);
    }


    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClientNormal() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    @Provides
    @PerApplication
    static Retrofit provideRetrofit(Gson gson, HttpUrl httpUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @PerApplication
    static MandiriMovieApi provideMandiriMovieApi(Retrofit retrofit) {
        return retrofit.create(MandiriMovieApi.class);
    }
}
