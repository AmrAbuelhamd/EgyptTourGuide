package com.blogspot.amrabuelhamd.EgyptTourGuide.ImageSliding;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.blogspot.amrabuelhamd.EgyptTourGuide.R;

/**
 * Created by amr_aboelhamd on 09/02/18.
 */

public class ImagViewerActivity extends AppCompatActivity {
    private int[] mSightCollectionPhots;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_sliding);

        mSightCollectionPhots = getIntent().getExtras().getIntArray("photoCollection");
        AndroidImageAdapter androidImageAdapter = new AndroidImageAdapter(this, mSightCollectionPhots);
        ViewPager mViewPager = findViewById(R.id.viewPageAndroid);
        mViewPager.setAdapter(androidImageAdapter);

        mViewPager.setPageMargin(24);
    }
}
