package aoc14;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class OneTimePad {
	public static final String SALT = "cuanljph";
	
	public static void main (String[] args) throws NoSuchAlgorithmException {
		
		int index = 0;
		int tripleIndex = 0;
		int keys = 0;
		boolean tripleFound = false;
		char result = '#';
		ArrayList<String> hashes = new ArrayList<>();
		
		
		
		while (keys<64) {
			String input = SALT+index;
			if (index>=hashes.size())
				hashes.add(generateHash(input));
			String md5Hash = hashes.get(index);
			if (tripleFound) {
				if (isTherePenta(result,md5Hash)) {
					keys++;
					System.out.printf("%d \t%c \t%d \t%d \t%s",keys,result,tripleIndex,index,md5Hash);
					tripleFound=false;
					index=tripleIndex;
					System.out.println(result);
					
				}
			}
			
			else if ((result=getTriple(md5Hash))!='#') {
				tripleFound = true;
				tripleIndex = index;
			}
			
			if (index>tripleIndex+1000) {
				tripleFound = false;
				index=tripleIndex;
			}
			index++;
		}
		
		
		
		
	}
	
	public static char getTriple (String md5Hash) {

		for(int i=0;i<md5Hash.length()-2;i++) {
			char wanted = md5Hash.charAt(i);
			if (wanted==md5Hash.charAt(i+2) && wanted==md5Hash.charAt(i+1))
				return wanted;
		}
		
		return '#';
		
	}
	
	public static boolean isTherePenta (char a, String md5Hash) {

		if (md5Hash.contains(""+a+a+a+a+a))
			return true;
		return false;
	}
	
	public static String generateHash (String input) throws NoSuchAlgorithmException {

		String md5Hash = input;
		
		for (int i=0;i<2017;i++) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(md5Hash.getBytes());
			byte[] digest = md.digest();
			BigInteger hashGenerator = new BigInteger(1,digest);
			md5Hash = String.format("%032x",hashGenerator);

		}
		
		return md5Hash;
		
		
	}
}
