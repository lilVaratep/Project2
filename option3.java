void superscalar(){
      int overlaps = 0, pipeDelay = 0, dependDelay = 0;
      int overlap1 = 0, overlap2 = 0, overlap3 = 0, overlap4 = 0;
      int totalDelay = 0;
      int i;
      set[1].delay = 0;
      for (i = 2; i <=n; i++){
         dependDelay = 0;
         pipeDelay = 0;
        
         if (i >= 5){
            if (set [i-5].dest == set[i].src1 ||
            set [i-5].dest == set[i].src2)
               pipeDelay = 1;
         }
      	
         if (i >= 4){
            if (set [i-4].dest == set[i].src1 ||
            set [i-4].dest == set[i].src2){
               if (overlap1 == 0 && overlap2 == 0 &&
               overlap3 == 0 && overlap4 == 0){
                  overlap4 = 1;
                  if (pipeDelay == 0)
                     dependDelay = 0;
                  else
                     dependDelay = 1;
               }
               else
                  overlap4 = 0;
            }
            else
               overlap4 = 0;
         }
      
         if (i >= 3){
            if (set [i-3].dest == set[i].src1 ||
            set [i-3].dest == set[i].src2){
               if (overlap1 == 0 && overlap2 == 0 &&
               overlap3 == 0){
                  overlap3 = 1;
                  if (pipeDelay == 0)
                     dependDelay = 1;
                  else
                     dependDelay = 1;
               }
               else
                  overlap3 = 0;
            }
            else
               overlap3 = 0;
         }
      
         if (i >= 2){
            if (set [i-2].dest == set[i].src1 ||
            set [i-2].dest == set[i].src2){
               if (overlap1 == 0 && overlap2 == 0){
                  overlap2 = 1;
                  if (pipeDelay == 0)
                     dependDelay = 1;//check this
                  else
                     dependDelay = 1;
               }
               else
                  overlap2 = 0;
            }
            else
               overlap2 = 0;
         }
      
      
         if (i >= 1){
            if (set [i-1].dest == set[i].src1 ||
            set [i-1].dest == set[i].src2){
               overlap1 = 1;
               dependDelay = 2;
               pipeDelay = 1;
            }
            else
               overlap1 = 0;
         }
      
         set[i].delay = set [i-1].delay + dependDelay + pipeDelay;
      }
      totalDelay = set[n].delay + 6;
      printf("Total number of cycles: %i \n", totalDelay);
      printChart();
   	
      return;
   }
