   import java.util.*;//for scanner
   import java.io.*;//for writer
   import javax.swing.*;//for file chooser

	
   public class Driver3{
      Node start = null;
      private File txtFile;
      private String fileName, line;
      boolean kFound = false;
      int k = 1; 
      ArrayList<Integer> roomsWithBlockedWalls = new ArrayList<Integer>();
   	
      public void readFile(){
         int scaryRoomNum = 0;
         int arrayPos, roomNum = 0;
         int room;
         String []stringsOfInt;
         ArrayList<Node> nodeArray = new ArrayList<Node>();
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
                  //creates node and puts it into array
                     nodeArray.add(new Node (Integer.parseInt(stringsOfInt[0]),roomNum));
                     roomNum++;
                  }
                  continue;
               }
               else if (scaryRoomNum < (k*k)){
                  nodeArray.get(scaryRoomNum).setSpookiness(Integer.parseInt(stringsOfInt[0]));
                  scaryRoomNum++;
               
               }
               else{
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[0]));
                  roomsWithBlockedWalls.add(Integer.parseInt(stringsOfInt[1]));
               }
            }
            for (roomNum = 0; roomNum < (k*k); roomNum++)//gets shared walls
               nodeArray.get(scaryRoomNum).defaultRoomAccess(k, roomNum);					
         }
            catch (Exception e){System.err.println("Error " + e);}
      }
   	
      public static void main (String[] args) throws Exception {
         Driver3 d3 = new Driver3();
         d3.readFile();
      }
   }
