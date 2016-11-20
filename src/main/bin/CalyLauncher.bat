@echo off
java  -jar caly-0.0.1-SNAPSHOT.jar 
SET exitcode=%errorlevel%
exit /B %exitcode%