package com.vestel.tv.middleware;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

/**
 * @brief This class parses application version from an XML resource
 */
public final class VestelAppVersionParser
{
    private static final String TAG = "AppVersionParser";
    private static final String TAG_APP_VERSION = "app-version";
    private static final String TAG_VERSION = "version";

    private static final String ATTR_DEVICE = "device";
    private static final String ATTR_COUNTRY = "country";
    private static final String ATTR_VERSION = "version";

    private static VestelAppVersionParser m_app_version_parser = null;

    private final Context m_context;
    private final String m_device_name;
    private String m_app_version;
    private final int m_xml_resource_id;

    /**
     * @brief Creates an instance of the class with the given Android application context
     * @param context Android application context
     * @param xml_resource_id Unique identifier of XML resource inside Android application
     */
    private VestelAppVersionParser(Context context, int xml_resource_id)
    {
        m_context = context;
        m_device_name = VestelSystemProperties.getDeviceName();
        m_app_version = "";
        m_xml_resource_id = xml_resource_id;

        VestelLog.debug(TAG, "Create Instance");
    }

    /**
     * @brief Returns the static instance of the class with the given Android application context
     * @param context Android application context
     * @param xml_resource_id Unique identifier of XML resource inside Android application
     * @return Static instance of the class
     */
    public static VestelAppVersionParser getInstance(Context context, int xml_resource_id)
    {
        if (m_app_version_parser != null) {
            m_app_version_parser = new VestelAppVersionParser(context, xml_resource_id);
        }
        return m_app_version_parser;
    }

    /**
     * @brief Returns the application version defined for the specified country
     * @param country Country name as defined in XML resource
     * @return Application version
     */
    public String getAppVersion(String country)
    {
        VestelLog.debug(TAG, "getAppVersion for: " + country);
        if (m_app_version == null)
        {
            m_app_version = getAppVersionByCountry(country);
        }

        return m_app_version;
    }

    /**
     * @brief Returns the application version defined for the specified country
     * @param country Country name as defined in XML resource
     * @return Application version
     */
    private String getAppVersionByCountry(String country)
    {
        List<AppVersionInfo> appVersions = readAppVersions();
        String app_version = null;

        for(int i = 0; i < appVersions.size(); i++)
        {
            if(country.equals(appVersions.get(i).getCountry()))
            {
                app_version = appVersions.get(i).getAppVersion();
                break;
            }
        }

        VestelLog.debug(TAG, "app_version " + app_version);
        return app_version;
    }

    /**
     * @brief Parses application versions from XML resource into an array of AppVersionInfo objects
     * @return Array of AppVersionInfo objects
     */
    private List<AppVersionInfo> readAppVersions()
    {
        List<AppVersionInfo> appVersions = new ArrayList<>();
        boolean continue_tag = true;

        try
        {
            XmlResourceParser parser = m_context.getResources().getXml(m_xml_resource_id);
            while (parser.next() != XmlResourceParser.START_TAG);

            parser.next();

            while (parser.getEventType() != XmlResourceParser.END_DOCUMENT)
            {
                switch (parser.getEventType())
                {
                    case XmlResourceParser.START_TAG:
                        if (TAG_APP_VERSION.equals(parser.getName()))
                        {
                            String device = parser.getAttributeValue(null, ATTR_DEVICE);
                            if (!m_device_name.equals(device))
                            {
                                continue_tag = false;
                            }
                        }
                        else if (continue_tag && (TAG_VERSION.equals(parser.getName())))
                        {
                            String country = parser.getAttributeValue(null, ATTR_COUNTRY);
                            String version = parser.getAttributeValue(null, ATTR_VERSION);
                            appVersions.add(new AppVersionInfo(country, version));
                        }
                        break;

                    case XmlResourceParser.END_TAG:
                        if (TAG_APP_VERSION.equals(parser.getName()))
                        {
                            continue_tag = true;
                        }
                        break;

                    default:
                        break;
                }

                parser.next();
            }

            parser.close();
        }
        catch (XmlPullParserException xppe)
        {
            VestelLog.error(TAG, "Ill-formatted app_versions.xml file");
        }
        catch (java.io.IOException ioe)
        {
            VestelLog.error(TAG, "Unable to read app_versions.xml file");
        }
        return appVersions;
    }

    /**
     * @brief Helper class to hold application version data parsed from XML resource.
     */
    private static final class AppVersionInfo
    {
        private final String m_country;
        private final String m_version;

        private AppVersionInfo(String country, String version)
        {
            m_country = country;
            m_version = version;
        }

        private String getCountry()
        {
            return m_country;
        }

        private String getAppVersion()
        {
            return m_version;
        }
    }
}

