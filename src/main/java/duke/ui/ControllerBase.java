package duke.ui;

import duke.Duke;

public class ControllerBase {
   private Duke instance; 

   public void setDuke(Duke instance) {
        this.instance = instance;
   }
   
   public Duke getDuke() {
        return this.instance;
   }
}
