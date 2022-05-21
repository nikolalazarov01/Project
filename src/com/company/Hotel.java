package com.company;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Hotel implements HotelInterface {
    List<Guest> guests = new ArrayList<>();
    List<Room> rooms = new ArrayList<>();

    public void AddRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
    }

    public void AddRoom(Room room){
        this.rooms.add(room);
    }

    public void AddGuests(List<Guest> guests) {this.guests.addAll(guests);}

    //function that takes room type, list of guests, days, note and date for checking in
    @Override
    public void CheckIn(RoomTypes roomType, List<Guest> guests, int days, String note, LocalDate from) {
        //finding available room
        Room availableRoom = Find(guests.size(), from, days);
        if(availableRoom == null)
        {
            MessageOutput.ConsoleOutput(DefaultMessagesTypes.NoRoom.label);
        }
        else{
            availableRoom.CheckIn(guests, days, note, from);
            availableRoom.setOccupiedFromAndToDate(from, days);
            this.guests.addAll(guests);
        }
    }

    //function that takes only the room number
    @Override
    public void CheckOut(int roomNumber) {
        Room room = null;
        //iterating through the list of rooms in order to find the matching one
        for(Room r : rooms){
            if(r.getRoomNumber() == roomNumber)
                room = r;
        }
        if(room == null)
            return;
        //if the room is not occupied
        if(room.getGuests().size() == 0)
            return;
        //taking the index of the first guest in the list
        int index = guests.indexOf(room.getGuests().get(0));
        //deleting the guests that have been checked out from the list
        guests.subList(index, index + room.getGuests().size()).clear();
        room.CheckOut();
    }

    //function that returns list of free rooms in the given date
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
        return availableRooms;
    }

    //function that returns report for the rooms in the hotel
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

    //function that finds suitable room for the need of the checking in guests
    //if there is not such room it returns null
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

    //function that returns the suitable room
    private Room SuitableRoom(List<Room> rooms){
        Room suitableRoom = null;

        for(Room room : rooms){
            //if the suitable room hasn't been initialized or it has been initialized to not optimal room it is being initialized to the current
            if(room.getType() == RoomTypes.Small && (suitableRoom == null || suitableRoom.getType() != RoomTypes.Small))
                suitableRoom = room;
            else if(room.getType() == RoomTypes.Large && (suitableRoom == null || suitableRoom.getType() == RoomTypes.Vip))
                suitableRoom = room;
            else if(suitableRoom == null)
                suitableRoom = room;
        }

        return suitableRoom;
    }

    @Override
    public Room FindUrgent(int beds, LocalDate from, int days) {
        return null;
    }

    //function that makes room unavailable, given the room, date from when it is unavailable, days unavailable and note
    @Override
    public void MakeUnavailable(Room room, LocalDate from, int days, String note) {
        room.MakeUnavailable(from, days, note);
    }
}
