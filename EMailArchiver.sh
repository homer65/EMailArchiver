#!/bin/bash
#
SCRIPTPATH=$(cd `dirname $0` && pwd)
cd ${SCRIPTPATH}
echo $SCRIPTPATH
for filename in ${SCRIPTPATH}/lib/*.jar
 do CLASSPATH=${CLASSPATH}:${filename}
done
echo $CLASSPATH
export CLASSPATH
java -Xmx1024M -Xms512M org.myoggradio.Main
echo 'Press Enter'
read dummy
exit
