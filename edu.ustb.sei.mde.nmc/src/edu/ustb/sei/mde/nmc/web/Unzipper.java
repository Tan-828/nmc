package edu.ustb.sei.mde.nmc.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzipper {
    private final static int BUFFER_SIZE = 2048;
    private final static String ZIP_FILE = "C:\\Users\\10242\\Desktop\\files.zip";
    private final static String DESTINATION_DIRECTORY = "C:\\Users\\10242\\Desktop\\files";
    private final static String ZIP_EXTENSION = ".zip";
    
    public static void unzip() {
        System.out.println("Trying to unzip file " + ZIP_FILE);
        Unzipper unzip = new Unzipper();
        if (unzip.unzipToFile(ZIP_FILE, DESTINATION_DIRECTORY)) {
            System.out.println("Succefully unzipped to the directory "
                    + DESTINATION_DIRECTORY);
        } else {
            System.out.println("There was some error during extracting archive to the directory "
                    + DESTINATION_DIRECTORY);
        }
    }

    public boolean unzipToFile(String srcZipFileName, String destDirectoryName) {
        try {
            BufferedInputStream bufIS = null;
            // create the destination directory structure (if needed)
            File destDirectory = new File(destDirectoryName);
            destDirectory.mkdirs();

            // open archive for reading
            File file = new File(srcZipFileName);
            ZipFile zipFile = new ZipFile(file, ZipFile.OPEN_READ);

            //for every zip archive entry do
            Enumeration<? extends ZipEntry> zipFileEntries = zipFile.entries();
            while (zipFileEntries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                System.out.println("\tExtracting entry: " + entry);

                //create destination file
                File destFile = new File(destDirectory, entry.getName());

                //create parent directories if needed
                File parentDestFile = destFile.getParentFile();
                parentDestFile.mkdirs();

                if (!entry.isDirectory()) {
                    bufIS = new BufferedInputStream(
                            zipFile.getInputStream(entry));
                    int currentByte;

                    // buffer for writing file
                    byte data[] = new byte[BUFFER_SIZE];

                    // write the current file to disk
                    FileOutputStream fOS = new FileOutputStream(destFile);
                    BufferedOutputStream bufOS = new BufferedOutputStream(fOS, BUFFER_SIZE);

                    while ((currentByte = bufIS.read(data, 0, BUFFER_SIZE)) != -1) {
                        bufOS.write(data, 0, currentByte);
                    }

                    // close BufferedOutputStream
                    bufOS.flush();
                    bufOS.close();

                    // recursively unzip files
                    if (entry.getName().toLowerCase().endsWith(ZIP_EXTENSION)) {
                        String zipFilePath = destDirectory.getPath() + File.separatorChar + entry.getName();

                        unzipToFile(zipFilePath, zipFilePath.substring(0,
                                zipFilePath.length() - ZIP_EXTENSION.length()));
                    }
                }
            }
            bufIS.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

