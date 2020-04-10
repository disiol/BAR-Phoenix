package com.barphoenixonly.ui.fragments.recorder.presenter;






import com.barphoenixonly.ui.base.BasePresenter;
import com.barphoenixonly.ui.fragments.recorder.view.RecorderView;

import javax.inject.Inject;

public class RecorderPresenter extends BasePresenter<RecorderView> {

    @Inject
    RecorderPresenter(){
    }

    public void showSite() {
        getView().showSite();
    }
}
