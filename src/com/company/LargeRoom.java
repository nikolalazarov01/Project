package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LargeRoom extends RoomBase{
    /**
     * Constructor for large room
     * @param number Room number
     */
    public LargeRoom(int number){
        super(number);
        super.numberOfBeds = 5;
        super.roomPrice = 300;
        super.type = RoomTypes.Large;
    }


    public String toString(){
        return "Room number: " + super.getRoomNumber() + "; Room type: " + super.getType().toString();
    }
}
