import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;

enum action {
    bye,
    list,
    mark,
    unmark,
    todo,
    deadline,
    event,
    delete
}
public class Duke {
    static void checkEmptyAction(String[] arr, String action) throws DukeException {
        if (arr.length <= 1) {
            throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", action));
        }
    }

    public static void main(String[] args) throws IllegalArgumentException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String[] listOfAction = new String[100];
        System.out.println("\nHello, I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        FileSaver file = new FileSaver("./data/duke.txt");
        listOfAction = file.load();

        String s = "";

        String newLine = sc.nextLine();
        String[] arr = newLine.split(" ");
        s = arr[0];

        int len = 0;
        while (listOfAction[len] != null) {
            len++;
        }
        while (!s.equals("bye")) {
            String remaining = "";
            try {
                action myAction = action.valueOf(s);
                switch(myAction) {
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;

                    case list:
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; j < listOfAction.length; j++) {
                            if (listOfAction[j] == null) {
                                break;
                            }
                            System.out.println(String.format("%d.%s", j + 1, listOfAction[j]));
                        }
                        break;

                    case mark:
                        try {
                            checkEmptyAction(arr, "mark");
                            int num = Integer.parseInt(arr[1]) - 1;
                            if (listOfAction[num] != null) {
                                System.out.println("OK, I've marked this task as not done yet:");
                                String original = listOfAction[num];

                                //Task newTask = new Task(orginal.indexAt(1),  original.substring(7), true);
                                listOfAction[num] = new Task(String.valueOf(original.charAt(1)),  original.substring(7), true).toString();
                                //listOfAction[num] = String.format("%s[X] %s", original.substring(0, 3), original.substring(7));
                                System.out.println(listOfAction[num]);
                                file.overwrite(listOfAction);

                            }
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case unmark:
                        try {
                            checkEmptyAction(arr, "unmark");
                            int num1 = Integer.parseInt(arr[1]) - 1;
                            if (listOfAction[num1] != null) {
                                System.out.println("OK, I've marked this task as not done yet:");
                                String original = listOfAction[num1];

                                Task newTask = new Task(String.valueOf(original.charAt(1)),  original.substring(7), false);
                                listOfAction[num1] = newTask.toString(); //String.format("%s[ ] %s", original.substring(0, 3), original.substring(7));
                                System.out.println(listOfAction[num1]);
                                file.overwrite(listOfAction);
                            }
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case delete:
                        try {
                            checkEmptyAction(arr, "delete");
                            int num1 = Integer.parseInt(arr[1]) - 1;
                            if (listOfAction[num1] != null) {
                                System.out.println("Noted. I've removed this task:");
                                String original = listOfAction[num1];
                                System.out.println(original);
                                len--;
                                System.out.println(String.format("Now you have %d tasks in the list", len));
                                int trace = num1;
                                String[] originalList = new String[100];
                                for (int k = 0; k < 100; k++) {
                                    originalList[k] = listOfAction[k];
                                }

                                listOfAction[trace] = originalList[trace + 1];
                                trace++;

                                while ((trace >= 1) && (originalList[trace - 1] != null)) {
                                    listOfAction[trace] = originalList[trace + 1];
                                    trace++;
                                }
                                file.overwrite(listOfAction);

                            }
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case todo:
                        try {
                            checkEmptyAction(arr, "todo");
                            System.out.println("Got it. I've added this task:");
                            for (int j = 1; j < arr.length; j++) {
                                //remaining += " ";
                                remaining += arr[j];
                                remaining += " ";
                            }
                            Todo newTask = new Todo(s, remaining, false);
                            listOfAction[len] = newTask.toString(); //String.format("[T][ ] %s", remaining);
                            System.out.println(listOfAction[len]);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                            file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case deadline:
                        try {
                            checkEmptyAction(arr, "deadline");
                            System.out.println("Got it. I've added this task:");
                            String detail = "";
                            int pointer = 1;
                            for (int j = 1 ; j < arr.length ; j++) {
                                if (String.valueOf(arr[j]).equals("/by")) {
                                    pointer = j + 1;
                                    break;
                                }
                                detail += arr[j];
                                detail += " ";
                            }

                            for (int j = pointer; j < arr.length; j++) {
                                if (String.valueOf(arr[j]).equals("/")) {
                                    remaining += "-";
                                } else {
                                    remaining += arr[j];
                                }
                                if (j != arr.length - 1) {
                                    remaining += " ";
                                }
                            }

                            Deadline newTask = new Deadline(s, detail, remaining);
                            listOfAction[len] = newTask.toString(); //String.format("[D][ ]%s", remaining);
                            System.out.println(listOfAction[len]);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                            file.overwrite(listOfAction);

                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case event:
                        try {
                            checkEmptyAction(arr, "event");
                            System.out.println("Got it. I've added this task:");
                            //int k = 0;
                            int startIndex = 0;
                            int endIndex = 0;
                            String detail = "";
                            for (int j = 1; j < arr.length; j++){
                                if (String.valueOf(arr[j]).equals("/from")) {
                                    startIndex = j + 1;
                                    break;
                                }
                                detail += arr[j];
                                detail += " ";
                            }
                            for (int j = startIndex; j < arr.length; j++){
                                if (String.valueOf(arr[j]).equals("/to")) {
                                    endIndex = j + 1;
                                    break;
                                }
                            }
                            String start = "";
                            String end = "";
                            for (int j = startIndex; j < endIndex - 1; j++) {
                                if (String.valueOf(arr[j]).equals("/")) {
                                    start += "-";
                                } else {
                                    start += arr[j];
                                }
                                if (j != endIndex - 2) {
                                    start += " ";
                                }
                            }
                            for (int j = endIndex; j < arr.length; j++) {
                                if (String.valueOf(arr[j]).equals("/")) {
                                    end += "-";
                                } else {
                                    end += arr[j];
                                }
                                if (j != arr.length - 1) {
                                    end += " ";
                                }
                            }
                            listOfAction[len] = new Event("event", detail, start, end).toString(); //String.format("[E][ ]%s", remaining);
                            System.out.println(listOfAction[len]);
                            System.out.println(String.format("Now you have %d tasks in the list", len + 1));
                            len++;
                           file.overwrite(listOfAction);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            newLine = sc.nextLine();
            arr = newLine.split(" ");
            s = arr[0];
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}

class DukeException extends  Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
}


