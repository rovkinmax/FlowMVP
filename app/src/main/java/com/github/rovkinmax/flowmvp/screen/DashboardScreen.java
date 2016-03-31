package com.github.rovkinmax.flowmvp.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.github.rovkinmax.flowmvp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;

/**
 * @author Rovkin Max
 */
public class DashboardScreen extends LinearLayout {
    public DashboardScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.menu_favorites, R.id.menu_settings})
    protected void onButtonClick(View view) {
        Flow.get(this).set(view.getId());
    }
}
