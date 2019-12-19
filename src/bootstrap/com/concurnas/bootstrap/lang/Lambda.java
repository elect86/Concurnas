package com.concurnas.bootstrap.lang;

import com.concurnas.bootstrap.runtime.cps.CObject;

public abstract class Lambda extends CObject{
	 public abstract Object[] signature();
	 public Object actingOn(){return null;}
	 public String methodName(){return null;}
	 public void bind(Object to){
		 throw new RuntimeException("bind not implemented for: " + this.getClass().getName());
	 }//TODO: make me abstract
	 
	 private final Class<?> type;
	 public Lambda(Class<?> type){
		 this.type = type;
	 }
	 public Class<?> actingOnType(){return type;}
	 
	 public static abstract class ClassRef<Output> extends Lambda{ public ClassRef(){super(null);}  }
	 public static abstract class Function0<Output> extends Lambda{ public Function0(Class<?> type){super(type);} public abstract Output apply(); }
	 public static abstract class Function1<Input1, Output> extends Lambda{ public Function1(Class<?> type){super(type);} public abstract Output apply(Input1 i1); }
	 public static abstract class Function2<Input1, Input2, Output> extends Lambda{ public Function2(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2); }
	 public static abstract class Function3<Input1, Input2, Input3, Output> extends Lambda{ public Function3(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3); }
	 public static abstract class Function4<Input1, Input2, Input3, Input4, Output> extends Lambda{ public Function4(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4); }
	 public static abstract class Function5<Input1, Input2, Input3, Input4, Input5, Output> extends Lambda{ public Function5(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5); }
	 public static abstract class Function6<Input1, Input2, Input3, Input4, Input5, Input6, Output> extends Lambda{ public Function6(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6); }
	 public static abstract class Function7<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Output> extends Lambda{ public Function7(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7); }
	 public static abstract class Function8<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Output> extends Lambda{ public Function8(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8); }
	 public static abstract class Function9<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Output> extends Lambda{ public Function9(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9); }
	 public static abstract class Function10<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Output> extends Lambda{ public Function10(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10); }
	 public static abstract class Function11<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Output> extends Lambda{ public Function11(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11); }
	 public static abstract class Function12<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Output> extends Lambda{ public Function12(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12); }
	 public static abstract class Function13<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Output> extends Lambda{ public Function13(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13); }
	 public static abstract class Function14<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Output> extends Lambda{ public Function14(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14); }
	 public static abstract class Function15<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Output> extends Lambda{ public Function15(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15); }
	 public static abstract class Function16<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Output> extends Lambda{ public Function16(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16); }
	 public static abstract class Function17<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Output> extends Lambda{ public Function17(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17); }
	 public static abstract class Function18<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Output> extends Lambda{ public Function18(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18); }
	 public static abstract class Function19<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Output> extends Lambda{ public Function19(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19); }
	 public static abstract class Function20<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Output> extends Lambda{ public Function20(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20); }
	 public static abstract class Function21<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21, Output> extends Lambda{ public Function21(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21); }
	 public static abstract class Function22<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21, Input22, Output> extends Lambda{ public Function22(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21, Input22 i22); }
	 public static abstract class Function23<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21, Input22,Input23, Output> extends Lambda{ public Function23(Class<?> type){super(type);} public abstract Output apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21, Input22 i22, Input23 i23); }
	 //return void type:
	 
	 
	 public static abstract class Function0v extends Lambda{ public Function0v(Class<?> type){super(type);} public abstract void apply(); }
	 public static abstract class Function1v<Input1> extends Lambda{ public Function1v(Class<?> type){super(type);} public abstract void apply(Input1 i1); }
	 public static abstract class Function2v<Input1, Input2> extends Lambda{ public Function2v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2); }
	 public static abstract class Function3v<Input1, Input2, Input3> extends Lambda{ public Function3v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3); }
	 public static abstract class Function4v<Input1, Input2, Input3, Input4> extends Lambda{ public Function4v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4); }
	 public static abstract class Function5v<Input1, Input2, Input3, Input4, Input5> extends Lambda{ public Function5v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5); }
	 public static abstract class Function6v<Input1, Input2, Input3, Input4, Input5, Input6> extends Lambda{ public Function6v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6); }
	 public static abstract class Function7v<Input1, Input2, Input3, Input4, Input5, Input6, Input7> extends Lambda{ public Function7v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7); }
	 public static abstract class Function8v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8> extends Lambda{ public Function8v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8); }
	 public static abstract class Function9v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9> extends Lambda{ public Function9v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9); }
	 public static abstract class Function10v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10> extends Lambda{ public Function10v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10); }
	 public static abstract class Function11v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11> extends Lambda{ public Function11v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11); }
	 public static abstract class Function12v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12> extends Lambda{ public Function12v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12); }
	 public static abstract class Function13v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13> extends Lambda{ public Function13v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13); }
	 public static abstract class Function14v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14> extends Lambda{ public Function14v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14); }
	 public static abstract class Function15v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15> extends Lambda{ public Function15v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15); }
	 public static abstract class Function16v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16> extends Lambda{ public Function16v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16); }
	 public static abstract class Function17v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17> extends Lambda{ public Function17v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17); }
	 public static abstract class Function18v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18> extends Lambda{ public Function18v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18); }
	 public static abstract class Function19v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19> extends Lambda{ public Function19v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19); }
	 public static abstract class Function20v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20> extends Lambda{ public Function20v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20); }
	 public static abstract class Function21v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21> extends Lambda{ public Function21v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21); }
	 public static abstract class Function22v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21, Input22> extends Lambda{ public Function22v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21, Input22 i22); }
	 public static abstract class Function23v<Input1, Input2, Input3, Input4, Input5, Input6, Input7, Input8, Input9, Input10, Input11, Input12, Input13, Input14, Input15, Input16, Input17, Input18, Input19, Input20, Input21, Input22,Input23> extends Lambda{ public Function23v(Class<?> type){super(type);} public abstract void apply(Input1 i1, Input2 i2, Input3 i3, Input4 i4, Input5 i5, Input6 i6, Input7 i7, Input8 i8, Input9 i9, Input10 i10, Input11 i11, Input12 i12, Input13 i13, Input14 i14, Input15 i15, Input16 i16, Input17 i17, Input18 i18, Input19 i19, Input20 i20, Input21 i21, Input22 i22, Input23 i23); }
}