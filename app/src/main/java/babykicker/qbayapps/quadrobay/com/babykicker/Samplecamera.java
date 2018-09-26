package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Samplecamera extends AppCompatActivity {


    ShowCamera showcam;
    android.hardware.Camera camera;
    FrameLayout mylayout;

    Boolean captured;

    Button captureButton, okButton;

    public static final int MEDIA_TYPE_IMAGE = 1;

    @Override
    public void onCreate(Bundle SavedInstanceSate){
        super.onCreate(SavedInstanceSate);

        captured = false;
        setContentView(R.layout.samplecamera);
camera=switch_oncamera();
        showcam=new ShowCamera(this,camera);
mylayout=(FrameLayout) findViewById(R.id.frame);
mylayout.addView(showcam);


        final Camera.PictureCallback mPicture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, final Camera camera) {

                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    // Log.d(TAG, "Error creating media file, check storage permissions: " +
                    //       e.getMessage());
                    return;
                }

                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                    captured = true;
                    captureButton.setText("Take Again");
                   // camera.startPreview();
                } catch (FileNotFoundException e) {
                    //    Log.d(TAG, "File not found: " + e.getMessage());
                } catch (IOException e) {
                    ///    Log.d(TAG, "Error accessing file: " + e.getMessage());
                }
            }
        };




        captureButton = (Button) findViewById(R.id.capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (captured){
                            captureButton.setText("Capture");
                            camera.startPreview();
                            captured =false;
                        }else {
                            // get an image from the camera
                            camera.takePicture(null, null, mPicture);
                        }


                    }
                }
        );

        okButton = (Button) findViewById(R.id.OkButton);
        okButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        finish();

                    }
                }
        );

    }

    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "babykicker");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("babykicker", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        //if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
      //  } else {
     //       return null;
     //   }

        return mediaFile;
    }
    public android.hardware.Camera switch_oncamera(){
        android.hardware.Camera can_obj=null;

        can_obj= android.hardware.Camera.open();

        android.hardware.Camera.Parameters parameters=can_obj.getParameters();
        if (this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE) {
            parameters.set("orientation", "portrait");

            can_obj.setDisplayOrientation(90);
            parameters.setRotation(90);
        }else{
            parameters.set("orientation","landscape");
            can_obj.setDisplayOrientation(0);
            parameters.setRotation(0);
            }
            return can_obj;
        }

    }

