#restart.sh
#!/bin/bash
echo "Restarting SpringBoot Application"
pid=`ps -ef | grep mybatisplus-spring-boot-1.0.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill -9 $pid
   echo "关闭进程："$pid
fi

echo "授予当前用户权限"
chmod 777 /root/.jenkins/workspace/springboot/target/mybatisplus-spring-boot-1.0.jar
echo "执行....."$pid
java -jar /root/.jenkins/workspace/springboot/target/mybatisplus-spring-boot-1.0.jar