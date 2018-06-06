package com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.ArabicPhrases;
import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.ArabicPhrasesAdapter;
import com.blogspot.amrabuelhamd.EgyptTourGuide.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amr_aboelhamd on 13/02/18.
 */

public class ArabicPhrasesFragment extends Fragment {
    ArrayList<ArabicPhrases> arr;
    //audio listeners and focus stuff
    ArabicPhrases phrases;
    int mainAudio;
    int count[] = new int[10];
    MediaPlayer mediaPlayer;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arabic_phrases,container,false);

        //set Data for each word
        arr = new ArrayList<>();
        arr.add(new ArabicPhrases(R.string.default_what_name,R.string.ara_what_name,R.raw.voice_whats_name,
                R.raw.voice_whats_name__slow));
        arr.add(new ArabicPhrases(R.string.default_my_name,R.string.ara_my_name,R.raw.myname,
                R.raw.mynameslow));
        arr.add(new ArabicPhrases(R.string.default_how_you,R.string.ara_how_you,R.raw.howareyou,
                R.raw.howareyouslow));
        arr.add(new ArabicPhrases(R.string.default_i_am_good,R.string.ara_i_am_good,R.raw.fine,
                R.raw.fineslow));
        arr.add(new ArabicPhrases(R.string.default_some_speakEngl,R.string.ara_some_speakEngl,R.raw.voice_some_one_speaks_eng,
                R.raw.voice_some_one_speaks_eng_slow));
        arr.add(new ArabicPhrases(R.string.default_littleArabic,R.string.ara_littleArabic,R.raw.speaklittleara,
                R.raw.speaklittlearaslow));
        arr.add(new ArabicPhrases(R.string.default_can_understand,R.string.ara_can_understand,R.raw.understood,
                R.raw.understoodslow));
        arr.add(new ArabicPhrases(R.string.default_what_said,R.string.ara_what_said,R.raw.whatsaid,
                R.raw.whatsaidslow));
        arr.add(new ArabicPhrases(R.string.default_sppeak_slowley,R.string.ara_sppeak_slowley,R.raw.beslow,
                R.raw.beslowslow));
        arr.add(new ArabicPhrases(R.string.default_what_said,R.string.ara_what_said,R.raw.whatsaid,
                R.raw.whatsaidslow));
        //TOdo add i can't understand you and modify audio then add it.



        //set the adapter of the data
        final ArabicPhrasesAdapter phrasesAdapter = new ArabicPhrasesAdapter(getActivity(),arr);
        //set adapter to populate data
        ListView listView = rootView.findViewById(R.id.arabic_list_view);
        listView.setAdapter(phrasesAdapter);

        //setting initial value for all array
        Arrays.fill(count,1);
        //for playing audio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                phrases = (ArabicPhrases)parent.getAdapter().getItem(position);

                //choose between playing fast or slow audio according on numbers of clicks
                if(count[position] == 1 ){
                    mainAudio = phrases.getAudio();
                    count[position]++;
                    //make user when click on another one then back don't play slow
                    int swap = count[position];
                    Arrays.fill(count,1);
                    count[position] = swap;
                }
                else {
                    Arrays.fill(count,1);
                    mainAudio = phrases.getSlowmAudio();
                }

                //request audio focus
                am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                int result = am.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    if(mediaPlayer!=null)
                        releaseResources();
                    mediaPlayer = MediaPlayer.create(getContext(),mainAudio );
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseResources();
                        }
                    });
                }
            }
        });
        return rootView;
    }

    /**
     * releases the resources of audio
     */
    public void releaseResources(){
        mediaPlayer.release();
        mediaPlayer = null;
        am.abandonAudioFocus(afChangeListener);
    }

    /**
     * callback happened when fragment is invisible on screen anymore
     * make sure to shut the app up when user go away
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser && mediaPlayer!=null) {
                releaseResources();
            }
        }
    }
}
