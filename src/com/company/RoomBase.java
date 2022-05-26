package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class RoomBase implements Room{
    private int roomNumber;
    protected  RoomTypes type;
    private List<Guest> guests = new ArrayList<>();
    private LocalDate occupiedFromDate;
    private LocalDate occupiedToDate;
    protected double roomPrice;
    protected int numberOfBeds;
    private String note;

    //the basic thing a room needs is a room number

    /**
     * Constructor taking the basic thing a room needs - a room number
     * @param roomNumber The room number
     */
    public RoomBase(int roomNumber){
        this.roomNumber = roomNumber;
    }

    /**
     * Function that returns the current room's number
     * @return Returns the current rooms number in Integer format
     */
    public int getRoomNumber(){
        return this.roomNumber;
    }

    /**
     * Function that returns the occupied from date in String format
     * @return Returns the occupied from date in String format
     */
    public String getOccupiedFromDateString(){
        if(this.occupiedFromDate == null)
            return "null";
        else return this.occupiedFromDate.toString();
    }

    /**
     * Function that returns the occupied to date in String format
     * @return Returns the occupied to date in String format
     */
    public String getOccupiedToDateString(){
        if(this.occupiedToDate == null)
            return "null";
        else return this.occupiedToDate.toString();
    }

    /**
     * Function that returns the date from which the room is occupied
     * @return Returns the date from which the room is occupied in LocalDate format
     */
    public LocalDate getOccupiedFromDate(){
        return this.occupiedFromDate;
    }

    /**
     * Function that returns the date to which the room is occupied
     * @return Returns the date to which the room is occupied in LocalDate format
     */
    public LocalDate getOccupiedToDate(){
        return this.occupiedToDate;
    }

    /**
     * Function that returns the room type of the current room
     * @return Returns the room type
     */
    public RoomTypes getType(){
        return this.type;
    }

    /**
     * Function that returns whether the room is free in the given period of time
     * @param from Starting date for the check
     * @param days Number of days for the check
     * @return Returns 'true' if the room is free and 'false' if not
     */
    @Override
    public boolean IsFree(LocalDate from, int days) {
        if(CheckOccupation(from, days))
            return true;
        else return false;
    }

    /**
     * Function that checks if the room is free in the given period of time
     * @param from Starting date for the check
     * @param days Number of days for the check
     * @return Returns 'true' if the room is free in the given period of time and 'false' if not
     */
    private boolean CheckOccupation(LocalDate from, int days){
        //if the occupied from and to dates are null it means that the room is always free
        if(this.occupiedFromDate == null && this.occupiedToDate == null)
            return true;

        //if the searched period is before the first coming occupation it means that the room is free
        if(from.isBefore(this.occupiedFromDate) && from.plus(days, ChronoUnit.DAYS).isBefore(this.occupiedFromDate)){
            return true;
        }
        else{
            //if the first day for the new occupation is after the last day in which the room is occupied it means
            //that the room is free
            if(from.isAfter(this.occupiedToDate))
                return true;
            else
                return false;
        }
    }

    /**
     * Function that returns the number of free beds in the room
     * @return Returns integer value for the number of free beds in the room
     */
    @Override
    public int NoOfBeds() {
        return numberOfBeds;
    }


    /**
     * Function that returns the final price for staying in the room for the list of guests
     * @param guests List of guests that are staying in this room
     * @return Returns the final price of the staying in double format
     */
    private double getPrice(List<Guest> guests) {
        double discount = 0;
        for(Guest g : guests){
            discount += g.getDiscount();
        }

        return roomPrice*(guests.size()) - roomPrice*discount;
    }

    /**
     * Function that makes the room unavailable in the given period of time, leaves a note, adds the guests to the list of guests in the room
     * and returns the price of the staying
     * @param guests List of guests that are checking in
     * @param days Number of days of staying
     * @param note Note from the guests
     * @param from Starting date for the staying
     * @return Returns the price of the staying
     */
    @Override
    public double CheckIn(List<Guest> guests, int days, String note, LocalDate from) {
        this.guests = guests;
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.numberOfBeds -= guests.size();
        return getPrice(guests);
    }

    /**
     * Function that checks all the guests out and makes the room available
     */
    @Override
    public void CheckOut() {
        this.guests = new ArrayList<>();
        this.occupiedFromDate = null;
        this.occupiedToDate = null;
        this.numberOfBeds = 3;
    }

    //making the room unavailable in the given time if the room will be repaired or other things

    /**
     * Function that makes the room unavailable for repairs or other reasons
     * @param from Starting date from which the room will be unavailable
     * @param days Number of days in which the room will be unavailable
     * @param note Note from the staff
     */
    public void MakeUnavailable(LocalDate from, int days, String note){
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.note = note;
    }

    /**
     * Function that returns the number of guests in the room
     * @return Returns integer value for the number of guests in the room
     */
    public int NumberOfGuests(){
        return this.guests.size();
    }

    /**
     * Function that returns list of the guests in the current room
     * @return Returns list of guests in the current room
     */
    public List<Guest> getGuests(){
        return this.guests;
    }

    /**
     * Function that sets the occupied period from given starting date and number of days staying
     * @param from Starting date for staying
     * @param days Number of days staying
     */
    public void setOccupiedFromAndToDate(LocalDate from, int days){
        this.occupiedFromDate = from;
        this.occupiedToDate = from.plusDays(days);
    }

    /**
     * Function that returns the note left for the room
     * @return Returns the current room's note
     */
    public String getNote(){
        return this.note;
    }
}
