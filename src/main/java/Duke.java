import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke the Chatbot!\nHow may i help you today?\n");
        StorageList s = new StorageList();

        while (true) {
            String line = scanner.nextLine();
            String[] checker = line.split(" ");
            if (line.toLowerCase().equals("bye")) {
                System.out.println("See you soon!");
                break;
            } else if (line.toLowerCase().equals("list")) {
                s.printList();
            } else if (checker[0].toLowerCase().equals("mark")) {
                int tasknumber = Integer.valueOf(checker[1])-1;
                s.markTask(tasknumber);
            } else if (checker[0].toLowerCase().equals("unmark")) {
                int tasknumber = Integer.valueOf(checker[1])-1;
                s.unmarkTask(tasknumber);
            } else {
                s.addTask(line);
                System.out.println("Added task" + ": " + line);
            }


        }


    }
}

