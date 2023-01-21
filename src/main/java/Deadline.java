 class Deadline extends Task {
     public Deadline(String message) {
         super(message);
     }

     @Override
     public String provideDetails() {
         String[] separateText = this.task.split("/by");

         return this.completed ? "[D]" + "[x] " + separateText[0] + "(by:" + separateText[1] + ")"
                               : "[D]" + "[ ] " + separateText[0] + "(by:" + separateText[1] + ")";
     }

 }
