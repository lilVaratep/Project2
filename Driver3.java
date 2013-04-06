   import java.util.*;//for scanner
   import java.io.*;//for writer
   import javax.swing.*;//for file chooser

	
   public class Driver3{
      Node start, room = null;
      private File txtFile;
      private String fileName, line;
      boolean kFound = false;
      int k = 1; 
      int roomNum, scaryRoomNum = 0;
   	
      public void readFile(){
      
         int arrayPos = 0;
         String []stringsOfInt;
         Node []nodeArray;
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
                     k = Integer.parseInt(stringsOfInt[1]);//get real k
                     nodeArray = new nodeArray(k*k);
                     kFound = true;}
                  else{
                  //creates node and puts it into array
                     nodeArray[roomNum] = new Node (Integer.parseInt(stringsOfInt[1]),roomNum);
                     roomNum++;
                  }
               }
               if (scaryRoomNum < (k*k)){
                  nodeArray[scaryRoomNum].setScary(Integer.parseInt(stringsOfInt[1]));
                  nodeArray[scaryRoomNum].setWork(Integer.parseInt(stringsOfInt[1]));//TEMPORARY
                  scaryRoomNum++;
               }
            }
         
            for (roomNum = 0; roomNum < (k*k); roomNum++){//gets shared walls
               nodeArray[roomNum].sharesWallWith(roomNum + 1, k);
               nodeArray[roomNum].sharesWallWtih(roomNum - 1, k);
               nodeArray[roomNum].sharesWallWith(roomNum + k, k);
               nodeArray[roomNum].sharesWallWith(roomNum + k - 1, k );
               nodeArray[roomNum].sharesWallWith(roomNum - k, k);
               nodeArray[roomNum].sharesWallWith(roomNum - k + 1, k);}
                
         }
            catch (Exception e){System.err.println("Error " + e);}
      }
   
      public static void main (String[] args) throws Exception {
         Driver3 d3 = new Driver3();
         d3.readFile();
      }
   }
