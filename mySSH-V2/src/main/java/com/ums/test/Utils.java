package com.ums.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class Utils {
	public static void main(String[] args) throws Exception {
		
		String mod = (new BigInteger("dd6a5ed5fb23e481d936cc684434f7e55afeb731c51b93e7db61bf5d11da41f270095a48d021c442f2bd9166c28c4eacdb92f020fcaa336688a43ad138ef1b2c3c127458c733b1ca9ad568dbca9ef398a1f7721e64f2a0e51a233182f62c2b7f9a5ff74c3c8aff019cee19ccd89663b0735c339201f4fac048021d10060a84bd", 16)).toString();
		String exp = (new BigInteger("833cb23b88e10c673e957170f7c4a18eab71c0a9da57ef42055199b893855783588ee299b7ebdbc586d57fe275bbee421cdfaa3f1323dad592762d7aa79169fba2df099c2b96f76a4febdbafd0e5be09748442e551d00978574419084d0de25f6cd8a236e067628bbd81263a65ef01b04a919f8e8b186b457d21569c24e41f21", 16)).toString();
		//RSAPublicKey pubKey = RSAUtils.getPublicKey(mod, exp);  
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(mod, exp);  
        
        /*System.out.println();
        FileOutputStream fos = new FileOutputStream("mypub.key");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(priKey);
        oos.close();
        fos.close();*/
        
       // System.out.println(priKey.toString());
        
        BufferedReader br = new BufferedReader(new FileReader("src/main/sources/rsa/rsa_private_key.pem"));
        String s= br.readLine();
        String str = "";
        s = br.readLine();
        while (s.charAt(0) != '-'){
        	str += s + "\r";
        	s = br.readLine();
        }
        System.out.println(str);
        
        System.out.println(RSAEncrypt.DEFAULT_PRIVATE_KEY);
        byte b[] = Base64Util.decryptBASE64(RSAEncrypt.DEFAULT_PRIVATE_KEY);
        
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        RSAPrivateKey privateKey = (RSAPrivateKey)kf.generatePrivate(keySpec);
        
       System.out.println(privateKey.getModulus().toString(16));
       System.out.println(privateKey.getPrivateExponent().toString(16));
          
	}
	
	
}
