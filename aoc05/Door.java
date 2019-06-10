package aoc05;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Door {
	
	private String doorId = "";
	private int index = 0;
	private MessageDigest md;
	private byte[] digest;
	private BigInteger hashGenerator;
	private String hash;
	private char[] password = "--------".toCharArray();
	
	public Door (String doorId) {
		this.doorId = doorId;
		try {
			md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException exception) {System.out.println("NO SUCH ALGORITHM!!!");}
	}
	
	private void getHash() {
		digest = md.digest((doorId+index).getBytes());
		hashGenerator = new BigInteger(1, digest);
		hash = hashGenerator.toString(16);
		while (hash.length()<32)
			hash = "0" + hash;
		
	}
	
	public void getPassword() throws NumberFormatException {
		int digitCounter = 0;
		while (digitCounter<8) {
			getHash();
			while (!hash.startsWith("00000")) {
				getHash();
				index++;
			}
			if (Character.isDigit(hash.charAt(5))) {
				int passwordIndex = Integer.parseInt(hash.charAt(5)+"");
				if (passwordIndex>=0 && passwordIndex<=7 && password[passwordIndex]=='-') {
					password[passwordIndex] = hash.charAt(6);
					digitCounter++;
					System.out.println(passwordIndex + " : " + hash.charAt(6));
				}
			}
		}
		for (char a : password) {
			System.out.print(a);
		}
	}
	
}
