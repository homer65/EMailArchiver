#!/bin/bash
#
SCRIPTPATH=$(cd `dirname $0` && pwd)
echo $SCRIPTPATH
for filename in ${SCRIPTPATH}/lib/*.jar
 do CLASSPATH=${CLASSPATH}:${filename}
done
echo $CLASSPATH
export CLASSPATH
java -Xmx1024M -Xms512M org.myoggradio.Main
java pack.Pause
exit
