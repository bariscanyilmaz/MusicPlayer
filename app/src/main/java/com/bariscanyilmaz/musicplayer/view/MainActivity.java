package com.bariscanyilmaz.musicplayer.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.model.PlaySong;
import com.bariscanyilmaz.musicplayer.model.Song;
import com.bariscanyilmaz.musicplayer.roomdb.AppDatabase;
import com.bariscanyilmaz.musicplayer.roomdb.PlayListDao;
import com.bariscanyilmaz.musicplayer.utils.MediaPlayerController;
import com.bariscanyilmaz.musicplayer.view.ui.main.MediaPlayerViewModel;
import com.bariscanyilmaz.musicplayer.view.ui.main.PlayerFragment;
import com.bariscanyilmaz.musicplayer.view.ui.main.SongViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.bariscanyilmaz.musicplayer.view.ui.main.SectionsPagerAdapter;
import com.bariscanyilmaz.musicplayer.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private final MediaPlayer mediaPlayer=MediaPlayerController.getInstance();

    int currentSongIndex;
    List<Song> currentSongList;


    private ActivityMainBinding binding;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private final int[] tabLayouts=new int[]{R.string.tab_song,R.string.tab_play_list,R.string.tab_player};

    private SongViewModel songViewModel;

    private MediaPlayerViewModel currentPlayerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO check permissions runtime
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sectionsPagerAdapter= new SectionsPagerAdapter(this);
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;

        new  TabLayoutMediator(tabs,viewPager,
                (tab,position)->tab.setText(tabLayouts[position])
        ).attach();



        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //mediaPlayer.pause();
                Log.v("MediaPlayer","Song end");
                next();
            }

        });

        songViewModel=new ViewModelProvider(this).get(SongViewModel.class);
        songViewModel.setSongs(getSongs(this));

        currentPlayerViewModel=new ViewModelProvider(this).get(MediaPlayerViewModel.class);
        currentPlayerViewModel.getChosenSong().observe(this, updatePlayerSong);


    }

    private Observer<PlaySong> updatePlayerSong= new Observer<PlaySong>() {
        @Override
        public void onChanged(PlaySong playSong) {

            if (currentSongList==null){
                currentSongList=playSong.songs;
                currentSongIndex=playSong.index;
                play();
                
            }else{

                if(currentSongList.equals(playSong.songs))
                {
                    //same or next item pressed

                    if(currentSongIndex==playSong.index){

                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                        }else{
                            mediaPlayer.start();
                        }

                    }else{
                        currentSongIndex=playSong.index;
                        play();
                    }

                }else{
                    currentSongList=playSong.songs;
                    currentSongIndex=playSong.index;

                    play();
                }
            }
        }
    };

    private void next(){
        if (currentSongIndex+1<currentSongList.size()){
            currentSongIndex++;
            play();

        }
    }
    private void prev(){
        if (currentSongIndex-1>=0){
            currentSongIndex--;
            play();
        }
    }

    private void play(){
        mediaPlayer.reset();
        Song chosen=currentSongList.get(currentSongIndex);
        try {
            mediaPlayer.setDataSource(chosen.path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public ArrayList<Song> getSongs(final Context context){
        ArrayList<Song> list = new ArrayList<Song>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.v("Uri",uri.toString());
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.DURATION, MediaStore.Audio.ArtistColumns.ARTIST,};
        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";
        //MediaStore.Audio.AudioColumns.DATA + " like ? "
        Cursor c = context.getContentResolver().query(uri, projection, selection, null, null);

        if (c != null) {

            while (c.moveToNext()) {


                String path = c.getString(0);
                String duration = c.getString(1);
                String artist = c.getString(2);

                String name = path.substring(path.lastIndexOf("/") + 1);

                Song song = new Song(name,path,duration,artist);

                list.add(song);
            }
            c.close();
        }
        Log.v("Songs","Size:"+list.size());
        return list;
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

