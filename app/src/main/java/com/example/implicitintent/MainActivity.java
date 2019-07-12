package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open_the_web(View v) {
        Intent intent = null, chooser = null;
        switch (v.getId()) {
            case R.id.website:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com"));
                chooser = Intent.createChooser(intent, "How would you want to open it ?");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;

            case R.id.bmaps:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://maps.google.com/maps?q=27.1750, 78.0422°(Label Point)"));
                chooser = Intent.createChooser(intent, "How would you want to open it? ");
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
                break;

            case R.id.bemail:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto: "));
                intent.setType("message/rfc822");
                String[] send_to = {"mangreat64@gmail.com", "nagmanohar2011@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, send_to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sent by the great Manish");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, Its Manish here.");
                chooser = Intent.createChooser(intent, "Select the email app...");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;

            case R.id.bsendImage:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                String imagePath = Environment.getExternalStorageDirectory() + "/photo.jpg";
                File imageFileToShare = new File(imagePath);
                Uri uri = Uri.fromFile(imageFileToShare);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                chooser = Intent.createChooser(intent, "Share image via...");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.bshareMessage:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                chooser = Intent.createChooser(intent, "Share message via...");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;

            case R.id.bcall:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918197237312"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            default:
                break;
        }
    }
}



