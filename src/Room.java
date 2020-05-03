import java.util.Collections;
import java.util.ArrayList;
import java.lang.StringBuilder;

/*
* The Room class is part of the implementation of the Zork application
*    and is part of an assignment for Java Bootcamp
*
* Kim Levin
* 5/1/2020
*/
public class Room {
    private int roomNumber;
    private String roomName;
    private String roomContents;
    private String roomDoors;
    private int visitCount;
 
    public Room() {
        int visitCount = 0;
    }
 
    void setRoomNumber(int number) {
        roomNumber = number;
    }
 
    int getRoomNumber() {
        return roomNumber;
    }
 
    void setRoomName(String name) {
        roomName = name;
    }
 
    String getRoomName() {
        return roomName;
    }
 
    void setRoomContents(String contents) {
        roomContents = contents;
    }
 
    String getRoomContents() {
        return roomContents;
    }
 
    void setRoomDoors(String doors) {
        roomDoors = doors;
    }
 
   String getRoomDoors() {
       return roomDoors;
    }
 
    void setVisitCount(int visits) {
        visitCount = visits;
    }
 
    int getVisitCount() {
        return visitCount;
    }
 
    public String toString() {
        StringBuilder roomStr = new StringBuilder("Room #");  
        roomStr.append(roomNumber + " ");
        roomStr.append(roomName + " ");
        roomStr.append(roomContents + " ");
        roomStr.append(roomDoors + " ");
        roomStr.append(visitCount + " ");
        return roomStr.toString();
    }
} // end class
