package com.example.ahmet.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//Çalışma Alanı Doğrulayıcı 427520757967756c616d612041686d657420456d696e2053542c204d65686d65742043616e2044656c696269726fc49f6c752076652042657974756c6c61682059616cc4b16d276120416974746972


public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Bitmap bitmap;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kamera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(kamera_intent,44);

            }

        });


        imageView =(ImageView) findViewById(R.id.imageView);
        textView=(TextView) findViewById(R.id.textView);

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN||motionEvent.getAction()==MotionEvent.ACTION_MOVE)
                {
                    bitmap=imageView.getDrawingCache();
                    int pixel=bitmap.getPixel((int) motionEvent.getX(),(int) motionEvent.getY());

                    int r= Color.red(pixel);
                    int g=Color.green(pixel);
                    int b=Color.blue(pixel);

                    textView.setBackgroundColor(Color.rgb(r,g,b));
                    textView.setText("Kırmızı =("+r+")\n"+"Yeşil =("+g+")\n"+"Mavi =("+b+")");
                }

                return false;
            }
        }
        );


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==44)
        {
            Bitmap image=(Bitmap)data.getExtras().get("data");
            ImageView resim=(ImageView)findViewById(R.id.imageView);
            resim.setImageBitmap(image);
        }
}}
