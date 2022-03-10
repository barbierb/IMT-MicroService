@echo off
java -version
"C:\Program Files\Java\jdk-11\bin\java.exe" -cp libs/hsqldb/hsqldb.jar org.hsqldb.server.Server --database.0 file:customdb --dbname.0 projetimt
pause