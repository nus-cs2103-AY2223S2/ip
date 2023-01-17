import java.util.Scanner;

public class Duke {
    public static void print(String s) {
        String p = "\t____________________________________________________________\n"
                + s
                +"\t____________________________________________________________\n";
        System.out.println(p);
    }

    public static void main(String[] args) {
        String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
        Duke.print(intro);

        String input;
        while (true) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String in = myObj.nextLine();  // Read user input
            input = in; // save the input

            if (input.toLowerCase().equals("bye")) {
                String extStr = "\t Bye. Hope to see you again soon!\n";
                Duke.print(extStr);
                System.exit(0);
            } else if (input.equals("")) {
                String empStr = "\t Please enter an input for me to echo!\n";
                Duke.print(empStr);
            } else {
                String out = String.format("\t %s\n", input);
                Duke.print(out);
            }
        }


    }
}
