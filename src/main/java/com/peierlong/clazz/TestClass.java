package com.peierlong.clazz;

/**
 * 对class文件执行javap来分析class相关信息
 * @author elong
 * @version V1.0
 * @date 2018/3/22
 */
public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }

}

/* javap -verbose TestClass
output:

Classfile /Users/elong/Downloads/war/TestClass.class
  Last modified Mar 23, 2018; size 393 bytes
  MD5 checksum d2e0624d9f582040d79b6e547d146eb8
  Compiled from "TestClass.java"
public class com.peierlong.clazz.TestClass
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#19         // com/peierlong/clazz/TestClass.m:I
   #3 = Class              #20            // com/peierlong/clazz/TestClass
   #4 = Class              #21            // java/lang/Object
   #5 = Utf8               m
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               LocalVariableTable
  #12 = Utf8               this
  #13 = Utf8               Lcom/peierlong/clazz/TestClass;
  #14 = Utf8               inc
  #15 = Utf8               ()I
  #16 = Utf8               SourceFile
  #17 = Utf8               TestClass.java
  #18 = NameAndType        #7:#8          // "<init>":()V
  #19 = NameAndType        #5:#6          // m:I
  #20 = Utf8               com/peierlong/clazz/TestClass
  #21 = Utf8               java/lang/Object
{
  public com.peierlong.clazz.TestClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 8: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/peierlong/clazz/TestClass;

  public int inc();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field m:I
         4: iconst_1
         5: iadd
         6: ireturn
      LineNumberTable:
        line 13: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Lcom/peierlong/clazz/TestClass;
}
SourceFile: "TestClass.java"


 */