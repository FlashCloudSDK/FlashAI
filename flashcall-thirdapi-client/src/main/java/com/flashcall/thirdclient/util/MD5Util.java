package com.flashcall.thirdclient.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public MD5Util() {
    }

    public static String toMD5(String s) {
        String result = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            result = (new BigInteger(1, m.digest())).toString(16);
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        }

        return result;
    }
}
