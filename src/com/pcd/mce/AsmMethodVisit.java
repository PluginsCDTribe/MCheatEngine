package com.pcd.mce;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmMethodVisit extends MethodVisitor {  
		String targetMethodname = "";
		String toName = "";
        public AsmMethodVisit(MethodVisitor mv,String name,String name2) {       	
            super(Opcodes.ASM4, mv);   
            toName = name2;
            targetMethodname = name;
        }
        public void setName(String methodName) {
        	this.targetMethodname = methodName;
        }
        public void setToName(String toName) {
        	this.toName = toName;
        }
        @Override  
        public void visitCode() {         
            super.visitCode();  
        }  
        @Override
        public void visitEnd() {
        	super.visitEnd();
        }
        @Override  
        public void visitInsn(int opcode) {       
        	super.visitInsn(opcode);
        }
        @Override
        public void visitLdcInsn(Object cst) {
        	if(cst instanceof String) {
        		if(cst != null && cst.toString().equalsIgnoreCase(targetMethodname)) {
        			super.visitLdcInsn(toName);
        		}else {
        			super.visitLdcInsn(cst);
        		}
        	}else {
        		super.visitLdcInsn(cst);
        	}
        }
    }  