// Varatep Buranintu
// Austin Myers
// Project 2

// Node.java

   import java.util.*;

   public class Node {
      int spookiness;
      int roomNumber;
      int work;
      int prevRoomNum;
      int roomsAdded;
      int adjRoomsChecked;
      int roomsReachableSize;
      ArrayList<Integer> roomsAccessible = new ArrayList<Integer>();
      
      public Node() {
         spookiness = 0;
         roomNumber = 0;
         prevRoomNum = 0;
         roomsAdded = 0;
         adjRoomsChecked = 0;
         roomsReachableSize = 0;
      }
   
      public Node(int roomNumber, int k, int spookiness, int prevRoomNum) {
         this.spookiness = spookiness;
         this.roomNumber = roomNumber;
         defaultRoomAccess(k, roomNumber);
         this.prevRoomNum = prevRoomNum;
      }
        
      public void defaultRoomAccess(int k, int roomNumber) {
      //inserts adjacent rooms with the
      //lowest room number to the highest room number
         roomsAccessible.add(roomNumber - k);
         roomsAccessible.add(roomNumber - k + 1);
         roomsAccessible.add(roomNumber - 1);
         roomsAccessible.add(roomNumber + 1);
         roomsAccessible.add(roomNumber + k - 1);
         roomsAccessible.add(roomNumber + k);    
      }
      public void setSpookiness(int n) {
         spookiness = n;
      }
      public int getSpookiness() {
         return spookiness;
      }
   
      public void setRoomNumber(int n) {
         roomNumber = n;
      }
      public int getRoomNumber() {
         return roomNumber;
      }
      public ArrayList<Integer> getRoomsAccessible() {
         return roomsAccessible;
      }
      public void setPrevRoom(int roomNum){
         prevRoomNum = roomNum;
      }
      public int getPrevRoom(){
         return prevRoomNum;
      }
      public void setRoomsAdded(int numOfRooms){
         this.roomsAdded = numOfRooms;
      }
   	
      public int getRoomsAdded(){
         return roomsAdded;
      }
   	
      public void setAdjRoomsChecked(int adjRoomsChecked){
         this.adjRoomsChecked = adjRoomsChecked;
      }
   	
      public int getAdjRoomsChecked(){
         return adjRoomsChecked;
      }
   }
