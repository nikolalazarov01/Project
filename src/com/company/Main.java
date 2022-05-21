package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Hotel hotel = HotelFactory.MakeHotel(5, 3, 0);
        Menu menu = new Menu();
        Path filePath = Paths.get("hotel.xml");

        if(Files.exists(filePath)){
            FileActions.ReadFromFile(filePath.toString(), hotel);
            MessageOutput.ConsoleOutput(DefaultMessagesTypes.DataFromFileLoaded.label);
        }

        else{
            String answer;
            Scanner sc = new Scanner(System.in);
            menu.Display(MenuTypes.DoYouHaveFile);
            answer = sc.nextLine();
            if(answer.equals("y")) {
                while(true){
                    String path;
                    menu.Display(MenuTypes.ChooseFile);
                    path = sc.nextLine();
                    if(path.charAt(path.length() - 1) == '\\'){
                        path.substring(0, path.length()-1);
                    }
                    filePath = Paths.get(path + "\\hotel.xml");
                    if(FileActions.ReadFromFile(filePath.toString(), hotel))
                        break;
                }
            }
        }

        while(true){
            String answer;
            Scanner sc = new Scanner(System.in);

            menu.Display(MenuTypes.Main);

            answer = sc.nextLine();

            switch (Integer.parseInt(answer)){
                case 1:{
                    String choice;
                    List<Guest> guests = new ArrayList<>();
                    menu.Display(MenuTypes.CheckInGuests);
                    //How manu guests
                    choice = sc.nextLine();
                    String guestType;
                    for(int i = 0; i<Integer.parseInt(choice); i++){
                        menu.Display(MenuTypes.GuestType);
                        guestType = sc.nextLine();
                        switch (Integer.parseInt(guestType)){
                            case 1: guests.add(new AdultGuest()); break;
                            case 2: guests.add(new RetiredGuest()); break;
                            case 3: guests.add(new ChildGuest()); break;
                            default: break;
                        }
                    }

                    menu.Display(MenuTypes.Days);
                    int days = sc.nextInt();
                    //in order to read the newline
                    sc.nextLine();

                    menu.Display(MenuTypes.Note);
                    String note = sc.nextLine();

                    menu.Display(MenuTypes.Date);
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    menu.Display(MenuTypes.CheckInRoomType);
                    choice = sc.nextLine();
                    switch (Integer.parseInt(choice)){
                        case 1: {
                            hotel.CheckIn(RoomTypes.Small, guests, days, note, date);
                        }break;
                        case 2:{
                            hotel.CheckIn(RoomTypes.Large, guests, days, note, date);
                        }break;
                        case 3:{
                            hotel.CheckIn(RoomTypes.Vip, guests, days, note, date);
                        }break;
                        default: break;
                    }

                }break;

                case 2:{
                    menu.Display(MenuTypes.RoomNumber);
                    int number = sc.nextInt();

                    hotel.CheckOut(number);
                } break;
                case 3:{
                    LocalDate date;
                    menu.Display(MenuTypes.AvailabilityDate);
                    date = LocalDate.parse(sc.nextLine());
                    List<Room> roomsAvailable = hotel.Availability(date);
                    System.out.println(roomsAvailable);
                } break;
                case 4:{
                    LocalDate fromDate;
                    LocalDate toDate;
                    menu.Display(MenuTypes.FromDate);
                    fromDate = LocalDate.parse(sc.nextLine());

                    menu.Display(MenuTypes.ToDate);
                    toDate = LocalDate.parse(sc.nextLine());

                    String report = hotel.Report(fromDate, toDate);
                    System.out.println(report);
                } break;
                case 5:{
                    //int beds, LocalDate from, int days
                    int beds;
                    menu.Display(MenuTypes.Beds);
                    beds = sc.nextInt();

                    LocalDate from;
                    menu.Display(MenuTypes.FromDate);
                    from = LocalDate.parse(sc.nextLine());

                    int days;
                    menu.Display(MenuTypes.Days);
                    days = sc.nextInt();

                    Room room = hotel.Find(beds, from, days);

                    System.out.println(room.toString());
                } break;
                case 0:{
                    String choice;
                    menu.Display(MenuTypes.SaveFileLocation);
                    choice = sc.nextLine();
                    switch (choice.toLowerCase(Locale.ROOT)){
                        case "y":{
                            String path;
                            menu.Display(MenuTypes.WhereDoYouWantTheFile);
                            path = sc.nextLine() + "\\hotel.xml";
                            FileActions.WriteToFile(hotel, path);
                        }break;
                        case "n":{
                            FileActions.WriteToFile(hotel, filePath.toString());
                        }break;
                    }


                }break;
                default: break;
            }
            //sc.close();
        }


        //System.out.println(hotel.guests);

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
