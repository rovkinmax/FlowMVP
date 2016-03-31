package com.github.rovkinmax.flowmvp.presenter;

import android.support.annotation.NonNull;

import com.github.rovkinmax.flowmvp.view.MainView;

/**
 * @author Rovkin Max
 */
public class MainPresenter {
    private final MainView mView;

    public MainPresenter(@NonNull MainView mainView) {
        mView = mainView;
    }

    public void dispatchCurrentScreen(int screenId) {
        mView.closeDrawer();
    }
}
