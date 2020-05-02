import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
* The ZorkApp class is an implementation of an assignment from Java Bootcamp
* It's a simplification of the text-only adventure game Zork.
* The player navigates the rooms of a castle by typing in commands
*/

public class ZorkApp {
    public static void main(String[] args) {
 
        //Initialize and Declare Variables
        HashMap<Integer,String> roomNames = new HashMap<>();
        HashMap<Integer,String> roomContents = new HashMap<>();
        HashMap<Integer,String> roomDoors = new HashMap<>();
        int allRoomVisits = 0;
        Room[] roomsArray = new Room[8];
        int currentRoom;
        boolean done = false;
        String[] msgInfoArray = new String[3];
        boolean foundSecret = false;
  
        //method call to initialized the HashMaps with data
        populateMaps(roomNames, roomContents, roomDoors);
  
        //method call to create instances of Rooms and
        //update the roomsArray
        fillRooms(roomNames, roomContents, roomDoors);
    } // end main

    static void populateMaps(HashMap<Integer,String> rNames, HashMap <Integer,String> rContents,HashMap <Integer,String> rDoors ){
        rNames.put(1,"foyer");     
        rNames.put(2,"front room");     
        rNames.put(3,"library");     
        rNames.put(4,"kitchen");     
        rNames.put(5,"dining room");     
        rNames.put(6,"vault");     
        rNames.put(7,"parlor");     
        rNames.put(8,"secret room");     
  
        rContents.put(1,"dead scorpion");
        rContents.put(2,"piano");
        rContents.put(3,"spiders");
        rContents.put(4,"kitchen");
        rContents.put(5,"dust,empty box");
        rContents.put(6,"3 walking skeletons");
        rContents.put(7,"piles of gold");
        rContents.put(8,"treasure chest");
  
        rDoors.put(1,"N1");
        rDoors.put(2,"S1,W3,E4");
        rDoors.put(3,"E2,N5");
        rDoors.put(4,"W2,N7");
        rDoors.put(5,"S3");
        rDoors.put(6,"E7,E8");
        rDoors.put(7,"W6,S4");
        rDoors.put(8,"W6");
       
    } // end populateMaps

    static void fillRooms(HashMap<Integer,String> rNames, HashMap <Integer,String> rContents,HashMap <Integer,String> rDoors ){
        int roomNumber = 0;
        String nameOfRoom = "";
        String contentsInRoom = "";
        String doorsInRoom = "";
        int visitCount = 0;
        int roomArrayIdx = 0;
        for (int i = 1; i <= 8; i++) {
            roomNumber = i;
            nameOfRoom = rNames.get(i);
            contentsInRoom = rContents.get(i);
            doorsInRoom = rDoors.get(i);
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setRoomName(nameOfRoom);
            room.setRoomContents(contentsInRoom);
            room.setRoomDoors(doorsInRoom);
            room.setVisitCount(visitCount);
            roomsArray[roomArrayIdx] = room;
            roomArrayIdx++;
        } // end for
    } // end fillRooms 
} // end class
