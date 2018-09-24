package com.easemob.livedemo.net.bean;

import java.util.Date;

public class UserModule {
    //0离线9在线2忙碌3观察
    public static final String STATUS_BUSY = "2";
    public static final String STATUS_ONLINE = "9";

    public static int A = 1;
    public static int B = 2;
    public static int C = 3;
    public static int E = 4;
    public int id;
    public String name;
    public String password;
    public int usertype;
    public String lastlogintime;
    public String status;
    public String Flag;
    public boolean insystem;
    public String roomid;
    public double longitude;
    public double latitude;
}
