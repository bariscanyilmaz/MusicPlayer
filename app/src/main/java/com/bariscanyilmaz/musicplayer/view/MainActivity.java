package com.bariscanyilmaz.musicplayer.view;

import android.os.Bundle;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.view.ui.main.PlayerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bariscanyilmaz.musicplayer.view.ui.main.SectionsPagerAdapter;
import com.bariscanyilmaz.musicplayer.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private final int[] tabLayouts=new int[]{R.string.tab_play_list,R.string.tab_song,R.string.tab_player};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sectionsPagerAdapter= new SectionsPagerAdapter(this);
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;

        new  TabLayoutMediator(tabs,viewPager,
                (tab,position)->tab.setText(tabLayouts[position])
        ).attach();

        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void playButton(View v){
        PlayerFragment.play();
    }

    public void nextButton(View v){
        PlayerFragment.next();
    }
    public void prevButton(View v){
        PlayerFragment.prev();
    }

}