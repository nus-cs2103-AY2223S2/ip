import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String newLine = System.getProperty("line.separator");
        ArrayList<String> tostore = new ArrayList<>(100);
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
            } else if (inData.equals("list")){
                int counter = 0;
                for(String i: tostore) {
                    counter++;
                    System.out.println(counter + ". " + i);
                }
                inData = scan.nextLine();
            } else {
                System.out.println("added: " + inData);
                tostore.add(inData);
                inData = scan.nextLine();
            }
        }







    }
}
