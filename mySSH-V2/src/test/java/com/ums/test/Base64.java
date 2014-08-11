package com.ums.test;

public class Base64 {
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++)
		{
			byte[] temp = {(byte)i};
			System.out.printf("%c:%d->%s ", i, i, Base64Util.encryptBASE64(temp));
		}
		System.out.println("\n--------------------------------------------\n");
		
		byte[] b = "1".getBytes();
		System.out.println(b[0]);
		//System.out.println(b[1]);
		System.out.println(Base64Util.encryptBASE64(b));
	}
}
