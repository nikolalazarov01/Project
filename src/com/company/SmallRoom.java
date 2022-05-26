package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmallRoom extends RoomBase{
    /**
     * Constructor for the new Small room
     * @param number Number of the room
     */
    public SmallRoom(int number){
        super(number);
        super.numberOfBeds = 3;
        super.roomPrice = 150;
        super.type = RoomTypes.Small;
    }

    public String toString(){
        return "Room number: " + Integer.toString(super.getRoomNumber()) + "; Room type: " + super.getType().toString();
    }
}
