package com.allen.loltool.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.allen.loltool.R;
import com.allen.loltool.hero.fragment.HeroFragment;
import com.allen.loltool.message.fragment.MessageFragment;
import com.allen.loltool.mine.fragment.MineFragment;
import com.allen.loltool.news.fragment.NewsHomeFragment;
import com.allen.loltool.widget.BottomMenu.TabBottom;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class HomeActivity extends AppCompatActivity {


    @Bind(R.id.main_content)
    LinearLayout mainContent;
    @Bind(R.id.tab_bottom)
    TabBottom tabBottom;

    private FragmentTransaction ft;
    private FragmentManager fm;

    NewsHomeFragment newsHomeFragment;
    HeroFragment heroFragment;
    MessageFragment messageFragment;
    MineFragment mineFragment;

    private Fragment[] fragment = {NewsHomeFragment.newInstance(),
            HeroFragment.newInstance(),
            MessageFragment.newInstance(),
            MineFragment.newInstance()};
    private Fragment fragments[] = {newsHomeFragment, heroFragment, messageFragment, mineFragment};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        showFragment(0);
        tabBottom.setOnClickListener(new TabBottom.TabOnClickListener() {
            @Override
            public void Tab_1_Listener() {
                showFragment(0);
            }

            @Override
            public void Tab_2_Listener() {
                showFragment(1);
            }

            @Override
            public void Tab_3_Listener() {
                showFragment(2);
            }

            @Override
            public void Tab_4_Listener() {
                showFragment(3);
            }
        });


    }

    private void showFragment(int index) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragment();
        if (fragments[index] != null) {
            ft.show(fragments[index]);
        } else {
            fragments[index] = fragment[index];
            ft.add(R.id.main_content, fragments[index]);
        }
        ft.commit();
    }

    private void hideFragment() {
        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i] != null) {
                ft.hide(fragments[i]);
            }
        }
    }
}
