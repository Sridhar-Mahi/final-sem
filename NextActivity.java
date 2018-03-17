package com.example.piccosoft.designing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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


public class NextActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client;

    EditText otp;
    Button btnNext;
    Button btnLinkToLoginScreen;
    JSONObject json;
    JSONObject json1;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        client = new OkHttpClient();
        json = new JSONObject();
        json1 = new JSONObject();
        otp = (EditText)findViewById(R.id.otp);

        btnNext = (Button) findViewById(R.id.btnNext);


        user_id = getIntent().getExtras().getString("id", null);
        Log.v("PASSED ID", user_id);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView iView = (ImageView) findViewById(R.id.test_image);
                if (iView != null)
                    Glide.with(NextActivity.this).load(loaders).asGif().into(iView);

                String Sotp = otp.getText().toString();
                try {

                    json.put("otp", Sotp);
                    json.put("user_id", user_id);
                    json.put("device_token", "sbdjkabdjsabd");
                    post("http://finalsem.com/finalsemapi/api/User/activation", json.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

    }

    void post(String url, final String json) throws IOException {
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
                Log.v("RESPONSE", res);

                try {
                    JSONObject jsonObject = new JSONObject(res);

                    if (jsonObject.has("token")) {


                        Log.v("RESULT", "LOGIN SUCCESSFULL");

                        NextWindow();


                    } else {

                        Log.v("RESULT", "LOGIN FAILED");




                    }


                } catch (JSONException e) {

                    e.printStackTrace();


                }

            }

        });

        btnLinkToLoginScreen = (Button) findViewById(R.id.btnLinkToLoginScreen);

        btnLinkToLoginScreen.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view) {

                ImageView iView = (ImageView) findViewById(R.id.test_image);
                if (iView != null) Glide.with(NextActivity.this).load(loaders).asGif().into(iView);

                String Sotp = otp.getText().toString();
                try {
                    json1.put("otp", Sotp);
                    json1.put("user_id", user_id);
                    json1.put("device_token", "sbdjkabdjsabd");

                    post1("http://finalsem.com/finalsemapi/api/User/regenerateotp", json1.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        }


        void post1(String url, final String json1) throws IOException {
        RequestBody body = RequestBody.create(JSON, json1);
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
                Log.v("RESPONSE", res);

                try {
                    JSONObject jsonObject = new JSONObject(res);

                    if (jsonObject.has("token")) {



                        Log.v("RESULT", "LOGIN SUCCESSFULL");

                        NextWindow();


                    }


                    else {

                        Log.v("RESULT", "LOGIN FAILED");



                    }


                }

                catch (JSONException e) {

                    e.printStackTrace();



                }

            }

        });

    }

    private void NextWindow() {


        Intent intent = new Intent(NextActivity.this, ThirdActivity.class);

        startActivity(intent);

        finish();

    }



}
