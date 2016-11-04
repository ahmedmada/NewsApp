package com.example.ahmed.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    TextView t1,t2,author;
    ImageView img;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        t1 = (TextView)findViewById(R.id.maintitle);
        t2 = (TextView)findViewById(R.id.maincontent);
        author = (TextView)findViewById(R.id.author);
        img = (ImageView)findViewById(R.id.profile_id);


        String s1 = i.getStringExtra("f1");
        String s2 = i.getStringExtra("f2");
        String s3 = i.getStringExtra("img");
        String s4 = i.getStringExtra("author");
        String s5 = i.getStringExtra("link");

        t1.setText(s1);
        t2.setText(s2);
        author.setText("BY : "+s4);
        Picasso.with(Details.this).load(s3).into(img);

        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(s5);
        webView.setWebViewClient(new WebViewClient());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Details.this,"لم يتم رفع التطبيق علي جوجال بلاي بعد.",Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Details.this,MainActivity.class);
        startActivity(i);
    }
}
