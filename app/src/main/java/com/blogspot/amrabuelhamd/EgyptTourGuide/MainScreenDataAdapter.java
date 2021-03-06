package com.blogspot.amrabuelhamd.EgyptTourGuide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.AswanInformation;

import java.util.List;

/**
 * Created by amro mohamed on 3/1/2018.
 */

//todo change names in this class
public class MainScreenDataAdapter extends RecyclerView.Adapter<MainScreenDataAdapter.ViewHolder> {
    private Context context;//todo delete the context
    private List<AswanInformation> dataSet;
    private CustomItemClickListener listener;

    public MainScreenDataAdapter(List<AswanInformation> dataSet, Context c, CustomItemClickListener l) {
        this.dataSet = dataSet;
        context = c;
        listener = l;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainScreenDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_screen_card_item, parent, false);

        final ViewHolder vh = new ViewHolder(v);

        //setting listener
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vh.getAdapterPosition());            }
        });

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        AswanInformation data = dataSet.get(position);
        holder.optionName.setText(data.getmSightNameResourceId());
        holder.optionNameDesc.setText(data.getmSightSmallDescribtion());
        holder.optionImage.setImageResource(data.getmImageResoueseId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView optionName, optionNameDesc;
        ImageView optionImage;
        CustomItemClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);
            optionName = itemView.findViewById(R.id.optionName);
            optionNameDesc = itemView.findViewById(R.id.optionNameDesc);
            optionImage = itemView.findViewById(R.id.main_screen_demo_option);
        }
    }

    //TODO:
    // If the list needs an update, call a notification method on the RecyclerView.Adapter object,
    // such as notifyItemChanged().
    // The layout manager then rebinds any affected view holders, allowing their data to be updated.
}
