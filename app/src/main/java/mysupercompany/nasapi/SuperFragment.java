package mysupercompany.nasapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private ArrayList<Photo> items;
    private PhotoAdapter adapter;
    static Integer maxSolCuriosity = 1570;
    static Integer maxSolOpportunity = 4603;
    static Integer maxSolSpirit = 2208;

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
        adapter = new PhotoAdapter(
                getContext(),
                R.layout.lv_photos_row,
                //R.id.rover,
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
                adapter.add(photo);
            }
        }

        @Override
        protected ArrayList<Photo> doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String roverCar = preferences.getString("select_rover", "curiosity");
            Integer sol = Integer.valueOf(preferences.getString("input_sol", "1000"));
            String camera = preferences.getString("select_camera", "navcam");
            Integer page = Integer.valueOf(preferences.getString("input_page", "1"));


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
            Log.d("DEBUG", "Starting...");
            ArrayList<Photo> result = checkFields(roverCar, sol, camera, page);

            //DataManager.deleteCards(getContext());
            //DataManager.saveCards(result, getContext());

            return result;
        }

        public ArrayList<Photo> checkFields(String roverCar, Integer sol, String camera, Integer page){

            if(((roverCar.equalsIgnoreCase("Curiosity") &&
                    (camera.equalsIgnoreCase("FHAZ") || camera.equalsIgnoreCase("RHAZ") ||
                            camera.equalsIgnoreCase("MAST") || camera.equalsIgnoreCase("CHEMCAM") ||
                            camera.equalsIgnoreCase("MAHLI") || camera.equalsIgnoreCase("MARDI") ||
                            camera.equalsIgnoreCase("NAVCAM")) && (sol <= maxSolCuriosity) && sol >= 0)) ||
                    ((roverCar.equalsIgnoreCase("Opportunity") || roverCar.equalsIgnoreCase("Spirit"))  &&
                            (camera.equalsIgnoreCase("FHAZ") || camera.equalsIgnoreCase("RHAZ") ||
                            camera.equalsIgnoreCase("NAVCAM") || camera.equalsIgnoreCase("PANCAM") ||
                            camera.equalsIgnoreCase("MINITES")) && (((sol <= maxSolOpportunity) && sol >= 0) ||
                            (sol <= maxSolSpirit) && sol >= 0))){

                ArrayList<Photo> result = DataAccesObject.getPhotos(roverCar, sol, camera, page);
                Log.d("DEBUG", result != null ? result.toString() : null);
                return result;
            }
            else return DataAccesObject.getPhotos("curiosity", 1000, "MAST", 1);
        }//checkFields end

    }
}
