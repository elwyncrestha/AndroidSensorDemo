package com.github.elwyncrestha.androidsensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GyroscopeActivity extends AppCompatActivity {

    private TextView tvGyroscope;
    private EditText etNumber1;
    private EditText etNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        this.tvGyroscope = findViewById(R.id.tvGyroscope);
        this.etNumber1 = findViewById(R.id.etNumber1);
        this.etNumber2 = findViewById(R.id.etNumber2);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (TextUtils.isEmpty(etNumber1.getText())) {
                    etNumber1.setError("Enter number 1");
                    return;
                }
                if (TextUtils.isEmpty(etNumber2.getText())) {
                    etNumber2.setError("Enter number 2");
                    return;
                }
                float num1 = Float.parseFloat(etNumber1.getText().toString());
                float num2 = Float.parseFloat(etNumber2.getText().toString());
                if (event.values[1] > 0) {
                    tvGyroscope.setText(String.format(Locale.ENGLISH, "%s of %f and %f is %f",
                            "Sum", num1, num2, (num1 + num2)));
                } else if (event.values[1] < 0) {
                    tvGyroscope.setText(String.format(Locale.ENGLISH, "%s of %f and %f is %f",
                            "Difference", num1, num2, (num1 - num2)));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor == null) {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
