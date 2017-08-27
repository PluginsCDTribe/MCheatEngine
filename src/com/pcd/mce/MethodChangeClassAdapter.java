package com.pcd.mce;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodChangeClassAdapter extends ClassVisitor implements Opcodes {  
		String targetMethodname = "";
		String toName = "";
		 public void setName(String methodName) {
	        	this.targetMethodname = methodName;
	        }
	        public void setToName(String toName) {
	        	this.toName = toName;
	        }
        public MethodChangeClassAdapter(final ClassVisitor cv,String n,String n2) {  
            super(Opcodes.ASM4, cv);  
            this.targetMethodname = n;
            this.toName = n2;
        }  
  
        @Override  
        public void visit(  
            int version,  
            int access,  
            String name,  
            String signature,  
            String superName,  
            String[] interfaces)  
        {  
            if (cv != null) {  
                cv.visit(version, access, name, signature, superName, interfaces);  
            }  
        }  
          
        @Override  
        public MethodVisitor visitMethod(  
            int access,  
            String name,  
            String desc,  
            String signature,  
            String[] exceptions)  
        {  
       
   
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);//先得到原始的方法    
                MethodVisitor newMethod = new AsmMethodVisit(mv,targetMethodname,toName);              
                return newMethod;    
        }  
  
  
    }  