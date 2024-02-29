package com.vestel.tv.middleware;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.ArrayList;


/**
 * @brief This class performs file and folder related operations
 */
@SuppressWarnings("WeakerAccess")
public class VestelFileSystem
{
    private static final String TAG = "VestelFileSystem";


    /**
     * @brief Reads file revision information from ini file
     * @param file_path Absolute path of ini file
     * @return File revision information if available or "Rev: N/A" otherwise
     */
    public static String getFileRevision(String file_path)
    {
        String revision = "Rev: N/A";
        String str = String.valueOf(getIniParameter(file_path, "REVISION:Rev"));

        if (str != null && !str.isEmpty())
        {
            revision = str.substring(str.indexOf('$') + 1, str.lastIndexOf('$'));
        }

        return revision;
    }

    /**
     * @brief Reads the value of given field from ini file
     * @param file_path Absolute path of ini file
     * @param field Field to be read in "<section_name>:<key_name>" format
     * @return The string representation of the value of given field
     */
    public static String getIniParameter(String file_path, String field)
    {
        return "";
    }

    /**
     * @brief Write the value of given field to ini file
     * @param file_path Absolute path of ini file
     * @param field Field to be written in "<section_name>:<key_name>" format
     * @param value String representation of the value to be written
     * @return RET_OK if the value is successfully written to ini file, false otherwise
     */
    public static boolean setIniParameter(String file_path, String field, String value)
    {
        return false;
    }

    /**
     * @brief Calculate MD5 checksum of the specified file
     * @param file_path Absolute path of the file
     * @return The string representation of MD5 checksum
     */
    public static String calculateMD5(String file_path)
    {
        return "";
    }

    /**
     * @brief Checks if the specified file exists in the file system of device
     * @param file_path Absolute path of the file
     * @return True if the specified file exists in the file system of device, false otherwise
     */
    public static synchronized boolean isFileExist(String file_path)
    {
        return false;
    }

    /**
     * @brief Removes the specified file from the file system of device
     * @param file_path Absolute path of the file
     * @return True if the specified file is successfully removed, false otherwise
     */
    public static synchronized boolean removeFile(String file_path)
    {
        return false;
    }

    /**
     * @brief Checks if the specified folder exists in the file system of device
     * @param folder_path Absolute path of the folder
     * @return True if the specified folder exists in the file system of device, false otherwise
     */
    public static synchronized boolean isFolderExist(String folder_path)
    {
        return false;
    }

    /**
     * @brief Creates the specified folder in the file system of device if it does not exist
     * @param folder_path Absolute path of the folder
     * @return True if the specified folder is successfully created, false otherwise
     */
    public static synchronized boolean createFolder(String folder_path)
    {
        return false;
    }

    /**
     * @brief Creates the specified folder in the file system of device if it does not exist
     * @param folder_path Absolute path of the folder
     * @param folder_mode Numerical format that accepts up to four digits. The three rightmost digits
     *        define permissions for file user, group and others. The optional leading digit,
     *        when 4 digits are given, specifies the special setuid, setgid, and sticky flags.
     *        Each digit of the three rightmost digits represents an octal value, which is the sum
     *        of values defined for "read" (4), "write" (2) and "execute" (1) permissions.
     * @return True if the specified folder is successfully created, false otherwise
     */
    public static synchronized boolean createFolder(String folder_path, int folder_mode)
    {
        boolean success = createFolder(folder_path);

        if (success)
        {
            changeFileMode(folder_path, folder_mode);
        }
        return success;
    }

    /**
     * @brief Changes access permissions of the specified file
     * @param file_path Absolute path of the file
     * @param file_mode Numerical format that accepts up to four digits. The three rightmost digits
     *        define permissions for file user, group and others. The optional leading digit,
     *        when 4 digits are given, specifies the special setuid, setgid, and sticky flags.
     *        Each digit of the three rightmost digits represents an octal value, which is the sum
     *        of values defined for "read" (4), "write" (2) and "execute" (1) permissions.
     * @return True file access permissions are successfully set, false otherwise
     */
    public static synchronized boolean changeFileMode(String file_path, int file_mode)
    {
        return false;
    }

    /**
     * @brief Commits changes in buffer cache to the file system of device
     * @return True changes are successfully committed to the file system, false otherwise
     */
    public static synchronized boolean syncFs()
    {
        return false;
    }

    public static synchronized int encryptSystemKey()
    {
        return -1;
    }

    /**
     * @brief Writes data to the specified file
     * @param file_path Absolute path of the file
     * @param data Array of bytes that will be written to the file
     * @param write_mode String in which each letter denotes a writing mode:
     *        "a" : data will be written to the end of file which will be created if it does not exist
     *        "b" : data will be written in binary mode
     *        "w" : if the file exists, content will be overwritten, otherwise file will be created
     * @return RET_OK if the data is successfully written to the file
     */
    public static synchronized int writeFileByte(String file_path, ArrayList<Byte> data, String write_mode)
    {
        return -1;
    }

    /**
     * @brief Reads data from the specified file in binary format
     * @param file_path Absolute path of the file
     * @param length Number of bytes to be read from the file
     * @param position The byte offset specifying the start position of reading
     * @return Array of bytes that are read from the file
     */
    public static synchronized ArrayList<Byte> readFileByte(String file_path, int length, long position)
    {
        return null;
    }

    /**
     * @brief Creates the specified file in the file system of device if it does not exist
     * @param file_path Absolute path of the file
     * @return True if the specified file is successfully created, false otherwise
     */
    public static synchronized boolean createFile(String file_path)
    {
        return false;
    }

    /**
     * @brief Creates the specified file in the file system of device if it does not exist
     * @param file_path Absolute path of the file
     * @param file_mode Numerical format that accepts up to four digits. The three rightmost digits
     *        define permissions for file user, group and others. The optional leading digit,
     *        when 4 digits are given, specifies the special setuid, setgid, and sticky flags.
     *        Each digit of the three rightmost digits represents an octal value, which is the sum
     *        of values defined for "read" (4), "write" (2) and "execute" (1) permissions.
     * @return True if the specified file is successfully created, false otherwise
     */
    public static synchronized boolean createFile(String file_path, int file_mode)
    {
        boolean success = createFile(file_path);

        if (success)
        {
            changeFileMode(file_path, file_mode);
        }
        return success;
    }

    /**
     * @brief Copies the specified file to the specified destination
     * @param input_file Absolute path of the file
     * @param destination Absolute path of the destination file or folder
     * @param file_mode Numerical format that accepts up to four digits. The three rightmost digits
     *        define permissions for file user, group and others. The optional leading digit,
     *        when 4 digits are given, specifies the special setuid, setgid, and sticky flags.
     *        Each digit of the three rightmost digits represents an octal value, which is the sum
     *        of values defined for "read" (4), "write" (2) and "execute" (1) permissions.
     * @return True if the specified file is successfully copied, false otherwise
     */
    public static synchronized boolean copyFile(String input_file, String destination, int file_mode)
    {
        return false;
    }

    /**
     * @brief Copies the specified file to the specified folder
     * @param input_file Absolute path of the file
     * @param folder Absolute path of the folder
     * @return True if the specified file is successfully copied, false otherwise
     */
    public static synchronized boolean copyFileToFolder(String input_file, String folder)
    {
        boolean success;

        try
        {
            Path source = Paths.get(input_file);
            Path target = Paths.get(folder).resolve(source.getFileName());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            success = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}

