#!/bin/bash

# compile and run "CheckDr.java"
javac -classpath ./lib/jsoup-1.8.3.jar:./lib/javax.mail.jar CheckDr.java
java -classpath .:./lib/jsoup-1.8.3.jar:./lib/javax.mail.jar CheckDr


