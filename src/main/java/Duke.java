import java.text.BreakIterator;
import java.util.Scanner;
import java.util.ArrayList;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        


        //print welcome message, ask for user input

        System.out.println("  ───── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ───── \n Hello! I'm Broccoli the dinosaur \n           <|°▿▿▿▿°|/ \n      What can I do for you? \n   ──── ･ ｡ﾟ☆: *.☽ .* :☆ﾟ. ────" ); 
        //getInput();
        ArrayList<Task> array=new ArrayList<Task>();

        int i = 0;
        while (i<100) {
            String[] hello = getInput();
            String combined = String.join(" ", hello);
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
            } else if(hello[0].equals("mark")){
                array.get((Integer.parseInt(hello[1])-1)).markAsDone();
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                System.out.println("      Nice! I've marked this task as done:");
                System.out.println("      "+ array.get((Integer.parseInt(hello[1])-1)).toString());
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            } else if(hello[0].equals("unmark")){
                array.get((Integer.parseInt(hello[1])-1)).markAsUnDone();
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                System.out.println("      OK, I've marked this task as not done yet:");
                System.out.println("      "+ array.get((Integer.parseInt(hello[1])-1)).toString());
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            }else {
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
    
    

}
