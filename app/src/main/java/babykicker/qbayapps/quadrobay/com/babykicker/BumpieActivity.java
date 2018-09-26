package babykicker.qbayapps.quadrobay.com.babykicker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;

public class BumpieActivity extends AppCompatActivity implements View.OnClickListener,VieewOnclickListner {

    ImageView back;

    RecyclerView bumpieListView;
    RelativeLayout warninView;
    VieewOnclickListner click;
    ImageView videorender;

    MyAdapter adapter;
    ProgressDialog dialog;
    //FFmpegFrameRecorder recorder;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PERMISSION_REQUEST = 100;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;

    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    Context context;

    File mediaStorageDir;

    String[] imageList, videolist;

    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};



    String movinginter=null;


    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;


    LinearLayout bumpieimage, videoprocess;
    ImageView fullScreenContainer;
    LinearLayout viewfull;
    ImageView shareImae;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.layout_bumpie_activity);

        context = this;
        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);

        bumpieListView = findViewById(R.id.bumpie_list);
        warninView = findViewById(R.id.warning_view);
        back = (ImageView) findViewById(R.id.backbutton1);
        viewfull=(LinearLayout) findViewById(R.id.linearv);
         fullScreenContainer = (ImageView) findViewById(R.id.full_screen_container);
        bumpieimage = (LinearLayout) findViewById(R.id.imageProcess);
        videoprocess = (LinearLayout) findViewById(R.id.videoProcess);
        shareImae=(ImageView) findViewById(R.id.shareView);
        videorender=(ImageView) findViewById(R.id.videorender);
        videoprocess.setOnClickListener(this);

        bumpieimage.setOnClickListener(this);
        movinginter="main";

        back.setOnClickListener(this);
        //checkPermissionAndProceed();

        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "babykicker");


        if (mediaStorageDir != null) {
            loadImages();
        }


    }

    public void loadImages() {
        if (mediaStorageDir.exists()) {
            imageList = mediaStorageDir.list();
            if (imageList != null) {

                if (imageList.length == 0) {
                    warninView.setVisibility(View.VISIBLE);
                    bumpieListView.setVisibility(View.GONE);
                } else {
                    warninView.setVisibility(View.GONE);
                    bumpieListView.setVisibility(View.VISIBLE);
                    int numberOfColumns = 2;
                    bumpieListView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
                    adapter = new MyAdapter(this, imageList,this);
                    bumpieListView.setAdapter(adapter);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mediaStorageDir != null) {
            loadImages();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backbutton1:
                onBackPressed();
                break;


            case R.id.imageProcess:

                checkPermissionAndProceed();
                break;

            case R.id.videoProcess:
                myprocess();
                break;
        }
    }

    private void myprocess() {

        AnimationDrawable animation = animation = new AnimationDrawable();


//        animation = new AnimationDrawable();
//        animation.addFrame(getResources().getDrawable(R.drawable.ball1), 100);
//        animation.addFrame(getResources().getDrawable(R.drawable.ball2), 1000);
//        animation.addFrame(getResources().getDrawable(R.drawable.ball3), 1000);
//        animation.setOneShot(false);

        if (imageList != null) {
            for (String imagePath : imageList) {

                Log.e("", imagePath);
                animation.addFrame(
                        Drawable.createFromPath(mediaStorageDir.getPath() + '/' + imagePath),
                        400);
            }

            movinginter = "full";
            shareImae.setVisibility(View.INVISIBLE);
            bumpieListView.setVisibility(View.GONE);
            viewfull.setVisibility(View.GONE);
            fullScreenContainer.setVisibility(View.GONE);

            videorender.setBackground(animation);
            videorender.setVisibility(View.VISIBLE);
            animation.start();

        }
    }


//    public void videoConvertinProcess() {
//        File folder = Environment.getExternalStorageDirectory();
//        String path = folder.getAbsolutePath() + "/DCIM/Camera";
//        long millis = System.currentTimeMillis();
//     String   videoPath = path + "/" + "test_sham_"+millis+".3gp";
//
//
//
//        if (mediaStorageDir.exists()) {
//            videolist = mediaStorageDir.list();
//
//            FrameGrabber grabber2 = new FFmpegFrameGrabber((Environment.DIRECTORY_PICTURES)+"babykicker");
//
//            FFmpegFrameRecorder recorder=new FFmpegFrameRecorder(path+ "/" + "test"+millis+".3gp",  grabber2.getImageWidth(),2);
//
//            try {
//                grabber2.start();
//                recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
//                recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
//                recorder.setFormat("Mp4");
//                recorder.setFrameRate(25);
//                recorder.setSampleRate(grabber2.getSampleRate());
//                recorder.setVideoBitrate(30);
//
//                org.bytedeco.javacv.Frame frame1= null;
//                while ((frame1 = grabber2.grabFrame()) != null){
//
//
//                    recorder.record(frame1);
//
//                }
//
//            } catch (FrameGrabber.Exception e) {
//                e.printStackTrace();
//            } catch (FrameRecorder.Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//    }

    public void checkPermissionAndProceed() {
        if (ActivityCompat.checkSelfPermission(BumpieActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(BumpieActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            //   if (ActivityCompat.shouldShowRequestPermissionRationale(BumpieActivity.this, PERMISSIONS[0]) || ActivityCompat.shouldShowRequestPermissionRationale(BumpieActivity.this, PERMISSIONS[1])) {
            ActivityCompat.requestPermissions(BumpieActivity.this, PERMISSIONS, PERMISSION_CALLBACK_CONSTANT);
            //   }

        } else {
            Intent intent11 = new Intent(BumpieActivity.this, Samplecamera.class);
            startActivity(intent11);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted
            boolean allgranted = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if (allgranted) {
                checkPermissionAndProceed();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(BumpieActivity.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs phone state and Location permissions.");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(BumpieActivity.this, PERMISSIONS, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }

        }
    }

    @Override
    public void onClickMethod(View v, final int position) {

        movinginter="full";
        shareImae.setVisibility(View.VISIBLE);
        bumpieListView.setVisibility(View.GONE);
        viewfull.setVisibility(View.GONE);
        fullScreenContainer.setVisibility(View.VISIBLE);
        //Bitmap bitmap = BitmapFactory.decodeResource(imageList[position]);

        //   fullScreenContainer.setImageDrawable(Drawable.createFromPath(imageList[position]));


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        // Calculate inSampleSize
        //  options.inSampleSize = calculateInSampleSize(options, 50, 50);
        options.inSampleSize = 2;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        final Bitmap bitmap = BitmapFactory.decodeFile(mediaStorageDir.getPath() + "/" + imageList[position], options);
        fullScreenContainer.setImageBitmap(bitmap);
//        shareImae.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri uri=null;
//
//                Intent share = new Intent(Intent.ACTION_SEND);
//                share.setType("image/*");
//
//                movinginter="main";
//                File file=new File(mediaStorageDir.getPath() + "/" + imageList[position]);
//
//               // Bitmap bitmap = BitmapFactory.decodeFile(mediaStorageDir.getPath() + "/" + imageList[position], options);
//
//                try {
//                    FileOutputStream stream=new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.PNG,99,stream);
//
//                    stream.close();
//                     uri=Uri.fromFile(file);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//                share.putExtra(Intent.EXTRA_STREAM, uri);
//                startActivity(Intent.createChooser(share, "Share Image!"));
//
//            }
//        });

        fullScreenContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView view = (ImageView) v;
                view.bringToFront();
                viewTransformation(view, event);
                return true;
            }
        });



    }
    private void viewTransformation(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                }

                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
                isZoomAndRotate = false;
                if (mode == DRAG) {
                    float x = event.getX();
                    float y = event.getY();
                }

            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        isZoomAndRotate = false;
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                    if (mode == ZOOM && event.getPointerCount() == 2) {
                        float newDist1 = spacing(event);
                        if (newDist1 > 10f) {
                            float scale = newDist1 / oldDist * view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                        if (lastEvent != null) {
                            newRot = rotation(event);
                            view.setRotation((float) (view.getRotation() + (newRot - d)));
                        }
                    }
                }
                break;
        }
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (int) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }



//    @Override
//    public void clickmethod(View v, int position) {
//
//        viewfull.setVisibility(View.GONE);
//        //Bitmap bitmap = BitmapFactory.decodeResource(imageList[position]);
//
//     //   fullScreenContainer.setImageDrawable(Drawable.createFromPath(imageList[position]));
//
//
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//        // Calculate inSampleSize
//        //  options.inSampleSize = calculateInSampleSize(options, 50, 50);
//        options.inSampleSize = 2;
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeFile(mediaStorageDir.getPath() + "/" + imageList[position], options);
//        fullScreenContainer.setImageBitmap(bitmap);

//
//    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        VieewOnclickListner clicklistener;

        Context context;

        String[] imageNames;


        MyAdapter(Context context, String[] imageList,VieewOnclickListner clicklistener) {


            this.context = context;

            this.imageNames = imageList;
            this.clicklistener=clicklistener;



        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bumpieimagesgrid, parent, false);
            MyAdapter.ViewHolder viewa = new MyAdapter.ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {


            Log.e("", mediaStorageDir.getPath() + "/" + imageNames[position]);

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            // Calculate inSampleSize
            //  options.inSampleSize = calculateInSampleSize(options, 50, 50);
            options.inSampleSize = 2;

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(mediaStorageDir.getPath() + "/" + imageNames[position], options);
            holder.bumpieImage.setImageBitmap(bitmap);


            //  Picasso.get().load(mediaStorageDir.getPath()+"/"+imageNames[position]).into(holder.bumpieImage);

        }

        @Override
        public int getItemCount() {

            return imageList.length;
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            ImageView bumpieImage;

            public ViewHolder(View itemView) {
                super(itemView);


                bumpieImage = itemView.findViewById(R.id.bumpieImage);

                bumpieImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        clicklistener.onClickMethod(v,getAdapterPosition());

                    }
                });
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize ;
    }
    @Override
    public void onBackPressed() {

        if (movinginter.equals("main")) {
            movinginter="full";
           super.onBackPressed();

        } else if (movinginter.equals("full")){

            movinginter="main";
            bumpieListView.setVisibility(View.VISIBLE);
            viewfull.setVisibility(View.VISIBLE);
            fullScreenContainer.setVisibility(View.GONE);
            videorender.setVisibility(View.GONE);

        }


    }

}
interface VieewOnclickListner{

    public void   onClickMethod(View v,int position);

}
