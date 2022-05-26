package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class FileActions {
    /**
     * Function that saves the data from the given Hotel to file at the given path
     * @param hotel Instance of Hotel class, containing the data that will be saved
     * @param filePath  Path to file where the data will be stored
     */
    public static void WriteToFile(Hotel hotel, String filePath){
        try {
            //creating the xml document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("hotel");
            doc.appendChild(rootElement);

            //saving the guests
            int guestIdCount = 1;
            for(Guest guest : hotel.guests){
                Element guests = doc.createElement("guests");
                rootElement.appendChild(guests);
                guests.setAttribute("id", Integer.toString(guestIdCount++));

                //t stands for 'type'
                Element type = doc.createElement("t");
                type.setTextContent(guest.getType());
                guests.appendChild(type);
            }

            //saving the rooms information
            int roomIdCount = 1;
            for (Room room : hotel.rooms) {

                Element rooms = doc.createElement("rooms");
                rootElement.appendChild(rooms);
                rooms.setAttribute("id", Integer.toString(roomIdCount++));

                Element number = doc.createElement("number");
                number.setTextContent(Integer.toString(room.getRoomNumber()));
                rooms.appendChild(number);

                Element type = doc.createElement("type");
                type.setTextContent(room.getType().toString());
                rooms.appendChild(type);

                //the guests are ordered, according to the occupation of the rooms
                //ex: if the first room has 2 guests, these 2 guests are the first that are written to the file
                Element guests = doc.createElement("guests");
                guests.setTextContent(Integer.toString(room.getGuests().size()));
                rooms.appendChild(guests);

                Element dateFrom = doc.createElement("occupied-from");
                dateFrom.setTextContent(room.getOccupiedFromDateString());
                rooms.appendChild(dateFrom);

                Element dateTo = doc.createElement("occupied-to");
                dateTo.setTextContent(room.getOccupiedToDateString());
                rooms.appendChild(dateTo);

                Element note = doc.createElement("note");
                note.setTextContent(room.getNote());
                rooms.appendChild(note);
            }


            FileOutputStream output =
                    new FileOutputStream(filePath);

            //writing the data to the file
            writeXml(doc, output);


        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Function that takes instance of Document and OutputStream and writes the data in a file
     * This function is taken from StackOverflow and modified
     * @param doc Instance of Document with the data, that will be saved
     * @param output Instance of OutputStream, which will save the data to file in the given location
     * @throws TransformerException
     */
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    /**
     * Function that takes path to the file with data and instance of Hotel class where the data will be loaded
     * @param filePath The path to the data file
     * @param hotel Instance of Hotel class in which the data will be loaded
     * @return Returns boolean value if the operation has succeeded or not
     */
    public static boolean ReadFromFile(String filePath, Hotel hotel){
        //checking if the file exists
        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            //if not - displaying error message and asking the user to try again
            MessageOutput.ConsoleOutput(DefaultMessagesTypes.NoSuchFile.label);
            return false;
        }

        List<Room> roomsList = new ArrayList<>();
        try{
            //loading the file
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            //loading the nodes for the guests and rooms
            NodeList guests = doc.getElementsByTagName("guests");
            NodeList rooms = doc.getElementsByTagName("rooms");

            //variable that indicates which guest from the nodes list must be read
            int guestNumber = 0;

            //iterating through the rooms
            for(int i = 0; i<rooms.getLength(); i++){
                Room roomFromFile = null;
                List<Guest> guestsFromFile = new ArrayList<>();
                Node roomsNode = rooms.item(i);
                if(roomsNode.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element roomElement = (Element) roomsNode;
                    switch (roomElement.getElementsByTagName("type").item(0).getTextContent()) {
                        case "Small":
                            roomFromFile = RoomFactory.SmallRoom(Integer.parseInt(roomElement.getElementsByTagName("number").item(0).getTextContent()));
                            break;
                        case "Large":
                            roomFromFile = RoomFactory.LargeRoom(Integer.parseInt(roomElement.getElementsByTagName("number").item(0).getTextContent()));
                            break;
                        case "VIP":
                            roomFromFile = RoomFactory.VipRoom(Integer.parseInt(roomElement.getElementsByTagName("number").item(0).getTextContent()));
                            break;
                    }

                        //if there are guests in the room
                        if(Integer.parseInt(roomElement.getElementsByTagName("guests").item(0).getTextContent()) != 0){

                            int numberOfGuests = Integer.parseInt(roomElement.getElementsByTagName("guests").item(0).getTextContent());
                            for(int j = guestNumber; j<guestNumber + numberOfGuests; j++){
                                Node guestsNode = guests.item(j);
                                if(guestsNode.getNodeType() == Node.ELEMENT_NODE)
                                {

                                    Element guestElement = (Element) guestsNode;
                                    String type = guestElement.getElementsByTagName("t").item(0).getTextContent();
                                    switch(type){
                                        case "Adult": guestsFromFile.add(new AdultGuest()); break;
                                        case "Child": guestsFromFile.add(new ChildGuest()); break;
                                        case "Retired": guestsFromFile.add(new RetiredGuest()); break;
                                    }
                                }
                            }
                            guestNumber++;
                            roomFromFile.CheckIn(guestsFromFile, (int)(DAYS.between(LocalDate.parse(roomElement.getElementsByTagName("occupied-from").item(0).getTextContent()), LocalDate.parse(roomElement.getElementsByTagName("occupied-from").item(0).getTextContent()))), "", LocalDate.parse(roomElement.getElementsByTagName("occupied-from").item(0).getTextContent()));
                        }

                }
                //adding the info to the hotel
                hotel.AddRoom(roomFromFile);
                hotel.AddGuests(guestsFromFile);
            }
            MessageOutput.ConsoleOutput(DefaultMessagesTypes.DataFromFileLoaded.label);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
