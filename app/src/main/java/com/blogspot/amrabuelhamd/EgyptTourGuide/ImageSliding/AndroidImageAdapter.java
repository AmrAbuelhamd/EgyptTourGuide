package com.blogspot.amrabuelhamd.EgyptTourGuide.ImageSliding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by amr_aboelhamd on 09/02/18.
 */

public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;
    private int[] sliderImagesId;
    public AndroidImageAdapter(Context context,int[] imagesIds) {
        this.mContext = context;
        sliderImagesId = imagesIds;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView mImageView = new ImageView(mContext);

        /*****/
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), sliderImagesId[position], options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        /*****/



        mImageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);




        mImageView.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(),
                        sliderImagesId[position],250,375));

        container.addView(mImageView, 0);
//        setMargins(mImageView,16,16,16,16);
        return mImageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object obj) {
        container.removeView((ImageView) obj);
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
}
