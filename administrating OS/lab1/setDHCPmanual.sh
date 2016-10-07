ifconfig eth1 172.16.10.50 netmask 255.255.0.0
route del default
route add default gw 172.16.0.1
sed –e ‘/^nameserver*$/d’ /etc/resolv.conf
echo “nameserver 172.16.255.254” >> /etc/resolv.conf
