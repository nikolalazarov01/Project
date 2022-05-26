package com.company;

import java.util.ArrayList;
import java.util.List;

public class RoomFactory {
    /**
     * Function that creates new small room
     * @param number Number of the room that is being created
     * @return Returns new instance of small room
     */
    public static Room SmallRoom(int number){
        return new SmallRoom(number);
    }

    /**
     * Function that creates new large room
     * @param number Number of the room that is being created
     * @return Returns new instance of large room
     */
    public static Room LargeRoom(int number){
        return new LargeRoom(number);
    }

    /**
     * Function that creates new vip room
     * @param number Number of the room that is being created
     * @return Returns new instance of vip room
     */
    public static Room VipRoom(int number){
        return new VipRoom(number);
    }

    //function returning list of rooms from given type

    /**
     * Function returning list of rooms from a given type
     * @param type Type of room (small, large, vip, etc.)
     * @param numberOfRooms Number of rooms to be created
     * @param startRoomNumber Number of the first room to be created
     * @return Returns list of rooms from the given type
     */
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
