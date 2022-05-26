package com.company;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Hotel implements HotelInterface {
    List<Guest> guests = new ArrayList<>();
    List<Room> rooms = new ArrayList<>();

    /**
     * Void function that adds list of rooms to the hotel
     * @param rooms List of different types of rooms
     */
    public void AddRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
    }

    /**
     * Void function that adds single room to the hotel
     * @param room Instance of Room
     */
    public void AddRoom(Room room){
        this.rooms.add(room);
    }

    /**
     * Function that adds list of guests to the hotel
     * @param guests List of different types of guests
     */
    public void AddGuests(List<Guest> guests) {this.guests.addAll(guests);}

    /**
     * Function that makes check in - Finds the best room that matches the requirements, makes the room unavailable at the current dates,
     * leaves a note and adds the list of guests to the hotel
     * @param roomType Instance of RoomType enumerable
     * @param guests List of instances of Guest
     * @param days Number of days of stay
     * @param note Note that the guests leave
     * @param from Date from which the guests are staying
     */
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

    /**
     * Function that checks out guests - it takes them out from the hotel's list and makes the room available
     * @param roomNumber Room number of the room, whose guests are checking out
     */
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

    /**
     * Function that returns list of available rooms in the given date
     * @param date Date for which free rooms are searched
     * @return Returns List of rooms which are available at the given date
     */
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

    /**
     * Function that returns full report for the rooms in a given time span
     * @param from Starting date for the report
     * @param to Ending date fof the report
     * @return Returns the report in String format
     */
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

    /**
     * Function that finds suitable room for the need of the checking in guests
     * if there is not such room it returns null
     * @param beds Number of beds needed
     * @param from Starting date for staying
     * @param days Number of days staying
     * @return Returns the room that meets the requirements
     */
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

    /**
     * Function that picks the optimal room from a list of rooms
     * @param rooms List of Room
     * @return Returns the room that meets the requirements
     */
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

    /**
     * Function that makes changes in the guests placement, so a room can be freed for a vip person
     * @param beds Number of beds needed
     * @param from Starting date for staying
     * @param days Number of days of staying
     * @return Returns the room that meets the requirements
     */
    @Override
    public Room FindUrgent(int beds, LocalDate from, int days) {
        return null;
    }

    /**
     * Function that makes given room from the list of rooms in the hotel unavailable, because of repairs or some other reason
     * @param room Instance of room in the list of rooms in the hotel
     * @param from Starting date from which the room will be unavailable
     * @param days Number of days unavailable
     * @param note Note from the staff
     */
    @Override
    public void MakeUnavailable(Room room, LocalDate from, int days, String note) {
        room.MakeUnavailable(from, days, note);
    }
}
