package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    String UserMail,userName,userdelivery;
    EditText mailid_text,password_text;
    Button TrackBaby;

    DatePicker datePicker;
    StringBuilder builder2;

    StringBuilder builder;
    StringBuffer builder1;
    float daysBetween;
    public static final String mypreference = "MyPrefs";
    SharedPreferences sharedpreferences;

    String usermail,username,userrole,usernumber,userzip,logindate;
    SimpleDateFormat myFormat;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);

        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add( Calendar.MONTH, -0);

        long minDate = c.getTime().getTime();

        Calendar c1 = Calendar.getInstance();
        c1.add( Calendar.MONTH, +9 );
        long maxDate=c1.getTime().getTime();

        mailid_text=(EditText) findViewById(R.id.input_email);
        password_text=(EditText) findViewById(R.id.input_password);
        datePicker=findViewById(R.id.dpDate);
        TrackBaby=(Button) findViewById(R.id.btn_signup);


        datePicker.setMinDate(minDate);
        datePicker.setMaxDate(maxDate);
        TrackBaby.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){



            case R.id.btn_signup:




                if (mailid_text.getText().toString().length()>0 & password_text.getText().length()>0) {

                    validateMail();
                }
                else {
                    mailid_text.setError("Enter your name");
                    password_text.setError("Enter your password");

                }
                break;

        }
    }




    void validateMail(){

        builder=new StringBuilder();
        builder.append(datePicker.getDayOfMonth() +" ");//month is 0 based
        builder.append((datePicker.getMonth()+1)+" ");
        builder.append(datePicker.getYear());

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        logindate = df.format(date.getTime());

        builder1=new StringBuffer();

        builder1.append((datePicker.getMonth()+1)+"-");
        builder1.append(datePicker.getDayOfMonth() +"-");
        builder1.append(datePicker.getYear());

        builder2 = new StringBuilder();
        if (datePicker.getMonth()+1 <= 9){
            builder2.append("0"+(datePicker.getMonth()+1)+"-");
        }else {
            builder2.append((datePicker.getMonth()+1)+"-");
        }
        if (datePicker.getDayOfMonth() <= 9){
            builder2.append("0"+(datePicker.getDayOfMonth())+"-");
        }else {
            builder2.append((datePicker.getDayOfMonth())+"-");
        }
        builder2.append(datePicker.getYear());



        usermail=mailid_text.getText().toString();
        userName=password_text.getText().toString();





        if (logindate.equals(builder2)){
            Toast.makeText(LoginActivity.this, "Please enter the valid date ",Toast.LENGTH_SHORT).show();



        }else {
            sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("DeliveryDate", String.valueOf(builder));
            editor.putString("UserDelivery", String.valueOf(builder2));

            editor.putString("LoginDate", String.valueOf(logindate));
            editor.putString("UserMail", usermail);
            editor.putString("UserName", userName);


            editor.apply();
            editor.commit();

            Intent inte=new Intent(LoginActivity.this,MainActivity.class);

            startActivity(inte);
        }






    }
    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }



}
