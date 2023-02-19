import java.util.Scanner;

public class Duke {

    public static void userInput() {
<<<<<<< .merge_file_a14772
        Data data = new Data();
=======
        Data data = FileManager.populateList();
>>>>>>> .merge_file_a22372
        System.out.println("Greetings");
        Scanner user = new Scanner(System.in);

        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) {
<<<<<<< .merge_file_a14772
=======
                FileManager.writeFile(data);
>>>>>>> .merge_file_a22372
                System.out.println("Bye!");
                break;
            }

            if (input.equals("list")) {
                data.printData();
                continue;
            }

            if (input.contains("unmark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                data.unmark(pos-1);
                System.out.println("Unmarked:");
                System.out.println(data.getEntry(pos-1).toString());
                continue;
            }

            if (input.contains("mark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                data.mark(pos-1);
                System.out.println("Marked:");
                System.out.println(data.getEntry(pos-1).toString());
                continue;
            }

            if (input.contains("delete")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                Task del = data.getEntry(pos-1);
                data.removeEntry(pos-1);
                System.out.println("Deleted:");
                System.out.println(del.toString());
                continue;
            }

            if (input.contains("todo ")) {
                Task todo = new ToDo();
<<<<<<< .merge_file_a14772
                try {
                    todo.genDscp(input);
=======
                String description = input.replace("todo ", "");
                try {
                    todo.genDscp(description);
>>>>>>> .merge_file_a22372
                } catch (DukeExceptions e){
                    System.out.println(e.getMessage());
                    continue;
                }
                data.addEntry(todo);
<<<<<<< .merge_file_a14772
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
=======
                System.out.println(String.format("Now you have %d tasks in the list", data.getSize()));
>>>>>>> .merge_file_a22372
                continue;
            }

            if (input.contains("event ")) {
                Task event = new Event();
<<<<<<< .merge_file_a14772
                try {
                    event.genDscp(input);
=======
                String description = input.replace("event ", "");
                try {
                    event.genDscp(description);
>>>>>>> .merge_file_a22372
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                data.addEntry(event);
<<<<<<< .merge_file_a14772
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
=======
                System.out.println(String.format("Now you have %d tasks in the list", data.getSize()));
>>>>>>> .merge_file_a22372
                continue;
            }

            if (input.contains("deadline ")) {
                Task deadline = new Deadline();
<<<<<<< .merge_file_a14772
                try {
                    deadline.genDscp(input);
=======
                String description = input.replace("deadline ", "");
                try {
                    deadline.genDscp(description);
>>>>>>> .merge_file_a22372
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                data.addEntry(deadline);
<<<<<<< .merge_file_a14772
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
=======
                System.out.println(String.format("Now you have %d tasks in the list", data.getSize()));
>>>>>>> .merge_file_a22372
                continue;
            }
            System.out.println("I do not understand your instructions...");
        }
        return;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        userInput();
    }
}
