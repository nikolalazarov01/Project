package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LargeRoom extends RoomBase{
    public LargeRoom(int number){
        super(number);
        super.numberOfBeds = 5;
        super.roomPrice = 300;
        super.type = RoomTypes.Large;
    }

    public String toString(){
        return "Room number: " + Integer.toString(super.getRoomNumber()) + "; Room type: " + super.getType().toString();
    }
}
