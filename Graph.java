   import java.util.*;
   import java.lang.*;//for abs
  
   public class Graph{
      private ArrayList<Integer> avaliableRooms = new ArrayList<Integer>();
      //nothing yet
      public ArrayList<Integer> reachableRooms;
      //list of rooms that are reachable by room 0
      private ArrayList<Node> nodeArray = new ArrayList<Node>();
      //list of array of rooms as nodes
      
   
        // put up a wall between two rooms
      public void blockWall(Node a, Node b){
         ArrayList<Integer> adjRooms = new ArrayList<Integer>();
         int i = 0;
         adjRooms = a.getRoomsAccessible();
         while (i < adjRooms.size())
            if (adjRooms.get(i) == b.getRoomNumber()) 
               a.getRoomsAccessible().remove(i);
            else
               i++;
         a.getRoomsAccessible().trimToSize();
      			
         i = 0;
         adjRooms = b.getRoomsAccessible();
         while (i < adjRooms.size())
            if (adjRooms.get(i) == a.getRoomNumber()) 
               b.getRoomsAccessible().remove(i);
            else
               i++;
         a.getRoomsAccessible().trimToSize();
      }   
   	
   	    // what is the starting room?
      private Node checkStartingRoom(Node startRoom){
         int accessibleRoom = 0, roomNum = 0, roomsAdded = 0, newRoomNum = 0;
         startRoom.setPrevRoom(-2);//used to end while loop
         reachableRooms.add(startRoom.getRoomNumber());
      	//if startRoom.getRoomsReachable.size() < 1 //cant acccess other rooms
         for (int y = 0; y < startRoom.getRoomsAccessible().size(); y++){
            accessibleRoom = startRoom.getRoomsAccessible().get(y);//adj room
            reachableRooms.add(accessibleRoom);//adj room can get to room 0
            roomNum = startRoom.getRoomNumber();
            nodeArray.get(accessibleRoom).setPrevRoom(roomNum);
            roomsAdded++;// how many nodes a node has added to reachableRooms
         }
         startRoom.setRoomsAdded(roomsAdded);//tot room added to reachableRooms
         startRoom.setAdjRoomsChecked(1);//going to check an adj room
         newRoomNum = reachableRooms.size() - roomsAdded;//next room num
         return nodeArray.get(reachableRooms.get(newRoomNum));//gets next room
      } 
   	
        // calculate the rooms that you can get to
      public ArrayList<Integer> calculateRoomsReachable
                (ArrayList<Node> ndArray, int k){
         nodeArray = ndArray;
         int nextAddedRoom = 0, totRoomsAdded = 0, adjRoomsChecked = 0;
         reachableRooms = new ArrayList<Integer>(k*k);//able to hold all romms
         Node currentRoom = nodeArray.get(0);//starting room
         int roomsAdded = 0, accessibleRoom = 0, roomNum = 0, aNewRoomNum = 0;
      	
         currentRoom = checkStartingRoom(currentRoom);//sets up first room
         while (currentRoom.getPrevRoom() != -2){//while not starting room
            roomsAdded = 0;
            for (int y = 0; y < currentRoom.getRoomsAccessible().size(); y++){
               accessibleRoom = currentRoom.getRoomsAccessible().get(y);//adjRm
               if(nodeArray.get(accessibleRoom).getPrevRoom() == -1){//new room
                  reachableRooms.add(accessibleRoom);//prev room goes to room 0
                  roomNum = currentRoom.getRoomNumber();
                  nodeArray.get(accessibleRoom).setPrevRoom(roomNum);//set prev
                  roomsAdded++;
               }//end if(nodeArray.get(accessibleRoom)...
            }// end   for (int y = 0...
            currentRoom.setRoomsAdded(roomsAdded);//saves tot rooms added
            if (roomsAdded == 0)//no rooms added
               currentRoom = getPrevRoom(currentRoom);//get prev room
            else{// rooms added
               adjRoomsChecked = currentRoom.getAdjRoomsChecked();//adj checked
               currentRoom.setAdjRoomsChecked(adjRoomsChecked + 1);
               aNewRoomNum = reachableRooms.size() - roomsAdded;
               currentRoom = nodeArray.get(reachableRooms.get(aNewRoomNum));}
         }// end while (currentRoom.getPrevRoom() != -2)
         return reachableRooms;
      }
   	
            // get the room that you were just at
      private Node getPrevRoom(Node currentRoom){
         int nextAddedRoom = 0;
         int accessible = 0, nextAdjRoom = 0;
         currentRoom = nodeArray.get(currentRoom.getPrevRoom());
         int totRoomsAdded = currentRoom.getRoomsAdded();
         while(currentRoom.getAdjRoomsChecked() >= totRoomsAdded){
            if (currentRoom.getPrevRoom() == -2)
               break;
            currentRoom = nodeArray.get(currentRoom.getPrevRoom());
            totRoomsAdded = currentRoom.getRoomsAdded();
         }
         if (currentRoom.getPrevRoom() == -2)
            return currentRoom;
         accessible = currentRoom.getRoomsAccessible().size();
         nextAdjRoom = currentRoom.getAdjRoomsChecked();
         nextAddedRoom = currentRoom.getRoomsAccessible().get(nextAdjRoom);
         currentRoom.setAdjRoomsChecked(currentRoom.getAdjRoomsChecked()+1);
         return nodeArray.get(nextAddedRoom);
      }
   
        // what is the min work to access all rooms?
      public int minWorkForAllRoomsAccessible(Node room){
         int totMinWork = 0;
         int work = 0;
         int leastWork = 100;
         int totUnlockedRoom = 0;
         int size = 0;
         int firstRoom = 0, secondRoom = 0;
         int saveRoom = 0;
         Node aRoom;
         ArrayList<Integer> unlockedRooms = new ArrayList<Integer>();
         ArrayList<Integer> roomsWithLockedAdjRooms = new ArrayList<Integer>();
         roomsWithLockedAdjRooms.add(room.getRoomNumber());
         while(roomsWithLockedAdjRooms.size() != reachableRooms.size()){
            size = roomsWithLockedAdjRooms.size();
            for (int a = 0; a < size; a++){
               firstRoom = roomsWithLockedAdjRooms.get(a);
               aRoom = nodeArray.get(firstRoom);
               for (int z = 0; z < aRoom.getRoomsAccessible().size(); z++){
                  secondRoom = aRoom.getRoomsAccessible().get(z);
                  if (roomsWithLockedAdjRooms.contains(secondRoom) == false){
                     int math = Math.abs(firstRoom - secondRoom);
                     if (leastWork > math){
                        leastWork = Math.abs(firstRoom - secondRoom);
                        saveRoom = secondRoom;}
                  }
               }//end for (int z = 0;...
            }//end for (int a = 0;...
            totMinWork = totMinWork + leastWork;
            roomsWithLockedAdjRooms.add(saveRoom);
            leastWork = 100;
         }
         return totMinWork;
      }
   
   
      //(varatep) min work to move between a pair of rooms
      //helper method
      public int leastWorkOpenDoors(Node nd1, Node nd2){
         return Math.abs(nd1.getRoomNumber()-nd2.getRoomNumber());
      }
   
    //helper methods
      public int totalSpookinessBetweenRooms(Node nd1, Node nd2){
         int totalSpookiness = 0;
      
         return totalSpookiness;
      }
        //helper method
      public int maxSpookiness(Node nd1, Node nd2){
         int maxSpookiness = 0;
      
         return maxSpookiness;
      }
   
    // check if the edge exists
      public boolean checkEdge(int aRoomNum, int anAccessibleRoom, int k){
         if (anAccessibleRoom < 0)//outside low range
            return true;
         else if (anAccessibleRoom >= (k*k))//outside high range
            return true;
         else if (aRoomNum % k == 0){//left edge room
            if (anAccessibleRoom == aRoomNum + k - 1)
               return true;
            else if (anAccessibleRoom == aRoomNum - 1)
               return true;
            else
               return false;}
         else if ((aRoomNum + 1) % k == 0){//right edge room
            if (anAccessibleRoom == aRoomNum - k + 1)
               return true;
            else if (anAccessibleRoom == aRoomNum + 1)
               return true;
            else
               return false;}
         else
            return false;
      }
   }
