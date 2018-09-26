package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {




    private Camera mycam;
    private  SurfaceHolder holder;




    public ShowCamera(Context context, Camera camera) {
        super(context);


        mycam=camera;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            mycam.setPreviewDisplay(holder);
            mycam.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            mycam.stopPreview();
           // mycam.release();

    }
}
