package com.example.zolo13.youtubedemoapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    private String videoId;
    AlbumClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public CardView card_view;
        private LinearLayout rootLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            card_view = (CardView) view.findViewById(R.id.card_view);
            rootLayout = (LinearLayout) view.findViewById(R.id.rootLayout);
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList, AlbumClickListener listener) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        try {
            videoId = extractYoutubeId("https://www.youtube.com/watch?v="+album.getPlayBackURL());

            System.out.println("hh yashal video Id " + videoId);

            String img_url = "http://img.youtube.com/vi/" + videoId + "/0.jpg"; // this is link which will give u thumnail image of that video

            Picasso.with(mContext)
                    .load(img_url)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.thumbnail);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAlbumItemClick(album);
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAlbumItemClick(album);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

    public interface AlbumClickListener {
        void onAlbumItemClick(Album album);

    }
}
