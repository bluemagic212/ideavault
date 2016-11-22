package com.example.randomestudios.ideavaultapp1;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mrinali on 11/5/2016.
 */
public class MasonryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;

    public class TextTypeViewHolder extends RecyclerView.ViewHolder{
        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.image = (ImageView) itemView.findViewById(R.id.background);

        }

    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.fab = (FloatingActionButton) itemView.findViewById(R.id.fab);

        }

    }


    public MasonryAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }

    //MediaPlayer mPlayer;
    //private boolean fabStateVolume = false;

    /*private final Context context;
    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;


    int[] imgList = {R.drawable.two};
    String[] nameList = {"One"};
    String [] taskList = {"Mrinali"};
*/
    /* public MasonryAdapter(ArrayList<Model> list, Context context) {
        this.context = context;
    } */

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);

        }
        return null;

        /*
        System.out.println("Get View" + viewType);
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == ITEM_TYPE_NORMAL) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
            viewHolder = new IdeaHolder(layoutView);
            return viewHolder;
        }
        else if (viewType == ITEM_TYPE_HEADER){
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);
            viewHolder = new TaskHolder((layoutView));
            return viewHolder;
        }
        return viewHolder;*/
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Model object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);

                    break;
                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Model.AUDIO_TYPE:

                    ((AudioTypeViewHolder) holder).txtType.setText(object.text);


                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (fabStateVolume) {
                                if (mPlayer.isPlaying()) {
                                    mPlayer.stop();

                                }
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.volume);
                                fabStateVolume = false;

                            } else {
                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
                                mPlayer.setLooping(true);
                                mPlayer.start();
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
                                fabStateVolume = true;

                            }
                        }
                    });


                    break;
            }
        }
        /*System.out.println("Get position" + position);
        final int itemType = getItemViewType(position);
        if (itemType == ITEM_TYPE_NORMAL) {
            IdeaHolder holder = (IdeaHolder) viewholder;
            holder.imageView.setImageResource(imgList[position]);
            holder.textView.setText(nameList[position]);
        }
        else if (itemType == ITEM_TYPE_HEADER) {
            TaskHolder holder = (TaskHolder) viewholder;
            holder.textView.setText(taskList[position]);
        }*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
        //return nameList.length;
    }


    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.AUDIO_TYPE;
            default:
                return -1;
        }
        /*
        if (position == ITEM_TYPE_NORMAL) {
            System.out.println("ITEM_TYPE_NORMAL" + position);
            return 0;

        } else if (position == ITEM_TYPE_HEADER) {
            System.out.println("ITEM_TYPE_HEADER" + position);
            return 1;
        }
        return 1;
        */
    }

}
