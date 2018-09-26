package babykicker.qbayapps.quadrobay.com.babykicker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String mypreference = "MyPrefs";

    String stri="nav";
    TextView calenderfra,birthclubfrag,toolsfrag;
    LinearLayout linearLayout;
    SharedPreferences sharedPreferences;
TextView idtextset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences= getApplication().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        linearLayout=(LinearLayout) findViewById(R.id.linear);
            calenderfra=findViewById(R.id.calender);
            birthclubfrag=findViewById(R.id.birthclub);
            toolsfrag=findViewById(R.id.tools);
        sharedPreferences = getApplicationContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);


//        idtextset=findViewById(R.id.useridname);
//        idtextset.setText(sharedpreferences.getString("UserName", ""));
//


            birthclubfrag.setOnClickListener(this);
            toolsfrag.setOnClickListener(this);
            calenderfra.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        CalenderActivityFrament1 calen=new CalenderActivityFrament1();
        calenderfra.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.cyan));

        FragmentManager fm=getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, calen);
        fragmentTransaction.commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.useridname);
        navUsername.setText(sharedPreferences.getString("UserName", ""));

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }


        else {


            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            linearLayout.setVisibility(View.GONE);
            ProfileFragment profile=new ProfileFragment();
            FragmentManager profm=getFragmentManager();
            FragmentTransaction fragmentTransaction = profm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, profile);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_baby) {

        } else if (id == R.id.nav_share) {




        }else if (id == R.id.nav_about) {
            linearLayout.setVisibility(View.GONE);

            AboutsActivity about=new AboutsActivity();
            FragmentManager fm1=getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
            fragmentTransaction1.replace(R.id.frameLayout, about);
            fragmentTransaction1.commit();


        }else if (id == R.id.nav_logout) {


            SharedPreferences.Editor editing = sharedPreferences.edit();



            editing.clear();
            editing.commit();


            Intent intentt=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intentt);
        }else if (id == R.id.nav_home) {
            linearLayout.setVisibility(View.VISIBLE);

            calenderfra.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.cyan));
            birthclubfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));
            toolsfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));


            CalenderActivityFrament1 calen=new CalenderActivityFrament1();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, calen);
            fragmentTransaction.commit();


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.calender:

                calenderfra.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.cyan));
                birthclubfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));
                toolsfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));


                CalenderActivityFrament1 calen=new CalenderActivityFrament1();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, calen);
                fragmentTransaction.commit();

                break;
            case R.id.birthclub:
                birthclubfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.cyan));
                toolsfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));
                calenderfra.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));

                BirtClubActivityFragment birth=new BirtClubActivityFragment();

                FragmentManager fmbirth=getFragmentManager();
                FragmentTransaction fmtrans = fmbirth.beginTransaction();
                fmtrans.replace(R.id.frameLayout, birth);
                fmtrans.commit();
                break;

            case R.id.tools:
                toolsfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.cyan));
                birthclubfrag.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));
                calenderfra.setBackgroundColor(calenderfra.getContext().getResources().getColor(R.color.lightcyan));



                ToolsActivityFragment toolfrag=new ToolsActivityFragment();
                FragmentManager fm2=getFragmentManager();
                FragmentTransaction fmtransaction = fm2.beginTransaction();
                fmtransaction.replace(R.id.frameLayout, toolfrag);
                fmtransaction.commit();

                break;

        }
    }

    public void alertDialoueBox(){

        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(getApplicationContext());


        alertDialog.setTitle("Confirm Logout");


        alertDialog.setMessage("Do you want logout your Profile ?");

        // Setting Icon to Dialog

        // Setting Positive "Yes" Button


        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {





                SharedPreferences.Editor editing = sharedPreferences.edit();



                editing.clear();
                editing.commit();


                Intent intentt=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intentt);
            }
        });


        // Showing Alert Message
        alertDialog.show();


    }



}
