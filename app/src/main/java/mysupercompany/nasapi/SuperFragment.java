package mysupercompany.nasapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.util.ArrayList;

import mysupercompany.nasapi.databinding.FragmentSuperBinding;
//import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;



public class SuperFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ArrayList<Photo> items;
    private PhotoCursorAdapter adapter;
    private ProgressDialog dialog;
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

        FragmentSuperBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_super, container, false);

        View view = binding.getRoot();

        adapter = new PhotoCursorAdapter(getContext(), Photo.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading Mars Photos...");

        //lvPhoto.setAdapter(adapter);
        binding.lvPhotos.setAdapter(adapter);
        //lvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        binding.lvPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Photo photo = (Photo) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("photo", photo);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(0, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    private class FilterDataTask extends AsyncTask<Void, Void, Void> {
        /*
        @Override
        protected void onPostExecute(ArrayList<Photo> photos) {
            adapter.clear();
            for (Photo photo : photos){
                adapter.add(photo);
            }
        }
        */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String roverCar = preferences.getString("select_rover", "curiosity");
            Integer sol = Integer.valueOf(preferences.getString("input_sol", "1000"));
            String camera = preferences.getString("select_camera", "navcam");
            Integer page = Integer.valueOf(preferences.getString("input_page", "1"));

            Log.d("DEBUG", "Starting...");
            //crida http a la Api
            ArrayList<Photo> result = checkFields(roverCar, sol, camera, page);

            //Log.d("DEBUG", result != null ? result.toString() : null);

            if(result == null){
                return null;
            }
            else{
                DataManager.deletePhotos(getContext());
                DataManager.savePhotos(result, getContext());
            }

            return null;
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
