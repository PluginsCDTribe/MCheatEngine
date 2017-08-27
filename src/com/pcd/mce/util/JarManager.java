package com.pcd.mce.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarManager {
public static List<Class<?>> LoadJar(File jarFile) {
	 List<Class<?>> classList = new ArrayList<>();
     assert !jarFile.exists();
     assert !jarFile.getName().endsWith(".jar");
	 try {
		JarFile jar = new JarFile(jarFile);
		Enumeration<JarEntry> entrys = jar.entries(); 
		while (entrys.hasMoreElements()) {  
			JarEntry jarEntry = entrys.nextElement();  
			String entryName = jarEntry.getName();
			if(entryName.endsWith(".class") && !entryName.contains("$")) {
				entryName = entryName.replace(".class", "")
							.replaceAll("/", "\\.");
				try {
					classList.add(Class.forName(entryName));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} 
		jar.close();
	} catch (IOException e) {
		e.printStackTrace();
	}  
	 return classList;
}
}
