package com.blogspot.amrabuelhamd.EgyptTourGuide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.support.v7.widget.RecyclerView;


import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.AswanInformation;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<AswanInformation> dataArrayList = new ArrayList<>();
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify an adapter and creating on click listener for each obj
        mAdapter = new MainScreenDataAdapter(dataArrayList,this, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0) {
                    String governorateName =
                            getResources().getString(dataArrayList.get(position).getmSightNameResourceId());
                    Intent intent = new Intent(context, SecondActivity.class);
                    intent.putExtra("governorateName",governorateName);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                if(position ==1){
//                    String governorateName = getResources().getString(dataArrayList.get(
//                            position).getmSightNameResourceId());
//                    Intent intent = new Intent(context, SecondActivity.class);
//                    intent.putExtra("governorateName",governorateName);
//                    startActivity(intent);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        //prepare Data
        prepareMainOptions();

//        //set the pager adapter
//        mViewPager = findViewById(R.id.view_pager);
//        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(mViewPagerAdapter);


    }

    /**
     * create the home screen governorates data
     */

    private void prepareMainOptions() {
        //TODO make another four governorates in the home screen
        dataArrayList.add(new AswanInformation(R.drawable.aswan,
                R.string.aswanName,R.string.aswanDesk));
        dataArrayList.add(new AswanInformation(R.drawable.sample,
                R.string.aswanName,R.string.aswanDesk));
        dataArrayList.add(new AswanInformation(R.drawable.sample,
                R.string.aswanName,R.string.aswanDesk));
        dataArrayList.add(new AswanInformation(R.drawable.sample,
                R.string.aswanName,R.string.aswanDesk));




        mAdapter.notifyDataSetChanged();

    }

    public void ssss(View view) {
        Intent intent = new Intent(this,StatisticsActivity.class);
        startActivity(intent);
    }
}
