package com.barphoenixonly.ui.fragments.start.view;


import com.barphoenixonly.routers.main.MainActivityRouter;
import com.barphoenixonly.ui.base.BaseView;

public interface StartView extends BaseView {

    void showGame(MainActivityRouter mainActivityRouter);

    void showWeb(MainActivityRouter mainActivityRouter);
}
