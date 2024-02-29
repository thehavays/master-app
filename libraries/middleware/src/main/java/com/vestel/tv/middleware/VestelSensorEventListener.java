package com.vestel.tv.middleware;

public interface VestelSensorEventListener
{
    void onLightSensorChange(float light, float lux);
}
