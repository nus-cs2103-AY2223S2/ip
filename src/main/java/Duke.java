import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke the Chatbot!\nHow may i help you today?\n");
        StorageList s = new StorageList();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] checker = line.split(" ");
            String type = checker[0].toLowerCase().trim();

            try {
                Commands command = Commands.valueOf(type);
                switch (command) {
                    case bye:
                        System.out.println("See you soon!");
                        return;
                    case list:
                        s.printList();
                        break;
                    case mark:
                        int tasknumbermark = Integer.valueOf(checker[1]) - 1;
                        s.markTask(tasknumbermark);
                        break;
                    case unmark:
                        int tasknumberunmark = Integer.valueOf(checker[1]) - 1;
                        s.unmarkTask(tasknumberunmark);
                        break;
                    case todo:
                        String[] checker2 = line.split("todo ");
                        s.addTodo(checker2[1]);
                        break;
                    case deadline:
                        String[] checkerby = line.split("/by ");
                        String[] checkerdeadline = checkerby[0].split("deadline ");
                        s.addDeadline(checkerdeadline[1], checkerby[1]);
                        break;
                    case event:
                        String[] checkerslash = line.split("/");
                        String[] checkerevent = checkerslash[0].split("event ");
                        String[] checker4 = checkerslash[1].split("from ");
                        String[] checker5 = checkerslash[2].split("to ");
                        s.addEvent(checkerevent[1], checker4[1], checker5[1]);
                        break;
                    case delete:
                        int tasknumberdel = Integer.valueOf(checker[1]) - 1;
                        s.deleteTask(tasknumberdel);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description cannot be empty.");
            } catch (IllegalArgumentException e) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                System.out.println("Now you have " + s.lengthOflist() + " tasks in your list.\n");
            }
        }


    }


}


