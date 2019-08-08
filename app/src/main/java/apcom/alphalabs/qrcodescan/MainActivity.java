package apcom.alphalabs.qrcodescan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    public static int time;
    public static int menu=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
    public void time(View v){
        Boolean checked=((RadioButton)v).isChecked();
        switch (v.getId()) {
            case R.id.radioButton:
                if(checked)
                    time=0;
                break;
            case R.id.radioButton2:
                if(checked)
                    time=1;
                break;
        }

    }
    public void scan(View view){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 23);

        }
        else
        {
            zXingScannerView=new ZXingScannerView(getApplicationContext());
            setContentView(zXingScannerView);
            zXingScannerView.setResultHandler(this);
            zXingScannerView.startCamera();
        }
    }
    public void newWindow(View v){

            setContentView(R.layout.manual);
        }


    public void nameEntered(View r)
    {
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(zXingScannerView!=null)
        zXingScannerView.stopCamera();
    }
    @Override
    public void handleResult(Result result) {
        Date currentTime= Calendar.getInstance().getTime();
        Toast.makeText(getApplicationContext(),result.getText() + currentTime.toString()+" value "+Integer.toString(time),Toast.LENGTH_SHORT).show();
       zXingScannerView.stopCamera();
        finish();
        startActivity(getIntent());

    }
}
