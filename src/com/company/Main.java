package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Hotel hotel = HotelFactory.MakeHotel(5, 3, 0);
        FileActions.ReadFromFile("hotel.xml", hotel);
        //System.out.println(hotel.guests);
        int a;
        /*List<Guest> guests = new ArrayList<>();
        guests.add(new AdultGuest());
        guests.add(new RetiredGuest());
        hotel.CheckIn(RoomTypes.Small, guests, 4, "I like it here!", LocalDate.now());
        System.out.println(hotel.Report(LocalDate.now().minus(1, ChronoUnit.DAYS), LocalDate.now().plus(2, ChronoUnit.DAYS)));
        System.out.println(hotel.Availability(LocalDate.now()));
        hotel.CheckOut(2);
        System.out.println(hotel.Availability(LocalDate.now()));
        List<Guest> guest = new ArrayList<>();
        guest.add(new ChildGuest());
        hotel.CheckIn(RoomTypes.Small, guest, 4, "", LocalDate.now());
        FileActions.WriteToFile(hotel);*/
    }
}
