package com.github.elwyncrestha.androidsensordemo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSensorList;
    private Button btnAccelerometer, btnGyroscope, btnProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sensor list");
        this.tvSensorList = findViewById(R.id.tvSensorList);
        this.btnAccelerometer = findViewById(R.id.btnAccelerometer);
        this.btnGyroscope = findViewById(R.id.btnGyroscope);
        this.btnProximity = findViewById(R.id.btnProximity);

        this.btnAccelerometer.setOnClickListener(this);
        this.btnGyroscope.setOnClickListener(this);
        this.btnProximity.setOnClickListener(this);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = Objects.requireNonNull(sensorManager).getSensorList(Sensor.TYPE_ALL);
        sensorList.forEach(s -> tvSensorList.append(s.getName() + "\n"));

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnAccelerometer:
                intent = new Intent(getApplicationContext(), AccelerometerActivity.class);
                break;
            case R.id.btnGyroscope:
                intent = new Intent(getApplicationContext(), GyroscopeActivity.class);
                break;
            case R.id.btnProximity:
                intent = new Intent(getApplicationContext(), ProximityActivity.class);
                break;
            default:
                Toast.makeText(this, "Not found!!!", Toast.LENGTH_SHORT).show();
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
