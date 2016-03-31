package com.github.rovkinmax.flowmvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.rovkinmax.flowmvp.R;
import com.github.rovkinmax.flowmvp.flow.MainFlow;
import com.github.rovkinmax.flowmvp.presenter.MainPresenter;
import com.github.rovkinmax.flowmvp.view.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @Bind(R.id.navView)
    protected NavigationView mNavigationView;

    @Bind(R.id.drawer_layout)
    protected DrawerLayout mDrawer;

    private MainPresenter mPresenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .defaultKey(R.id.menu_dahsboard)
                .dispatcher(new MainFlow(this))
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Flow.get(this).set(item.getItemId());
        mPresenter.dispatchCurrentScreen(item.getItemId());
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!closeDrawer()){
            super.onBackPressed();
        }
    }

    @Override
    public boolean closeDrawer() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}
