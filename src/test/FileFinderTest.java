package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gui.DataPanel;
import static java.nio.file.StandardCopyOption.*;

import org.junit.Before;
import org.junit.Test;

public class FileFinderTest {

	DataPanel data;
	
	/*
	@Before
	public void setUp(){
		try {
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log_backup.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Files.delete(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log_backup.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	@Test
	public void simpleFindThreeTest() {
		data = new DataPanel(null, null);
		assertEquals("H:\\git\\SEG24\\ExampleInputData\\click_log.csv", data.getClickField());
		assertEquals("H:\\git\\SEG24\\ExampleInputData\\impression_log.csv", data.getImpressionField());
		assertEquals("H:\\git\\SEG24\\ExampleInputData\\server_log.csv", data.getServerField());
	}

	@Test
	public void breadthFirstTest(){
		try {
			data = new DataPanel(null, null);
			File testDir = new File("H:\\git\\SEG24\\ExampleInputData\\breadthTest");
			testDir.mkdir();
			
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\breadthTest\\click_log.csv"), REPLACE_EXISTING);
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\impression_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\breadthTest\\impression_log.csv"), REPLACE_EXISTING);
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\server_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\breadthTest\\server_log.csv"), REPLACE_EXISTING);
			
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\click_log.csv", data.getClickField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\impression_log.csv", data.getImpressionField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\server_log.csv", data.getServerField());
			
			deleteDirectory(testDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void twoFromThreeTest(){

		try {
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log_backup.csv"), REPLACE_EXISTING);
			Files.delete(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"));
			
			data = new DataPanel(null, null);
			assertEquals("", data.getClickField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\impression_log.csv", data.getImpressionField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\server_log.csv", data.getServerField());
			
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log_backup.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), REPLACE_EXISTING);
			Files.delete(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log_backup.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void differentDepthsTest(){
		
		File testDir = new File("H:\\git\\SEG24\\ExampleInputData\\depthTest");
		testDir.mkdir();
		
		try {
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\depthTest\\click_log.csv"), REPLACE_EXISTING);
			Files.delete(Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"));
			
			data = new DataPanel(null, null);
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\depthTest\\click_log.csv", data.getClickField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\impression_log.csv", data.getImpressionField());
			assertEquals("H:\\git\\SEG24\\ExampleInputData\\server_log.csv", data.getServerField());
			
			Files.copy(Paths.get("H:\\git\\SEG24\\ExampleInputData\\depthTest\\click_log.csv"), Paths.get("H:\\git\\SEG24\\ExampleInputData\\click_log.csv"), REPLACE_EXISTING);
			deleteDirectory(testDir);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean deleteDirectory(File directory) {
		if(directory.exists()){
			for (File f: directory.listFiles()){
				if (f.isFile()){
					f.delete();
				} else if (f.isDirectory()){
					deleteDirectory(f);
				}
			}
		}
		return(directory.delete());
	}
	
}
