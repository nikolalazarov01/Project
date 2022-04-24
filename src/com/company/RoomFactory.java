package com.company;

import java.util.ArrayList;
import java.util.List;

public class RoomFactory {
    private static Room SmallRoom(int number){
        return new SmallRoom(number);
    }

    private static Room LargeRoom(int number){
        return new LargeRoom(number);
    }

    private static Room VipRoom(int number){
        return new VipRoom(number);
    }

    public static List<Room> ListRoom(RoomTypes type, int numberOfRooms, int startRoomNumber){
        switch (type){
            case Small:{
                List<Room> rooms = new ArrayList<>();
                for(int i = 0; i < numberOfRooms; i++, startRoomNumber++){
                    rooms.add(SmallRoom(startRoomNumber));
                }

                return rooms;
            }

            case Large:{
                List<Room> rooms = new ArrayList<>();
                for(int i = 0; i < numberOfRooms; i++, startRoomNumber++){
                    rooms.add(LargeRoom(startRoomNumber));
                }

                return rooms;
            }

            case Vip:{
                List<Room> rooms = new ArrayList<>();
                for(int i = 0; i < numberOfRooms; i++, startRoomNumber++){
                    rooms.add(VipRoom(startRoomNumber));
                }

                return rooms;
            }

            default: return null;
        }
    }
}
