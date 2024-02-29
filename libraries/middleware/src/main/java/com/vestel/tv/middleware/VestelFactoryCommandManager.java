package com.vestel.tv.middleware;

import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class VestelFactoryCommandManager {

    private static final String TAG = "VestelFactoryCommandManager";

    private static Context mContext;


    public static void init(Context context) {
        mContext = context;
    }

    public static void performFactoryReset() {
        boolean isFileExist = VestelFileSystem.isFileExist(VestelConfig.getFactoryFlagFile());
        if (isFileExist) {
            boolean result = VestelFileSystem.removeFile(VestelConfig.getFactoryFlagFile());
            if (result) {
                VestelLog.debug(TAG, "performFactoryReset. FILE DELETED");
            } else {
                VestelLog.debug(TAG, "performFactoryReset. FILE CANNOT BE DELETED");
            }
        } else {
            VestelLog.debug(TAG, "performFactoryReset. THERE IS NO FILE");
        }

        resetTotalUserWatchTime();

        Intent resetIntent = createFactoryResetIntent();
        mContext.sendBroadcast(resetIntent);
    }

    public static boolean performFactoryExit() {

        boolean result = false;
        final String factoryCmdDestroy = "2";
        final String factoryServiceServer = "@FACTORY_SERVER";
        try {
            LocalSocket ls = new LocalSocket();
            ls.connect(new LocalSocketAddress(factoryServiceServer));

            if (ls.isConnected()) {
                VestelLog.debug(TAG, "performFactoryExit: connect ok");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ls.getOutputStream()));
                writer.write(factoryCmdDestroy);
                writer.close();
                result = true;
            } else {
                VestelLog.debug(TAG, "performFactoryExit: connect fail");
            }

            ls.close();
        } catch (IOException e) {
            e.printStackTrace();
            VestelLog.info(TAG, "performFactoryExit: connect fail");
        }

        return result;
    }

    public static void restoreToFactoryDefault(boolean shouldShutdown) {

    }

    public static void installOpAppKey() {
        Intent intent = new Intent();
        intent.setAction("mediatek.tvsetting.factory.intent.action.FactoryInstallOpappKeyActivity");
        mContext.startActivity(intent);
    }

    private static boolean resetTotalUserWatchTime() {
        return false;
    }

    private static Intent createFactoryResetIntent() {
        final String actionFactoryReset = "android.intent.action.FACTORY_RESET";
        final String packageName = "android";
        final String extraReasonKey = "android.intent.extra.REASON";
        final String extraKeyValue = "ResetConfirmFragment";

        Intent resetIntent = new Intent(actionFactoryReset);
        resetIntent.setPackage(packageName);
        resetIntent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        resetIntent.putExtra(extraReasonKey, extraKeyValue);

        return resetIntent;
    }

}
