import java.util.Scanner;

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
        getInput();
    }

    private static void getInput() {
        //create scanner object
        Scanner sc = new Scanner(System.in);
        //get user input
        String userinput = sc.next();
        if (userinput.equals("bye")) {
            System.out.println("    ────────────── ⋆⋅☆⋅⋆ ───────────────");
            System.out.println( "      Bye. Hope to see you again soon!");
            System.out.println("\n   ‧˚₊꒷꒦︶︶︶︶︶꒷꒦︶︶︶︶︶꒦꒷‧₊˚⊹");

        } else {
            System.out.println("    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
            System.out.println("     " + userinput);
            System.out.println("\n    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══");
            getInput();
        }
    }
}
