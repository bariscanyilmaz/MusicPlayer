package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.databinding.FragmentPlayerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment {

    private FragmentPlayerBinding binding;

    public static PlayerFragment newInstance() {
        PlayerFragment fragment = new PlayerFragment();

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
        binding= FragmentPlayerBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
       binding.playerSong.setText("Hell Bells");
        binding.playerArtist.setText("AC/DC");
        Log.v("Player","Player run");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }

    public static void play(){
        Log.v("Button","Play Pause button clicked");
    }

    public static void next(){
        Log.v("Button","Next button clicked");
    }

    public static void prev(){
        Log.v("Button","Prev button clicked");
    }
}