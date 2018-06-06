package com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList;

/**
 * Created by amr_aboelhamd on 13/02/18.
 */

public class ArabicPhrases {
    private int mDefaultTrans;
    private int mArabicTrans;
    private int mAudio;
    private int mSlowAudio;

    public ArabicPhrases(int defaultTrans, int arabicTrans, int audio, int slowAudio) {
        mDefaultTrans = defaultTrans;
        mArabicTrans = arabicTrans;
        this.mAudio = audio;
        mSlowAudio = slowAudio;
    }



    public int getDefaultTrans() {
        return mDefaultTrans;
    }

    public int getArabicTrans() {
        return mArabicTrans;
    }

    public int getSlowmAudio() {
        return mSlowAudio;
    }
    public int getAudio() {
        return mAudio;
    }

}
