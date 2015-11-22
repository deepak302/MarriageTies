package com.mentobile.marriageties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
public class AdapterSuccessStory extends ArrayAdapter<SuccessStory> {

    private Context context;
    private ArrayList<SuccessStory> storyArrayList = new ArrayList<>();
    private int resourceID;
    private URI uri;

    public AdapterSuccessStory(Context context, int resource, ArrayList<SuccessStory> successStories) {
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
    public SuccessStory getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(SuccessStory item) {
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

            holder.tvBrideName = (TextView) gridView.findViewById(R.id.row_story_tv_bride_name);
            holder.tvGroomName = (TextView) gridView.findViewById(R.id.row_story_tv_groom_name);
            holder.imgStoryPhoto = (ImageView) gridView.findViewById(R.id.row_story_img_photo);
            holder.tvWeddingDate = (TextView) gridView.findViewById(R.id.row_story_tv_wedding_date);
            holder.tvYourStory = (TextView) gridView.findViewById(R.id.row_story_tv_our_story_data);

            gridView.setTag(holder);
        } else {
            holder = (RecordHolder) gridView.getTag();
        }
        SuccessStory successStory = storyArrayList.get(position);
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

        holder.tvBrideName.setText(successStory.getBrideName());
        holder.tvGroomName.setText(successStory.getGroomName());
        holder.tvWeddingDate.setText(successStory.getWeddingDate());
        holder.tvYourStory.setText(successStory.getYourExperience());

        return gridView;
    }

    static class RecordHolder {
        TextView tvBrideName;
        TextView tvGroomName;
        ImageView imgStoryPhoto;
        TextView tvWeddingDate;
        TextView tvYourStory;
    }
}
