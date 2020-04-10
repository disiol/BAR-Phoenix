package com.barphoenixonly.ui.fragments.web.presenter;




import com.barphoenixonly.ui.base.BasePresenter;
import com.barphoenixonly.ui.fragments.web.view.WebView;

import javax.inject.Inject;

public class WebPresenter extends BasePresenter<WebView> {

    @Inject
    WebPresenter(){
    }

    public void showSite() {
        getView().showSite();
    }
}
