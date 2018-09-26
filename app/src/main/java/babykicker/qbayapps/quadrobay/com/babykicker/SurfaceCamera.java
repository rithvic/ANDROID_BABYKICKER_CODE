package babykicker.qbayapps.quadrobay.com.babykicker;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SurfaceCamera extends Activity implements SurfaceHolder.Callback{

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;;

    String stringPath = "/sdcard/samplevideo.3gp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_surface_layout);

        Button buttonStartCameraPreview = (Button)findViewById(R.id.startcamerapreview);
        Button buttonStopCameraPreview = (Button)findViewById(R.id.stopcamerapreview);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        buttonStartCameraPreview.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!previewing){
              //      camera = Camera.open();

                    try {
                        if (camera != null) {
                            camera.release();
                            camera = null;
                        }
                       // if (camId == 0) {
                           // camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                       // }
                       // else {
                            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                       // }
                    } catch (Exception e) {
                       // Log.e(getString(R.string.app_name), "failed to open Camera");
                        e.printStackTrace();
                    }

                    if (camera != null){
                        try {
                            camera.setPreviewDisplay(surfaceHolder);
                            camera.startPreview();
                            previewing = true;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }});

        buttonStopCameraPreview.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(camera != null && previewing){
                    camera.stopPreview();
                    camera.release();
                    camera = null;

                    previewing = false;
                }
            }});

    }

    private void releaseCameraAndPreview() {
      //  myCameraPreview.setCamera(null);
       // if (mCamera != null) {
        //    mCamera.release();
        //    mCamera = null;
      //  }
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }
}
