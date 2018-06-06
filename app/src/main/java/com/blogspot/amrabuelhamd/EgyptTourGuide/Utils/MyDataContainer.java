package com.blogspot.amrabuelhamd.EgyptTourGuide.Utils;

import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.AswanInformation;
import com.blogspot.amrabuelhamd.EgyptTourGuide.R;

import java.util.ArrayList;

/**
 * Created by amro mohamed on 4/7/2018.
 */
//todo: continue this class
public class MyDataContainer {

    // the getter methods that used inside fragments

    public static ArrayList<AswanInformation> getSights(int num){
        num+=1;
        switch (num) {
            case 1:
                return getAswanSights();
            default:
                return null;
        }

    }
    public static ArrayList<AswanInformation> getHotels(int num){
        num+=1;
        switch (num) {
            case 1:
                return getAswanHotels();
            default:
                return null;
        }

    }
    public static ArrayList<AswanInformation> getResurant(int num){
        num+=1;
        switch (num) {
            case 1:
                return getAswanResturants();
            default:
                return null;
        }
    }

    //each governorate data are in separate get method

    /**
     * get aswan restaurant
     */
    static ArrayList<AswanInformation> getAswanResturants(){

        ArrayList<AswanInformation> arrayList;
        //make objects contains my data about each sight
        arrayList = new ArrayList<>();

        arrayList.add(new AswanInformation(R.drawable.rest1, R.string.rest1,
                R.string.rest1desk,R.string.rest1link));

        return arrayList;
    }
    /**
     * get aswan hotels
     */
    static ArrayList<AswanInformation> getAswanHotels(){

        ArrayList<AswanInformation> arrayList;

        //make objects contains my data about each sight
        arrayList = new ArrayList<>();

        arrayList.add(new AswanInformation(R.drawable.hotel1, R.string.hotel1,
                R.string.hotel1desc));
        arrayList.add(new AswanInformation(R.drawable.hotel2, R.string.hotel2,
                R.string.hotel2desc));
        arrayList.add(new AswanInformation(R.drawable.hotel3, R.string.hotel3,
                R.string.hotel3desc));
        arrayList.add(new AswanInformation(R.drawable.hotel4, R.string.hotel4,
                R.string.hotel4desc));
        arrayList.add(new AswanInformation(R.drawable.hotel5, R.string.hotel5,
                R.string.hotel5desc));
        arrayList.add(new AswanInformation(R.drawable.hotel6, R.string.hotel6,
                R.string.hotel6desc));
        arrayList.add(new AswanInformation(R.drawable.hotel7, R.string.hotel7,
                R.string.hotel7desc));
        arrayList.add(new AswanInformation(R.drawable.hotel8, R.string.hotel8,
                R.string.hotel8desc));
        arrayList.add(new AswanInformation(R.drawable.hotel9, R.string.hotel9,
                R.string.hotel9desc));
        arrayList.add(new AswanInformation(R.drawable.hotel10, R.string.hotel10,
                R.string.hotel10desc));

        return arrayList;

    }
    /**
     * get aswan sights
     * @return arraylist of obj type that contains all data about aswan sights
     */
    static ArrayList<AswanInformation> getAswanSights(){

        int[][] mPhotoCollection = new int[9][];
        //get ids of each sight photo collection
        //kom ambo
        mPhotoCollection[0] = new int[]{R.drawable.ombo1, R.drawable.ombo2,
                R.drawable.ombo3, R.drawable.ombo4, R.drawable.ombo5};
        //philae
        mPhotoCollection[1] = new int[] {R.drawable.fyla1,R.drawable.fyla2,
                R.drawable.fyla3,R.drawable.fyla4,R.drawable.fyla5};
        //Mausoleum of Aga Khan
        mPhotoCollection[2] = new int[] {R.drawable.aga1,R.drawable.aga2,
                R.drawable.aga3,R.drawable.aga4,R.drawable.aga5};
        //Nubian Museum
        mPhotoCollection[3] = new int[] {R.drawable.noba1,R.drawable.noba2,
                R.drawable.noba3,R.drawable.noba4,R.drawable.noba5};
        //Monastery of St Simeon
        mPhotoCollection[4] = new int[] {R.drawable.mons1,R.drawable.mons2,
                R.drawable.mons3,R.drawable.mons4,R.drawable.mons5};
        //Trajan's Kiosk
        mPhotoCollection[5] = new int[] {R.drawable.traj1,R.drawable.traj2,
                R.drawable.traj3,R.drawable.trag4,R.drawable.trag5};
        //El Nabatat Island
        mPhotoCollection[6] = new int[]{R.drawable.naba1,R.drawable.naba2,
                R.drawable.naba3,R.drawable.naba4,R.drawable.naba5};
        //Sehel Island
        mPhotoCollection[7] = new int[]{R.drawable.sehel1,R.drawable.sehel2,
                R.drawable.sehel3,R.drawable.sehel4,R.drawable.sehel5};
        //Elephantine Island
        mPhotoCollection[8] = new int[]{R.drawable.elph1,R.drawable.elph2,
                R.drawable.elph3,R.drawable.elph4,R.drawable.elph5};

        //make objects contains my data about each sight
        ArrayList<AswanInformation> arrayList = new ArrayList<>();

        arrayList.add(new AswanInformation(R.drawable.kom_ombo_temple, R.string.kom_ambo_name,
                R.string.kom_ambo_describtion,mPhotoCollection[0]));
        arrayList.add(new AswanInformation(R.drawable.philae, R.string.philae_name,
                R.string.philae_desc,mPhotoCollection[1]));
        arrayList.add(new AswanInformation(R.drawable.mausoleum_of_aga_khan, R.string.mausoleum_of_aga_khan_name,
                R.string.mausoleum_of_aga_khan_desc,mPhotoCollection[2]));
        arrayList.add(new AswanInformation(R.drawable.nubian_museum, R.string.nubian_museum_name,
                R.string.nubian_museum_describtion,mPhotoCollection[3]));
        arrayList.add(new AswanInformation(R.drawable.mons, R.string.monastery_simeon_name,
                R.string.monastery_simeon_desc,mPhotoCollection[4]));
        arrayList.add(new AswanInformation(R.drawable.trag, R.string.trajan_kiosk_name,
                R.string.trajan_kiosk_desc,mPhotoCollection[5]));
        arrayList.add(new AswanInformation(R.drawable.el_nabatat_island, R.string.el_nabatat_island_name,
                R.string.el_nabatat_island_desc,mPhotoCollection[6]));
        arrayList.add(new AswanInformation(R.drawable.sehel, R.string.sehel_island_name,
                R.string.sehel_island_desc,mPhotoCollection[7]));
        arrayList.add(new AswanInformation(R.drawable.elephantine_island_aswan, R.string.elephantine_name,
                R.string.elephantine_name_desc,mPhotoCollection[8]));

        return arrayList;
    }
}
