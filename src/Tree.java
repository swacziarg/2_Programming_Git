import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tree{
	public Tree(ArrayList<String> s) throws NoSuchAlgorithmException, IOException{
		String str = "";
		int count = 0;
	    for (String b : s) {
	    	if (count>0) {
	    		str +=("\n");
	    	}
	    	count++;
	    	str+= b;
	    	
	    	
	    }
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		 
	     // digest() method is called
	     // to calculate message digest of the input string
	     // returned as array of byte
	    byte[] messageDigest = md.digest(str.getBytes());
	
	     // Convert byte array into signum representation
	    BigInteger no = new BigInteger(1, messageDigest);
	
	     // Convert message digest into hex value 
	    String hashtext = no.toString(16);
	
	     // Add preceding 0s to make it 32 bit
	    while (hashtext.length() < 32) {
	        hashtext = "0" + hashtext;
	    }
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter("objects" + "\\" +hashtext));
	    writer.write(str);
	    writer.close();
	}
}
