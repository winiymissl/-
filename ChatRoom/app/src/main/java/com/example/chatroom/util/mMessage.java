package com.example.chatroom.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class mMessage implements Serializable {
    public int image;
    public String message;
    public String time;
    public static List<mMessage> list;

    public String getNickname() {
        return nickname;
    }

    public String nickname;
    public int type;

    public mMessage(String nickname, int image, String message, String time, int type) {
        this.nickname = nickname;
        this.image = image;
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public int getType() {
        return type;
    }


    @Override
    public String toString() {
        return "mMessage{" +
                "image=" + image +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type=" + type +
                ", i=" +
                '}';
    }

    public static List<mMessage> getListDefault() {
        //只能生成一个默认list
        list = new ArrayList<>();
        return list;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
