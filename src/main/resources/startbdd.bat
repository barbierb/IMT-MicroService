@echo off
java -version
"C:\Program Files\Java\jdk-15.0.1\bin\java.exe" -cp libs/hsqldb/hsqldb.jar org.hsqldb.server.Server --database.0 file:customdb --dbname.0 projetimt
pause