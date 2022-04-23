package com.company;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Hotel implements HotelInterface {
    List<Guest> guests;
    List<Room> rooms = new ArrayList<>();

    public void AddRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
    }

    public void AddRoom(Room room){
        this.rooms.add(room);
    }

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
            availableRoom.setOccupiedFromAndToDate(from, days);
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
    public List<Room> Availability(LocalDate date) {
        if(date == null)
            date = LocalDate.now();

        List<Room> availableRooms = new ArrayList<>();

        for(Room room : rooms){
            if(room.IsFree(date, 1)){
                availableRooms.add(room);
            }
        }
        return null;
    }

    @Override
    public String Report(LocalDate from, LocalDate to) {
        List<Room> freeRooms = new ArrayList<>();
        List<Room> occupiedRooms = new ArrayList<>();

        for(Room room : rooms){
            if(room.getOccupiedFromDate() != null && room.getOccupiedToDate() != null) {
                if (room.getOccupiedFromDate().isAfter(from) && room.getOccupiedFromDate().isBefore(to))
                    occupiedRooms.add(room);
            }
            else
                freeRooms.add(room);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("------Report rooms from:" + from.toString() + " to:" + to.toString() + "------\n");

        sb.append("Free rooms:\n");
        for(Room room : freeRooms)
            sb.append("Room number: " + room.getRoomNumber() + " \n");

        sb.append("Occupied rooms:\n");
        for(Room room : occupiedRooms){
            int days = (int)(Duration.between(from.atStartOfDay(), to.atStartOfDay()).toDays());
            sb.append("Room number: " + room.getRoomNumber() + " is occupied from " + room.getOccupiedFromDate() + " to "
                    + room.getOccupiedToDate() + " total: " + days + " days\n");
        }

        return sb.toString();

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
            if(room.getType() == RoomTypes.Small && suitableRoom == null)
                suitableRoom = room;
            else if(room.getType() == RoomTypes.Large && suitableRoom == null)
                suitableRoom = room;
            else
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
