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
        ArrayList<String> array=new ArrayList<String>();

        int i = 0;
        while (i<100) {
            String hello = getInput();
            if(hello.equals("bye")) {
                System.out.println("    ──────── ⋅ ∙ ∘ ☽ ༓ ☾ ∘ ⋅ ⋅ ─────────");
                System.out.println( "      Bye. Hope to see you again soon!");
                System.out.println("\n   ‧˚₊꒷꒦︶︶︶︶︶꒷꒦︶︶︶︶︶꒦꒷‧₊˚⊹");
                break;

            } else if (hello.equals("list")){
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
                for(int j=0; j<array.size(); j++) {
                    System.out.println( "       "+ (j+1) + ". "+ array.get(j));
                }
                System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            } else {
                System.out.println("    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                System.out.println("     added:" + hello);
                System.out.println("\n    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
                array.add(hello);
                i++;
            }
        }

    }

    private static String getInput(){
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        return userinput;
    }



}
