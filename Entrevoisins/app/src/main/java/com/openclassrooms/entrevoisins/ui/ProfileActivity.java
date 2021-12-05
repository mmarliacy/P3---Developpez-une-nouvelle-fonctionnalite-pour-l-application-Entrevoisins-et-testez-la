package com.openclassrooms.entrevoisins.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class ProfileActivity extends AppCompatActivity{

    public Neighbour Neighbour,
            favNeighbour;
    private NeighbourApiService mApiService;

    ImageView mPhoto;
    TextView mHeadName;
    TextView mName;
    TextView mAddress;
    TextView mPhoneNumber;
    TextView mUrlContact;
    TextView mAboutMe;
    TextView mNeighborDescription;
    FloatingActionButton mFab;

    public int position;
    public  int fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mApiService = DI.getNeighbourApiService();

        // Take back the intent between the itemView and ProfileActivity
        Neighbour = getIntent().getParcelableExtra("Neighbour");
        position = getIntent().getIntExtra("Position", 0);
        fragment = getIntent().getIntExtra("Fragment", 0);

        //Enhance and referencing profile elements
        mPhoto = findViewById(R.id.profile_activity_photo);
        mHeadName = findViewById(R.id.profile_activity_heading_name);
        mName = findViewById(R.id.profile_activity_name);
        mAddress = findViewById(R.id.profile_activity_address);
        mPhoneNumber = findViewById(R.id.profile_activity_phone_number);
        mUrlContact = findViewById(R.id.profile_activity_URL_contact);
        mAboutMe = findViewById(R.id.profile_activity_about_me);
        mNeighborDescription = findViewById(R.id.profile_activity_neighbor_description);
        mFab = findViewById(R.id.profile_activity_fab);

        if (fragment == 0) {
            getNeighbourDescription();
        } else if (fragment == 1) {
            getFavoriteNeighbourDescription();
        }

        //Back arrow
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow_action);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        /*
         * BUTTON PARAMETERS :
         * */

        //Style of Button
        if (mApiService.getFavoriteNeighbours().contains(favNeighbour)) {
            mFab.setImageResource(R.drawable.ic_baseline_star_24);
        } else {
            mFab.setImageResource(R.drawable.ic_baseline_star_border_24);
        }

        //Click on favorite button --> add neighbour to favorite list, else delete favorite
        mFab.setOnClickListener(view -> {
            if (!mApiService.getFavoriteNeighbours().contains(favNeighbour)) {
                mApiService.createFavoriteNeighbour(favNeighbour);
                mFab.setImageResource(R.drawable.ic_baseline_star_24);

            } else {
                mFab.setImageResource(R.drawable.ic_baseline_star_border_24);

            }
        });
    }

    //Get neighbour profile description
        public void getNeighbourDescription() {
        //Attach view to profile description
        favNeighbour = mApiService.getPositionOfNeighbour(position);
        Glide.with(this).
                load(Neighbour.getAvatarUrl()).
                into(mPhoto);
        mName.setText(Neighbour.getName());
        mHeadName.setText(Neighbour.getName());
        mUrlContact.setText(String.format("www.facebook.com/%s", Neighbour.getName().toLowerCase()));
        mAddress.setText(Neighbour.getAddress());
        mPhoneNumber.setText(Neighbour.getPhoneNumber());
        mNeighborDescription.setText(Neighbour.getAboutMe());
        }
        //Get favorite neighbour profile description
        public void getFavoriteNeighbourDescription(){
            //Attach view to favorite profile description
            favNeighbour = mApiService.getPositionOfFavoriteNeighbour(position);
            Glide.with(this).
                    load(favNeighbour.getAvatarUrl()).
                    into(mPhoto);
            mName.setText(favNeighbour.getName());
            mHeadName.setText(favNeighbour.getName());
            mUrlContact.setText(String.format("www.facebook.com/%s", favNeighbour.getName().toLowerCase()));
            mAddress.setText(favNeighbour.getAddress());
            mPhoneNumber.setText(favNeighbour.getPhoneNumber());
            mNeighborDescription.setText(favNeighbour.getAboutMe());
        }

        @Override
        public void onResume() {
        super.onResume();
        }
}





