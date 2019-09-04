package servicos;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Sha1 {
	public static String EncodeSha1(String text) {
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(text.getBytes("utf8"));
			sha1 = String.format("%040x", 
					new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha1;
	}
}
