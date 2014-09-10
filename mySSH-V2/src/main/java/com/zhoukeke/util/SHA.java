package com.zhoukeke.util;

import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.security.DigestInputStream;  
import java.security.DigestOutputStream;  
import java.security.MessageDigest;  
import java.util.Arrays;  
  
import javax.crypto.Mac;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.PBEKeySpec;  
  
/** 
 * ��ϢժҪ��һ���㷨������ԭʼ��ݶ೤����ϢժҪ�Ľ���ǹ̶����ȵģ���һ�ֲ�������㷨 
 * ԭʼ�������bitλ�ı仯�����ᵼ����ϢժҪ�Ľ���кܴ�Ĳ�ͬ���Ҹ�ݽ�������ԭʼ��ݵĸ��ʼ��͡� 
 * ��ϢժҪ���Կ���ԭʼ��ݵ�ָ�ƣ�ָ�Ʋ�ͬ��ԭʼ��ݲ�ͬ�� 
 *  
 * SHA secure hash algorithm ��ȫ��ϣ�㷨 
 * @author stone 
 * @date 2014-03-11 18:21:16 
 */  
public class SHA {  
      
      
    public static void main(String[] args) throws Exception {  
        encodeByMAC("�й�oP����&*������&802134��");  
          
        encodeBySHA("�й�oP����&*������&802134��");  
          
        shaFile();  
    }  
      
    /** 
     * ʹ��MAC �㷨�� ��ϢժҪ 
     * @param data 
     * @throws Exception 
     */  
    public static void encodeByMAC(String data) throws Exception{  
//      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA1");  
//      SecretKey key = keyGen.generateKey(); //���ÿ����ɵ�key��һ��, �˴�����ʹ��  
          
        PBEKeySpec keySpec = new PBEKeySpec("randomkey^(^&*^%$".toCharArray());  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
        SecretKey key = keyFactory.generateSecret(keySpec);  
          
        /* 
         *  �����ṩ����Ϣ��֤�롱��Message Authentication Code��MAC���㷨�Ĺ��ܡ� 
         *  MAC ����������Կ�ṩһ�ַ�ʽ������ڲ��ɿ������Ͻ��д����洢����Ϣ�������ԡ� 
         *  ͨ������Ϣ��֤���ڹ���������Կ������������֮��ʹ�ã�����֤������֮�䴫�����Ϣ�� 
         *  ���ڼ��ܹ�ϣ����� MAC ����Ҳ���� HMAC��������ܹ�����Կ�� 
         *  HMAC ���������κμ��ܹ�ϣ������ MD5 �� SHA-1�� 
         */  
        Mac mac = Mac.getInstance("HmacSHA1");  
        //�������ֶ�����  
//      Mac mac = Mac.getInstance("HmacSHA256");  
//      Mac mac = Mac.getInstance("HmacSHA384");  
//      Mac mac = Mac.getInstance("HmacSHA512");  
        mac.init(key);  
        byte[] dest = mac.doFinal(data.getBytes());  
        System.out.println(dest.length);  
        System.out.println("MACժҪ��" + Arrays.toString(dest));  
    }  
      
    /** 
     * SHA1����  ʹ����ϢժҪMessageDigest ���� 
     * @throws Exception  
     */  
    public static String encodeBySHA(String str) throws Exception{  
        MessageDigest sha1;  
        sha1 = MessageDigest.getInstance("SHA1");  
        //�������ֲ�����  
//      sha1 = MessageDigest.getInstance("SHA256");  
//      sha1 = MessageDigest.getInstance("SHA384");  
//      sha1 = MessageDigest.getInstance("SHA512");  
          
        sha1.update(str.getBytes()); //�ȸ���ժҪ  
        byte[] digest = sha1.digest(); //��ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣�ڵ��ô˷���֮��ժҪ�����á�  
          
        /* 
         * ʹ��ָ���� byte �����ժҪ���������£�Ȼ�����ժҪ���㡣 
         * Ҳ����˵���˷������ȵ��� update(input)�� 
         * �� update �������� input ���飬Ȼ����� digest()�� 
         */  
//      byte[] digest = sha1.digest(str.getBytes());  
          
        String hex = toHex(digest);  
        //System.out.println("SHA1ժҪ:" + hex);  
        return hex;  
    }  
      
    /** 
     * �ļ����ժҪ 
     * @throws Exception 
     */  
    public static void shaFile() throws Exception {  
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  
        DigestOutputStream dos = new DigestOutputStream(new FileOutputStream(new File("abc.txt")), messageDigest);  
        dos.write("�л����񡭡�&������f*��214��admin*".getBytes());  
        dos.close();  
        byte[] digest = messageDigest.digest();  
        System.out.println("ʹ����д�ļ������ļ���ժҪΪ:" + toHex(digest));  
          
        DigestInputStream dis = new DigestInputStream(new FileInputStream(new File("abc.txt")), messageDigest);  
        byte[] buf = new byte[100];  
        int len;  
        while ((len = dis.read(buf)) != -1) {  
            System.out.println("��ȡ�������Ϊ��" + new String(buf, 0, len));  
        }  
        dis.close();  
        byte[] digest2 = messageDigest.digest();  
        //������ȡ��ϣ������ļ������ˣ� ��ʱ��ժҪ ���� д��ʱ�� һ��  
        System.out.println("ʹ�������ļ������ļ���ժҪΪ:" + toHex(digest2));  
    }  
      
    /** 
     * sha1 ժҪת16���� 
     * @param digest 
     * @return 
     */  
    private static String toHex(byte[] digest) {  
        StringBuilder sb = new StringBuilder();  
        int len = digest.length;  
          
        String out = null;  
        for (int i = 0; i < len; i++) {  
//          out = Integer.toHexString(0xFF & digest[i] + 0xABCDEF); //������ salt  
            out = Integer.toHexString(0xFF & digest[i]);//ԭʼ����  
            if (out.length() == 1) {  
                sb.append("0");//���Ϊ1λ ǰ�油��0  
            }  
            sb.append(out);  
        }  
        return sb.toString();  
    }  
  
}  