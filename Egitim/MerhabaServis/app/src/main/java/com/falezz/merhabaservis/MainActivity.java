package com.falezz.merhabaservis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean servisDurumu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bServis = (Button) findViewById(R.id.bServis);
        bServis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!servisDurumu) {
                    Intent i = new Intent(MainActivity.this, Servis.class);
                    startService(i);
                    servisDurumu = true;
                    bServis.setText("SERVİSİ DURDUR");
                } else {
                    Intent i = new Intent(MainActivity.this, Servis.class);
                    stopService(i);
                    servisDurumu = false;
                    bServis.setText("SERVİSİ BAŞLAT");
                }
            }
        });
    }
}
