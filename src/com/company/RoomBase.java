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
    public RoomBase(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber(){
        return this.roomNumber;
    }

    public String getOccupiedFromDateString(){
        if(this.occupiedFromDate == null)
            return "null";
        else return this.occupiedFromDate.toString();
    }

    public String getOccupiedToDateString(){
        if(this.occupiedToDate == null)
            return "null";
        else return this.occupiedToDate.toString();
    }

    public LocalDate getOccupiedFromDate(){
        return this.occupiedFromDate;
    }

    public LocalDate getOccupiedToDate(){
        return this.occupiedToDate;
    }

    public RoomTypes getType(){
        return this.type;
    }

    //If the room is free between two dates the function returns true, else returns false
    @Override
    public boolean IsFree(LocalDate from, int days) {
        if(CheckOccupation(from, days))
            return true;
        else return false;
    }

    //function that checks if the room is free
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

    @Override
    public int NoOfBeds() {
        return numberOfBeds;
    }

    //function that calculates the room prise when a payment system is introduced
    private double getPrice(List<Guest> guests) {
        double discount = 0;
        for(Guest g : guests){
            discount += g.getDiscount();
        }

        return roomPrice*(guests.size()) - roomPrice*discount;
    }

    //function that checks in the guests
    @Override
    public double CheckIn(List<Guest> guests, int days, String note, LocalDate from) {
        this.guests = guests;
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.numberOfBeds -= guests.size();
        return getPrice(guests);
    }

    //function that deletes all the information about the guests, that are being checked out
    @Override
    public void CheckOut() {
        this.guests = new ArrayList<>();
        this.occupiedFromDate = null;
        this.occupiedToDate = null;
        this.numberOfBeds = 3;
    }

    //making the room unavailable in the given time if the room will be repaired or other things
    public void MakeUnavailable(LocalDate from, int days, String note){
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.note = note;
    }

    public int NumberOfGuests(){
        return this.guests.size();
    }
    public List<Guest> getGuests(){
        return this.guests;
    }


    public void setOccupiedFromAndToDate(LocalDate from, int days){
        this.occupiedFromDate = from;
        this.occupiedToDate = from.plusDays(days);
    }

    public String getNote(){
        return this.note;
    }
}
