package com.example.piccosoft.designing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chabbal.slidingdotsplash.SlidingSplashView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.piccosoft.designing.R.drawable.loaders;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client;
    EditText phone;
    JSONObject json;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phone = (EditText) findViewById(R.id.phone);

        client = new OkHttpClient();

        json = new JSONObject();

        TextView textView12 = (TextView) findViewById(R.id.textView12);
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView iView = (ImageView) findViewById(R.id.test_image);
                if (iView != null)
                    Glide.with(MainActivity.this).load(loaders).asGif().into(iView);

                Intent in=new Intent();
                in.setClass(MainActivity.this,RegisterActivity.class);
                startActivity(in);

            }
        });


        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView iView = (ImageView) findViewById(R.id.test_image);
                if (iView != null)
                    Glide.with(MainActivity.this).load(loaders).asGif().into(iView);

                try {
                    String Sphone = phone.getText().toString();
                    json.put("mobile", Sphone);
                    post("http://finalsem.com/finalsemapi/api/User/login", json.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "OTP Sent to Your Mobile" , Toast.LENGTH_SHORT ).show();


            }
        });


        SlidingSplashView splashView  = (SlidingSplashView) findViewById(R.id.splash);
        splashView.addOnPageChangeListener(this);


    }
    void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string().trim();
                try{
                    JSONObject jsonObject = new JSONObject(res);
                    String userId = jsonObject.getString("id");
                    Log.v("RESPONSE", res);

                    Intent intent = new Intent(MainActivity.this, NextActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);


                }
                catch (JSONException e){

                    e.printStackTrace();
                }


            }

        });


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("OnPageScrolled", String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("OnPageSelected", String.valueOf(position));

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PageScrollStateChanged", String.valueOf(state));

    }
}


