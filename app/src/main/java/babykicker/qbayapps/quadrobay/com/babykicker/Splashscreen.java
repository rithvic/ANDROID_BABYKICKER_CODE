package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.splash_screen_layout);

        sharedPreferences= getSharedPreferences("MyPrefs", MODE_PRIVATE);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                String username = sharedPreferences.getString("UserName","");


                if (username.toString().length()==0) {


                    Intent intent = new Intent(Splashscreen.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent1 = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(intent1);

                }
                finish();
            }

        }, SPLASH_TIME_OUT);

    }
}