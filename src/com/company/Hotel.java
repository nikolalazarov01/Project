package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Hotel {
    void CheckIn(RoomTypes roomType, List<Guest> guests, int days, String note, LocalDate from);
    void CheckOut(Room room);
    LocalDate availability(LocalDate date);
    void Report(LocalDate from, LocalDate to);
    Room Find(int beds, LocalDate from, int days);
    Room FindUrgent(int beds, LocalDate from, int days);
    void MakeUnavailable(Room room, LocalDate from, int days, String note);

}
