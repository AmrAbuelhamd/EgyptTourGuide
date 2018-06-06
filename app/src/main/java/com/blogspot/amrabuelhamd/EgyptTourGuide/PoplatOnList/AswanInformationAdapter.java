package com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.amrabuelhamd.EgyptTourGuide.R;
import com.blogspot.amrabuelhamd.EgyptTourGuide.ImageSliding.ImagViewerActivity;

import java.util.ArrayList;

/**
 * Created by amr_aboelhamd on 07/02/18.
 */

//TODO change this to recycler view -not important-

public class AswanInformationAdapter extends ArrayAdapter<AswanInformation> {
    private AswanInformation aswanInformation;


    public AswanInformationAdapter(Context context, ArrayList<AswanInformation> aswanInformations){
        super(context,0, aswanInformations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        aswanInformation = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sight_item_view, parent, false);
        }
        // Lookup view for data population
        ImageView imageView = convertView.findViewById(R.id.sight_image);

        TextView textView1 = convertView.findViewById(R.id.sight_name);
        TextView textView2 = convertView.findViewById(R.id.sight_breief_describtion);

        // Populate the data into the template view using the data object
        textView1.setText(aswanInformation.getmSightNameResourceId());
        textView2.setText(aswanInformation.getmSightSmallDescribtion());

        /****************************/
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(),
                aswanInformation.getmImageResoueseId(), options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(getContext().getResources(), aswanInformation.getmImageResoueseId()
                        , 150, 300));

        //set on click listener on the image
        if(aswanInformation.hasCollectionPhotos()) {
            //i had to declare it final because not doing so messes things up
            //and make everything not as expected like first collection instead of the third etc
            final int[] mSightPhotosCollection = aswanInformation.getmSightCollectoinPhotos();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ImagViewerActivity.class);
                    intent.putExtra("photoCollection", mSightPhotosCollection);
                    getContext().startActivity(intent);
                }
            });
        }

        // Return the completed view to render on screen
        return convertView;
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
