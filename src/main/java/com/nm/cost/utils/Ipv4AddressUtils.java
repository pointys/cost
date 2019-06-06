package com.nm.cost.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author: 韩老魔
 * 记录Ipv真实地址工具类
 * @Date: 2019/5/11 0011 19:16
 */
public class Ipv4AddressUtils {
    public static String getLocalIpv4Address() throws SocketException {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (java.net.SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;

        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                    .nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    if (ip.getHostAddress().equals("127.0.0.1")) {
                        continue;
                    }
                    System.out.println("ipv4 = " + ip.getHostAddress());
                    return ip.getHostAddress();
                }
            }
        }

        return "网络无连接！";
    }
}
