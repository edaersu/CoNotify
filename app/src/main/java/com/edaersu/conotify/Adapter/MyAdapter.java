package com.edaersu.conotify.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.edaersu.conotify.Model.CoModel;
import com.edaersu.conotify.R;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<CoModel> modelArrayList;

    public MyAdapter(Context context, ArrayList<CoModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }



    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_item,container,false);

        final TextView tarih=view.findViewById(R.id.tarih_tw);
        TextView hasta=view.findViewById(R.id.hasta_sayisi_tw);
        TextView test=view.findViewById(R.id.test_sayisi_tw);
        TextView vaka=view.findViewById(R.id.vaka_sayisi_tw);
        TextView iyilesen=view.findViewById(R.id.iyilesen_Sayisi_tw);
        TextView vefat=view.findViewById(R.id.vefat_sayisi_tw);

        CoModel model=modelArrayList.get(position);

        final String s_tarih= model.getTarih();
        String s_hasta=model.getHasta_sayisi();
        String s_test=model.getTest_sayisi();
        String s_vaka=model.getVaka_Sayisi();
        String s_iyilesen= model.getIyilesen_sayisi();
        String s_vefat=model.getVefat_sayisi();

        tarih.setText(s_tarih);
        hasta.setText(s_hasta);
        test.setText(s_test);
        vaka.setText(s_vaka);
        iyilesen.setText(s_iyilesen);
        vefat.setText(s_vefat);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,s_tarih,Toast.LENGTH_LONG).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
