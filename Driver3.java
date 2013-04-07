   import java.util.*;//for scanner
   import java.io.*;//for writer
   import javax.swing.*;//for file chooser

	
   public class Driver3{
      private Node start = null;
      private File txtFile;
      private String fileName, line;
      private boolean kFound = false;
      private Graph theGraph = new Graph();
      private int k = 2; 
      private ArrayList<Integer> roomsWithBlockedWalls = new ArrayList<Integer>();
      private ArrayList<Node> nodeArray = new ArrayList<Node>();
   	
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
         calcReachableRooms(k);
         calcMinWorkForTotalAccess(k);
         calcMinWorkBetweenRooms();
         calcSpookinessBetweenRooms();
         calcMaxSpookiness();
      }
   	
      private void checkIfIsEdge(){
         for (int i = 1; i < nodeArray.size();i++)
            theGraph.checkEdge(nodeArray.get(i));
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
   	
      private void calcReachableRooms(int k){
         int [] reachableRooms = new int[k*k];//in case all the rooms are reachable
         reachableRooms = theGraph.calculateRoomsReachable(nodeArray.get(0));
         for (int a = 0; a < reachableRooms.length; a++)
            System.out.print(reachableRooms[a]);
      }
   	
      private void calcMinWorkForTotalAccess(int k){
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
