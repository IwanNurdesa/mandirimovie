package co.id.bankmandiri.mandirimovie.ui.base;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
