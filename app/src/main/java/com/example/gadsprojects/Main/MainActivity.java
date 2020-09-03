package com.example.gadsprojects.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gadsprojects.Main.UI.Hours.LearningFragment;
import com.example.gadsprojects.Main.UI.Skills.SkillsFragment;
import com.example.gadsprojects.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private SkillsFragment mSkillsFragment;
    private LearningFragment mLearningFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Button mToolBarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLearningFragment = new LearningFragment();
        mSkillsFragment = new SkillsFragment();
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        mToolBarButton = findViewById(R.id.toolbarButton);
        mToolBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.toolbar_title);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(mLearningFragment, getString(R.string.learning));
        mViewPagerAdapter.addFragment(mSkillsFragment, getString(R.string.skills));

        mViewPager.setAdapter(mViewPagerAdapter);
    }


}