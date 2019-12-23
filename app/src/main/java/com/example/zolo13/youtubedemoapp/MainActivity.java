package com.example.zolo13.youtubedemoapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AlbumsAdapter.AlbumClickListener {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private CoordinatorLayout main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        Intent intent = new Intent();
        sendBroadcast(intent);

        main_content = (CoordinatorLayout) findViewById(R.id.main_content);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

//        try {
//            Picasso.with(this).load(R.drawable.ic_launcher_background).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void prepareAlbums() {
//        String[] playBackURL = new String[]
//                {
//                        "etF3uOunRIY",
//                        "AzVUBCJEMXE",
//                        "hZvGSb-H0TI",
//                        "fcDLaKQa6Sc",
//                        "5as-Wdj4ECs",
//                        "q3XL2axKmXY",
//                        "E6C-NrghFRA",
//                        "9tOWMNk4MvY",
//                        "F547OrsKmzk",
//                        "ipMvsNBBIK4",
//                        "MugCEDbJ6vI",
//                        "r3hnQbHiZME",
//                        "ATI3CmXPtow",
//                        "QBww5ia5tWU",
//                        "Gum0cUybfJk",
//                        "AFbcId4NgTI&t=2s"
//                };

        String[] playBackURL = new String[]
                {
                        "K5DRnRW92IU",
                        "aejje2Tibds",
                        "kB5BsE8piq4",
                        "BiBonJ_T53A",
                        "nZFioSe37Vo",
                        "dOrzriMcv8E",
                        "dWZc8T3WlF4",
                        "aG_Jt_NA8bk",
                        "9nJwVBAwfks",
                        "YAIABZvaBTU",
                        "_LcraGZsb5Y",
                        "c2iuL5ptjqY",
                        "E-mGIZCgo2I",
                        "HEAYsJPcHI4",
                        "cW3COFI9WYg",
                        "Y1bmOF4PGP8",
                        "_1orXB2Yqk0"
                };

        Album a = new Album(playBackURL[0], "Check in Code");
        albumList.add(a);

        a = new Album(playBackURL[1], "Check out Code");
        albumList.add(a);

        a = new Album(playBackURL[2], "Mark on Notice");;
        albumList.add(a);

        a = new Album(playBackURL[3], "Walk in");
        albumList.add(a);

        a = new Album(playBackURL[4],"KYC Request");
        albumList.add(a);

        a = new Album(playBackURL[5], "Notice Board");
        albumList.add(a);

        a = new Album(playBackURL[6], "Schedule visit and Comment");
        albumList.add(a);

        a = new Album(playBackURL[7], "Attendance");
        albumList.add(a);

        a = new Album(playBackURL[8],"Booking Request");
        albumList.add(a);

        a = new Album(playBackURL[9], "Confirm Booking");
        albumList.add(a);

        a = new Album(playBackURL[10], "Move");
        albumList.add(a);

        a = new Album(playBackURL[11], "Swap");
        albumList.add(a);

        a = new Album(playBackURL[12], "Payment");
        albumList.add(a);

        a = new Album(playBackURL[13], "Quick Links");
        albumList.add(a);

        a = new Album(playBackURL[14], "Dashboard");
        albumList.add(a);

        a = new Album(playBackURL[15], "Electricity Meters");
        albumList.add(a);

        a = new Album(playBackURL[16], "Tickets and Internal comments");
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

//        int position = (int) v.getTag(R.string.pos);
//        int track = (int) v.getTag(R.string.track);
//        int img = (int) v.getTag(R.string.img);
//        String name = (String) v.getTag(R.string.name);
//        String url = (String) v.getTag(R.string.url);
//
//        System.out.println("hh yashal position is " + position);
//        System.out.println("hh yashal track is " + track);
//        System.out.println("hh yashal img is " + img);
//        System.out.println("hh yashal name is " + name);
//        System.out.println("hh yashal url is " + url);

//        Intent intent = new Intent(MainActivity.this, VideoDetailActivity.class);
//        intent.putExtra("video_name", name);
//        intent.putExtra("video_url", url);
//        startActivity(intent);


    }

    @Override
    public void onAlbumItemClick(Album album) {
        if (album != null) {
            String videoId = album.getPlayBackURL();
            Intent mIntent = new Intent(this, BasicPlayerActivity.class);
            mIntent.putExtra("video_id", videoId);
            startActivity(mIntent);
        }
        else {
            Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
