#!/bin/sh
PORT=4446

rm *.class
javac *.java
# lsof -P | grep ':$PORT' | awk '{print $2}' | xargs kill -9
java ConnectFourServer localhost $PORT &