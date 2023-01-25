import java.text.BreakIterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("splitInput from\n" + logo);

        


        //print welcome message, ask for user input

        System.out.println("  ───── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───── \n splitInput! I'm Broccoli the dinosaur \n           <|°▿▿▿▿°|/ \n      What can I do for you? \n   ──── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────" ); 
        //getInput();
        ArrayList<Task> array=new ArrayList<Task>();

        int i = 0;
        while (i<100) {
            String[] splitInput = getInput();
            String combined = String.join(" ", splitInput);
            if(combined.equals("bye")) {
                System.out.println("    ──────── ⋅ ∙ ∘ ☽ ༓ ☾ ∘ ⋅ ⋅ ─────────");
                System.out.println( "      Bye. Hope to see you again soon!");
                System.out.println("\n   ‧˚₊꒷꒦︶︶︶︶︶꒷꒦︶︶︶︶︶꒦꒷‧₊˚⊹");
                break;

            } else if (combined.equals("list")){
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                for(int j=0; j<array.size(); j++) {
                    System.out.println("      "+(j+1) +"."+ array.get(j).toString());
                    //System.out.println( "       "+ (j+1) + ". "+ array.get(j));
                }
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            } else if(splitInput[0].equals("mark")){
                array.get((Integer.parseInt(splitInput[1])-1)).markAsDone();
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                System.out.println("      Nice! I've marked this task as done:");
                System.out.println("      "+ array.get((Integer.parseInt(splitInput[1])-1)).toString());
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            } 
            //Unmark the task as done
            else if(splitInput[0].equals("unmark")){
                array.get((Integer.parseInt(splitInput[1])-1)).markAsUnDone();
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                System.out.println("      OK, I've marked this task as not done yet:");
                System.out.println("      "+ array.get((Integer.parseInt(splitInput[1])-1)).toString());
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            } else if(splitInput[0].equals("deadline")){
                for(int j=1; j< splitInput.length; j++){
                    if(splitInput[j].equals("/by")){
                        for (int k=1; k< j-1; k++){
                            splitInput[1] = splitInput[1] + " " + splitInput[k+1];
                        }
                        for (int l=splitInput.length-1; l > j +1; l--){
                            splitInput[splitInput.length-1] = splitInput[l-1]+" "+splitInput[splitInput.length-1];
                        }
                    } else {
                        splitInput[j] = splitInput[j];
                    }
                }
              
                String date = splitInput[splitInput.length-1];
                String desc = splitInput[1];
                Deadline d = new Deadline(desc, date);
                System.out.println("    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                System.out.println("     Got it. I've added this task:");
                System.out.println("     " + d.toString());
                System.out.println("    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                array.add(d);
            } else {
                System.out.println("    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                System.out.println("     added:" + combined);
                System.out.println("\n    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                Task t = new Task(combined);
                array.add(t);
                i++;
            }
        }
        

    }

    private static String[] getInput(){
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        String [] splitted = userinput.split("\\s+");

        return splitted;
    }
    static class Task {
        protected String description;
        protected boolean isDone;
    
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
    
        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUnDone() {
            this.isDone = false;
        }

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
    
    static class Deadline extends Task {

        protected String by;
    
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
    
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    

}
