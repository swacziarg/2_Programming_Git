import java.io.IOException;

public class BlobTester {
	
	public static void main(String[] args) throws IOException
	{
		Blob blob = new Blob("textfile.txt");
		System.out.println(blob.generateSHA1(blob.content));
		
	}
}
