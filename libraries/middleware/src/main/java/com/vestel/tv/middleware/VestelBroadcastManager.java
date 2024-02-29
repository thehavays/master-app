package com.vestel.tv.middleware;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VestelBroadcastManager {

    private static final String TAG = "VestelBroadcastManager";

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void setRFSignalOn() {
    }

    public static void setRFSignalOff() {
    }

    private static int getTIFChannelInfoById(int id) {
        return -1;
    }

}
