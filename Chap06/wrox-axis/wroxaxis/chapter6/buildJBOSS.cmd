@REM buildJBOSS.cmd
SET JBOSS_DIST=d:\JBoss-2.4.4

@REM Create the build directory, and copy the deployment descriptors into it
mkdir JBOSSBuild JBOSSBuild\META-INF
copy META-INF\ejb-jar.xml JBOSSBuild\META-INF
copy META-INF\jboss.xml JBOSSBuild\META-INF
cd ..\..

@REM Compile EJB classes into the build directory (jar preparation)
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH% -d wroxaxis/chapter6/JBOSSBuild wroxaxis/chapter6/SparePartPriceSessionHome.java
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH% -d wroxaxis/chapter6/JBOSSBuild wroxaxis/chapter6/SparePartPriceSession.java
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH% -d wroxaxis/chapter6/JBOSSBuild wroxaxis/chapter6/SparePartPriceSessionBean.java
javac -d wroxaxis/chapter6/JBossBuild wroxaxis/chapter6/JBOSSServiceClient.java

@REM Make a EJB jar file, including XML deployment descriptors
cd wroxaxis\chapter6\JBOSSBuild
jar cv0f SparePartPriceEJB.jar META-INF wroxaxis/chapter6/SparePartPriceSessionHome.class wroxaxis/chapter6/SparePartPriceSession.class wroxaxis/chapter6/SparePartPriceSessionBean.class
cd ..
