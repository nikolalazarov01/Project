package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBase implements Hotel{
    List<Guest> guests;
    List<Room> rooms = new ArrayList<>();

    @Override
    public void CheckIn(RoomTypes roomType, List<Guest> guests, int days, String note, LocalDate from) {
        Room availableRoom = Find(guests.size() - 1, from, days);
        if(availableRoom == null)
        {
            String message = DefaultMessages.DisplayDefaultMessage(DefaultMessagesTypes.NoRoom);
            MessageOutput.ConsoleOutput(message);
        }
        else{
            availableRoom.CheckIn(guests, days, note, from);
            guests.addAll(guests);
        }
    }

    @Override
    public void CheckOut(Room room) {
        int index = guests.indexOf(room.getGuests().get(0));
        guests.subList(index, room.getGuests().size()).clear();
        room.CheckOut();
    }

    @Override
    public LocalDate availability(LocalDate date) {
        return null;
    }

    @Override
    public void Report(LocalDate from, LocalDate to) {

    }

    @Override
    public Room Find(int beds, LocalDate from, int days) {
        List<Room> suitableRooms = new ArrayList<>();

        for(Room room : rooms){
            if(room.IsFree(from, days)){
                if(room.NoOfBeds() >= beds)
                    suitableRooms.add(room);
            }
        }

        if(suitableRooms.size() == 0)
            return null;
        if(suitableRooms.size() == 1)
            return suitableRooms.get(0);
        else{
            return SuitableRoom(rooms);
        }

    }

    private Room SuitableRoom(List<Room> rooms){
        Room suitableRoom = null;

        for(Room room : rooms){
            if(room.getType() == RoomTypes.Small && suitableRoom != null)
                suitableRoom = room;
            else if(room.getType() == RoomTypes.Large && suitableRoom != null)
                suitableRoom = room;
        }

        return suitableRoom;
    }

    @Override
    public Room FindUrgent(int beds, LocalDate from, int days) {
        return null;
    }

    @Override
    public void MakeUnavailable(Room room, LocalDate from, int days, String note) {
        room.MakeUnavailable(from, days, note);
    }


}
