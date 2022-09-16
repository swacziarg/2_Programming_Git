import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GitTest {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
//		File directory = new File("Objects\\");
//		directory.mkdir();
		File jun = new File("junit.txt");
		jun.createNewFile();
		BufferedWriter w = new BufferedWriter(new FileWriter(jun));
		w.write("this is a test message \nand i love cows");
		w.close();
		File wun = new File("wunit.txt");
		wun.createNewFile();
		BufferedWriter wr = new BufferedWriter(new FileWriter(wun));
		wr.write("this is a test message \nand i love rotundae");
		wr.close();
	}
	@Test
	void checkBlob() throws IOException {
		Blob test = new Blob("junit.txt");
		
		BufferedReader r = new BufferedReader(new FileReader("objects\\"+test.generateSHA1(test.content)));
		String s = "";
		while (r.ready()) {
			s +=(r.readLine());
			if (r.ready()) {
				s+="\n";
			}
		}
		r.close();
		assertTrue(s.equals("this is a test message \nand i love cows"));
		File directory = new File("objects\\");
		if (directory.isDirectory()) {
	        for (File sub : directory.listFiles()) {
	            sub.delete();
	        }
	    }
		directory.delete();

	}
	@Test
	void checkIndexDirCreation() throws IOException {
		Index test = new Index();
		test.initialize();
		File directory = new File("objects\\");
		File f = new File("index");
		//tests index and directory existence
		assertTrue(f.exists()); 
		assertTrue(directory.exists());
	}
	@Test
	void checkAddRem() throws IOException {
		Index t = new Index();
		t.initialize();
		File d = new File("objects\\");
		File f = new File("index");
		t.add("junit.txt");
		t.add("wunit.txt");
		File newFile = new File("objects\\" + "0c8748dea0ec617687ca6ea76de5b3d395acaa3e");
		assertTrue(newFile.exists());
		File nwFile = new File("objects\\" + "849ceab4c235df5f84fb562d9093fdff89d4b798");
		assertTrue(nwFile.exists());
		BufferedReader r = new BufferedReader(new FileReader("index"));
		String s = "";
		while(r.ready()) {
			s+=r.readLine();
			if (r.ready()) {
				s+="\n";
			}
		}
		r.close();
		assertTrue(s.equals("wunit.txt : 849ceab4c235df5f84fb562d9093fdff89d4b798\n" + "junit.txt : 0c8748dea0ec617687ca6ea76de5b3d395acaa3e"));
		
		t.remove("junit.txt");
		t.remove("wunit.txt");
		File nFile = new File("objects\\" + "849ceab4c235df5f84fb562d9093fdff89d4b798");
		assertFalse(nFile.exists());
		File nile = new File("objects\\" + "0c8748dea0ec617687ca6ea76de5b3d395acaa3e");
		assertFalse(nile.exists());
		BufferedReader re = new BufferedReader(new FileReader("index"));
		String ss = "";
		while(re.ready()) {
			ss+=re.readLine();
			if (re.ready()) {
				ss+="\n";
			}
		}
		re.close();
		assertTrue(ss.equals(""));
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File hi = new File("junit.txt");
		File h = new File("wunit.txt");
		File ind = new File("index");

		File directory = new File("objects\\");
		if (directory.isDirectory()) {
	        for (File sub : directory.listFiles()) {
	            sub.delete();
	        }
	    }
		directory.delete();
		h.delete();
		ind.delete();
		hi.delete();
	}
 
	

}
