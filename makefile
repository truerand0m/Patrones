run:
	java Parser primeraPrac.py && java Program

oolong:
	cd COM\sootNsmoke\ && javac instructions/*.java jvm/*.java oolong/*.java prolog/*.java scheme/*.java && javac ../../Oolong.java
	
compile:
	yacc -dv -Jsemantic=Node Arith.y && java -jar "C:\Users\Dell\Documents\Clases\Compi\jflex-1.6.1.tar\jflex-1.6.1\lib\jflex-1.6.1.jar"  Flexer.flex
	javac Parser.java && javac Flexer.java
	java Parser primeraPrac.py && java Program


