package com.minxing365.mxemm.utils;

import com.test.test.map.MD5Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class AESUtil {
	private static final String IV = "a0fe7c7c98e09e8c";

	public static String encrypt(String key, String clearText) {
		byte[] result = null;
		try {
			result = encrypt(toByte(MD5Util.getMD5String(MD5Util.getMD5String(key))), clearText.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(result);
	}

	public static String decrypt(String key ,String clearText) throws Exception{
		byte[] result = null;
		try {
			result = decrypt(toByte(MD5Util.getMD5String(MD5Util.getMD5String(key))), Base64.getDecoder().decode(clearText.getBytes("UTF-8")));
		}catch (Exception e){
			e.printStackTrace();
		}

		return new String(result, "UTF-8");
//		return toHex(result);
	}

	public static String getRawKey() {
		try {
			String seed = System.currentTimeMillis() + "";
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(toByte(seed));
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
//			return Base64.encodeToString(raw, Base64.NO_WRAP);
			return Base64.getEncoder().encodeToString(raw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(IV.getBytes("UTF-8")));
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private  static byte[] decrypt(byte[] raw, byte[] clear) throws Exception{
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(IV.getBytes("UTF-8")));
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] toByte(String hexString) {
//		System.out.println(">>>>>>>>>>>>>>>>>>>" + hexString);
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
//			System.out.println(">>>>>>>>>>>>>>>>>>>" + result[i]);
		}
		return result;
	}

	private static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static void appendHex(StringBuffer sb, byte b) {
		final String HEX = "0123456789abcdef";
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	public static void main(String[] args) throws Exception {
		String key = getRawKey();
		System.out.println("key ====== " + key);

		String topic = "11111111111111111" ;
		System.out.println("topic ====== " + topic);

		String token = encrypt(key,topic);
		System.out.println("token ====== " + token);
		token = Base64.getEncoder().encodeToString(token.getBytes());
		String deTopic = decrypt(key,token);
		System.out.println("deTopic ===== " + deTopic);

	}

}
