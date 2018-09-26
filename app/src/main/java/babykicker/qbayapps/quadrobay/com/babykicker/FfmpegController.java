package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class FfmpegController {

    private static Context mContext;
    private static Utility mUtility;
    private static String mFfmpegBinaryPath;
    File mediaStorageDir;
    String[] imageList;
    public FfmpegController(Context context) {

        mContext = context;

        mUtility = new Utility(context);

        initFfmpeg();
    }

    private void initFfmpeg()
    {


        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "babykicker");
        imageList = mediaStorageDir.list();


        mFfmpegBinaryPath = mContext.getApplicationContext().getFilesDir().getAbsolutePath() + "/ffmpeg";

        if (Utility.isFileExsisted(mFfmpegBinaryPath))
            return;

       InputStream inputStream = null;
//
// = mContext.getResources().openRawResource(R.id.ffmpeg);

        mUtility.saveFileToAppInternalStorage(inputStream, "ffmpeg");

        Utility.excuteCommand(CommandHelper.commandChangeFilePermissionForExecuting(mFfmpegBinaryPath));
    }

    public void convertImageToVideo(String inputImgPath)
    {
        /*
        Delete previous video.
         */

        Log.e("Image Parth", "inputImgPath - "+inputImgPath);

        if (Utility.isFileExsisted(pathOuputVideo()))
            Utility.deleteFileAtPath(pathOuputVideo());

        /*
        Save the command into a shell script.
         */

        saveShellCommandImg2VideoToAppDir(inputImgPath);

        Utility.excuteCommand("sh" + " " + pathShellScriptImg2Video());
    }

    public String pathOuputVideo()
    {
        return mUtility.getPathOfAppInternalStorage() + "/out.mp4";
    }

    private String pathShellScriptImg2Video()
    {
        return mUtility.getPathOfAppInternalStorage() + "/img2video.sh";
    }

    private void saveShellCommandImg2VideoToAppDir(String inputImgPath)
    {
        String command = CommandHelper.commandConvertImgToVideo(mFfmpegBinaryPath, inputImgPath, pathOuputVideo());

        InputStream is = new ByteArrayInputStream(command.getBytes());

        mUtility.saveFileToAppInternalStorage(is, "img2video.sh");
    }
}