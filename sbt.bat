set SCRIPT_DIR=%~dp0
java -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=512m -Xmx2g -Xss4M -jar "%SCRIPT_DIR%\sbt-launcher.jar" %*
