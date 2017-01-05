package mysupercompany.nasapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Mat on 05/01/2017.
 */

public class PhotoAdapter extends ArrayAdapter<Photo>{

    public PhotoAdapter(Context context, int resource, List<Photo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_photos_row, parent, false);
        }

        TextView tvRoverName = (TextView) convertView.findViewById(R.id.roverName);
        TextView tvRoverId = (TextView) convertView.findViewById(R.id.roverId);
        TextView tvSol = (TextView) convertView.findViewById(R.id.sol);
        TextView tvCamera = (TextView) convertView.findViewById(R.id.camera);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.status);
        TextView tvLaunchDate = (TextView) convertView.findViewById(R.id.launchDate);
        TextView tvLandingDate = (TextView) convertView.findViewById(R.id.landingDate);
        TextView tvMaxDate = (TextView) convertView.findViewById(R.id.maxDate);
        TextView tvMaxSol = (TextView) convertView.findViewById(R.id.maxSol);
        TextView tvPage = (TextView) convertView.findViewById(R.id.page);
        TextView tvTotalPhotos = (TextView) convertView.findViewById(R.id.totalPhotos);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        tvRoverName.setText(photo.getRoverName());
        tvSol.setText(photo.getSol().toString());
        tvRoverId.setText(photo.getRoverId().toString());
        tvCamera.setText(photo.getRoverCam());
        tvStatus.setText(photo.getStatus());
        tvLaunchDate.setText(photo.getLaunchDate());
        tvLandingDate.setText(photo.getLandingDate());
        tvMaxDate.setText(photo.getMaxDate());
        tvMaxSol.setText(photo.getMaxSol().toString());
        //tvPage.setText(photo.getPage().toString());
        tvTotalPhotos.setText(photo.getTotalPhotos().toString());

        Glide.with(getContext()).load(photo.getImageUrl()).into(imageView);

        return convertView;
    }

}
