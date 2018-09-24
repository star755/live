package com.easemob.livedemo.custom;

import com.google.gson.Gson;

public class M {
    //群消息
    public static final String TO_GROUP = "61097053847553";
    public static final String CHAT_ROOM = "61095445331969";
    public static final String SPLITER = "@~@";
    public static final String JION_ROOM = "JIONROOM"+SPLITER;
    public static final String JION_ROOM_REPLAY = "REPLAY:JIONROOM"+SPLITER;

    private static Gson GSON = new Gson();

    public static class JoinRoom{
        public String toUser;
        public String roomId;

        public JoinRoom(String toUser, String roomId) {
            this.toUser = toUser;
            this.roomId = roomId;
        }
    }


    public static String buildInviteUser(String roomid,String userId){
        return M.JION_ROOM + GSON.toJson(new JoinRoom(userId,roomid));
    }


    public static String replayInviteUser(String userId){
        return M.JION_ROOM_REPLAY + GSON.toJson(new JoinRoom(userId,""));
    }


    public static JoinRoom parseInviteUser(String mes){
        return GSON.fromJson(mes.split(SPLITER)[1],JoinRoom.class);
    }
}
