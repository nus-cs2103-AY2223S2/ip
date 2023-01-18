///**
// * DUKE
// * CS2103 project
// * @author EDWIN LIM
// * @version 0.01
// */
//
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class Duke_GG {
//    private static DukeIO dio;
//    public enum KEYWORDS {
//        USER_ERR, USER_EMPTY, BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
//    }
//
//    /**
//     * Main method for the program
//     * @param args UNUSED
//     */
//    public static void main(String[] args) {
//        dio = new DukeIO();
//
//        greet();
//        Scanner sc = new Scanner(System.in);
//        String userIn;
//        String[] userInSplit;
//        Object userInTemp;
//
//        LinkedList<Task> list = new LinkedList<>();
//        Task toAdd;
//
//        boolean QUIT = false;
//        while (!QUIT) {
//            userIn = sc.nextLine();
//            userInSplit = userIn.split(" ", 2); // look into .split 's 2nd arg.
//            System.out.println("____________________________________________________________");
//            switch (userInSplit[0]) {
//                case "list" :
//                    // Perhaps look into not creating Iterators
//                    Iterator<Task> list_entries = list.iterator();
//                    int number = 1;
//                    while (list_entries.hasNext()) {
//                        System.out.println(String.format("%d.%s", number++, list_entries.next()));
//                    }
//                    break;
//
//                case "todo":
//                    if (userInSplit.length > 1) {
//                        System.out.println("Got it. I've added this task:");
//                        toAdd = new ToDo(userInSplit[1]);
//                        list.add(toAdd);
//                        System.out.println(toAdd);
//                        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The description of %s cannot be empty.","todo"));
//                    }
//
//                    break;
//
//                case "deadline":
//                    if (userInSplit.length > 1) {
//                        userInSplit = userInSplit[1].split("/by", 2);
//
//                        if (userInSplit.length > 1 && userInSplit[1].length() > 0) {
//                            System.out.println("Got it. I've added this task:");
//                            toAdd = new Deadline(userInSplit[0], userInSplit[1]);
//                            list.add(toAdd);
//                            System.out.println(toAdd);
//                            System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
//                        } else {
//                            System.out.println(String.format("☹ Error!!!! Usage: %s <task> /by <date>","deadline"));
//                        }
//
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The description of %s cannot be empty.","deadline"));
//                    }
//                    break;
//
//                case "event":
//                    if (userInSplit.length > 1) {
//                        System.out.println("Got it. I've added this task:");
//                        userInSplit = userInSplit[1].split("/from", 2);;
//
//                        if (userInSplit.length > 1 && userInSplit[1].length() > 0) {
//                            userIn = userInSplit[0];
//                            userInSplit = userInSplit[1].split("/to", 2);
//                            if (userInSplit.length > 1 && userInSplit[1].length() > 0) {
//                                toAdd = new Event(userIn, userInSplit[0], userInSplit[1]);
//                                list.add(toAdd);
//                                System.out.println(toAdd);
//                                System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
//                            } else {
//                                System.out.println(String.format("☹ Error!!!! Usage: %s <task> /from <date> /to <date>", "event"));
//                            }
//                        } else {
//                            System.out.println(String.format("☹ Error!!!! Usage: %s <task> /from <date> /to <date>","event"));
//                        }
//
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The description of %s cannot be empty.","event"));
//                    }
//                    break;
//
//                case "mark" :
//                    if (userInSplit.length > 1) {
//                        userInTemp = Integer.parseInt(userInSplit[1]) - 1;
//                        // User input expect index counting from 1
//                        list.get((Integer) userInTemp).setStatus(true);
//                        System.out.println("Nice! I've marked this task as done:");
//                        System.out.println(list.get((Integer) userInTemp));
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The entry to %s cannot be empty.","mark"));
//                    }
//                    break;
//
//                case "unmark":
//                    if (userInSplit.length > 1) {
//                        userInTemp = Integer.parseInt(userInSplit[1]) - 1;
//                        list.get((Integer) userInTemp).setStatus(false);
//                        System.out.println("OK, I've marked this task as not done yet:");
//                        System.out.println(list.get((Integer) userInTemp));
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The entry to %s cannot be empty.","mark"));
//                    }
//                    break;
//
//                case "delete":
//                    if (userInSplit.length > 1) {
//                        userInTemp = Integer.parseInt(userInSplit[1]) - 1;
//
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println(list.remove(((Integer) userInTemp).intValue()));
//                        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
//                    } else {
//                        System.out.println(String.format("☹ OOPS!!! The entry to %s cannot be empty.","mark"));
//                    }
//                    break;
//
//                case "bye" :
//                    QUIT = true;
//                    System.out.println("Bye. Hope to see you again soon!");
//                    break;
//
//                default:
//                    if (!(userIn.isEmpty())) {
//                        System.out.println(String.format("☹ OOPS!!! I'm sorry, but I don't know what `%s` means :-(", userIn));
//                    }
//                    break;
//            }
//            System.out.println("____________________________________________________________");
//        }
//    }
//
//    /**
//     * Prints standard welcome message.
//     */
//    public static void greet() {
//        String logo = "                __  __ _           \n"
//                + "               / _|/ _| |          \n"
//                + "__      ____ _| |_| |_| | ___  ___ \n"
//                + "\\ \\ /\\ / / _` |  _|  _| |/ _ \\/ __|\n"
//                + " \\ V  V / (_| | | | | | |  __/\\__ \\\n"
//                + "  \\_/\\_/ \\__,_|_| |_| |_|\\___||___/\n";
//        dio.println("Hello from\n" + logo + "\n");
//        dio.println("Hello! I'm " + "Waffles");
//        dio.println("What can I do for you?");
//        dio.flush();
//    }
//
//    public static void goodbye() {
//        dio.println("Bye. Hope to see you again soon!");
//        dio.flush();
//    }
//
//}
//
