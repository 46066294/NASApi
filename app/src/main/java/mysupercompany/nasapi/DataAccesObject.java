package mysupercompany.nasapi;

/**
 * Created by Mat on 04/01/2017.
 */

import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by 46066294p on 14/10/16.
 */

public class DataAccesObject {
    /*
    LLamada general a la API
https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=mast&page=33&api_key=XXXXXXXXX
    */

    //private static final int PAGES = 1;
    private static final String API_KEY = "5tYEBLjPGEoVxuzwZ7dNIoWMtnmCPpPo8Mk7WRen";
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    public DataAccesObject() {}

    public static ArrayList<Photo> getPhotos(String roverName, Integer sol, String camera, Integer page)  {
        String url = getUrl(roverName, sol, camera, page);
        Log.d("URL: ", url);

        return doCall(url);
    }

    public static String getUrl(String roverName, Integer sol, String camera, Integer page){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(roverName)
                .appendPath("photos")
                .appendQueryParameter("sol", String.valueOf(sol))
                .appendQueryParameter("camera", camera)
                .appendQueryParameter("page", String.valueOf(page))
                .appendQueryParameter("api_key", API_KEY)
                .build();

        return builtUri.toString();
    }


    private static ArrayList<Photo> doCall(String url) {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            //conexio a la api
            String jsonResponse = HttpUtils.get(url);
            ArrayList<Photo> list = processJson(jsonResponse);
            photos.addAll(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return photos;
    }


    private static ArrayList<Photo> processJson(String jsonResponse) {
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonPhotos = data.getJSONArray("photos");

            for (int i = 0; i < jsonPhotos.length(); i++) {
                JSONObject jsonOnePhoto = jsonPhotos.getJSONObject(i);
                JSONObject jsonRover = jsonOnePhoto.getJSONObject("rover");
                JSONObject jsonCamera = jsonOnePhoto.getJSONObject("camera");

                Photo photo = new Photo();
                photo.setRoverId(jsonOnePhoto.getInt("id"));
                photo.setSol(jsonOnePhoto.getInt("sol"));
                //photo.setPage(jsonOnePhoto.getInt("page"));
                photo.setImageUrl(jsonOnePhoto.getString("img_src"));

                //"rover":{}
                for(int j = 0; j < jsonRover.length(); j++){
                    photo.setTotalPhotos(jsonRover.getInt("total_photos"));
                    photo.setMaxSol(jsonRover.getInt("max_sol"));
                    photo.setMaxDate(jsonRover.getString("max_date"));
                    photo.setStatus(jsonRover.getString("status"));
                    photo.setRoverName(jsonRover.getString("name"));
                    photo.setLaunchDate(jsonRover.getString("launch_date"));
                    photo.setLandingDate(jsonRover.getString("landing_date"));
                }

                //"camera":{}
                for(int j = 0; j < jsonCamera.length(); j++){
                    photo.setRoverCam(jsonCamera.getString("name"));
                }

                photos.add(photo);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return photos;
    }

}// DataAccesObject class