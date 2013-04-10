   import java.util.*;//for scanner
   import java.io.*;//for writer
   import javax.swing.*;//for file chooser

	
   public class Driver3{
      private File txtFile;
      private String fileName, line;
      private boolean kFound = false;
      private Graph theGraph = new Graph();
      private int k = 2; 
      private ArrayList<Integer> roomsWithBlockedWalls = new ArrayList<Integer>();
      private ArrayList<Node> nodeArray = new ArrayList<Node>();
      private ArrayList<Integer> reachableRooms = new ArrayList<Integer>(); 
      private int aRoom = 0;
   	
      public void readFile(){
         int scaryRoomNum, arrayPos, roomNum= 0;
         int room;
         String []stringsOfInt;
         try{//choose a file
            JFileChooser aChooser = new JFileChooser();
            System.out.println("Please choose a .txt file");
            aChooser.showOpenDialog(null);//opens pop-up window to choose file
            txtFile = aChooser.getSelectedFile();
            fileName = txtFile.getName();
            BufferedReader readFile = new BufferedReader(new FileReader(fileName));
            while ((line = readFile.readLine()) != null){
               stringsOfInt = line.split(" ");//needed to read diff rooms on same line
               if (roomNum < (k*k)){//initially one, but we get the real k later
                  if (kFound == false){//only gets the k val
                     k = Integer.parseInt(stringsOfInt[0]);//get real k
                     kFound = true;}
                  else{
                  //creates node and puts it into array and adds spookiness
                     nodeArray.add(new Node (roomNum, k, Integer.parseInt(stringsOfInt[0])));
                     roomNum++;}
               }
               else{//make array of rooms with blocked walls
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[0]));
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[1]));}
            }				
         } 
            catch (Exception e){System.err.println("Error " + e);}
         checkIfIsEdge();
         setBlockedWalls();
         calcReachableRooms();
        // calcMinWorkForTotalAccess();
        // calcMinWorkBetweenRooms();
        // calcSpookinessBetweenRooms();
        // calcMaxSpookiness();
      }
   	
      private void checkIfIsEdge(){
         int specificRoomNum, accessibleRoom;
         int v = 0;
         for (int i = 0; i < nodeArray.size();i++){//for each node
            specificRoomNum = nodeArray.get(i).getRoomNumber();
            v = 0;
            while (v < nodeArray.get(i).getRoomsAccessible().size()){
               accessibleRoom = nodeArray.get(i).getRoomsAccessible().get(v);
               if (theGraph.checkEdge(specificRoomNum, accessibleRoom, k) == true){
                  nodeArray.get(i).getRoomsAccessible().remove(v);
                  nodeArray.get(i).getRoomsAccessible().trimToSize();}
               else
                  v++;
            }
         }
      }
   	
      private void setBlockedWalls(){
         Node firstRoom, secondRoom;
         int firstRoomNum, secondRoomNum;
         for (int i = 1; i < roomsWithBlockedWalls.size();i++){
            firstRoomNum = roomsWithBlockedWalls.get(i-1);//gets roomnumber
            secondRoomNum = roomsWithBlockedWalls.get(i);//gets roomnumber
            firstRoom = nodeArray.get(firstRoomNum);//gets node
            secondRoom = nodeArray.get(secondRoomNum);//gets node
            theGraph.blockWall(firstRoom,secondRoom);}
      }
   
      private void calcReachableRooms(){//gets a room and sees if there is a path to starting room
         Node room, startRoom;
         int adjacentRoom;
         reachableRooms.add(nodeArray.get(0).getRoomNumber());// . . . . . . . . . . . .room 0 always first starting room
         for (int y = 1; y < nodeArray.size(); y++){//                                  for each room
            room = nodeArray.get(y);// . . . . . . . . . . . . . . . . . . . . . . . . .set as starting room
            startRoom = room;//                                                         save starting room to possibly add to array
            while (room != nodeArray.get(0)){//. . . . . . . . . . . . . . . . . . . . .while we have not gotten to room 0
               for(int adjRm = 0; adjRm < room.getRoomsAccessible().size(); adjRm++){// for each room accessible
                  int adjacentRoomNum = room.getRoomsAccessible().get(adjRm);// . . . . get an accessible room number
                  for(int rchRmNum = 0; rchRmNum < reachableRooms.size(); rchRmNum++){//for each room that -> room 0
                     int reachableRoomNum = reachableRooms.get(rchRmNum);// . . . . . . get a specific reachable room
                     if (adjacentRoomNum == reachableRoomNum){//								 if the room has an adjacent room number that has a path to room 0
                        room = nodeArray.get(0);// . . . . . . . . . . . . . . . . . . .room is reachable(path to room 0)
                        break;}//got to room 0
                  }
                  if(room == nodeArray.get(0))
                     break;
               }
            	
               if(room == nodeArray.get(0))
                  break;
               else{
                  int w = 0;
                  Node lowAdjRoom = nodeArray.get(room.getRoomsAccessible().get(w));
                  if (lowAdjRoom.getPrevRoom() == room.getRoomNumber())
                     break;
                  else{
                     lowAdjRoom.setPrevRoom(room.getRoomNumber());
                     room = lowAdjRoom;}
               }
            }
            if (room == nodeArray.get(0))
               reachableRooms.add(startRoom.getRoomNumber());
         }
               			
         
         for (int b = 0; b <reachableRooms.size();b++)
            System.out.print(reachableRooms.get(b) + ", ");
         
      }
   	
      private void calcMinWorkForTotalAccess(){
         int [] minWork = new int[k];// not sure if long enough
         minWork = theGraph.minWorkForAllRoomsAccessible(nodeArray.get(0));
         for (int a = 0; a < minWork.length; a++)
            System.out.print(minWork[a]);
      }
   	
      private void calcMinWorkBetweenRooms(){
         int [] minWorkBetweenRooms = new int[k];// not sure if long enough
         Node startingRoomNode, endingRoomNode;
         Scanner aScanner = new Scanner (System.in);
         System.out.println("Enter starting room");
         startingRoomNode = nodeArray.get(aScanner.nextInt());
         System.out.println("Enter ending room");
         endingRoomNode = nodeArray.get(aScanner.nextInt());
         minWorkBetweenRooms = theGraph.leastWorkOpenDoors(startingRoomNode,endingRoomNode);
         for (int a = 0; a < minWorkBetweenRooms.length; a++)
            System.out.print(minWorkBetweenRooms[a]);
      } 
   	
      private void calcSpookinessBetweenRooms(){
         int SpookinesskBetweenRooms = 0;
         Node startingRoomNode, endingRoomNode;
         Scanner aScanner = new Scanner (System.in);
         System.out.println("Enter starting room");
         startingRoomNode = nodeArray.get(aScanner.nextInt());
         System.out.println("Enter ending room");
         endingRoomNode = nodeArray.get(aScanner.nextInt());
         SpookinesskBetweenRooms = theGraph.totalSpookinessBetweenRooms(startingRoomNode,endingRoomNode);
         System.out.print(SpookinesskBetweenRooms);
      } 
   
      private void calcMaxSpookiness(){
         int SpookinesskBetweenRooms = 0;
         Node startingRoomNode, endingRoomNode;
         Scanner aScanner = new Scanner (System.in);
         System.out.println("Enter starting room");
         startingRoomNode = nodeArray.get(aScanner.nextInt());
         System.out.println("Enter ending room");
         endingRoomNode = nodeArray.get(aScanner.nextInt());
         SpookinesskBetweenRooms = theGraph.maxSpookiness(startingRoomNode,endingRoomNode);
         System.out.print(SpookinesskBetweenRooms);
      
      }
   
      public static void main (String[] args) throws Exception {
         Driver3 d3 = new Driver3();
         d3.readFile();
      }
   }
