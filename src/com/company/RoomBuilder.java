package com.company;

import java.util.ArrayList;
import java.util.List;

public class RoomBuilder {
    public static class Builder{
        List<Room> roomsList = new ArrayList<>();
        int startRoomNumber;
        public Builder(int startRoomNumber){
            this.startRoomNumber = startRoomNumber;
        }

        public Builder AddSmallRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Small, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        public Builder AddLargeRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Large, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        public Builder AddVipRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Vip, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        public List<Room> Build(){
            return this.roomsList;
        }
    }
}
