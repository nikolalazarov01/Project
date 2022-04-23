package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Hotel hotel = HotelFactory.MakeHotel(5, 3, 0);
        List<Guest> guest = new ArrayList<>();
        guest.add(new AdultGuest());
        hotel.CheckIn(RoomTypes.Small, guest, 4, "I like it here!", LocalDate.now());
        System.out.println(hotel.Report(LocalDate.now().minus(1, ChronoUnit.DAYS), LocalDate.now().plus(2, ChronoUnit.DAYS)));
    }
}
