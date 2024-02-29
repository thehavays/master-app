package com.vestel.tv.middleware;

/**
 * @apiNote This class provides information about video signal
 */
public class VestelVideoInformation {
    private static final String TAG = "VestelVideoInformation";

    private static boolean mVideoStable;
    private static boolean mIsHbbtvMode;
    private static VestelChannelSwitchListener mChannelSwitchListener;
    private static VestelCIModuleListener mCiModuleInfoListener;
    private static VestelHbbtvInfoListener mHbbtvInfoListener;
    private static VestelVideoInformation mVideoInformation;

    /**
     * @return Static instance of the class
     * @apiNote Returns the static instance of the class
     */
    public static synchronized VestelVideoInformation getInstance() {
        if (mVideoInformation == null) {
            mVideoInformation = new VestelVideoInformation();
        }
        return mVideoInformation;
    }

    /**
     * @param videoStable True if video signal is stable, false otherwise
     * @apiNote Notifies video signal stability of the media being played
     */
    public static void setSignalStable(boolean videoStable) {
        mVideoStable = videoStable;
    }

    /**
     * @return True if video signal is stable, false otherwise
     * @apiNote Checks if video signal of the media being played is stable
     */
    public static boolean isSignalStable() {
        return true;
    }

    /**
     * @param type One of the values defined in VestelVideoType enumeration
     * @return String representation of VestelVideoType value
     * @apiNote Returns string representation of high dynamic range formats
     */
    public static String convertToString(VestelVideoType type) {
        String str = "Unknown";

        switch (type) {
            case VIDEO_TYPE_STANDARD -> str = "STANDARD";
            case VIDEO_TYPE_DOLBY_VISION -> str = "DOLBY";
            case VIDEO_TYPE_HDR -> str = "HDR10";
            case VIDEO_TYPE_HDR_PLUS -> str = "HDR10+";
            case VIDEO_TYPE_HLG -> str = "HLG";
        }

        return str;
    }

    /**
     * @return One of the values defined in VestelVideoType enumeration
     * @apiNote Returns high dynamic range format of the video being played
     */
    public static VestelVideoType getVideoType() {
        VestelVideoType video_type;

        int value = 0;

        video_type = switch (value) {
            case 1 -> VestelVideoType.VIDEO_TYPE_HDR;
            case 2 -> VestelVideoType.VIDEO_TYPE_HLG;
            case 3 -> VestelVideoType.VIDEO_TYPE_DOLBY_VISION;
            case 5 -> VestelVideoType.VIDEO_TYPE_HDR_PLUS;
            default -> VestelVideoType.VIDEO_TYPE_STANDARD;
        };

        return video_type;
    }

    public static void setHbbTvInfoListener(VestelHbbtvInfoListener hbbtvInfoListener) {
        mHbbtvInfoListener = hbbtvInfoListener;
    }

    public static void setChanelSwitchListener(VestelChannelSwitchListener channelSwitchListener) {
        mChannelSwitchListener = channelSwitchListener;
    }

    public static void setCIModuleListener(VestelCIModuleListener ciModuleListener) {
        mCiModuleInfoListener = ciModuleListener;
    }

    /**
     * @return True if hybrid broadcast broadband TV application is started, false otherwise
     * @apiNote Checks if hybrid broadcast broadband TV application is started
     */
    public static boolean isInHbbTV() {
        return mIsHbbtvMode;
    }

}
