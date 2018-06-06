package com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.AswanInformation;
import com.blogspot.amrabuelhamd.EgyptTourGuide.PoplatOnList.AswanInformationAdapter;
import com.blogspot.amrabuelhamd.EgyptTourGuide.R;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Utils.MyDataContainer;

import java.util.ArrayList;

/**
 * Created by amr_aboelhamd on 15/02/18.
 */

public class ResturantFragment extends Fragment {
    private ArrayList<AswanInformation> arrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aswan,container,false);

        arrayList = MyDataContainer.getResurant(getActivity().getIntent().getExtras().getInt(
                "position"));

        //find the view then populate data items on it using the adapter
        ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(new AswanInformationAdapter(getActivity(),arrayList));


        //set my contextual menu
        registerForContextMenu(listView);

        //change menu item
        getActivity().invalidateOptionsMenu();
        return rootView;
    }

    //change item title in the menu
//    TODO change menu itme programatically

    /**
     * When the registered view receives a long-click event, the system calls it.
     * @param menu
     * @param v
     * @param menuInfo provides additional information about the item selected.
     *                 if your activity has several views that each provide a different context menu,
     *                 you might use these parameters to determine which context menu to inflate.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    //i must had put if (getUserVisibleHint()) {}return false
    //it prevent context item menu form appearing in all activities
    //as mentioned here
    //https://stackoverflow.com/questions/25096534/context-menu-in-fragment-uses-listview-from-a-different-fragment-registerforcon

    /**
     * When the user selects a menu item, the system calls this
     * method so you can perform the appropriate action.
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            String itemName = getResources().getString(arrayList.get(
                    info.position).getmSightNameResourceId()).replace(' ', '+');

            Intent intent = new Intent(Intent.ACTION_VIEW);
            switch (item.getItemId()) {
                case R.id.get_location_menuItem:
                    //set uri
                    String loc = "geo:0,0?q=" + itemName;
                    Uri m = Uri.parse(loc);
                    intent.setData(m);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                    return true;

                case R.id.search_morePhotos:
                    //set uri
                    Uri webpage = Uri.parse(
                            "https://www.google.com.eg/search?hl=en&source=hp&ei=jsCEWuDdKc3GwQKFkaHYBQ&q=" + itemName+
                                    "+site+tripadvisor.com");
                    intent.setData(webpage);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                    return true;
                case R.id.getMoreInfoMenueItem:
                    //set uri
                    Uri webpage1 = Uri.parse(getString(R.string.rest1link));
                    intent.setData(webpage1);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }
        return false;
    }


}
