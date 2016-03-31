package com.github.rovkinmax.flowmvp.flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rovkinmax.flowmvp.R;

import flow.Dispatcher;
import flow.State;
import flow.Traversal;
import flow.TraversalCallback;
import rx.functions.Func1;

/**
 * @author Rovkin Max
 */
public class MainFlow implements Dispatcher {
    private static final SparseArray<Func1<MainFlow, View>> ACTIONS = new SparseArray<>();

    static {
        ACTIONS.put(R.id.menu_dahsboard, MainFlow::navigateToDashboard);
        ACTIONS.put(R.id.menu_favorites, MainFlow::navigateToFavorite);
        ACTIONS.put(R.id.menu_settings, MainFlow::navigateToSettings);
    }

    private final Activity mActivity;

    private ViewGroup mContainer;

    public MainFlow(@NonNull Activity activity) {
        mActivity = activity;
    }

    @Override
    public void dispatch(Traversal traversal, TraversalCallback callback) {
        State outState = traversal.origin == null ? null : traversal.getState(traversal.origin.top());
        State inState = traversal.getState(traversal.destination.top());
        saveInstanceState(outState);
        final Func1<MainFlow, View> func1 = ACTIONS.get(inState.getKey());
        if (func1 != null){
            final View view = func1.call(this);
            inState.restore(view);
            mContainer.removeAllViews();
            mContainer.addView(view);
            callback.onTraversalCompleted();
        }
    }


    private View navigateToFavorite() {
        return showLayout(R.layout.sc_favorite);
    }

    private View navigateToDashboard() {
        return showLayout(R.layout.sc_dashboard);
    }

    private View navigateToSettings() {
        return showLayout(R.layout.sc_settings);
    }

    private View showLayout(@LayoutRes int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        initContainerIfNull();
        return inflater.inflate(layoutResId, mContainer, false);
    }

    private void initContainerIfNull() {
        if (mContainer == null){
            mContainer = (ViewGroup) mActivity.findViewById(R.id.container);
        }
    }

    private void saveInstanceState(@Nullable State outgoingState) {
        if (outgoingState != null){
            View view = mContainer.getChildAt(0);
            if (view != null){
                outgoingState.save(view);
            }
        }
    }
}
