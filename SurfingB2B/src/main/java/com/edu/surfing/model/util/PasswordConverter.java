package com.edu.surfing.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordConverter {
	public static String getCovertedPassword(String pass) {
		// 자바 보안관련 기능을 지원하는 API가 모여있는 패키지 = java.security
		StringBuffer hexString = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pass.getBytes("UTF-8"));

			// String은 불변! 따라서 값이 변경될 수 없음
			// 누적해야하는 경우 : StringBuffer 또는 StringBuilder 객체 사용
			hexString = new StringBuffer();

			for (byte bt : hash) {
				String hex = Integer.toHexString(0xff & bt);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
}
