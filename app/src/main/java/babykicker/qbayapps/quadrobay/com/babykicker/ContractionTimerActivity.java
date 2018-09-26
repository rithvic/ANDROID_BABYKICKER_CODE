package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ContractionTimerActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView recyclerView;

    JSONArray json=new JSONArray();
    TextView hourstext,minutestext,secondstext,contractionsecondstext;
    Button contractionaction;
    MyAdapter myAdapt;
    String frequency;
    int count=1;
    String startandstoptime;
    String timeApart;
    ImageView backButton;

    String laststarttime,laststoptime;


    int temp = 0;
    String StartTime,StopTime;

    Boolean contractionStart = true;

    Timer contractionTimer;
    int contractionHours = 0;
    int contractionMinutes = 0;
    int contractionSeconds = 0;
    int kickCountforSqlite = 00;

   int tempcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contraction_activity);

        hourstext=(TextView) findViewById(R.id.hours);
        minutestext=(TextView) findViewById(R.id.minutes);
        secondstext=(TextView) findViewById(R.id.seconds);
        contractionaction=(Button) findViewById(R.id.stopcontraction);
        contractionsecondstext=(TextView)findViewById(R.id.contractionsecondstext);
        backButton=(ImageView) findViewById(R.id.backbutton1);


        backButton.setOnClickListener(this);
        contractionaction.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager linear=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linear);






    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.stopcontraction:

                ContractionCount();

                break;

            case R.id.backbutton1:
                onBackPressed();
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void ContractionCount(){

        String lengthoftime = null;



        if (contractionStart){

            contractionStart=false;
            contractionaction.setText("STOP CONTRACTION");



            Calendar cals=Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            StartTime = mdformat.format(cals.getTime());



            laststarttime=StartTime;
            contractionaction.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.RedColor));

          contractionTimer=new Timer();
          contractionTimer.schedule(new TimerTask() {
              @Override
              public void run() {

                  contractionSeconds++;

                  if (contractionSeconds == 60) {
                      contractionMinutes++;
                      contractionSeconds = 00;
                  }
                  if (contractionMinutes == 60) {
                      contractionHours++;
                  }


                      ContractionTimerActivity.this.runOnUiThread(new Runnable() {
                          @Override
                          public void run() {

                             hourstext.setText(String.valueOf(contractionHours));

                                minutestext.setText(String.valueOf(contractionMinutes));
                                secondstext.setText(String.valueOf(contractionSeconds));
                          }
                      });



              }
          },00,1000);




        }else{

            contractionStart = true;
            contractionTimer.cancel();

            Calendar cals = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            StopTime = mdformat.format(cals.getTime());

        contractionaction.setText("Start Contraction");
        contractionaction.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.GreenColor));

        lengthoftime=hourstext.getText().toString()+":"+minutestext.getText().toString()+":"+secondstext.getText().toString();
            contractionTimer.cancel();
            //Log.e("time",diff);
contractionHours=0;
contractionHours=0;
contractionSeconds=0;
            hourstext.setText("0");
            minutestext.setText("0");
            secondstext.setText("0");


            contractionsecondstext.setText(lengthoftime+" : Time taken contraction");

            startandstoptime=StartTime+"-"+StopTime;


            laststoptime=StopTime;
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");



                try {
                    Date Date1 = sdf.parse(laststoptime);

                    Date Date2 = sdf.parse(laststarttime);

                    long millse = Date1.getTime() - Date2.getTime();
                    long mills = Math.abs(millse);

                    int Hours = (int) (mills / (1000 * 60 * 60));
                    int Mins = (int) (mills / (1000 * 60)) % 60;
                    long Secs = (int) (mills / 1000) % 60;
                     frequency = String.valueOf(Hours) + ":" + String.valueOf(Mins) + ":" + String.valueOf(Secs);



                } catch (ParseException e) {
                    e.printStackTrace();
                }




            JSONObject jsonObject=new JSONObject();

            try {
                jsonObject.put("lenth",lengthoftime);
                jsonObject.put("timeaprt",frequency);
                jsonObject.put("startandstop",startandstoptime);
                json.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            myAdapt=new MyAdapter(getApplicationContext(),json);
            recyclerView.setAdapter(myAdapt);



        }




       // contractionsecondstext.setText(diff+" : Time taken contraction");





    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



        Context context;

        JSONArray jsonArray;


        MyAdapter(Context context,JSONArray jsonArray) {


            this.context = context;

            this.jsonArray=jsonArray;


        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contraction_view, parent, false);
            ViewHolder viewa = new ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {


            try {
                holder.lengthtxt.setText(String.valueOf(jsonArray.getJSONObject(position).get("lenth")));
                holder.timeaparttxt.setText(String.valueOf(jsonArray.getJSONObject(position).get("timeaprt")));
                holder.startandstoptimetxt.setText(String.valueOf(jsonArray.getJSONObject(position).get("startandstop")));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {

            return jsonArray.length();
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView lengthtxt,timeaparttxt,startandstoptimetxt;

            public ViewHolder(View itemView) {
                super(itemView);


                lengthtxt = (TextView) itemView.findViewById(R.id.lenth);
                timeaparttxt = (TextView) itemView.findViewById(R.id.timeapart);
                startandstoptimetxt = (TextView) itemView.findViewById(R.id.startandstop);


            }
        }
    }


}
