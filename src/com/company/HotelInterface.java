package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface HotelInterface {
    /**
     * Function that checks in guests in the hotel in a given room type for a period of time
     * @param roomType Type of room - small, large, vip etc.
     * @param guests List of guests that are going to be checked in
     * @param days Number of days of staying
     * @param note Note from the guests
     * @param from Starting date of staying
     */
    void CheckIn(RoomTypes roomType, List<Guest> guests, int days, String note, LocalDate from);

    /**
     * Function that checks out guests from a specific room
     * @param roomNumber Number of the room whose guests are checking out
     */
    void CheckOut(int roomNumber);

    /**
     * Function that finds the available rooms in a given period of time
     * @param date Date for which free rooms are searched
     * @return Returns list of available rooms
     */
    List<Room> Availability(LocalDate date);

    /**
     * Function that makes report for the rooms in the hotel in a given period of time
     * @param from Starting date for the report
     * @param to Ending date for the report
     * @return Returns the report in String format
     */
    String Report(LocalDate from, LocalDate to);

    /**
     * Function that finds room that needs to meet specific requirements
     * @param beds Number of beds
     * @param from Starting date for checking in
     * @param days Number of days for staying in
     * @return Returns the room that meets the requirements
     */
    Room Find(int beds, LocalDate from, int days);

    /**
     * Function that finds room that must meet specific requirements and if needed - changes the placement of the current guests
     * @param beds Number of beds needed
     * @param from Starting date for the staying
     * @param days Number of days staying
     * @return Returns the room that meets the requirements
     */
    Room FindUrgent(int beds, LocalDate from, int days);

    /**
     * Function that makes given room from the list of rooms in the hotel unavailable, because of repairs or some other reason
     * @param room Instance of room in the list of rooms in the hotel
     * @param from Starting date from which the room will be unavailable
     * @param days Number of days unavailable
     * @param note Note from the staff
     */
    void MakeUnavailable(Room room, LocalDate from, int days, String note);

}
