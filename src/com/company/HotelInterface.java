package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface HotelInterface {
    void CheckIn(RoomTypes roomType, List<Guest> guests, int days, String note, LocalDate from);
    void CheckOut(Room room);
    List<Room> Availability(LocalDate date);
    String Report(LocalDate from, LocalDate to);
    Room Find(int beds, LocalDate from, int days);
    Room FindUrgent(int beds, LocalDate from, int days);
    void MakeUnavailable(Room room, LocalDate from, int days, String note);

}
