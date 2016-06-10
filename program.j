.class public super Program
.super java/lang/Object
.method public static main ([Ljava/lang/String;)V


sipush 14
sipush 5
irem
istore 9
iload 9
sipush 2
idiv
istore 11
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "y vale"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 11
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "x vale"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 9
invokevirtual java/io/PrintStream/println(I)V
iload 9
iload 11
imul
istore 13
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "z es "
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 13
invokevirtual java/io/PrintStream/println(I)V
iload 13
sipush 10
iadd
istore 15
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "r = "
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 15
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "abcdefghijkl"
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
return
.end method
