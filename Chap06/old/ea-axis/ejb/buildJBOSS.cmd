SET JBOSS_DIST=d:\JBoss-2.4.4

@REM Create the build directory, and copy the deployment descriptors into it
mkdir JBOSSBuild JBOSSBuild\META-INF
copy META-INF\ejb-jar.xml JBOSSBuild\META-INF
copy META-INF\jboss.xml JBOSSBuild\META-INF


@REM Compile EJB classes into the build directory (jar preparation)
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH%  -d JBOSSBuild com\eaaxis\chapter6\SparePartPriceSessionHome.java
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH% -d JBOSSBuild com\eaaxis\chapter6\SparePartPriceSession.java
javac -classpath .;%JBOSS_DIST%\lib\ext\jboss.jar;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar;%JBOSS_DIST%\lib\ext\jnpserver.jar;%JBOSS_DIST%\lib\ext\jndi.jar;%CLASSPATH% -d JBOSSBuild com\eaaxis\chapter6\SparePartPriceSessionBean.java
javac -d JBOSSBuild com\eaaxis\chapter6\JBOSSServiceClient.java

@REM Make a EJB jar file, including XML deployment descriptors
cd JBOSSBuild
jar cv0f SparePartPriceEJB.jar META-INF com\eaaxis\chapter6\SparePartPriceSessionHome.class com\eaaxis\chapter6\SparePartPriceSession.class com\eaaxis\chapter6\SparePartPriceSessionBean.class
cd ..
