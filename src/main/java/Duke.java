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
                int tasknumber = Integer.valueOf(checker[1]) - 1;
                s.markTask(tasknumber);
            } else if (checker[0].toLowerCase().equals("unmark")) {
                int tasknumber = Integer.valueOf(checker[1]) - 1;
                s.unmarkTask(tasknumber);
            } else {

                if (checker[0].toLowerCase().equals("todo")) {
                    String[] checker2 = line.split("todo ");
                    s.addTodo(checker2[1]);
                } else if (checker[0].toLowerCase().equals("deadline")) {
                    String[] checker2 = line.split("/by ");
                    String[] checker3 = checker2[0].split("deadline ");
                    s.addDeadline(checker3[1], checker2[1]);
                } else if (checker[0].toLowerCase().equals("event")) {
                    String[] checker2 = line.split("/");
                    String[] checker3 = checker2[0].split("event ");
                    String[] checker4 = checker2[1].split("from ");
                    String[] checker5 = checker2[2].split("to ");
                    s.addEvent(checker3[1], checker4[1], checker5[1]);
                }
                System.out.println("Now you have " + s.lengthOflist() + "tasks in your list.");

            }


        }


    }
}

