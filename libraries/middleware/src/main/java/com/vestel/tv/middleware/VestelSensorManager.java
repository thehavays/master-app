package com.vestel.tv.middleware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;

public class VestelSensorManager
{
    private static final String TAG = "VestelSensorManager";

    private static Context m_context = null;
    private static SensorManager m_sensor_manager = null;
    private static Sensor m_sensor = null;

    private static float m_front_lux_scale = 10;
    private static float m_light = 0;
    private static float m_lux = 0;

    private static VestelSensorEventListener m_vestel_sensor_event_listener = null;

    private static SensorEventListener m_sensor_event_listener = new SensorEventListener(){

        @Override
        public void onSensorChanged(SensorEvent event) {

            m_light = event.values[0];
            m_lux = m_light * m_front_lux_scale;

            if (m_vestel_sensor_event_listener != null)
            {
                m_vestel_sensor_event_listener.onLightSensorChange(m_light, m_lux);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public static void init(Context context)
    {
        m_context = context;
        m_sensor_manager = (SensorManager) m_context.getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensor_manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        readDolbyConfigFile();

        m_sensor_manager.registerListener(m_sensor_event_listener, m_sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static void registerSensorEventListener(VestelSensorEventListener listener)
    {
        m_vestel_sensor_event_listener = listener;
    }

    public static void deregisterSensorEventListener()
    {
        m_vestel_sensor_event_listener = null;
    }

    public static float getLight()
    {
        return m_light;
    }

    public static float getLux()
    {
        return m_lux;
    }

    private static void readDolbyConfigFile()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(VestelConfig.getDolbyFactoryCfgFile()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("TFrontLuxScale ="))
                {
                    // Check if the line contains "TFrontLuxScale =" including spaces
                    // If the line contains TFrontLuxScale value, read and parse it
                    String[] parts = line.split("=");
                    if (parts.length == 2)
                    {
                        m_front_lux_scale = (float) Double.parseDouble(parts[1].trim());
                        break; // We found the value, exit the loop
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
