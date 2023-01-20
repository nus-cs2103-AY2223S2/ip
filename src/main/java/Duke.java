import java.util.Scanner;
import java.util.Arrays;
enum action {
    bye,
    list,
    mark,
    unmark,
    todo,
    deadline,
    event
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
        String bye = "bye";
        String s = "";

        String newLine = sc.nextLine();
        String[] arr = newLine.split(" ");
        s = arr[0];

        /*action myAction = action.valueOf(s);
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
                    System.out.println(String.format("%d.%s", j + 1,listOfAction[j]));
                }
                break;

            case mark:
                int num = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = listOfAction[num];
                    listOfAction[num] = String.format("%s[ ] %s", original.substring(0, 3), original.substring(4));
                    System.out.println(listOfAction[num]);
                }
                break;

            case unmark:
                int num1 = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num1] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = listOfAction[num1];
                    listOfAction[num1] = String.format("%s[ ] %s", original.substring(0, 3), original.substring(4));
                    System.out.println(listOfAction[num1]);
                }
                break;

            case todo:
                //String newTask = sc.next();
                System.out.println("Got it. I've added this task:");
                //System.out.println(newTask);
                break;

            case deadline:
                //String newTask = sc.next();
                System.out.println("Got it. I've added this task:");
                //System.out.println(newTask);
                break;

            case event:
                //String newTask = sc.next();
                System.out.println("Got it. I've added this task:");
                //System.out.println(newTask);
                break;


        }*/
        int i = 0;
        while (!bye.equals(s)) {
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
                            listOfAction[num] = String.format("%s[X] %s", original.substring(0, 3), original.substring(7));
                            System.out.println(listOfAction[num]);
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
                            listOfAction[num1] = String.format("%s[ ] %s", original.substring(0, 3), original.substring(7));
                            System.out.println(listOfAction[num1]);
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
                        listOfAction[i] = String.format("[T][ ] %s", remaining);
                        System.out.println(listOfAction[i]);
                        System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                        i++;
                        //System.out.println(newTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case deadline:
                    try {
                        checkEmptyAction(arr, "deadline");
                        System.out.println("Got it. I've added this task:");
                        for (int j = 1; j < arr.length; j++) {
                            remaining += " ";
                            if (String.valueOf(arr[j].charAt(0)).equals("/")) {
                                remaining += "(";
                                if (arr[j].length() != 1) {
                                    remaining += arr[j].substring(1);
                                }
                            } else {
                                //remaining += arr[j];
                                remaining += arr[j];
                            }
                        }
                        remaining += ")";
                        listOfAction[i] = String.format("[D][ ]%s", remaining);
                        System.out.println(listOfAction[i]);
                        System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                        i++;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    //System.out.println(newTask);
                    break;

                case event:
                    try {
                        checkEmptyAction(arr, "event");
                        System.out.println("Got it. I've added this task:");
                        int k = 0;
                        for (int j = 1; j < arr.length; j++) {
                            remaining += " ";
                            if (String.valueOf(arr[j].charAt(0)).equals("/") && (k != 0)) {
                                if (arr[j].length() != 1) {
                                    remaining += arr[j].substring(1);
                                    remaining += ":";
                                }
                            } else if (String.valueOf(arr[j].charAt(0)).equals("/") && (k == 0)) {
                                remaining += "(";
                                if (arr[j].length() != 1) {
                                    remaining += arr[j].substring(1);
                                    remaining += ":";
                                }
                                k++;
                            } else if (arr[j-1].substring(1).equals("to") && (k!= 0)) {
                                //remaining += ":";
                                remaining += arr[j];
                            } else {
                                //remaining += arr[j];
                                //remaining += "";
                                remaining += arr[j];
                            }
                        }
                        remaining += ")";
                        listOfAction[i] = String.format("[E][ ]%s", remaining);
                        System.out.println(listOfAction[i]);
                        System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                        i++;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                }
                    //System.out.println(newTask);
                    break;
            }
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    /*public String printMessage() {
        return super.getMessage();
    }*/

}

