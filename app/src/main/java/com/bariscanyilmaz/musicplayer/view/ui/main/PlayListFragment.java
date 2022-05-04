package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.databinding.FragmentPlayListBinding;
import com.bariscanyilmaz.musicplayer.model.PlayList;
import com.bariscanyilmaz.musicplayer.model.Song;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayListFragment extends Fragment {

    private FragmentPlayListBinding binding;

    private PlayListViewModel playListViewModel;
    RecyclerView playListRecyclerView;
    PlayListDataAdapter playListDataAdapter;

    private SongViewModel viewModel;


    public static PlayListFragment newInstance() {
        PlayListFragment fragment = new PlayListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playListViewModel=new ViewModelProvider(getActivity()).get(PlayListViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentPlayListBinding.inflate(inflater,container,false);
        playListRecyclerView=binding.playlistRecyclerView;
        playListDataAdapter=new PlayListDataAdapter();

        //set playlist here
        playListRecyclerView.setAdapter(playListDataAdapter);

        playListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View root=binding.getRoot();

        playListViewModel.getPlayLists().observe(getViewLifecycleOwner(), new Observer<List<PlayList>>() {
            @Override
            public void onChanged(List<PlayList> playLists) {
                playListDataAdapter.setPlayLists(playLists);
                playListRecyclerView.setAdapter(playListDataAdapter);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel=new ViewModelProvider(this).get(SongViewModel.class);
        viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                Log.i("Song","Songs in playlist");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}