package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VipRoom extends RoomBase implements VipRoomInterface{
    public VipRoom(int number){
        super(number);
        super.numberOfBeds = 3;
        super.roomPrice = 450;
        super.type = RoomTypes.Vip;
    }

    public String toString(){
        return "Room number: " + Integer.toString(super.getRoomNumber()) + "; Room type: " + super.getType().toString();
    }
}
