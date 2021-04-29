SET WL_HOME=d:/bea/wlserver6.1

@REM Create the build directory, and copy the deployment descriptors into it
mkdir WLBuild WLBuild\META-INF
copy META-INF\ejb-jar.xml WLBuild\META-INF
copy META-INF\weblogic-ejb-jar.xml WLBuild\META-INF

@REM Compile EJB classes into the build directory (jar preparation)
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d WLBuild com\eaaxis\chapter6\SparePartPriceSessionHome.java
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d WLBuild com\eaaxis\chapter6\SparePartPriceSession.java
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d WLBuild com\eaaxis\chapter6\SparePartPriceSessionBean.java
javac -d WLBuild com\eaaxis\chapter6\WLServiceClient.java

@REM Make a EJB jar file, including XML deployment descriptors
cd WLBuild
jar cv0f SparePartPriceEJB.jar META-INF com\eaaxis\chapter6\SparePartPriceSessionHome.class com\eaaxis\chapter6\SparePartPriceSession.class com\eaaxis\chapter6\SparePartPriceSessionBean.class
cd ..

@REM EJBC
java -classpath %WL_HOME%/lib/weblogic.jar weblogic.ejbc -compiler javac WLBuild\SparePartPriceEJB.jar WLBuild\SparePartPriceEJB_WL.jar
