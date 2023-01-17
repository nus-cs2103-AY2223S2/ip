import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        //Introductory Responses
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String name = "C4PO-Storage";
        String line = "-----------------------------------------------";
        String quote = "Hello. I don’t believe we have been introduced. A pleasure to meet you. I am " + name + " Human-Computer Relations.";
        String job = "I collect words which you say.";
        System.out.println(quote);
        System.out.println(job);
        System.out.println(name + ": Master, please type your response below.");
        System.out.println(line);
        System.out.println(line);

        //Init scanner
        Scanner newScan = new Scanner(System.in);

        //Init list
        //ArrayList<Task> inputList  = new ArrayList<>();


        //Main Loop

        while (true) {
            String receive = newScan.nextLine(); //reads user input

            if ("bye".equalsIgnoreCase(receive)) {
                System.out.println(name + ": Bye! I'll miss all of you.");
                break;
            } else if ("list".equalsIgnoreCase(receive)) {
                Task.printList();
            } else if (receive.length() > 5 && "mark ".equalsIgnoreCase(receive.substring(0,5))) {
                Integer index = Integer.valueOf(receive.substring(5));
                String out = Task.mark(index, "mark");
                System.out.println("Great job! I've marked this task as done. Task:");
                System.out.println(out);
            } else if (receive.length() > 7 && "unmark ".equalsIgnoreCase(receive.substring(0,7))) {
                Integer index = Integer.valueOf(receive.substring(7));
                String out = Task.mark(index, "unmark");
                System.out.println("Ahhh I see ...  I shall unmark that task then. *beep* Done. Task:");
                System.out.println(out);

            } else {
                Task newTask = new Task(receive);

                System.out.println("Added " + receive);
            }



            System.out.println(line);
        }
    }
}
