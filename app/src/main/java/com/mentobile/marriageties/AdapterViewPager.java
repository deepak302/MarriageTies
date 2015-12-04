package com.mentobile.marriageties;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 12/1/2015.
 */

public class AdapterViewPager extends PagerAdapter {

    private final Activity activity;
    private ArrayList<String> arrayList = new ArrayList<>();
    private URI uri;

    public AdapterViewPager(Activity activity, ArrayList<String> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View view = null;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_view_pager, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.viewpager_image);
//        imageView.setBackgroundResource(arrayList.get(position));

        try {
            URL url = new URL(Application.URL_PHOTO_BIG + arrayList.get(position));
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(activity.getApplicationContext())
                .load(uri.toString())
                .error(R.mipmap.no_banner_photo)
                .fit()
                .placeholder(R.mipmap.no_banner_photo)
                .into(imageView);

//        TextView textView = (TextView) view.findViewById(R.id.viewpager_image_counter);
//        textView.setText((position + 1) + "/" + getCount());

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
