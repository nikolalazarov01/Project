package com.company;

import java.util.ArrayList;
import java.util.List;

public class HotelFactory {
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
