package babykicker.qbayapps.quadrobay.com.babykicker;

import android.util.Log;

public class CommandHelper {
    public static String commandConvertImgToVideo(String ffmpegBinaryPath, String inputImgPath, String outputVideoPath) {
        Log.e("ffmpegBinaryPath", "ffmpegBinaryPath - "+ffmpegBinaryPath);
        Log.e("inputImgPath", "inputImgPath - "+inputImgPath);
        Log.e("outputVideoPath", "outputVideoPath - "+outputVideoPath);

        return ffmpegBinaryPath + " -r 1/1 -i " + inputImgPath + " -c:v libx264 -crf 23 -pix_fmt yuv420p -s 640x480 " + outputVideoPath;
    }

    public static String commandChangeFilePermissionForExecuting(String filePath) {
        return "chmod 777 " + filePath;
    }
}