package com.example.myzipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button zipButton;
    EditText zipText;
    String TAG = "dimode";

    int nCurrentPermission = 0;
    static final int PERMISSIONS_REQUEST = 0x0000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipButton = findViewById(R.id.zipButton);
        zipText = findViewById(R.id.myZipText);

        zipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String folderName = zipText.getText().toString();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName;

                if (fileExist(path)) {
                    String zipStartToast = folderName + ".zip File 생성 중";
                    Toast.makeText(MainActivity.this.getBaseContext(), zipStartToast, Toast.LENGTH_LONG).show();

                    Zip4jComp.compress(path);
                } else {
                    String zipFailToast = folderName + " 해당 파일이 없습니다.";
                    Toast.makeText(MainActivity.this.getBaseContext(), zipFailToast, Toast.LENGTH_LONG).show();
                }

            }
        });

        onCheckPermission();
    }

    private void onCheckPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "앱 설정을 위한 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST);
            }
        }
    }

    private boolean fileExist(String path) {
        File file = new File(path);
        return file.exists();
    }
}