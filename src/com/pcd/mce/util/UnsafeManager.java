package com.pcd.mce.util;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
@SuppressWarnings("restriction")
public class UnsafeManager {
public static Unsafe instance;
public static void initialize() {
if(instance != null) {
return;
}
try {
	Field f = Unsafe.class.getDeclaredField("theUnsafe");
	f.setAccessible(true);  
	instance = (Unsafe) f.get(null);
} catch (Exception e) {
	e.printStackTrace();
} 	
}
public UnsafeManager() {
	assert instance == null;
}
}
