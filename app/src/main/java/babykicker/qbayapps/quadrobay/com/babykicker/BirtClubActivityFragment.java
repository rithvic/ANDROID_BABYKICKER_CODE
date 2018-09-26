package babykicker.qbayapps.quadrobay.com.babykicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BirtClubActivityFragment extends android.app.Fragment{

    RecyclerView recyclerView;
    String[] arr;;
    int[] imageIds = {R.drawable.first,R.drawable.first,R.drawable.first,R.drawable.cfourweeks,R.drawable.cfifthweeks,R.drawable.csixthweeks,R.drawable.cseventhweek,R.drawable.ceihtthweeks,R.drawable.cninethweeks,R.drawable.ctenthweeks,R.drawable.celeventhweek,R.drawable.ctwelethweeks,R.drawable.cthirteenweeks,R.drawable.cfourteenthweeks,R.drawable.cfifteenthweek,R.drawable.cseventeenthweek,R.drawable.ceihteenthweek,R.drawable.cnineteenthweek,R.drawable.ctwentythweeks,R.drawable.ctwentyoneweek,R.drawable.ctwentytwoweek,R.drawable.ctwentythreeweek,R.drawable.ctwentyfourthweek,R.drawable.ctwentyfifthweek,R.drawable.ctwentysixthweek,R.drawable.ctwentyseventhweek,R.drawable.ctwentyeightweek,R.drawable.ctwentyninethweek,R.drawable.cthirtythweek,R.drawable.cthirtyoneweek,R.drawable.cthirttwoweeks,R.drawable.cthirtythirdweeks,R.drawable.cthirtyfourthweeks,R.drawable.cthirtfifthweeks,R.drawable.cthirtysixthweek,R.drawable.cthirtyseventhweek,R.drawable.cthirtyeightweek,R.drawable.cthirtnineweeks,R.drawable.cfourtyweeks};

    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


      View view=inflater.inflate(R.layout.birth_club_layout,container,false);
        arr = getResources().getStringArray(R.array.cweek);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleradapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        MyAdapter adapter=new MyAdapter(getActivity(),imageIds);
        recyclerView.setAdapter(adapter);







    return view;
}
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



        Context context;

        int[] imagess;


        MyAdapter(Context context,int[] images) {


            this.context = context;

            this.imagess=images;


        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.birthclub_adapter, parent, false);
            ViewHolder viewa = new ViewHolder(v);

            return viewa;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
int wee=position+1;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imagess[position]);
            holder.weektext.setText(String.valueOf("Week "+wee));
            holder.contenttext.setText(arr[position]);
            //Picasso.get().load(imagess[position]).into(holder.imageview);

            holder.imageview.setImageBitmap(bitmap);

        }

        @Override
        public int getItemCount() {

            return imagess.length;
        }


        class ViewHolder extends RecyclerView.ViewHolder {


            TextView weektext,contenttext;
            ImageView imageview;

            public ViewHolder(View itemView) {
                super(itemView);


                weektext = (TextView) itemView.findViewById(R.id.text1);
                contenttext = (TextView) itemView.findViewById(R.id.weektext1);
                imageview = (ImageView) itemView.findViewById(R.id.imageview);


            }
        }
    }


}









