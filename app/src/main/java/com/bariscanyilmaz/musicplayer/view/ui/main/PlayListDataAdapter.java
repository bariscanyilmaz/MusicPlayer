package com.bariscanyilmaz.musicplayer.view.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bariscanyilmaz.musicplayer.R;
import com.bariscanyilmaz.musicplayer.model.PlayList;

import java.util.ArrayList;
import java.util.List;

public class PlayListDataAdapter extends RecyclerView.Adapter<PlayListDataAdapter.ViewHolder>  {

    private List<PlayList> playLists;

    public PlayListDataAdapter(){this.playLists=new ArrayList<PlayList>();}

    public void setPlayLists(List<PlayList> playLists){
        this.playLists=playLists;
    }


    @NonNull
    @Override
    public PlayListDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View dataView=inflater.inflate(R.layout.play_list_item,parent,false);
        PlayListDataAdapter.ViewHolder viewHolder=new ViewHolder(dataView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList=playLists.get(position);

        holder.playListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play the list;
            }
        });

    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout playListLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playListLayout=itemView.findViewById(R.id.playListItemLayout);

        }
    }

}
