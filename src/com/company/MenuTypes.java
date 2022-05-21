package com.company;

public enum MenuTypes {
    WhereDoYouWantTheFile("Please insert the path where your file will be located: "),
    SaveFileLocation("Do you want to save your file in different location?(y/n)"),
    DoYouHaveFile("Do you have a file with data?(y/n)"),
    ChooseFile("If you have a file, please insert path to it(Without the file name, it is always hotel.xml): "),
    Main("1.Check In \n" +
            "2.Check Out \n" +
            "3.Availability \n" +
            "4.Report \n" +
            "5.Find \n" +
            "0.Exit and save \n" +
            "Your choice: "),
    CheckInRoomType("Please choose a room type:\n" +
            "1.Small\n" +
            "2.Large\n" +
            "3.VIP\n"),
    CheckInGuests("How many guests?\n"),
    GuestType("1.Adult\n" +
            "2.Retired\n" +
            "3.Child\n"),
    Days("How many days: "),
    Note("Leave a note: "),
    Date("When are you checking in (yyyy-mm-dd): "),
    AvailabilityDate("Available when(yyyy-mm-dd): "),
    FromDate("From when(yyyy-mm-dd): "),
    ToDate("Until when(yyyy-mm-dd): "),
    Beds("How many beds: "),
    RoomNumber("What is the room number: ");

    public final String label;

    private MenuTypes(String label){
        this.label = label;
    }


}
