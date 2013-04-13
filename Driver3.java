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
      
      private int aRoom = 0;
      int r= 1;
      int newRoomsAdded = 0;
   	
      public void readFile(){
         System.out.println("Welcome to Castle Helper.");
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
                     nodeArray.add(new Node (roomNum, k, Integer.parseInt(stringsOfInt[0]), -1));
                     roomNum++;}
               }
               else{//make array of rooms with blocked walls
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[0]));
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[1]));}
            }				
         } 
            catch (Exception e){System.err.println("Error " + e);}
         calcStuff();
      }
   				
      private void calcStuff(){
         checkIfIsEdge();
         setBlockedWalls();
         calcReachableRooms();
         calcMinWorkForTotalAccess();
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
   
      private void calcReachableRooms(){//goes from starting room to get to other rooms
         ArrayList<Integer> reachableRooms = new ArrayList<Integer>(); 
         reachableRooms = theGraph.calculateRoomsReachable(nodeArray, k);
         System.out.print("The reachable rooms are: "); 
         for (int b = 0; b < k*k;b++)
            if (reachableRooms.indexOf(b) != -1)
               System.out.print(b + ", ");
         System.out.println();
      }
      
   	
      private void calcMinWorkForTotalAccess(){
         for (int i = 0; i < nodeArray.size(); i++)
            nodeArray.get(i).setRoomsAdded(0);
         int minWork = 0;// not sure if long enough
         minWork = theGraph.minWorkForAllRoomsAccessible(nodeArray.get(0));
         System.out.print("The minimum amount of work necessary to open doors so");
         System.out.print ("that all rooms are accessable is: ");
         System.out.println(minWork);
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
