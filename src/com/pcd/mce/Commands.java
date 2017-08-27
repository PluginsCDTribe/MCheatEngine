package com.pcd.mce;

import java.io.ByteArrayOutputStream;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.objectweb.asm.ClassReader;  
import org.objectweb.asm.ClassWriter;
import com.pcd.mce.util.JarManager;


public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(!sender.isOp()) {
			return false;
		}
		if(args[0].equalsIgnoreCase("version")) {
		sender.sendMessage(ChatColor.GREEN+"������ʹ��PCDС���ԭ�����:MCheatEngine");
		sender.sendMessage(ChatColor.GREEN+"����:LocyDragon");
		sender.sendMessage(ChatColor.GREEN+"�汾��: "+PluginVersion.version);
		}else if(args[0].equalsIgnoreCase("change")) {
			sender.sendMessage(ChatColor.GREEN+"д����������Ҫ�رղ���������ֶ�����������ᵼ��д��ʧ��.");
			if(args.length == 5) {
			String endjar = args[4];
			Plugin targetPlugin = Bukkit.getPluginManager().getPlugin(args[1]);
			File path = new File(".//plugins//"+args[1]+".jar");
			if(!path.exists()) {
				sender.sendMessage(ChatColor.GREEN+"��������ֵ��쳣!���������!");
				sender.sendMessage(ChatColor.GREEN+"ָ��ִ��ʧ��.");
				return false;
			}
			if(args[1].equalsIgnoreCase(endjar)) {
				sender.sendMessage(ChatColor.RED+"ԭ������͸��ĺ����ֲ�����ͬ!");
				return false;
			}
			List<Class<?>> list = JarManager.LoadJar(path);
			JarOutputStream stream = null;
			JarInputStream firstJar = null;
			try {
				stream = new JarOutputStream(new FileOutputStream(".//plugins//"+endjar+".jar"));
				firstJar = new JarInputStream(new FileInputStream(path));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sender.sendMessage(ChatColor.GREEN+"���ڿ�ʼ��ȡ���Դ����.");
			sender.sendMessage(ChatColor.AQUA+"���ڿ�ʼд������������ڹر�!�����ֶ�����������ᵼ��д��ʧ��!����config......");
			for(Class<?> c : list) {
				try {
					ClassReader r = null;
					InputStream instream = null;
					//if(!c.getName().contains("$")) {
						instream = c.getClassLoader().getResourceAsStream(c.getName().replace(".", "/")+".class");
						r = new ClassReader(instream);
					//c.getClassLoader().getResourceAsStream(c.getName().replace(".", "/")+".class")
					//}
					//else {
					//	continue;
					//}
					ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
					r.accept(new MethodChangeClassAdapter(cw,ChatColor.translateAlternateColorCodes('&',args[2]),ChatColor.translateAlternateColorCodes('&',args[3])),ClassReader.SKIP_DEBUG);
					try {
						java.util.jar.JarEntry entry = new java.util.jar.JarEntry(c.getName().replace(".", "/")+".class");
						stream.putNextEntry(entry);
						stream.write(cw.toByteArray());
						stream.closeEntry();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				
				} catch (IOException e) {
					sender.sendMessage(ChatColor.GREEN+"��������ֵ��쳣!ִ��ʧ��!��鿴����̨.");
					e.printStackTrace();
					continue;
				}
			}
			try {
				while(true) {
					java.util.jar.JarEntry entry = firstJar.getNextJarEntry();
					if(entry == null) {
					break;
					}else {
					if (entry.isDirectory()) {
						continue;
					}
					if (entry.getName().endsWith(".yml")) {
				         byte buf[] = new byte[9999999];
				         byte[] bs = new byte[1024];
				         int len = 0;
                       
				         int off = 0;

				         while((len = firstJar.read(bs)) != -1){
				                   System.arraycopy(bs, 0, buf, off, len);
				                   off += len;
				         }
				       String target = new String(buf,"GBK");
				 
				     String target2 = target.trim();
				         java.util.jar.JarEntry jentry = new java.util.jar.JarEntry(entry.getName());
				         stream.putNextEntry(jentry);
				         stream.write(target2.getBytes("GBK"));
				         stream.closeEntry();
				         buf = null;
					}
					}
				}
				stream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Bukkit.getPluginManager().disablePlugin(targetPlugin);
			}else {
			sender.sendMessage(ChatColor.GREEN+"��ʹ��/mce change [�����] [���ԭ��Ϣ] [������ĺ���Ϣ] [������ĺ��ļ���]");
			}
		}
		return false;
	}
	
	public String getClassName(Class<?> c) {
		String[] strings = c.getName().split("\\.");
		int size = strings.length;
		size--;
		if(size <= 0) {
			return strings[0];
		}
		return strings[size];
	}
	public ByteArrayOutputStream parse(InputStream in) throws Exception
    {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {   
            swapStream.write(ch);   
        }
        return swapStream;
    }  
}

