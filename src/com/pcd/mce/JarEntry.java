package com.pcd.mce;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class JarEntry implements Listener {
static List<Class<?>> classList = new ArrayList<>();
@EventHandler
public void onPluginLoad(PluginEnableEvent e) {
}
}
