package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bariscanyilmaz.musicplayer.databinding.FragmentSongBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongFragment extends Fragment {

    private FragmentSongBinding binding;


    public SongFragment() {
        // Required empty public constructor
    }


    public static SongFragment newInstance() {
        SongFragment fragment = new SongFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSongBinding.inflate(inflater,container,false);
        View root=binding.getRoot();


        return root;
    }
}