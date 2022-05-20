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
                String path;
                menu.Display(MenuTypes.ChooseFile);
                path = sc.nextLine();
                filePath = Paths.get(path);
                FileActions.ReadFromFile(filePath.toString(), hotel);

                MessageOutput.ConsoleOutput(DefaultMessagesTypes.DataFromFileLoaded.label);
            }

        }

        while(true){
            String answer;
            Scanner sc = new Scanner(System.in);

            menu.Display(MenuTypes.Main);

            answer = sc.nextLine();

            switch (answer){
                case 1:{
                    menu.Display(MenuTypes.CheckInRoomType);
                }break;
                case 2:{
                    //
                } break;
                case 3:{
                    //
                } break;
                case 4:{
                    //
                } break;
                case 5:{
                    //
                } break;
                default: break;
            }
        }


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
