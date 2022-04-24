package com.company;

import java.time.LocalDate;
import java.util.List;

public interface Room {
    boolean IsFree(LocalDate from, int days);
    int NoOfBeds();
    double CheckIn(List<Guest> guests, int days, String note, LocalDate from);
    void CheckOut();
    void MakeUnavailable(LocalDate from, int days, String note);
    int NumberOfGuests();
    RoomTypes getType();
    List<Guest> getGuests();
    LocalDate getOccupiedFromDate();
    LocalDate getOccupiedToDate();
    void setOccupiedFromAndToDate(LocalDate from, int days);
    int getRoomNumber();
    String getNote();
    String getOccupiedFromDateString();
    String getOccupiedToDateString();
}
