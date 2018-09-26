package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ToolsActivityFragment extends android.app.Fragment implements View.OnClickListener {



LinearLayout bumpie,babyname,contractiontimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {







                View view = inflater.inflate(R.layout.layout_tools_activity,   container, false);

                bumpie=(LinearLayout) view.findViewById(R.id.bumpie);
                    babyname=(LinearLayout) view.findViewById(R.id.babynames);
                     contractiontimer=(LinearLayout) view.findViewById(R.id.contractiontimer);

        bumpie.setOnClickListener(this);
        babyname.setOnClickListener(this);
        contractiontimer.setOnClickListener(this);

        return view;






    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bumpie:
                Intent intent11=new Intent(getActivity(),BumpieActivity.class);
                startActivity(intent11);

                break;


            case R.id.babynames:

               Intent intent3=new Intent(getActivity(), BabyNameActivity.class);
               startActivity(intent3);
                break;


            case R.id.contractiontimer:

                Intent contract=new Intent(getActivity(),ContractionTimerActivity.class);
                startActivity(contract);
                break;
        }
    }
}
