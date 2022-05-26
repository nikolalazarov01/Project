package com.company;

import java.util.ArrayList;
import java.util.List;

public class HotelFactory {
    /**
     * Function that returns instance of hotel with the given data
     * @param numberOfSmallRooms Integer for the number of small rooms that are going to be included in the hotel
     * @param numberOfLargeRooms Integer for the number of large rooms that are going to be included in the hotel
     * @param numberOfVipRooms Integer for the number of vip rooms that are going to be included in the hotel
     * @return Returns instance of Hotel with the given data inputted
     */
    public static Hotel MakeHotel(int numberOfSmallRooms, int numberOfLargeRooms, int numberOfVipRooms){
        Hotel hotel = new Hotel();

        List<Room> rooms = new ArrayList<>();

        rooms = new RoomBuilder.Builder(1)
                .AddSmallRooms(numberOfSmallRooms)
                .AddLargeRooms(numberOfLargeRooms)
                .AddVipRooms(numberOfVipRooms)
                .Build();

        hotel.AddRooms(rooms);

        return hotel;
    }
}
