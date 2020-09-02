package com.example.gadsprojects.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gadsprojects.Main.UI.LearningFragment;
import com.example.gadsprojects.Main.UI.SkillsFragment;
import com.example.gadsprojects.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private SkillsFragment mSkillsFragment;
    private LearningFragment mLearningFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLearningFragment = new LearningFragment();
        mSkillsFragment = new SkillsFragment();
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.toolbar_title);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(mLearningFragment, getString(R.string.learning));
        mViewPagerAdapter.addFragment(mSkillsFragment, getString(R.string.skills));

        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.submit_button:
                Toast.makeText(this, "Submit button clicked", Toast.LENGTH_SHORT).show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}