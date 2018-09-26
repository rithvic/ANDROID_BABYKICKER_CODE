package babykicker.qbayapps.quadrobay.com.babykicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutsActivity extends android.app.Fragment implements View.OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aboutus_layout, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}