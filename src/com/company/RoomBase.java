package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class RoomBase implements Room{
    protected  RoomTypes type;
    private List<Guest> guests = new ArrayList<>();
    private LocalDate occupiedFromDate;
    private LocalDate occupiedToDate;
    protected double roomPrice;
    protected int numberOfBeds;
    private String note;


    public RoomTypes getType(){
        return this.type;
    }
    @Override
    public boolean IsFree(LocalDate from, int days) {
        if(CheckOccupation(from, days))
            return true;
        else return false;
    }

    private boolean CheckOccupation(LocalDate from, int days){
        if(from.isBefore(this.occupiedFromDate) && from.plusDays(days).isBefore(this.occupiedToDate)){
            return true;
        }
        else{
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

    private double getPrice(List<Guest> guests) {
        double discount = 0;
        for(Guest g : guests){
            discount += g.getDiscount();
        }

        return roomPrice*(guests.size()) - roomPrice*discount;
    }

    @Override
    public double CheckIn(List<Guest> guests, int days, String note, LocalDate from) {
        this.guests = guests;
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.numberOfBeds -= guests.size();
        return getPrice(guests);
    }

    @Override
    public void CheckOut() {
        this.guests = new ArrayList<>();
        this.occupiedFromDate = null;
        this.occupiedToDate = null;
        this.numberOfBeds = 3;
    }

    public void MakeUnavailable(LocalDate from, int days, String note){
        this.occupiedFromDate = from;
        this.occupiedToDate = this.occupiedFromDate.plusDays(days);
        this.note = note;
    }

    public int NumberOfGuests(){
        return this.guests.size();
    }

}
