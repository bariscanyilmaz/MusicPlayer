package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bariscanyilmaz.musicplayer.databinding.FragmentSongBinding;
import com.bariscanyilmaz.musicplayer.model.PlaySong;
import com.bariscanyilmaz.musicplayer.model.Song;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongFragment extends Fragment {

    private FragmentSongBinding binding;

    private SongViewModel viewModel;
    private MediaPlayerViewModel mpViewModel;
    RecyclerView songRecyclerView;
    SongDataAdapter songDataAdapter;

    public static SongFragment newInstance() {

        SongFragment fragment = new SongFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(SongViewModel.class);
        mpViewModel=new ViewModelProvider(getActivity()).get(MediaPlayerViewModel.class);

    }

    private SongDataAdapter.OnItemClickListener<PlaySong> playHandler=new SongDataAdapter.OnItemClickListener<PlaySong>() {
        @Override
        public void onItemClick(PlaySong data) {
            mpViewModel.setChosenSong(data);
        }
    };

    private SongDataAdapter.OnItemClickListener<Song> shareHandler=new SongDataAdapter.OnItemClickListener<Song>() {
        @Override
        public void onItemClick(Song data) {

            Uri uri= Uri.parse(data.path);
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("audio/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
            startActivity(Intent.createChooser(shareIntent,"Share your musics"));

        }
    };

    private SongDataAdapter.OnItemClickListener<Song> deleteHandler=new SongDataAdapter.OnItemClickListener<Song>() {
        @Override
        public void onItemClick(Song data) {
            //mpViewModel.setChosenSong(data);

            File file = new File(data.path);
            file.delete();

            MediaScannerConnection.scanFile(getContext(),
                    new String[]{file.toString()},
                    new String[]{file.getName()},null);
        }
    };

    private SongDataAdapter.OnItemClickListener<Song> addtoPlayListHandler=new SongDataAdapter.OnItemClickListener<Song>() {
        @Override
        public void onItemClick(Song data) {
            //TODO implement play list
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSongBinding.inflate(inflater,container,false);

        songRecyclerView=binding.songRecyclerView;

        songDataAdapter=new SongDataAdapter(playHandler,shareHandler,deleteHandler,addtoPlayListHandler);

        //songDataAdapter.setSongs(getSongs(getContext()));

        songRecyclerView.setAdapter(songDataAdapter);

        viewModel.getSongs().observe(getViewLifecycleOwner(),updateSongList);


        View root=binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    private Observer<List<Song>> updateSongList= new Observer<List<Song>>() {

        @Override
        public void onChanged(List<Song> songs) {

            Log.v("Song","songs recieved and send to adapter");
            songDataAdapter.setSongs(songs);
        }

    };

}