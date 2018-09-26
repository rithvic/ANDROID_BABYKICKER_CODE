package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFragment extends android.app.Fragment implements View.OnClickListener {
    public static final String mypreference = "MyPrefs";
    SharedPreferences sharedpreferences;
int i;

    TextView usermailtext;
    EditText usernametext,userdelivertext,childname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.layout_profile, container, false);


        usermailtext=(TextView) view.findViewById(R.id.usermail);
        usernametext=(EditText) view.findViewById(R.id.username);
        userdelivertext=(EditText) view.findViewById(R.id.userdelivery);
        childname=(EditText) view.findViewById(R.id.childname);

        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);


        usermailtext.setText(sharedpreferences.getString("UserMail", ""));
        usernametext.setText(sharedpreferences.getString("UserName", ""));
        userdelivertext.setText(sharedpreferences.getString("UserDelivery", ""));
       // childname.setHint(sharedpref.getString("UserChild", ""));






        return view;
    }


    @Override
    public void onClick(View v) {

    }
}