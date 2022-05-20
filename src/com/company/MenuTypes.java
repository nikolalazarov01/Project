package com.company;

public enum MenuTypes {
    WhereDoYouWantTheFile("Please insert the path where your file will be located: "),
    DoYouHaveFile("Do you have a file with data?(y/n)"),
    ChooseFile("If you have a file, please insert path to it: "),
    Main("1.Check In" +
            "2.Check Out" +
            "3.Availability" +
            "4.Report" +
            "5.Find" +
            "Your choice: "),
    CheckInRoomType("Please choose a room type:" +
            "1.Small" +
            "2.Large" +
            "3.VIP"),
    CheckInGuests("How many guests?"),
    GuestType("1.Adult" +
            "2.Retired" +
            "3.Child"),
    Days("How many days: "),
    Note("Leave a note: "),
    Date("When are you checking in (yyyy-mm-dd)");

    public final String label;

    private MenuTypes(String label){
        this.label = label;
    }


}
