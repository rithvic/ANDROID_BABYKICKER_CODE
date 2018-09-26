package babykicker.qbayapps.quadrobay.com.babykicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class BabyNameActivity extends AppCompatActivity implements View.OnClickListener{



    ListView listboys,listwomens;

    String[] boys;
    String[] girls;
    ImageView backbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babynames_layout);


        boys=getResources().getStringArray(R.array.boysname);

        girls=getResources().getStringArray(R.array.womansname);

        backbutton=findViewById(R.id.backbutton1);
        backbutton.setOnClickListener(this);

        listboys=(ListView) findViewById(R.id.listboys);
        listwomens=(ListView) findViewById(R.id.listwomen);
ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,boys);
listboys.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,girls);
        listwomens.setAdapter(adapter1);





    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
