package mysupercompany.nasapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class SuperFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public SuperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Intent i = getActivity().getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_super, container, false);
        ListView lvPhoto = (ListView) view.findViewById(R.id.lv_photos);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_photos_row,
                R.id.rover,
                items
        );
        lvPhoto.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Settings
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void downloadPhotos() {
        SuperFragment.FilterDataTask task = new SuperFragment.FilterDataTask();
        task.execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("DEBUG","onStart");
        downloadPhotos();
    }

    private class FilterDataTask extends AsyncTask<Void, Void, ArrayList<Photo>> {

        @Override
        protected void onPostExecute(ArrayList<Photo> photos) {
            adapter.clear();
            for (Photo photo : photos){
                adapter.add(photo.getRoverName());
            }
        }

        @Override
        protected ArrayList<Photo> doInBackground(Void... voids) {

            //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            //Set<String> selections = preferences.getStringSet("multi_select_list_preference_1" , null);
            //String[] selectedColor = selections.toArray(new String[] {});

            /*
            for (int i = 0; i < selectedColor.length ; i++){
                System.out.println("\ntestColor" + i +" : " + selectedColor[i]);
            }

            String selectedRarity = preferences.getString("list_preference_1" , null);
            System.out.println("\ntestRariry : " + selectedRarity);
            */

            //DataAccesObject dao = new DataAccesObject();
            Log.d("DEBUG", "AAAAAAAAAAAAAAAAAAAA");
            ArrayList<Photo> result = DataAccesObject.getPhotos("curiosity", "1000", "navcam", "10");

            Log.d("DEBUG", result != null ? result.toString() : null);

            //DataManager.deleteCards(getContext());
            //DataManager.saveCards(result, getContext());

            return result;
        }
    }
}