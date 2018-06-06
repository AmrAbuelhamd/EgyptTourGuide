package com.blogspot.amrabuelhamd.EgyptTourGuide;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Data.TouristContract.TouristEntry;

import com.blogspot.amrabuelhamd.EgyptTourGuide.Data.TouristContract;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Data.TouristDbHelper;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments.HotelsFragment;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Utils.TextValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by amr mohamed on 3/29/2018.
 */
//TODO: handle database output
public class TakeUserData extends AppCompatActivity {
    String hotelName , governoName;
    SeekBar durationSeekBar,visitorsSeekBar;
    TextView durationSeekbarValue ,visitorsSeekbarValue , governoTextView , hotelTextView;
    Spinner spinner, mGenderSpinner;
    Drawable myIcon;
    TextInputLayout emailEditText,nameEditText,lastNameEditText, phoneEditText;
    TextInputEditText emailText, nameText,lastText,phoneText;

    private int mGender = TouristContract.TouristEntry.GENDER_MALE;
    private TouristDbHelper tHelper= new TouristDbHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_user_data);

        findViews();

        getHotelGovNames();

        prepareSpinner();
        prepareSeekbar();
        setupSpinner();
        validateEditTextInputs();


        //setupUI(findViewById(R.id.scrollView));
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = TouristContract.TouristEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = TouristContract.TouristEntry.GENDER_FEMALE;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = TouristContract.TouristEntry.GENDER_MALE;
            }
        });
    }

    /**
     * some polish on edit text
     * &validate user input
     * very well designed
     */
    private void validateEditTextInputs() {
        //create custom right icon
        myIcon = getResources().getDrawable(R.drawable.ic_done_green_24dp);
        myIcon.setBounds(0, 0, myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight());

        nameText.addTextChangedListener(new TextValidator(nameEditText) {
            @Override
            public void validate() {
                validateNameInput(nameText.getText().toString(),nameEditText);
            }
        });
        lastText.addTextChangedListener(new TextValidator(lastNameEditText) {
            @Override
            public void validate() {
                validateNameInput(lastText.getText().toString(),lastNameEditText);
            }
        });
        emailText.addTextChangedListener(new TextValidator(emailEditText) {
            @Override
            public void validate() {
                if(isValidEmail(emailText.getText().toString())) {
                    emailEditText.setErrorTextAppearance(R.style.correct_appearance);
                    emailEditText.setError("nice email ^_-");

                }
                else {
                    emailEditText.setErrorTextAppearance(R.style.error_appearance);
                    emailEditText.setError("please enter a valid email");

                }
            }
        });
        phoneText.addTextChangedListener(new TextValidator(phoneEditText) {
            @Override
            public void validate() {
                validateNumber();
            }
        });

    }

    private void validateNumber() {
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneText.getText().toString())){
            phoneEditText.setErrorTextAppearance(R.style.correct_appearance);
            phoneEditText.setError("Good");
        }
        else {
            phoneEditText.setError("please enter a valid number");
            phoneEditText.setErrorTextAppearance(R.style.error_appearance);
        }
    }

    //validate edit text methods
    void validateNameInput(String name,TextInputLayout editText) {
        if (!name.matches("[a-zA-Z]*") || TextUtils.isEmpty(name) || !(name.length() >= 3)){
            editText.setError("please enter a valid name");
            editText.setErrorTextAppearance(R.style.error_appearance);
    }
        else {
            editText.setErrorTextAppearance(R.style.correct_appearance);
            editText.setError("nice name bro/sis");
        }
    }

    /**
     * get hotel and governorate names
     * from calling fragment
     */
    void getHotelGovNames(){
        //get hotel and govern nameEditText
        hotelName = getIntent().getStringExtra("hotelName");
        governoName = HotelsFragment.governorateName;
        //set governo and hotel values
        governoTextView.setText(governoName);
        hotelTextView.setText(hotelName);
    }
    /**
     * find views obj
     */
    void findViews(){
        //get elements obj
        durationSeekBar = findViewById(R.id.seekBar);
        durationSeekbarValue = findViewById(R.id.seekbar_value);
        visitorsSeekBar = findViewById(R.id.seekBar2);
        visitorsSeekbarValue = findViewById(R.id.numberOfVisitors);
        governoTextView = findViewById(R.id.governorate_name);
        hotelTextView = findViewById(R.id.hotel_name);
        spinner = findViewById(R.id.spinner);
        //find edit text
        nameEditText = findViewById(R.id.first_name);
        nameText = findViewById(R.id.first_nameEdit);

        lastNameEditText = findViewById(R.id.last_name);
        lastText = findViewById(R.id.last_nameEdit);

        emailEditText = findViewById(R.id.email);
        emailText = findViewById(R.id.emailEditText);

        phoneEditText = findViewById(R.id.phoneNumber);
        phoneText = findViewById(R.id.phoneEdit);
        mGenderSpinner = findViewById(R.id.spinner_gender);

    }
    /**
     * prepare seekbar value displayer
     */
    void prepareSeekbar(){
        //set listener on duration seekbar to display values to user
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                durationSeekbarValue.setText(String.valueOf(progress+10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //set listener on visitors seekbar to display values to user
        visitorsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                visitorsSeekbarValue.setText(String.valueOf(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    /**
     * this method get countries i copied the code from stackoverflow as it's.
     */
    //Todo make this calc in separt thread
    private void prepareSpinner() {
        //[start] get countries
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        //[end]
        //set the spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, countries);
        spinner.setAdapter(adapter);



    }

    //todo move them in util class
    /**
     * some util methods
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    /**
     * copied from StackOverFlow
     */
    void hideKeyBoard(){
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     *
     * triggered when user click the submit button
     * @param view
     */
    public void takeInputs(View view) {
//        this if checks the four editText, well designed -_^
        if((emailEditText.getError()!=null && emailEditText.getError().charAt(0)!='p')&&
                (phoneEditText.getError()!=null && phoneEditText.getError().charAt(0)!='p')&&
                nameEditText.getError()!=null && nameEditText.getError().charAt(0)!='p'&&
                lastNameEditText.getError()!=null && lastNameEditText.getError().charAt(0)!='p'){


//            Toast.makeText(this,"thanks,\n" +
//                    "spinner data is= "+spinner.getSelectedItem()+"\n" +
//                    "visitors= "+visitorsSeekbarValue.getText().toString()+"\n" +
//                    "duration= "+durationSeekbarValue.getText().toString(),Toast.LENGTH_LONG).show();

            insertNewUser();
//
//            displayDatabaseInfo();


        }
        else
            Toast.makeText(this,"fill all required fields",Toast.LENGTH_LONG).show();
    }
/*****************************************************************************/

private void displayDatabaseInfo() {
    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    SQLiteDatabase db= tHelper.getReadableDatabase();
    String selection= TouristEntry.COLUMN_GEVARNO_NAME +"=?";
    String []selectionArgs={"ASWAN"};
    // Perform a query on the provider using the ContentResolver.
    // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
    Cursor cursor = db.query(
            TouristEntry.TABLE_NAME,   // The content URI of the words table
            null,
              selection,   // The columns to return for each row
            selectionArgs,                   // Selection criteria
            null,                   // Selection criteria
            null,
            null,
            null);                  // The sort order for the returned rows

    int displayView = cursor.getCount();





//
//    try {
//        // Create a header in the Text View that looks like this:
//        //
//        // The pets table contains <number of rows in Cursor> pets.
//        // _id - name - breed - gender - weight
//        //
//        // In the while loop below, iterate through the rows of the cursor and display
//        // the information from each column in this order.
//        displayView =displayView.concat(TouristEntry._ID + " - " +
//                TouristEntry.COLUMN_GEVARNO_NAME );
//
//        // Figure out the index of each column
//        int idColumnIndex = cursor.getColumnIndex(TouristEntry._ID);
//        int nameColumnIndex = cursor.getColumnIndex(TouristEntry.COLUMN_GEVARNO_NAME);
//
//
//        // Iterate through all the returned rows in the cursor
//        while (cursor.moveToNext()) {
////             Use that index to extract the String or Int value of the word
////             at the current row the cursor is on.
//            int currentID = cursor.getInt(idColumnIndex);
//            String currentName = cursor.getString(nameColumnIndex);

//             Display the values from each column of the current row in the cursor in the TextView
//            displayView = displayView.concat(("\n" + currentID + " - " +
//                    currentName ));
//        }
//    } finally {
//        // Always close the cursor when you're done reading from it. This releases all its
//        // resources and makes it invalid.
//        cursor.close();
//    }
    Toast.makeText(this," "+displayView,Toast.LENGTH_LONG).show();
}
/*****************************************************************************/
    private void insertNewUser() {
        SQLiteDatabase db= tHelper.getWritableDatabase();
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = nameText.getText().toString().trim();
        String lastNameString = lastText.getText().toString().trim();
        String emailString = emailText.getText().toString().trim();
        String  ff= phoneText.getText().toString().trim();
//        int phoneNum = Integer.parseInt(ff);
        int visitors = Integer.parseInt(visitorsSeekbarValue.getText().toString());
        int duration = Integer.parseInt(durationSeekbarValue.getText().toString());
        String country = spinner.getSelectedItem().toString().trim();
        //we have also these two:
        //          hotelName , governoName, mGender

/*************************************************************************************************************/
        // Create a ContentValues object where column names are the keys,
        // and tourist attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(TouristEntry.COLUMN_TOURIST_FNAME, nameString);
        values.put(TouristEntry.COLUMN_TOURIST_LNAME, lastNameString);
        values.put(TouristEntry.COLUMN_TOURIST_EMAIL, emailString);
//        values.put(TouristEntry.COLUMN_TOURIST_PHONE, phoneNum);
        values.put(TouristEntry.COLUMN_TOURIST_CONTRY, country);
        values.put(TouristEntry.COLUMN_TOURISTS_NUMBER, visitors);
        values.put(TouristEntry.COLUMN_TOURIST_DURATION, duration);
        values.put(TouristEntry.COLUMN_PET_GENDER, mGender);
        values.put(TouristEntry.COLUMN_BOOKED_HOTEL, hotelName);
        values.put(TouristEntry.COLUMN_GEVARNO_NAME, governoName);
        long newRowId= db.insert(TouristEntry.TABLE_NAME,null,values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving your Data\nPlease try again", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "THANKS FOR REGISTRATION", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * copied from stack....
     * hide keyboard when user click anything other than the editText
     * @param view
     */
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    emailEditText.clearFocus();
                    phoneEditText.clearFocus();
                    nameEditText.clearFocus();
                    lastNameEditText.clearFocus();
                    hideSoftKeyboard(TakeUserData.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                //setupUI(innerView);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null)
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
