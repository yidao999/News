package com.example.myapplication.mvp.mvp_home.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.AboutActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HomePagerAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mvp.mvp_cardtop.view.CardTopFragment;
import com.example.myapplication.mvp.mvp_news.view.NewsFragment;
import com.example.myapplication.mvp.mvp_photo.view.PhotoActivity;
import com.example.myapplication.mvp.mvp_video.view.VideoFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * author: 小川
 * Date: 2019/5/14
 * Description:
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.flowingDrawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;
    private View view;
    private boolean touch = true;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            touch = true;
            return false;
        }
    });
    private Unbinder bind;

    @Override
    protected void loadData() {
        bind = ButterKnife.bind(this);
        view = getWindow().getDecorView();
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setTitle("学习项目");
            ab.setDisplayHomeAsUpEnabled(true);
        }

        //侧滑MUNU
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        //viewPager
        if (viewPager != null) {
            setupViewPagerContent(viewPager);
            tabs.setViewPager(viewPager);
//            tabs.setTabMode(TabLayout.MODE_FIXED);//tab均分,适合少的tab

        }
    }

    //侧滑的item点击
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_photo:
                                startActivity(new Intent(HomeActivity.this, PhotoActivity.class));
                                break;
                            case R.id.nav_video:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.nav_music:
                                Snackbar.make(navigationView, "音乐持续更新中....", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_about:
                                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                                break;
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        drawerLayout.setSelected(true);
                        return true;
                    }
                });
    }

    private void setupViewPagerContent(final ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(7);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CardTopFragment.newInstance("头条"), "头条");
        adapter.addFragment(VideoFragment.newInstance("视频"), "视频");
        adapter.addFragment(NewsFragment.newInstance("体育"), "体育");
        adapter.addFragment(NewsFragment.newInstance("娱乐"), "娱乐");
        adapter.addFragment(NewsFragment.newInstance("科技"), "科技");
        adapter.addFragment(NewsFragment.newInstance("NBA"), "NBA");
        adapter.addFragment(NewsFragment.newInstance("股票"), "股票");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == 2 && viewPager.getCurrentItem() != 1) {
                    JCVideoPlayerStandard.releaseAllVideos();
                }
            }

        });
    }

    @Override
    protected int initView() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (JCVideoPlayerStandard.backPress()) {
                    touch = false;
                    handler.sendEmptyMessageDelayed(0, 300);
                    return true;
                }
                if (touch) {
                    touch = false;
                    Snackbar.make(view, "再次点击退出", Snackbar.LENGTH_SHORT).show();
                    handler.sendEmptyMessageDelayed(0, 2000);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
