package common.security;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : Encryption
// PURPOSE          : Encryption of given string using MD5 algorithm + our
//                    own padding
// NOTES            : None
// LAST MODIFIED    :
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030805 AKS012 Auditing - TCC : Performance Category
//  20030716 AKS009 Auditing - TCC : Critical Errors
//  20030711 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.security.*;

/**
 * Encryption of given string using MD5 algorithm + our own padding
 *
 * @author RCN
 */
final public class Encryption {

    /**
     * MD5 based encryption. The logic must not be changed in the method once
     * the strings (eg password) are stored using it.
     *
     * @param str Given String to be encrypted
     *
     * @return The ecrypted fixed length MD5 based string or null if something
     * went wrong.
     */
    public static final String md5(String str) {
        // Get a MD5 algorithm based Message Digest
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        // Update the digest and complete the hash computation by
        // performing final operations such as padding
        md.reset();
        md.update(str.getBytes());
        byte[] hash = md.digest();
        //
        StringBuffer encryptedStr = new StringBuffer();
        int v = 0;
        for (int i = 0; i < hash.length; i++) {
            v = hash[i] & 0xFF;
            if (v < 16) {
                encryptedStr.append("0");
            }
            encryptedStr.append(Integer.toString(v, 16).toUpperCase());
        }

        // Return the encrypted fixed length string
        return encryptedStr.toString();
    }

    public static void main(String[] args) {
        System.out.println("admin: " + md5("admin"));
    }
}
