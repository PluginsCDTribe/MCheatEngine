package com.pcd.mce;

import org.bukkit.Bukkit;


import org.bukkit.plugin.java.JavaPlugin;

import com.pcd.mce.util.UnsafeManager;


public class Main extends JavaPlugin {
public static boolean enableCpp = true;
@Override
public void onEnable() {
getLogger().info("=====[MCheatEngine]=====");
getLogger().info("MCheatEngine�Ѿ�����!����LocyDragon,QQ2424441676");
getLogger().info("����C++��ָ̬�����......");
try {
	System.load(Main.class.getResource("Main.dll").toString().substring(6));
}catch (Throwable e) {
	getLogger().info("����ʧ��");
enableCpp = false;
}
if (enableCpp) {
	getLogger().info("���سɹ�");
}
UnsafeManager.initialize();
Bukkit.getPluginManager().registerEvents(new JarEntry(), this);
getCommand("mce").setExecutor(new Commands());
}
native static void putInt(String name,long offset,int target);
}
