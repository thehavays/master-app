package com.vestel.tv.middleware;


public class VestelPvrInformation{
    private static final String TAG = "VestelPvrInformation";
    private static VestelPvrInformation vestelPvrInformation = null;
    private static VestelPvrInfoListener m_pvr_info_listener = null;

    private VestelPvrInformation() {
        VestelLog.verbose(TAG, "Constructor worked: ");
    }

    public static VestelPvrInformation getInstance() {
        if (vestelPvrInformation == null) {
            vestelPvrInformation = new VestelPvrInformation();
        }
        return vestelPvrInformation;
    }

    public static void setPvrInfoListener(VestelPvrInfoListener pvrInfoListener) {
        m_pvr_info_listener = pvrInfoListener;
    }

}
