package com.example.gadsprojects.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gadsprojects.Main.UI.Hours.LearningFragment;
import com.example.gadsprojects.Main.UI.Skills.SkillsFragment;
import com.example.gadsprojects.R;
import com.example.gadsprojects.Submit.SubmitActivity;
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


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.toolbar_title);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(mLearningFragment, getString(R.string.learning));
        mViewPagerAdapter.addFragment(mSkillsFragment, getString(R.string.skills));

        mViewPager.setAdapter(mViewPagerAdapter);
        mToolBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubmitActivity.class));
            }
        });
    }


}