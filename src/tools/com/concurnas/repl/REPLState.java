package com.concurnas.repl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.concurnas.compiler.ast.ClassDefJava;
import com.concurnas.compiler.utils.TypeDefTypeProvider;
import com.concurnas.runtime.Pair;

public class REPLState {

	public static class REPLTopLevelImports{
		public Map<String, String> topshortNameToLong = new HashMap<String, String>();
		public Map<String, ClassDefJava> topshortNameToLongUsing = new HashMap<String, ClassDefJava>();
		public Set<String> toprawImports = new HashSet<String>();
		public Set<String> toprawUsings = new HashSet<String>();
		public HashMap<Pair<String, Integer>, TypeDefTypeProvider> toptypeDef = new HashMap<Pair<String, Integer>, TypeDefTypeProvider>();
	}
	
	public REPLTopLevelImports tliCache=new REPLTopLevelImports();
	
	public long tmpVarcnt= 0;
	
	private boolean shouldIncTmpVarcnt=false;
	public void inc() {
		if(shouldIncTmpVarcnt) {
			tmpVarcnt++;
			shouldIncTmpVarcnt=false;
		}
	}
	
	public String getTmpVarName() {
		shouldIncTmpVarcnt=true;
		return "$"+tmpVarcnt;
	}
	
}