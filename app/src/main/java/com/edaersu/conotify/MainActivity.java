package com.edaersu.conotify;

//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.edaersu.conotify.Adapter.MyAdapter;
import com.edaersu.conotify.Model.CoModel;
import com.edaersu.conotify.Service.JsonPlaceHolderApi;
import com.edaersu.conotify.Service.Servis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    private MyAdapter myAdapter;
    ArrayList<CoModel> corona_list;
    Context context=this;
    TextView mTitle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(context, Servis.class));

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://co-notify-f6d72-default-rtdb.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create((JsonPlaceHolderApi.class));

        Call<ArrayList<CoModel>> call=jsonPlaceHolderApi.getStatistics();

        call.enqueue(new Callback<ArrayList<CoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CoModel>> call, Response<ArrayList<CoModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,response.code(),Toast.LENGTH_LONG).show();
                    System.out.println("RESPONSE ERROR"+response.message());
                }
                corona_list=response.body();

                for (CoModel co:corona_list){
                    System.out.println(co.getTarih());
                    System.out.println(co.getHasta_sayisi());
                }
                setContentView(R.layout.activity_main);
             //   actionBar=getSupportActionBar();
              //  actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9CCFCB")));

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                mTitle.setText("");
                setSupportActionBar(toolbar);
                viewPager=findViewById(R.id.viewPager);

                myAdapter=new MyAdapter(MainActivity.this,corona_list);
                viewPager.setAdapter(myAdapter);
                viewPager.setPadding(100,0,100,0);
                viewPager.setCurrentItem(myAdapter.getCount()-1);


                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        String title=corona_list.get(position).getTarih();
                       // actionBar.setTitle(title);
                        mTitle.setText(title);
                       setSupportActionBar(toolbar);

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });



            }

            @Override
            public void onFailure(Call<ArrayList<CoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                System.out.println("FAILURE ERROR"+t.getMessage());
            }
        });


    }
}