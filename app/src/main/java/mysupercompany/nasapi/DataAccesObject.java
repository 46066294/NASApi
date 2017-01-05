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
import java.util.List;
import java.util.Set;

/**
 * Created by 46066294p on 14/10/16.
 */

public class DataAccesObject {
    /*
    LLamada general a la API
https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=mast&page=33&api_key=XXXXXXXXX
    */

    private static final int PAGES = 10;
    private static final String API_KEY = "XXXXXXXXXXXX";
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";


    public DataAccesObject() {}

    public static ArrayList<Photo> getPhotos(String roverName, String sol, String camera, String page)  {
        String url = getUrl(roverName, sol, camera, page);
        Log.d("URL: ", url);

        return doCall(url);
    }

    public static String getUrl(String roverName, String sol, String camera, String page){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(roverName)
                .appendPath("photos")
                .appendQueryParameter("sol", sol)
                .appendQueryParameter("camera", camera)
                .appendQueryParameter("page", page)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        return builtUri.toString();
    }


    private static ArrayList<Photo> doCall(String url) {
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            //conexio a a la api
            String JsonResponse = HttpUtils.get(url);
            ArrayList<Photo> list = processJson(JsonResponse);
            photos.addAll(list);
            return processJson(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static ArrayList<Photo> processJson(String jsonResponse) {
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonPhotos = data.getJSONArray("photos");

            for (int i = 0; i < jsonPhotos.length(); i++) {
                JSONObject jsonOnePhoto = jsonPhotos.getJSONObject(i);
                JSONArray jsonRover = jsonOnePhoto.getJSONArray("rover");
                JSONArray jsonCamera = jsonOnePhoto.getJSONArray("camera");

                Photo photo = new Photo();
                //generic
                photo.setId(jsonOnePhoto.getInt("id"));
                photo.setSol(jsonOnePhoto.getInt("sol"));
                photo.setPage(jsonOnePhoto.getInt("page"));
                photo.setImageUrl(jsonOnePhoto.getString("img_src"));

                //"rover":{}
                for(int j = 0; j < jsonRover.length(); j++){
                    photo.setTotalPhotos(jsonRover.getInt(Integer.parseInt("total_photos")));
                    photo.setMaxSol(jsonRover.getInt(Integer.parseInt("max_sol")));
                    photo.setMaxDate(jsonRover.getString(Integer.parseInt("max_date")));
                    photo.setStatus(jsonRover.getString(Integer.parseInt("status")));
                    photo.setRoverName(jsonRover.getString(Integer.parseInt("name")));
                    photo.setLaunchDate(jsonRover.getString(Integer.parseInt("launch_date")));
                    photo.setLandingDate(jsonRover.getString(Integer.parseInt("landing_date")));
                }

                //"camera":{}
                for(int j = 0; j < jsonCamera.length(); j++){
                    photo.setRoverCam(jsonCamera.getString(Integer.parseInt("name")));
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


/*
public static String getUrl(Set<String> colors, String rarity) {

        Log.d("getURL-colorSet" , colors.toString());
        Log.d("getURL-rarity" , rarity);

        List<String> colorList = new ArrayList<String>(colors);
        int size = colorList.size();

        Uri builtUri = Uri.parse(BASE_URL);

        if(colorList.get(0).equals("no_color")){
            builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("rarity", rarity)
                    //.appendQueryParameter("page", String.valueOf(page))
                    .build();
            return builtUri.toString();
        }

        switch (size) {
            case 1:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 2:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 3:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 4:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 5:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        .appendQueryParameter("colors", colorList.get(4))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;
        }

        return builtUri.toString();
    }

*/