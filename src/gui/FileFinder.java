package gui;

import java.io.File;
import java.util.*;

public class FileFinder {

	private Set<String> namesToSearch;
	List<String> foundFiles = new ArrayList<String>();

	public Set<String> getNamesToSearch() {
		return namesToSearch;
	}

	public void setNamesToSearch(Set<String> namesToSearch) {
		this.namesToSearch = namesToSearch;
	}
	
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
				for (File f: file.listFiles()){
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
