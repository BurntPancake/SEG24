package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class FileFinder {

	private Set<String> namesToSearch;
	List<String> foundFiles = new ArrayList<String>();
	Queue<File> toCheck = new LinkedList<File>();

	public Set<String> getNamesToSearch() {
		return namesToSearch;
	}

	public void setNamesToSearch(Set<String> namesToSearch) {
		this.namesToSearch = namesToSearch;
	}
	
	public void getNeighbours(File directory){
		for (File f: directory.listFiles()){
			toCheck.add(f);
		}
	}
	
	public void searchDirectory(File directory, String file1, String file2, String file3){
		
		setNamesToSearch(new HashSet<String>(Arrays.asList(new String[] {file1, file2, file3})));
		
		if (directory.isDirectory()) {
			search(directory);
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
		
	}
	
	
	public void search (File f){
		
		//System.out.println(f.getAbsoluteFile());
		if (f.isDirectory()){
			this.getNeighbours(f);
		} else if (f.isFile() && getNamesToSearch().contains(f.getName().toLowerCase())){
			System.out.println("Found: " + f.getAbsolutePath().toLowerCase());
			this.foundFiles.add(f.getAbsolutePath().toLowerCase());
		}
		//System.out.println(this.toCheck.peek());
		try{
			search(this.toCheck.remove());
		}catch (Exception e){
			System.out.println("Search completed!");
		}
	}

	
	/*
	public void searchDirectory(File directory, String file1, String file2, String file3) {
		
		setNamesToSearch(new HashSet<String>(Arrays.asList(new String[] {file1, file2, file3})));
		
		if (directory.isDirectory()) {
			search(directory);
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
	 
	}
	
	private void search(File file){
		
		if (file.isDirectory()){
			System.out.println("Searching directory");
			
			if (file.canRead()){
				for (File f: file.listFiles(ff)){
					if (f.isDirectory()){
						search(f);
					} else if (getNamesToSearch().contains(f.getName().toLowerCase())){
						foundFiles.add(f.getAbsoluteFile().toString());						
					}
					
				}
				
			}
			
		} else {
			System.out.println(file.getAbsoluteFile() + "Access denied!");
		}
		
	}
	
}

	*/
}