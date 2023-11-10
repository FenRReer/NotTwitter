package com.example.nottwitter.domain.util;

import com.example.nottwitter.domain.User;

public abstract class MessageHelper {

    public static String getAuthorName(User author){
        return author != null ? author.getUsername() : "<none>";
    }

}
