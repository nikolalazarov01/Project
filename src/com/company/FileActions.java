package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileActions {
    public static void WriteToFile(Hotel hotel){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("hotel");
            doc.appendChild(rootElement);


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

            int guestIdCount = 1;
            for(Guest guest : hotel.guests){
                Element guests = doc.createElement("guests");
                rootElement.appendChild(guests);
                guests.setAttribute("id", Integer.toString(guestIdCount++));

                Element type = doc.createElement("type");
                type.setTextContent(guest.getType());
                guests.appendChild(type);
            }
            FileOutputStream output =
                    new FileOutputStream("hotel.xml");

            writeXml(doc, output);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

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
}
