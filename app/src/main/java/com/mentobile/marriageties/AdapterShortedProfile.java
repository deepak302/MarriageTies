package com.mentobile.marriageties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by user on 11/12/2015.
 */

public class AdapterShortedProfile extends ArrayAdapter<ProfileShorted> {

    private Context context;
    private ArrayList<ProfileShorted> storyArrayList = new ArrayList<>();
    private int resourceID;
    private URI uri;

    public AdapterShortedProfile(Context context, int resource, ArrayList<ProfileShorted> successStories) {
        super(context, resource, successStories);
        this.context = context;
        this.resourceID = resource;
        this.storyArrayList = successStories;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public ProfileShorted getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(ProfileShorted item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecordHolder holder = null;
        View gridView = convertView;

        if (gridView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(resourceID, parent, false);
            holder = new RecordHolder();

            holder.imgStoryPhoto = (ImageView) gridView.findViewById(R.id.row_shorted_img_photo);
            holder.tvProfileID = (TextView) gridView.findViewById(R.id.row_shorted_tv_id);
            holder.tvName = (TextView) gridView.findViewById(R.id.row_shorted_tv_name);
            holder.tvAgeHeightReligion = (TextView) gridView.findViewById(R.id.row_shorted_tv_age_height_religion);
            holder.tvCasteGotraEducation = (TextView) gridView.findViewById(R.id.row_shorted_tv_caste_gotra_education);
            holder.tvCityStateCountry = (TextView) gridView.findViewById(R.id.row_shorted_tv_city_state_country);

            gridView.setTag(holder);
        } else {
            holder = (RecordHolder) gridView.getTag();
        }
        ProfileShorted profileShorted = storyArrayList.get(position);
//        try {
//            URL url = new URL(Application.PATH_IMAGE_FOLDER + projectListItem.getImageName());
//            uri = url.toURI();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Picasso.with(context)
//                .load(uri.toString())
//                .error(R.mipmap.bg)
//                .fit()
//                .into(holder.imgStoryPhoto);

        holder.tvProfileID.setText("" + profileShorted.getId());
        holder.tvName.setText(profileShorted.getName());
        holder.tvAgeHeightReligion.setText(profileShorted.getAge() + ", " + profileShorted.getHeight() + ", " + profileShorted.getReligion());
        holder.tvCasteGotraEducation.setText(profileShorted.getCaste() + "-" + profileShorted.getGotra() + ", " + profileShorted.getEducation());
        holder.tvCityStateCountry.setText(profileShorted.getCity() + ", " + profileShorted.getState() + ", " + profileShorted.getCountry());

        return gridView;
    }

    static class RecordHolder {
        ImageView imgStoryPhoto;
        TextView tvProfileID;
        TextView tvName;
        TextView tvAgeHeightReligion;
        TextView tvCasteGotraEducation;
        TextView tvCityStateCountry;
    }
}
