package com.blogspot.amrabuelhamd.EgyptTourGuide.Utils;

import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;


/**
 * Created by amro mohamed on 3/30/2018.
 * i made this combination by myself yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaay
 */
//copied from stackoverflow
abstract public class TextValidator implements TextWatcher {
    TextInputLayout emailEditText;
    public TextValidator(TextInputLayout emailEditText) {
        this.emailEditText = emailEditText;
    }
    public TextValidator(){

    }

    public abstract void validate();

    @Override
    final public void afterTextChanged(Editable s) {
        //avoid triggering event when text is empty
        if (s.length() > 0) {
            emailEditText.setErrorEnabled(true);
            last_text_edit = System.currentTimeMillis();
            handler.postDelayed(input_finish_checker, delay);
        }
        else {
            emailEditText.setError(null);
        }
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {
        //You need to remove this to run only once
//        obj.handler.removeCallbacks(obj.input_finish_checker);
        handler.removeCallbacks(input_finish_checker);
    }
    /**
     * [start]
     * setting delay to use to know if user finished typing or not
     * to valide the edditText
     */
    private long delay = 1500; // 1 seconds after user stops typing
    private long last_text_edit = 0;
    private Handler handler = new Handler();

    public Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                validate();
            }
        }
    };
    //[end]
}

