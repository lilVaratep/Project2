   import java.util.*;
  
   public class Graph{
      int wallsPerRoom = 6;
      private ArrayList<Integer> avaliableRooms = new ArrayList<Integer>();
   
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
   
      public ArrayList calculateRoomsReachable(int adjacentRoom){
         boolean notInArray = true;
         for (int g = 0; g < avaliableRooms.size(); g++)
            if (avaliableRooms.get(g) == adjacentRoom){
               notInArray = false;
               break;}
         if (notInArray == true)
            avaliableRooms.add(adjacentRoom);
         return avaliableRooms;
      }
   
   
      public int[] minWorkForAllRoomsAccessible(Node nd){
         int[] minDoorWork = null;
      
         return minDoorWork;
      }
   
      public int[] leastWorkOpenDoors(Node nd1, Node nd2){
         int[] minDoorWorkBetweenRooms = null;
      
         return minDoorWorkBetweenRooms;
      }
   
      public int totalSpookinessBetweenRooms(Node nd1, Node nd2){
         int totalSpookiness = 0;
      
         return totalSpookiness;
      }
   
      public int maxSpookiness(Node nd1, Node nd2){
         int maxSpookiness = 0;
      
         return maxSpookiness;
      }
   
      public boolean checkEdge(int aRoomNum, int anAccessibleRoom, int k){
         if (anAccessibleRoom < 0)//outside low range
            return true;
         else if (anAccessibleRoom > (k*k))//outside high range
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
