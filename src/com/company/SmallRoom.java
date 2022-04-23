package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmallRoom extends RoomBase{
    public SmallRoom(int number){
        super(number);
        super.numberOfBeds = 3;
        super.roomPrice = 150;
        super.type = RoomTypes.Small;
    }
}
