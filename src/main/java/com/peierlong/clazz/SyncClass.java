package com.peierlong.clazz;

/**
 * 测试同步代码块的字节码层面执行流程
 * @author elong
 * @version V1.0
 * @date 2018/3/28
 */
public class SyncClass {

    void onlyMe(Object f) {
        synchronized (f){
            doSomething();
        }
    }

    void doSomething(){}

}

/*
out:
Classfile /Users/elong/WorkSpace/IdeaProjects/ImJavaer-two/target/classes/com/peierlong/clazz/SyncClass.class
  Last modified Mar 28, 2018; size 621 bytes
  MD5 checksum ebcf34b623635a3e3a4f7d58930601bf
  Compiled from "SyncClass.java"
public class com.peierlong.clazz.SyncClass
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#23         // java/lang/Object."<init>":()V
   #2 = Methodref          #3.#24         // com/peierlong/clazz/SyncClass.doSomething:()V
   #3 = Class              #25            // com/peierlong/clazz/SyncClass
   #4 = Class              #26            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
  #11 = Utf8               Lcom/peierlong/clazz/SyncClass;
  #12 = Utf8               onlyMe
  #13 = Utf8               (Ljava/lang/Object;)V
  #14 = Utf8               f
  #15 = Utf8               Ljava/lang/Object;
  #16 = Utf8               StackMapTable
  #17 = Class              #25            // com/peierlong/clazz/SyncClass
  #18 = Class              #26            // java/lang/Object
  #19 = Class              #27            // java/lang/Throwable
  #20 = Utf8               doSomething
  #21 = Utf8               SourceFile
  #22 = Utf8               SyncClass.java
  #23 = NameAndType        #5:#6          // "<init>":()V
  #24 = NameAndType        #20:#6         // doSomething:()V
  #25 = Utf8               com/peierlong/clazz/SyncClass
  #26 = Utf8               java/lang/Object
  #27 = Utf8               java/lang/Throwable
{
  public com.peierlong.clazz.SyncClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 9: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/peierlong/clazz/SyncClass;

  void onlyMe(java.lang.Object);
    descriptor: (Ljava/lang/Object;)V
    flags:
    Code:
      stack=2, locals=4, args_size=2
         0: aload_1
         1: dup
         2: astore_2
         3: monitorenter
         4: aload_0
         5: invokevirtual #2                  // Method doSomething:()V
         8: aload_2
         9: monitorexit
        10: goto          18
        13: astore_3
        14: aload_2
        15: monitorexit
        16: aload_3
        17: athrow
        18: return
      Exception table:
         from    to  target type
             4    10    13   any
            13    16    13   any
      LineNumberTable:
        line 12: 0
        line 13: 4
        line 14: 8
        line 15: 18
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      19     0  this   Lcom/peierlong/clazz/SyncClass;
            0      19     1     f   Ljava/lang/Object;
      StackMapTable: number_of_entries = 2
        frame_type = 255 // full_frame
          offset_delta = 13
                  locals = [ class com/peierlong/clazz/SyncClass, class java/lang/Object, class java/lang/Object ]
        stack = [ class java/lang/Throwable ]
        frame_type = 250 // chop
        offset_delta = 4

        void doSomething();
        descriptor: ()V
        flags:
        Code:
        stack=0, locals=1, args_size=1
        0: return
        LineNumberTable:
        line 17: 0
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       1     0  this   Lcom/peierlong/clazz/SyncClass;
        }
        SourceFile: "SyncClass.java"


 */