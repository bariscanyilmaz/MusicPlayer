package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.databinding.FragmentPlayListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayListFragment extends Fragment {

    private FragmentPlayListBinding binding;

    public PlayListFragment() {
        // Required empty public constructor
    }


    public static PlayListFragment newInstance() {
        PlayListFragment fragment = new PlayListFragment();

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
        binding=FragmentPlayListBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}