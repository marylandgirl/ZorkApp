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
        int previousRoom;
        boolean done = false;
        String[] msgInfoArray;
        boolean foundSecret = false;
        Scanner keyboard = new Scanner(System.in);
        String kbInput = "";
        String errMsg = "";
  
        //method call to initialized the HashMaps with data
        populateMaps(roomNames, roomContents, roomDoors);
  
        //method call to create instances of Rooms and
        //update the roomsArray
        fillRooms(roomNames, roomContents, roomDoors,roomsArray);

        previousRoom = 0;
        currentRoom = 1;
        errMsg = "The direction entered is invalid. Please try again.\n";
        do{
            msgInfoArray = new String[5];
            toRooms(currentRoom,allRoomVisits,roomsArray,msgInfoArray,foundSecret);
            allRoomVisits = Integer.parseInt(msgInfoArray[3]);
            foundSecret = Boolean.parseBoolean(msgInfoArray[4]);
            System.out.print("You are in the " + msgInfoArray[0] + " ");
            System.out.println("and this room has " + msgInfoArray[1]+"." );
            System.out.print("Which direction would you like to go ... ");
            System.out.printf("%s\n",msgInfoArray[2] + "?\n");
            System.out.print("Enter the first letter of direction ");
            System.out.println("i.e. (N)orth,(S)outh,(E)ast,(W)est");
            System.out.print("followed by the room number. ");
            System.out.println("So, N1 means North to room 1");
            System.out.println("Or you could enter 'Q' to quit ");
            kbInput = keyboard.nextLine();
            previousRoom = currentRoom;
            currentRoom = Character.getNumericValue(kbInput
                        .charAt(kbInput.length()-1));
            String validStrTst = roomDoors.get(previousRoom);
            if (kbInput.trim().toUpperCase().equals("Q")) {
                done = true;
            }   else if (kbInput.length() > 1) {
                kbInput = Character.toString(kbInput.trim().charAt(0))
                        .toUpperCase() + kbInput.charAt(kbInput.length()-1);
                if ("".equals(validStrTst)) {
                    System.out.println(errMsg);
                    if (previousRoom > 0) {
                       currentRoom = 1;
                    } else {
                       currentRoom = previousRoom;
                    }
                } else if (!validStrTst.contains(",")) {
                     if (kbInput.equals(validStrTst) ) {
                         toRooms(currentRoom,allRoomVisits,roomsArray,msgInfoArray,foundSecret);
                     } else {
                         System.out.println(errMsg);
                         if (previousRoom > 0) {
                            currentRoom = 1;
                         } else {
                            currentRoom = previousRoom;
                         }
                     }
                } else {
                    String[] validArrayTst = validStrTst.split(",");
                    boolean found = false;
                    for ( int i = 0; i < validArrayTst.length; i++) {
                        if (validArrayTst[i].equals(kbInput)) {
                           found = true;
                           toRooms(currentRoom,allRoomVisits,roomsArray,msgInfoArray,foundSecret);
                        }
                    }
                    if (!found) {
                        System.out.println(errMsg);
                        if (previousRoom > 0) {
                           currentRoom = 1;
                        } else {
                           currentRoom = previousRoom;
                        }
                    }
                }
            } else {
                System.out.println(errMsg);
                if (previousRoom > 0) {
                   currentRoom = 1;
                } else {
                   currentRoom = previousRoom;
                }
            }
        }while(!done);
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
  
        rContents.put(1,"a dead scorpion");
        rContents.put(2,"a piano");
        rContents.put(3,"spiders");
        rContents.put(4,"a kitchen");
        rContents.put(5,"some dust and an empty box");
        rContents.put(6,"3 walking skeletons");
        rContents.put(7,"some piles of gold");
        rContents.put(8,"a treasure chest");
  
        rDoors.put(1,"N2");
        rDoors.put(2,"S1,W3,E4");
        rDoors.put(3,"E2,N5");
        rDoors.put(4,"W2,N7");
        rDoors.put(5,"S3");
        rDoors.put(6,"E7,E8");
        rDoors.put(7,"W6,S4");
        rDoors.put(8,"W6");
       
    } // end populateMaps

    static void fillRooms(HashMap<Integer,String> rNames, HashMap <Integer,String> rContents,HashMap <Integer,String> rDoors, Room[] rArray ){
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
            rArray[roomArrayIdx] = room;
            roomArrayIdx++;
        } // end for
    } // end fillRooms 

    static String[] toRooms(int cRoomNum, int allVisits,Room[] roomsArray, String[] msgInfoArray, boolean secretFound){
        if (cRoomNum == 8) {
            secretFound = true;
        }
        allVisits++;
        int idx = cRoomNum - 1;
        Room cRoom = roomsArray[idx];
        String testStr="";
        String[] testArray = null;
        int visitCnt = cRoom.getVisitCount();
        visitCnt++;
        cRoom.setVisitCount(visitCnt);
        msgInfoArray[0] = cRoom.getRoomName();
        msgInfoArray[1] = cRoom.getRoomContents();
        String connectRms = cRoom.getRoomDoors();
        if (cRoomNum == 6 && !secretFound ) {
            if ((visitCnt * 4) < allVisits ) {
                testStr = connectRms.replaceAll("[A-Z]8","");
                testStr = testStr.replaceAll(",,",",");
                testStr = testStr.replaceAll("N","North to room ");
            } else if ((visitCnt * 4) >= allVisits) {
                testStr = connectRms.replaceAll("N","North to room ");
            }
        } else {
            testStr = connectRms.replaceAll("N","North to room ");
        }
        testStr = testStr.replaceAll("S","South to room ");
        testStr = testStr.replaceAll("E","East to room ");
        testStr = testStr.replaceAll("W","West to room ");
        testStr = testStr.replaceAll(","," or ");
        if (testStr.endsWith(" or ")) {
            testStr = testStr.substring(0,testStr.length() - 4);
        }
        msgInfoArray[2] = testStr;
        msgInfoArray[3] = Integer.toString(allVisits);
        msgInfoArray[4] = Boolean.toString(secretFound);
        roomsArray[idx] = cRoom;
        return msgInfoArray;
    } // end toRooms
} // end class
