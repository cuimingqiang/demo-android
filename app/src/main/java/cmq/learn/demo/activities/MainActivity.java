package cmq.learn.demo.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import cmq.learn.demo.R;
import cmq.learn.demo.fragments.BaseFragment;
import cmq.learn.demo.fragments.tab.DubFragment;
import cmq.learn.demo.fragments.tab.MineFragment;
import cmq.learn.demo.fragments.tab.TeacherFragment;
import cmq.learn.demo.fragments.tab.TobFragment;
import cmq.learn.demo.widgets.NoScrollViewPager;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    @Bind({R.id.tab_dub,R.id.tab_teacher,R.id.tab_top,R.id.tab_mine})
    TextView[] tab;
    private int select = 0;
    @Bind(R.id.viewPager)
    NoScrollViewPager viewPager;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
        viewBindData();
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.i("-----", (LoginActivity.controller.getLoginView() == null) + " ");
                    }
                });

    }

    @Override
    void contentView() {
        setContentView(R.layout.activity_main);
    }

    protected void initData() {
        fragments = new ArrayList(4);
        fragments.add(new DubFragment());
        fragments.add(new TeacherFragment());
        fragments.add(new TobFragment());
        fragments.add(new MineFragment());
    }


    protected void initView() {
        actionBar.setTitleText("少儿趣配音");


    }

    protected void viewBindData() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(select);
        tab[select].setSelected(true);
    }
    @OnClick({R.id.tab_dub,R.id.tab_teacher,R.id.tab_top,R.id.tab_mine})
    public void onTabChange(View v){
        if(!v.isSelected()){
            v.setSelected(true);
            tab[select].setSelected(false);
            select = Integer.parseInt(v.getTag().toString());
            actionBar.setTitleText(tab[select].getText());
        }
        Log.i("-----", (LoginActivity.controller.getLoginView() == null) + " ");
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
