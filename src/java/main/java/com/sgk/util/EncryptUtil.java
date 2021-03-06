package com.sgk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class EncryptUtil
{
  public static String md5(String str)
  {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    BASE64Encoder base64en = new BASE64Encoder();
    String newstr = null;
    try {
      newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return newstr;
  }
}