package com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blogspot.amrabuelhamd.EgyptTourGuide.R;

import java.util.ArrayList;

/**
 * Created by amr_aboelhamd on 13/02/18.
 */
//TODO change this to recycler view -not important-
public class ArabicPhrasesAdapter extends ArrayAdapter<ArabicPhrases> {
    int mainAudio;
    boolean firsttime = true;
     static int count=1;
    ArabicPhrases phrases;
     static MediaPlayer mediaPlayer;
     static AudioManager am;
     static AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseResources();
                        am.abandonAudioFocus(afChangeListener);
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };


    public ArabicPhrasesAdapter(@NonNull Context context,ArrayList<ArabicPhrases> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

         phrases = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.arabic_item_view, parent, false);
        }
        //find views
        TextView defaultTrans = convertView.findViewById(R.id.default_trans);
        TextView arabicTrans = convertView.findViewById(R.id.arabic_trans);
        //pupulate data
        defaultTrans.setText(phrases.getDefaultTrans());
        arabicTrans.setText(phrases.getArabicTrans());

//        //i need to get the audio id her befor calling it inside the onClick
//        //because calling the phrases.getAudio directly out there messes things up-hear second voice instead of first -.
//        //updated comment "no babe, what messes things is not declaring it as final"
//        final int audoi1Id = phrases.getAudio();
//        final int audioSlowId = phrases.getSlowmAudio();
//
//        Log.v("before onclick","count= "+count);
//        //set listener on each item
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("inside onClick","count= "+count);
//
//                if(count == 1 ){
//                    mainAudio = audoi1Id;
//                    Log.v("if statment","slow count= "+count);
//                    count++;
//                }
//                else {
//                    count = 1;
//                    Log.v("else statment","fast count = "+count);
//                    mainAudio = audioSlowId;
//                }
//                //request audio focus
//                am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//                int result = am.requestAudioFocus(afChangeListener,
//                        // Use the music stream.
//                        AudioManager.STREAM_MUSIC,
//                        // Request permanent focus.
//                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
//                    if(mediaPlayer!=null)
//                        releaseResources();
//                    mediaPlayer = MediaPlayer.create(getContext(),mainAudio );
//                    mediaPlayer.start();
//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            releaseResources();
//                        }
//                    });
//                }
//            }
//        });

        return convertView;

    }

    public static void releaseResources(){
        mediaPlayer.release();
        mediaPlayer = null;
        am.abandonAudioFocus(afChangeListener);
    }
}
