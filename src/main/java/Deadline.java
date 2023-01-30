 class Deadline extends Task {
     public Deadline(String keyword, String message, Boolean status) {
         super(keyword, message, status);
     }

     @Override
     public String provideDetails() {
         String[] separateText = this.description.split("/by");

         return this.completed ? "[D]" + "[x] " + separateText[0] + "(by:" + separateText[1] + ")"
                               : "[D]" + "[ ] " + separateText[0] + "(by:" + separateText[1] + ")";
     }

 }
