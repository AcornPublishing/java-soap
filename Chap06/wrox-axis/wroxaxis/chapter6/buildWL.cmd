SET WL_HOME=d:\bea\wlserver6.1

@REM Create the build directory, and copy the deployment descriptors
mkdir WLBuild WLBuild\META-INF
copy META-INF\ejb-jar.xml WLBuild\META-INF
copy META-INF\weblogic-ejb-jar.xml WLBuild\META-INF
cd ..\..

@REM Compile EJB classes into the build directory (jar preparation)
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d wroxaxis/chapter6/WLBuild wroxaxis/chapter6/SparePartPriceSessionHome.java
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d wroxaxis/chapter6/WLBuild wroxaxis/chapter6/SparePartPriceSession.java
javac -classpath .;%WL_HOME%\lib\weblogic.jar;%CLASSPATH% -d wroxaxis/chapter6/WLBuild wroxaxis/chapter6/SparePartPriceSessionBean.java
javac -d wroxaxis/chapter6/WLBuild wroxaxis/chapter6/WLServiceClient.java

@REM Make a EJB jar file, including XML deployment descriptors
cd wroxaxis\chapter6\WLBuild

jar cv0f SparePartPriceEJB.jar META-INF wroxaxis/chapter6/SparePartPriceSessionHome.class wroxaxis/chapter6/SparePartPriceSession.class wroxaxis/chapter6/SparePartPriceSessionBean.class

@REM EJBC
java -classpath %WL_HOME%\lib\weblogic.jar weblogic.ejbc -compiler javac SparePartPriceEJB.jar SparePartPriceEJB_WL.jar
cd ..
