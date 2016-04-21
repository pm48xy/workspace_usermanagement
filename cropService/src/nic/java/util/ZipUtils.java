package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ZipUtils
// PURPOSE          : Zip methods.
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Package re-structuring
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030804 SIM005 Message grammar correction
//  20030702 JIS013 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
import java.util.zip.*;

/**
 * Provides number of utility static method for compressing the file.
 *
 * @author JIS
 */
public abstract class ZipUtils {

    /**
     * Zip the given stream content into the given File
     *
     * @param is Input stream providing the content to be zipped
     * @param dataFileName The name of the file that will be encompass within
     * thw Zip file having the content
     * @param zipFile Zip file with path to which the Zip file will be written
     *
     * @throws IOException
     */
    public static void zip(InputStream is,
            String dataFileName,
            File zipFile) throws IOException {

        // Create the Zip output stream
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zipout = new ZipOutputStream(fos);

        // Create an entry (ie the content file name) for the Zip file
        ZipEntry zent = new ZipEntry(dataFileName);
        zipout.putNextEntry(zent);

        // Write the given data to the Zip output stream
        byte[] b = new byte[1024]; // Read 1K at a time
        int bytesRead = 0; // Number of bytes read by "read(byte[])" call
        while ((bytesRead = is.read(b)) != -1) {
            zipout.write(b, 0, bytesRead);
        }

        // Close the entry and the output streams
        zipout.closeEntry();
        zipout.close();
        fos.close();
        is.close();
    }

    /**
     * Zip the given File. This will zip the file in the dir where the source
     * file is and the zip file name will be the source file name minus any last
     * '.extention' plus ".zip".
     *
     * @param file Given file
     *
     * @throws IOException
     */
    public static void zip(File file) throws IOException {
        // Check the input
        if (file == null || !file.exists()) {
            throw new IOException("File " + file.getName() + " does not exist.");
        }

        // Format the inputs required for zip.
        // Generate the zip file name (fileNameWithoutExt.zip)
        FileInputStream fis = new FileInputStream(file);
        String dataFileName = file.getName();
        int idx = dataFileName.lastIndexOf(".");
        String zipFileName = (idx == -1)
                ? dataFileName + ".zip"
                : dataFileName.substring(0, idx) + ".zip";
        String zipFileNameWithPath = file.getParentFile().getPath() + System.getProperty("file.separator") + zipFileName;
        File zipFile = new File(zipFileNameWithPath);

        // Zip
        zip(fis, dataFileName, zipFile);
    }

    /**
     * Zip the given string content into the given File
     *
     * @param data Text string (to zip)
     * @param dataFileName The name of the file that will be encompass within
     * thw Zip file having the content
     * @param zipFile Zip file path
     *
     * @throws IOException
     */
    public static void zip(String data,
            String dataFileName,
            File zipFile)
            throws IOException {

        // Convert the String into the ByteArray stream
        ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());

        // Zip
        zip(bais, dataFileName, zipFile);
    }

    /**
     * Zip the given source File into the given destination file.
     *
     * @param sourceFile Given source file to be zipped
     * @param destinationFile Zip file to be written.
     * @throws IOException
     */
    public static void zip(File sourceFile,
            File destinationFile)
            throws IOException {

        zip(new FileInputStream(sourceFile), sourceFile.getName(), destinationFile);
    }

    /**
     * Main for testing
     *
     * @throws ZipUtilsException
     */
    public static void main(String[] args) throws ZipUtilsException {
        //checkZip
        String data
                = "<TR BGCOLOR=BBBBBB>"
                + "<TD CLASS=td_mail1 WIDTH=2% VALIGN=TOP>#</TD>"
                + "<TD CLASS=td_mail1 WIDTH=8% VALIGN=TOP>date</TD>"
                + "<TD CLASS=td_mail1 WIDTH=15% VALIGN=TOP>from</TD>"
                + "<TD CLASS=td_mail1 WIDTH=15% VALIGN=TOP>to</TD>"
                + "<TD CLASS=td_mail1 WIDTH=60% VALIGN=TOP>subject</TD>"
                + "</TR>";
        String filePath = "D:\\temp\\rcn.zip";
        File zipFile = new File(filePath);
        try {
            System.out.print("Zipping to " + filePath + "....");
            zip(data, "myzip.txt", zipFile);
            zip(new File("C:\\WINNT\\system.ini"));
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
