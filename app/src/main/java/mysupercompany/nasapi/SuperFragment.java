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
    private OnFragmentInteractionListener mListener;

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

        ListView lvPhoto = (ListView) view.findViewById(R.id.lvPhoto);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.fragment_super,
                R.id.lv_photos,
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





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
