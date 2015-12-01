package com.mentobile.marriageties;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 11/12/2015.
 */

public class AdapterShortedProfile extends ArrayAdapter<ProfileShorted> implements View.OnClickListener {

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

            holder.imgBtnSendMSG = (ImageButton) gridView.findViewById(R.id.row_shorted_img_sendMSG);
            holder.imgBtnSendMSG.setOnClickListener(this);

            holder.imgBtnShortListed = (ImageButton) gridView.findViewById(R.id.row_shorted_img_shortlist);
            holder.imgBtnShortListed.setOnClickListener(this);

            holder.imgBtnBlock = (ImageButton) gridView.findViewById(R.id.row_shorted_img_block);
            holder.imgBtnBlock.setOnClickListener(this);

            holder.btnSendInterest = (Button) gridView.findViewById(R.id.row_shorted_btn_sendInterest);

            gridView.setTag(holder);
        } else {
            holder = (RecordHolder) gridView.getTag();
        }
        ProfileShorted profileShorted = storyArrayList.get(position);
        try {
            URL url = new URL(Application.URL_PHOTO_BIG + profileShorted.getPhoto());
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(context)
                .load(uri.toString())
                .error(R.mipmap.no_photo)
                .fit()
                .placeholder(R.mipmap.no_photo)
                .into(holder.imgStoryPhoto);

        holder.tvProfileID.setText("" + profileShorted.getId());
        holder.tvName.setText(profileShorted.getName());
        holder.tvAgeHeightReligion.setText(profileShorted.getAge() + ", " + profileShorted.getHeight() + ", " + profileShorted.getReligion());
        holder.tvCasteGotraEducation.setText(profileShorted.getCaste() + "-" + profileShorted.getGotra() + ", " + profileShorted.getEducation());
        holder.tvCityStateCountry.setText(profileShorted.getCity() + ", " + profileShorted.getState() + ", " + profileShorted.getCountry());


        return gridView;
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.aplha);
        v.startAnimation(animation);

        switch (v.getId()) {
            case R.id.row_shorted_img_sendMSG:
                Log.d("AdapterShortedProfile",":::::Send Message");
                break;

            case R.id.row_shorted_img_block:
                Log.d("AdapterShortedProfile",":::::Block");
                break;

            case R.id.row_shorted_img_shortlist:
                Log.d("AdapterShortedProfile",":::::Shortlisted");
                break;

            case R.id.row_shorted_btn_sendInterest:
                Log.d("AdapterShortedProfile",":::::Interset");
                break;

        }
    }

    static class RecordHolder {
        ImageView imgStoryPhoto;
        TextView tvProfileID;
        TextView tvName;
        TextView tvAgeHeightReligion;
        TextView tvCasteGotraEducation;
        TextView tvCityStateCountry;
        ImageButton imgBtnSendMSG;
        ImageButton imgBtnShortListed;
        ImageButton imgBtnBlock;
        Button btnSendInterest;
    }
}
