   import java.util.*;//for scanner
   import java.io.*;//for writer
   import javax.swing.*;//for file chooser

  
   public class Driver3{
      Node start, room = null;
      private File txtFile;
      private String fileName, line;
      boolean kFound = false;
      int k = -1; 
      int roomNum = 0;
		
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
            while ((line = readFile.readLine()) != null)
               if (roomNum < (k*k)){
                  stringsOfInt = line.split(" ");
                  if (kFound == false){
                     k = Integer.parseInt(stringsOfInt[1]);
                     nodeArray = new nodeArray(k*k);
                     kFound = true;}
                  else{
                     nodeArray[roomNum] = new Node (Integer.parseInt(stringsOfInt[1]), roomNum);
							roomNum++;
                  }
						}
						else{
						for (roomNum = 0; roomNum < (k*k); roomNum++){
						nodeArray[roomNum].sharesWallWith(roomNum + 1);
						nodeArray[roomNum].sharesWallWtih(roomNum - 1);
						nodeArray[roomNum].sharesWallWith(roomNum + k);
						nodeArray[roomNum].sharesWallWith(roomNum + k - 1);
						nodeArray[roomNum].sharesWallWith(roomNum - k);
						nodeArray[roomNum].sharesWallWith(roomNum - k + 1);}
			}
               
         		
            
            
         }
            catch (Exception e){System.err.println("Error " + e);}
      
      }
   
   
   
   
      public static void main (String[] args) throws Exception {
         Driver3 d3 = new Driver3();
         d3.readFile();
      }
   }
