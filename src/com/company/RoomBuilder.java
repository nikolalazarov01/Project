package com.company;

import java.util.ArrayList;
import java.util.List;

public class RoomBuilder {
    public static class Builder{
        //builder that returns list of rooms for the hotel
        List<Room> roomsList = new ArrayList<>();
        int startRoomNumber;
        public Builder(int startRoomNumber){
            this.startRoomNumber = startRoomNumber;
        }


        /**
         * Function that adds small rooms to the list of rooms
         * @param numberOfRooms Number of small rooms
         * @return Adds the new rooms to the list and returns Builder
         */
        public Builder AddSmallRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Small, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        /**
         * Function that adds large rooms to the list of rooms
         * @param numberOfRooms Number of large rooms
         * @return Adds the new rooms to the list and returns Builder
         */
        public Builder AddLargeRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Large, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        /**
         * Function that adds vip rooms to the list of rooms
         * @param numberOfRooms Number of vip rooms
         * @return Adds the new rooms to the list and returns Builder
         */
        public Builder AddVipRooms(int numberOfRooms){
            this.roomsList.addAll(RoomFactory.ListRoom(RoomTypes.Vip, numberOfRooms, this.startRoomNumber));
            this.startRoomNumber += numberOfRooms;
            return this;
        }

        /**
         * Function that returns the list of rooms that were build
         * @return Returns the list of rooms
         */
        public List<Room> Build(){
            return this.roomsList;
        }
    }
}
