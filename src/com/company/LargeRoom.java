package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LargeRoom extends RoomBase{
    public LargeRoom(){
        super.numberOfBeds = 5;
        super.roomPrice = 300;
        super.type = RoomTypes.Large;
    }
}
