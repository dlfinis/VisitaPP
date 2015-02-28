package com.fisei.visitapp.app;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class VisitaSistemasActivity extends ActionBarActivity implements View.OnClickListener {


    Button btnTakePhoto;
    Button btnSavePhoto;
    ImageView imageView;
    Intent intent;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita_sistemas);

        btnTakePhoto= (Button) findViewById(R.id.btnVTakePhoto);
        btnSavePhoto= (Button) findViewById(R.id.btnVSavePhoto);
        imageView=(ImageView) findViewById(R.id.imageView);

        btnTakePhoto.setOnClickListener(this);
        btnSavePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id;
        id=view.getId();

        switch(id)
        {
            case R.id.btnVTakePhoto:

                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
                startActivityForResult(intent, 0);

                break;
            case R.id.btnVSavePhoto:
//                // create intent with ACTION_IMAGE_CAPTURE action
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                // this part to save captured image on provided path
//                File file = new File(Environment.getExternalStorageDirectory(),
//                        Date".jpg");
//                Uri photoPath = Uri.fromFile(file);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
//
//                // start camera activity
//                startActivityForResult(intent, TAKE_PICTURE);

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
        {
            Bundle ext = data.getExtras();
            image=(Bitmap)ext.get("data");
            imageView.setImageBitmap(image);



            Log.e("Info", intent.getStringExtra(MediaStore.EXTRA_OUTPUT));

        }

    }

}
