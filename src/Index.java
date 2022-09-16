import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class Index {
	
	HashMap<String, String> blobMap = new HashMap<String, String>();
	String location;
	File index;
	
	public Index()
	{
		
	}
	
	public void initialize()
	{
		File directory = new File("./objects/");
		directory.mkdir();
		index = new File("index");
	}
	
	public void add(String fileName) throws IOException
	{
		Blob blob = new Blob(fileName);
		blobMap.put(fileName, blob.sha1);
		PrintWriter out = new PrintWriter(new FileWriter(index));
		for(String key : blobMap.keySet())
		{
			out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.close();
	}
	
	public void remove(String fileName) throws IOException
	{
		File toDelete = new File("./objects/" + blobMap.get(fileName)); 
		toDelete.delete();
	    blobMap.remove(fileName);
	    PrintWriter out = new PrintWriter(new FileWriter(index));
		for(String key : blobMap.keySet())
		{
			out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.close();

	}
	public void Tree(ArrayList<String> s) throws NoSuchAlgorithmException, IOException {
		String str = s.toString();
		
		
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
        writer.write(s.toString());
    
        writer.close();
        
	}

}
