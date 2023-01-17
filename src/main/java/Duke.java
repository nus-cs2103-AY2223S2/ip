import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            Integer val = i+1;
            StringBuilder strbuild = new StringBuilder();
            strbuild.append(val.toString())
                    .append(". ")
                    .append(list.get(i));

            System.out.println(strbuild.toString());
        }
    }
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
        ArrayList<String> inputList  = new ArrayList<>();




        while (true) {
            String receive = newScan.nextLine(); //reads user input

            if ("bye".equalsIgnoreCase(receive)) {
                System.out.println(name + ": Bye! I'll miss all of you.");
                break;
            } else if ("list".equalsIgnoreCase(receive)) {
                printList(inputList);
            } else {
                inputList.add(receive);
                System.out.println("Added " + receive);
            }



            System.out.println(line);
        }
    }
}
