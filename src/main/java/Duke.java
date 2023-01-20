import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String newLine = System.getProperty("line.separator");
        System.out.println("-------------------------------------------------------" +
                newLine + "Hello! Jak Sie Masz! I am Duke.\n What I do for you boss?");

        String inData;
        boolean exit = false;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();

        while(!exit) {
            if (inData.equals("bye")) {
                exit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(inData);
                inData = scan.nextLine();
            }
        }







    }
}
