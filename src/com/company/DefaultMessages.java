package com.company;

public class DefaultMessages {
    public static String DisplayDefaultMessage(DefaultMessagesTypes type){
        if(type == DefaultMessagesTypes.NoRoom)
            return "No suitable room is available at the moment! Sorry";
        else
            return "";

    }
}
