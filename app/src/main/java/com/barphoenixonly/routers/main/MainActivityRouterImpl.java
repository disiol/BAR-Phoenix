package com.barphoenixonly.routers.main;

import com.barphoenixonly.R;
import com.barphoenixonly.routers.base.BaseRouter;
import com.barphoenixonly.ui.activities.MainActivity;
import com.barphoenixonly.ui.fragments.recorder.view.RecorderFragment;
import com.barphoenixonly.ui.fragments.start.view.StartFragment;
import com.barphoenixonly.ui.fragments.web.view.WebFragment;

import javax.inject.Inject;


public class MainActivityRouterImpl extends BaseRouter<MainActivity> implements MainActivityRouter {


    @Inject
    MainActivityRouterImpl(MainActivity activity) {
        super(activity);
    }



    @Override
    public void showLogoFragment() {
        if (!isCurrentFragment(R.id.fragment_container, StartFragment.class)) {
            replaceFragment(R.id.fragment_container, new StartFragment());
        }
    }
    @Override
    public void showWebFragment() {
        if(!isCurrentFragment(R.id.fragment_container, WebFragment.class)) {
            replaceFragment(R.id.fragment_container, new WebFragment());
        }
    }

    @Override
    public void showRecordFragment() {
        if(!isCurrentFragment(R.id.fragment_container, RecorderFragment.class)) {
            replaceFragment(R.id.fragment_container, new RecorderFragment());
        }
    }


}
