package com.mentobile.marriageties;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment_RegComplete extends Fragment implements View.OnClickListener {

    private ImageView imgProfile;
    private CheckBox chkTermsCondition;
    private Button btnUpload;
    private Button btnRegister;

    private CProgressDialog cProgressDialog;

    public Fragment_RegComplete() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regcomplete, container, false);
        imgProfile = (ImageView) view.findViewById(R.id.register_comp_img_upload);
        imgProfile.setOnClickListener(this);
        chkTermsCondition = (CheckBox) view.findViewById(R.id.register_comp_chkbox_condition);
        chkTermsCondition.setOnClickListener(this);
        btnRegister = (Button) view.findViewById(R.id.register_comp_btn_register);
        btnRegister.setOnClickListener(this);
        btnUpload = (Button) view.findViewById(R.id.register_comp_btn_upload);
        btnUpload.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_comp_img_upload: // Select Picture
                openGalleryImage();
                break;

            case R.id.register_comp_btn_upload:// Upload Image

                break;
            case R.id.register_comp_btn_register: // Register User Profile
                if (chkTermsCondition.isChecked()) {
                    MyAsynchTask myAsynchTask = new MyAsynchTask();
                    myAsynchTask.execute();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_chk_condition), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openGalleryImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imgProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            String fileNameSegments[] = picturePath.split("/");
            String fileName = fileNameSegments[fileNameSegments.length - 1];
            // Put file name in Async Http Post Param which will used in Php web app
//            nam.put("filename", fileName);
            cursor.close();
        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyAsynchTask extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cProgressDialog = new CProgressDialog(getActivity());
            cProgressDialog.setMessage("Register is processing...");
            cProgressDialog.setCanceledOnTouchOutside(false);
            cProgressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            WebService webService = new WebService();
            JSONObject jsonObject = webService.makeHttpRequest(Application.URL_REGISTRATION, FragmentProfile.valuePairsRegister);
            Log.d("Fragment", ":::::Json Data " + jsonObject.toString());
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("city");
                int resCode = jsonArray.getInt(0);
                String resMsg = jsonArray.getString(1);

                if (resCode == 1) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("User Registration");
                    builder.setMessage("" + resMsg);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(getActivity(), "" + resMsg, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cProgressDialog.hide();
        }
    }
}
