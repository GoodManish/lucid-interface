# Issues

while connecting Confluent Kafka (which is installed in windows using WSL), when I tried to connect to the Kafka Broker 
running under WSL2, from the IntelliJ Java application running on Windows. Broker was not able to connect.

## Solution:-

In such a case, the solution is to disable IPv6 in WSL2 for this to work properly.
WSL2 currently has a networking issue that prevents outside programs to connect to Kafka running on WSL2 (for example your Java programs, Conduktor, etc...); 
To fix this, we recommend disabling IPv6 on WSL2. Your Windows password will be prompted on the first command:

### sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1
### sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1

https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-windows/