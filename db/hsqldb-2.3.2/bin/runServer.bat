cd ..\data
@java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:testdb --dbname.0 testdb %1 %2 %3 %4 %5 %6 %7 %8 %9
