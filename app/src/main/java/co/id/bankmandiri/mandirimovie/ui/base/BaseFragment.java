package co.id.bankmandiri.mandirimovie.ui.base;

import android.support.v4.app.Fragment;

import co.id.bankmandiri.mandirimovie.injection.component.DaggerFragmentComponent;
import co.id.bankmandiri.mandirimovie.injection.component.FragmentComponent;
import co.id.bankmandiri.mandirimovie.injection.module.FragmentModule;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public abstract class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentComponent = null;
    }

    protected final FragmentComponent fragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .activityComponent(((BaseActivity) getActivity()).activityComponent())
                    .build();
        }
        return mFragmentComponent;
    }
}
