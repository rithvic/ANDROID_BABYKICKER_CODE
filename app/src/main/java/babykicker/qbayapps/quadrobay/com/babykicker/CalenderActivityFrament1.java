package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import az.plainpie.PieView;

import static android.content.Context.MODE_PRIVATE;


public class CalenderActivityFrament1 extends android.app.Fragment implements View.OnClickListener {


    float daysBetween;
    SharedPreferences sharedPreferences;
    String loginDAte;
    String currentDate;
    String deliveryFinal;

    LinearLayout leftclick,rightclick;

    ImageView weekImage;

    String[] arr;
    PieView pieView;

    int currentWeek;


    int[] imageIds = {R.drawable.fourweek,R.drawable.fourweek,R.drawable.fourweek,R.drawable.fourweek, R.drawable.fiveweek, R.drawable.sixweek, R.drawable.sevenweek, R.drawable.sevenweek, R.drawable.nineweek, R.drawable.tenweek, R.drawable.elevenweek, R.drawable.twelveweek, R.drawable.thirteenweek, R.drawable.fourteenweek, R.drawable.fifteenweek, R.drawable.sixteenweek, R.drawable.seventeenweek, R.drawable.seventeenweek, R.drawable.nineteenweek, R.drawable.twentyweek, R.drawable.twentyoneweek, R.drawable.twentytwoweek, R.drawable.twentytwoweek, R.drawable.twentyfourweek, R.drawable.twentyfiveweek, R.drawable.twentysixweek,R.drawable.twentysevenweek,R.drawable.twentyeihtweek,R.drawable.twentynineweek,R.drawable.thirtyweek, R.drawable.thirtyoneweek,R.drawable.thirtytwoweek,R.drawable.thirtythreeweek,R.drawable.thirtyfourweek,R.drawable.thirtyfiveweek,R.drawable.thirtysixweek,R.drawable.thirtysevenweek,R.drawable.thirtyeightweek,R.drawable.thirtynineweek,R.drawable.fourtyweek};

    int finalwee;

    TextView weekshowtext,babytipstext,deliverdatetext,rightext,lefttext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calender_activity_fragment1,   container, false);

        arr = getResources().getStringArray(R.array.week_info);

        weekImage=view.findViewById(R.id.week_image);
        weekshowtext=(TextView)view.findViewById(R.id.weeksShow);
        babytipstext=(TextView)view.findViewById(R.id.babytips);
        deliverdatetext=(TextView) view.findViewById(R.id.deliverydate);

        leftclick=(LinearLayout) view.findViewById(R.id.leftArrow);
        rightclick=(LinearLayout) view.findViewById(R.id.rightarrow);

        rightext=(TextView) view.findViewById(R.id.rithtext);
        lefttext=(TextView) view.findViewById(R.id.lefttext);


        leftclick.setOnClickListener(this);
        rightclick.setOnClickListener(this);

        pieView = (PieView) view.findViewById(R.id.pieView);
        pieView.setMaxPercentage(100);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorAccent));
        pieView.setMainBackgroundColor(getResources().getColor(R.color.cyan));




        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        currentDate = df.format(date.getTime());


        Log.e("dare", currentDate);
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);

//        if (sharedPreferences.contains("currentWeek")){
//            currentWeek = sharedPreferences.getInt("currentWeek",1);
//        }else {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("currentWeek", 0);
//            editor.apply();
//        }




        String deliveryDate1 = sharedPreferences.getString("DeliveryDate1", "");
        String deliveryDate = sharedPreferences.getString("deliverydate", "");
        String duewweek = sharedPreferences.getString("DeliveryDate", "");

        deliveryFinal = sharedPreferences.getString("UserDelivery", "");
        loginDAte = sharedPreferences.getString("LoginDate", "");
        Log.e("date", deliveryDate1);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

        Date date1 = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("dd MM yyyy");
        String currentDate1 = df1.format(date1.getTime());

        try {
            Date dateBefore = myFormat.parse(currentDate1);
            Date dateAfter = myFormat.parse(duewweek);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000 * 60 * 60 * 24));
            Log.e("daye", String.valueOf(daysBetween));
        } catch (Exception e) {
            e.printStackTrace();
        }

        float totaldays = daysBetween - 280;
        float Untilweak = daysBetween / 7;
        float Crossedweak = 40 - Untilweak;

        String Untils = String.valueOf(Untilweak);
        Log.e("weak", String.valueOf(Untilweak));

        String weeksfinal = Untils.split("\\.")[0];
        String daysfinal = Untils.split("\\.")[1];


        int val = Integer.parseInt(daysfinal);
        Log.e("daysa", weeksfinal + "W" + String.valueOf(Math.abs((long) val)).charAt(0) + "D");


        float calculation=40-Integer.parseInt(weeksfinal);
        float sum=calculation/40;


        float finalweeksum=sum*100;



         finalwee= (int) calculation;




        pieView.setPercentage(finalweeksum);
        pieView.setInnerText(finalwee+"\n Weeks");

        rightext.setText(String.valueOf(finalwee));
        lefttext.setText(String.valueOf(40-finalwee));

        currentWeek = finalwee;

        if (currentWeek < 40) {


            weekImage.setImageResource(imageIds[currentWeek]);
            babytipstext.setText(arr[currentWeek]);
            if (currentWeek == 0) {
                leftclick.setVisibility(View.INVISIBLE);
                leftclick.setClickable(false);
                rightext.setText(String.valueOf(currentWeek + 1));
            } else if (currentWeek == 40) {
                rightclick.setVisibility(View.INVISIBLE);
                rightclick.setClickable(false);
                lefttext.setText(String.valueOf(currentWeek - 1));
            } else {
                lefttext.setText(String.valueOf(currentWeek - 1));
                rightext.setText(String.valueOf(currentWeek + 1));
            }
        }
    //    pieView.setPercentage(finalweeksum);

        deliverdatetext.setText(weeksfinal + " Weeks  : " + (String.valueOf(Math.abs((long) val)).charAt(0) + " Days")+"\n" + "Delivery : "+deliveryFinal);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lefttext:

               // pieView.setInnerText((finalwee+1)+"\n Weeks");
                break;

            case R.id.rithtext:

                break;

                case R.id.leftArrow:
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putInt("currentWeek", currentWeek);
//                    editor.apply();

                    if (currentWeek != 0){
                        rightclick.setVisibility(View.VISIBLE);
                        leftclick.setClickable(true);
                        rightclick.setClickable(true);
                        currentWeek--;
                        babytipstext.setText(arr[currentWeek]);
                        rightext.setText(String.valueOf(currentWeek+1));
                        lefttext.setText(String.valueOf(currentWeek));

                    }else {
                        leftclick.setVisibility(View.INVISIBLE);
                        leftclick.setClickable(false);
                        rightext.setText(String.valueOf(currentWeek+1));
                    }
                    weekImage.setImageResource(imageIds[currentWeek]);
                    break;

            case R.id.rightarrow:
                if (currentWeek != 40){
                    leftclick.setVisibility(View.VISIBLE);
                    leftclick.setClickable(true);
                    currentWeek++;
                    babytipstext.setText(arr[currentWeek]);
                    rightext.setText(String.valueOf(currentWeek));
                    lefttext.setText(String.valueOf(currentWeek-1));
                }else {
                    rightclick.setVisibility(View.INVISIBLE);
                    rightclick.setClickable(true);
                    lefttext.setText(String.valueOf(currentWeek-1));
                }

                if (currentWeek != 40){
                    weekImage.setImageResource(imageIds[currentWeek]);
                }

                break;

        }
    }
}


