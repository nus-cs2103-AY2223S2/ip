import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> arr;
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        arr = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = bf.readLine()) != null) {
            if (str.equals("bye")) {
                lineUI();
                System.out.println("Bye. Hope to see you again soon!");
                lineUI();
                arr = new ArrayList<>();
                break;
            }
            if (str.equalsIgnoreCase("list")) {
                listCommand();
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("mark ")) {
               markCommand(str);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("unmark ")) {
               unmarkCommand(str);
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("todo ")) {
                todoCommand(str);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("event ")) {
                eventCommand(str);
            } else if (str.length() >= 9 && str.toLowerCase().startsWith("deadline ")) {
                deadlineCommand(str);
            } else if (str.length() >= 7 && str.toLowerCase().startsWith("delete ")) {
                deleteCommand(str);
            } else {
                System.out.println(new InvalidCommandException().getMessage());
            }
        }

        bf.close();
    }

    private static void deleteCommand(String str) {
        try {
            int num = getTaskNumber(str, 7);
            Task removed = arr.remove(num - 1);
            lineUI();
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed.toString());
            System.out.println("Now you have " + arr.size() + " tasks in the list.");
            lineUI();

        } catch (MissingArguments | InvalidTaskNumber err) {
            System.out.println(err.getMessage());
        }
    }

    private static void deadlineCommand(String str) {
        try {
            String[] foo;
            try {
                foo = separate(str.substring(9));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArguments();
            }

            Task toAdd = new Deadline(foo[0], getDeadline(foo[1]));
            arr.add(toAdd);
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toAdd);
            System.out.println("Now you have " + arr.size() + " tasks in the list.");
            lineUI();
        } catch (MissingArguments | MissingTimeException | MissingNameException | InvalidSyntaxException err) {
            System.out.println(err.getMessage());
        }
    }

    private static void eventCommand(String str) {
        try {
            String[] foo;
            try {
                foo = separate(str.substring(6));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArguments();
            }
            String[] bar = getDuration(foo[1]);
            Task toAdd = new Event(foo[0], bar[0], bar[1]);
            arr.add(toAdd);
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toAdd);
            System.out.println("Now you have " + arr.size() + " tasks in the list.");
            lineUI();
        } catch (MissingArguments | MissingNameException | MissingTimeException | InvalidSyntaxException err) {
            System.out.println(err.getMessage());
        }

    }

    private static void listCommand() {
        int cnt = 1;
        while (cnt <= arr.size()) {
            Task item = arr.get(cnt - 1);
            System.out.println(cnt + "." + item.toString());
            cnt++;
        }
    }

    private static void markCommand(String str) {
        try {
            Task itemToMark = arr.get(getTaskNumber(str,5) - 1);
            itemToMark.markDone();
            lineUI();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + itemToMark.getTaskName());
            lineUI();
        } catch (MissingArguments | InvalidTaskNumber err) {
            System.out.println(err.getMessage());
        }
    }

    private static void unmarkCommand(String str) {
        try {
            Task itemToMark = arr.get(getTaskNumber(str, 7) - 1);
            itemToMark.markUndone();
            lineUI();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[] " + itemToMark.getTaskName());
            lineUI();
        } catch (MissingArguments | InvalidTaskNumber err) {
            System.out.println(err.getMessage());
        }
    }

    private static void todoCommand(String str) {
        try {
            Task toAdd = new ToDo(getName(str, 5));
            arr.add(toAdd);
            lineUI();
            System.out.println("Got it. I've added this task:");
            System.out.println(toAdd);
            System.out.println("Now you have " + arr.size() + " tasks in the list.");
            lineUI();
        } catch (MissingArguments | MissingNameException err) {
            System.out.println(err.getMessage());
        }
    }

    private static String getName(String str, int from) throws MissingNameException, MissingArguments {
        try {
            String name = str.substring(from);

            if (name.isBlank()) {
                throw new MissingNameException();
            }

            return name;
        }  catch (IndexOutOfBoundsException err) {
            throw new MissingArguments();
        }
    }

    private static int getTaskNumber(String str, int from) throws MissingArguments, InvalidTaskNumber {
        int numOfTask;
        try {
            numOfTask = Integer.parseInt(str.substring(from));
        } catch (NullPointerException | IndexOutOfBoundsException err) {
            throw new MissingArguments();
        }

        // Handle when the index is out of bound -> create a separate function
        if (numOfTask <= 0 || numOfTask > arr.size()) {
            throw new InvalidTaskNumber();
        }
        return numOfTask;
    }

    private static String[] separate(String line) throws MissingTimeException, MissingNameException {
        try {
            StringBuilder sb = new StringBuilder();

            int i;
            for (i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '/') {
                    break;
                }
                sb.append(line.charAt(i));
            }

            String name = sb.toString();
            if (name.isBlank()) {
                throw new MissingNameException();
            }
            String theRest = line.substring(i + 1);
            return new String[] {name, theRest};
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    private static String getDeadline(String line) throws MissingTimeException, InvalidSyntaxException {
        try {
            String toReturn = line.substring(3);
            if (toReturn.isBlank()) {
                throw new MissingTimeException();
            }
            if (!line.startsWith("by ")) {
                throw new InvalidSyntaxException();
            }
            return toReturn;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    private static String[] getDuration(String line) throws MissingArguments, MissingTimeException, InvalidSyntaxException {
        try {
            String[] arr = line.split("/");
            if (!arr[0].startsWith("from ")) {
                throw new InvalidSyntaxException();
            }
            arr[0] = arr[0].substring(5);
            if (!arr[1].startsWith("to ")) {
                throw new InvalidSyntaxException();
            }
            arr[1] = arr[1].substring(3);
            if (arr[0].isBlank() || arr[1].isBlank()) {
                throw new MissingTimeException();
            }
            return arr;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingArguments();
        }
    }

    private static void lineUI() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
