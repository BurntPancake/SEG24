package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class FileFinder {

	private Set<String> namesToSearch;
	List<String> foundFiles = new ArrayList<String>();
	Stack<File> children;

	public Set<String> getNamesToSearch() {
		return namesToSearch;
	}

	public void setNamesToSearch(Set<String> namesToSearch) {
		this.namesToSearch = namesToSearch;
	}
	
	public Stack<File> getNeighbours(File directory){
		children = new Stack<File>();
		for (File f: directory.listFiles()){
			children.add(f);
		}
		return children;
	}
	
	public void searchDirectory(File directory, String file1, String file2, String file3){
		
		setNamesToSearch(new HashSet<String>(Arrays.asList(new String[] {file1, file2, file3})));
		
		if (directory.isDirectory()) {
			int max = 5;
			int curr = 1;
			while (curr <= max){
				//System.out.println("curr = " + curr);
				search(directory, curr);
				if (foundFiles.size() < 3){
					curr ++;
				} else {
					System.out.println("All files found at depth: " + curr);
					break;
				}
			}
				
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
		
	}
	
	
	public void search (File f, int depthLeft){
		
		if (depthLeft >= 0){
			if (f.isDirectory()){
				for(File child: this.getNeighbours(f)){
					search(child, depthLeft - 1);
				}
			} else if (f.isFile() && getNamesToSearch().contains(f.getName().toLowerCase())){
				System.out.println("Found: " + f.getAbsolutePath().toLowerCase());
				Set<String> newSet = this.getNamesToSearch();
				newSet.remove(f.getName().toLowerCase());
				this.setNamesToSearch(newSet);
				this.foundFiles.add(f.getAbsolutePath());
				if (foundFiles.size() == 3) 
					return;
			}

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