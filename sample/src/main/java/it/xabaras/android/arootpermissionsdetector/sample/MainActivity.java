package it.xabaras.android.arootpermissionsdetector.sample;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import it.xabaras.android.arootpermissionsdetector.ARootPermissionsDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheck = (Button)findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String msg = "";
                    if ( ARootPermissionsDetector.isRooted(getApplicationContext()) ) {
                        msg = "Your device is rooted!!!";
                    } else {
                        msg = "No root permissions detected.";
                    }

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.app_name)
                            .setMessage(msg)
                            .setNeutralButton(android.R.string.ok, null)
                            .show();
                } catch(Exception e) {
                    Log.e("sample", e.getMessage());
                }
            }
        });
    }
}
