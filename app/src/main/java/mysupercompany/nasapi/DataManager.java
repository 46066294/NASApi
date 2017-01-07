package mysupercompany.nasapi;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Mat on 07/01/2017.
 */

public class DataManager {
    private static UriHelper URI_HELPER = UriHelper.with(NasApiContentProvider.AUTHORITY);
    private static Uri PHOTO_URI = URI_HELPER.getUri(Photo.class);

    static void savePhotos(ArrayList<Photo> photos, Context context) {
        cupboard().withContext(context).put(PHOTO_URI, Photo.class, photos);
    }

    static void deletePhotos(Context context) {
        cupboard().withContext(context).delete(PHOTO_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context){
        return new CursorLoader(context, PHOTO_URI, null, null, null, null);
    }

}
