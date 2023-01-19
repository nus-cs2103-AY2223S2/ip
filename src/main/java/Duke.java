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
    public static void main(String[] args) {
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
        //System.out.println("? " + newLine);
        String[] arr = newLine.split(" ");
        //System.out.println("? " + arr[0]);
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
                        System.out.println(String.format("%d.%s", j + 1,listOfAction[j]));
                    }
                    break;

                case mark:
                    int num = Integer.parseInt(arr[1]) - 1;
                    if (listOfAction[num] != null) {
                        System.out.println("OK, I've marked this task as not done yet:");
                        String original = listOfAction[num];
                        listOfAction[num] = String.format("%s[X] %s", original.substring(0, 3), original.substring(7));
                        System.out.println(listOfAction[num]);
                    }
                    break;

                case unmark:
                    int num1 = Integer.parseInt(arr[1]) - 1;
                    if (listOfAction[num1] != null) {
                        System.out.println("OK, I've marked this task as not done yet:");
                        String original = listOfAction[num1];
                        listOfAction[num1] = String.format("%s[ ] %s", original.substring(0, 3), original.substring(7));
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


            }
           /* String newLine = sc.nextLine();
            String[] arr = newLine.split(" ");
            s = arr[0]; */
            String remaining = "";
            if (s.equals("todo")) {
                for (int j = 1; j < arr.length; j++) {
                    //remaining += " ";
                    remaining += arr[j];
                    remaining += " ";
                }
            } else if (s.equals("deadline")) {
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
            } else if (s.equals("event")) {
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
            }
            if (s.equals("bye")) {
                //myAction = action.bye;
                break;
            }
            else if ((!s.equals("list")) && !s.equals("mark") && !s.equals("unmark")) {
                if (s.equals("todo")) {
                    //myAction = action.todo;
                    //System.out.println(myAction);
                    String newTask = remaining;
                    //System.out.println(newTask);
                    //System.out.println(String.format("added: %s", s));
                    listOfAction[i] = String.format("[T][ ] %s", newTask);
                    System.out.println(listOfAction[i]);
                    System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                    i++;
                }
                else if (s.equals("deadline")) {
                    //myAction = action.deadline;
                    //System.out.println(myAction);
                    String newTask = remaining;
                    //System.out.println(newTask);
                    //System.out.println(String.format("added: %s", s));
                    listOfAction[i] = String.format("[D][ ]%s", newTask);
                    System.out.println(listOfAction[i]);
                    System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                    i++;
                }
                else if (s.equals("event")) {
                    //myAction = action.event;
                    //System.out.println(myAction);
                    String newTask = remaining;
                    //System.out.println(newTask);
                    //System.out.println(String.format("added: %s", s));
                    listOfAction[i] = String.format("[E][ ]%s", newTask);
                    System.out.println(listOfAction[i]);
                    System.out.println(String.format("Now you have %d tasks in the list", i + 1));
                    i++;
                } else {
                    System.out.println("error!");
                }
            }
            /*else if (s.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int j = 0; j < listOfAction.length; j ++) {
                    if (listOfAction[j] == null) {
                        break;
                    }
                    System.out.println(String.format("%d.%s", j + 1,listOfAction[j]));
                }
            } else if (s.equals("mark")) {
                int num = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num] != null) {
                    System.out.println("Nice! I've marked this task as done:");
                    String original = listOfAction[num];
                    listOfAction[num] = String.format("[X] %s", original.substring(4));
                    System.out.println(listOfAction[num]);
                }
            } else if (s.equals("unmark")) {
                int num = Integer.parseInt(sc.next()) - 1;
                if (listOfAction[num] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = listOfAction[num];
                    listOfAction[num] = String.format("[ ] %s", original.substring(4));
                    System.out.println(listOfAction[num]);
                }
            }*/
            newLine = sc.nextLine();
            arr = newLine.split(" ");
            s = arr[0];
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
