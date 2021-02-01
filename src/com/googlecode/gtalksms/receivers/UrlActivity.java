package com.googlecode.gtalksms.receivers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.gtalksms.tools.Tools;

public class UrlActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();       
        Uri uri = intent.getData();
        if (uri != null) {
            Tools.send(uri.toString(), null, this);
        }
        
        finish();
    }
}
