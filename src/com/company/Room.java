package com.company;

import java.time.LocalDate;
import java.util.List;

public interface Room {
    /**
     * Function that returns whether the room is free in the given period of time
     * @param from Starting date for the check
     * @param days Number of days for the check
     * @return Returns 'true' if the room is free and 'false' if not
     */
    boolean IsFree(LocalDate from, int days);

    /**
     * Function that returns the number of free beds in the room
     * @return Returns integer value for the number of free beds in the room
     */
    int NoOfBeds();

    /**
     * Function that makes the room unavailable in the given period of time, leaves a note, adds the guests to the list of guests in the room
     * and returns the price of the staying
     * @param guests List of guests that are checking in
     * @param days Number of days of staying
     * @param note Note from the guests
     * @param from Starting date for the staying
     * @return Returns the price of the staying
     */
    double CheckIn(List<Guest> guests, int days, String note, LocalDate from);

    /**
     * Function that checks all the guests out and makes the room available
     */
    void CheckOut();

    /**
     * Function that makes the room unavailable for repairs or other reasons
     * @param from Starting date from which the room will be unavailable
     * @param days Number of days in which the room will be unavailable
     * @param note Note from the staff
     */
    void MakeUnavailable(LocalDate from, int days, String note);

    /**
     * Function that returns the number of guests in the room
     * @return Returns integer value for the number of guests in the room
     */
    int NumberOfGuests();

    /**
     * Function that returns the room type of the current room
     * @return Returns the room type
     */
    RoomTypes getType();

    /**
     * Function that returns list of the guests in the current room
     * @return Returns list of guests in the current room
     */
    List<Guest> getGuests();

    /**
     * Function that returns the date from which the room is occupied
     * @return Returns the date from which the room is occupied in LocalDate format
     */
    LocalDate getOccupiedFromDate();

    /**
     * Function that returns the date to which the room is occupied
     * @return Returns the date to which the room is occupied in LocalDate format
     */
    LocalDate getOccupiedToDate();

    /**
     * Function that sets the occupied period from given starting date and number of days staying
     * @param from Starting date for staying
     * @param days Number of days staying
     */
    void setOccupiedFromAndToDate(LocalDate from, int days);

    /**
     * Function that returns the current room's number
     * @return Returns the current rooms number in Integer format
     */
    int getRoomNumber();

    /**
     * Function that returns the note left for the room
     * @return Returns the current room's note
     */
    String getNote();

    /**
     * Function that returns the occupied from date in String format
     * @return Returns the occupied from date in String format
     */
    String getOccupiedFromDateString();

    /**
     * Function that returns the occupied to date in String format
     * @return Returns the occupied to date in String format
     */
    String getOccupiedToDateString();
}
