package com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList;

/**
 * Created by amr_aboelhamd on 07/02/18.
 */

public class AswanInformation {
    private int mSightImageResoueseId;
    private int mSightNameResourceId;
    private int mSightSmallDescribtion;
    private int[] mSightCollectoinPhotos;
    private boolean hasCollectionPhotos;
    //tripadvisor website link that contains detailed info about the location
    private int mSightWebLink;

    //experimintal constructor didcated for restaurant fragment
    public AswanInformation(int sightImageResoueseId, int sightNameResourceId, int sightSmallDescribtion,
                            int mSightWebLink) {
        this.mSightImageResoueseId = sightImageResoueseId;
        this.mSightNameResourceId = sightNameResourceId;
        this.mSightSmallDescribtion = sightSmallDescribtion;
        this.mSightWebLink = mSightWebLink;
        hasCollectionPhotos=false;
    }
    //taking info without a collection photo
    public AswanInformation(int sightImageResoueseId, int sightNameResourceId, int sightSmallDescribtion) {
        this.mSightImageResoueseId = sightImageResoueseId;
        this.mSightNameResourceId = sightNameResourceId;
        this.mSightSmallDescribtion = sightSmallDescribtion;
        hasCollectionPhotos=false;
    }
    //taking info with a collection photo
    public AswanInformation(int sightImageResoueseId, int sightNameResourceId, int sightSmallDescribtion,
                            int[] sightCollectoinPhotos ) {
        this.mSightImageResoueseId = sightImageResoueseId;
        this.mSightNameResourceId = sightNameResourceId;
        this.mSightSmallDescribtion = sightSmallDescribtion;
        this.mSightCollectoinPhotos = sightCollectoinPhotos;
        hasCollectionPhotos=true;
    }

    public int getmImageResoueseId() {
        return mSightImageResoueseId;
    }
    public int getmSightNameResourceId() {
        return mSightNameResourceId;
    }

    /**
     *
     * @return an description of that sight
     */
    public int getmSightSmallDescribtion() {
        return mSightSmallDescribtion;
    }
    /**
     *
     * @return the collection of photos for that sight
     */
    public int[] getmSightCollectoinPhotos() {
        return mSightCollectoinPhotos;
    }

    public boolean hasCollectionPhotos(){
        return hasCollectionPhotos;
    }

    public int getmSightWebLink() {
        return mSightWebLink;
    }

}
