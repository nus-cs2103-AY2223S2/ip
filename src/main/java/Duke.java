import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import DukeHelpfulCode.*;



public class Duke {

    private static String LINEBREAK = "_________________________________________________________________\n";
    private static UserList USERLIST = new UserList();

    public static void main(String[] args) {
        File save = new File("data/save.txt");
        try {
            if (save.createNewFile()) {
                System.out.println("File created: " + save.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        greeting();
        takeCmd();
    }

    private static void takeCmd() {
        /**
         * Controls flow, logic, checking of the user inputs as commands.
         * Recognized user commands:
         *      bye     -> quit
         *      todo    -> creates todo task
         *      deadline-> creates deadline task
         *      event   -> creates event task
         *      list    -> display
         *      mark    -> marks the (i-1)th item as done, since UserList is 0-index
         *      unmark  -> unmarks the (i-1)th item
         * Unrecognized commands will simply be echoed then Duke waits next command.
         */
        Scanner userCmd = new Scanner(System.in);
        String askForCmd = LINEBREAK + "Okay. What would you like to do next?\nIf you want to exit DUKE, type 'bye'!\n";
        String[] cmd = userCmd.nextLine().split(" ");
        if (cmd[0].equals("bye")) {
            exit();
        } else if (cmd[0].equals("todo")) {
            String taskName = "";
            if (cmd.length>1) {
                for (int i = 1; i < cmd.length; i++) {
                    taskName += cmd[i] + " ";
                }
            } else {
                System.out.println("Sorry, I didn't quite catch that. What task would you like to add?\n" + LINEBREAK);
                taskName = userCmd.nextLine();
            }
            addTodo(new Todo(taskName));
            System.out.println(askForCmd);
            takeCmd();
        } else if (cmd[0].equals("deadline")){
            String taskName = "";
            String dateTime = "";
            if (cmd.length>1) {
                for (int i = 1; i < cmd.length; i++) {
                    if (!cmd[i].equals("/by")){
                        taskName += cmd[i] + " ";
                    } else {
                        i++;
                        dateTime += cmd[i] + " ";
                    }
                }
            } else {
                System.out.println("Sorry, I didn't quite catch that. What task would you like to add?\n" + LINEBREAK);
                cmd = userCmd.nextLine().split(" ");
                for (int i = 0; i < cmd.length; i++) {
                    if (!cmd[i].equals("/by")){
                        taskName += cmd[i] + " ";
                    } else {
                        i++;
                        dateTime += cmd[i] + " ";
                    }
                }
            }
            addDeadline(new Deadline(taskName, dateTime));
            System.out.println(askForCmd);
            takeCmd();
        } else if (cmd[0].equals("event")){
            String taskName = "";
            String startDateTime = "";
            String endDateTime = "";
            if (cmd.length > 1) {
                int from = Arrays.asList(cmd).indexOf("/from");
                int to = Arrays.asList(cmd).indexOf("/to");
                for (int i = 1; i < cmd.length; i++){
                    if (i < from) {
                        taskName += cmd[i] + " ";
                    } else if (i > from && i < to){
                        startDateTime += cmd[i] + " ";
                    } else if (i > to){
                        endDateTime += cmd[i] + " ";
                    }
                }
            } else {
                System.out.println("Sorry, I didn't quite catch that. What task would you like to add?\n" + LINEBREAK);
                cmd = userCmd.nextLine().split(" ");
                int from = Arrays.asList(cmd).indexOf("/from");
                int to = Arrays.asList(cmd).indexOf("/to");
                for (int i = 0; i < cmd.length; i++){
                    if (i < from) {
                        taskName += cmd[i] + " ";
                    } else if (i > from && i < to){
                        startDateTime += cmd[i] + " ";
                    } else if (i > to){
                        endDateTime += cmd[i] + " ";
                    }
                }
            }
            addEvent(new Event(taskName, startDateTime, endDateTime));
            System.out.println(askForCmd);
            takeCmd();
        } else if (cmd[0].equals("list")){
            list();
            System.out.println(askForCmd);
            takeCmd();
        } else if (cmd[0].equals("mark") || cmd[0].equals("unmark")) {
            String markUnmark = cmd[0];
            if (userCmd.hasNext()) {
                try {
                    int listIdx = Integer.parseInt(userCmd.next());
                    mark(markUnmark, listIdx);
                    System.out.println(askForCmd);
                    takeCmd();
                } catch (NumberFormatException e) {
                    System.out.println("Sorry, I don't understand what you want to me to do.\n" + askForCmd);
                    takeCmd();
                }
            } else {
                System.out.println("Sorry, I don't understand what you want to me to do.\n" + askForCmd);
                takeCmd();
            }
        } else {
            System.out.println(askForCmd);
            takeCmd();
        }
    }

    private static void greeting() {
        /**
         * Start-up logo and message
         */
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + LINEBREAK + "Type 'bye' to exit!\n" + LINEBREAK + "What can I do for you today?\n");
    }

    private static void exit() {
        /**
         * Exits Duke.
         * userCmd "bye"
         */
        System.out.println("Bye. Hope to see you again soon!\n" + LINEBREAK);
    }

    private static void addTodo(Todo todo){
        USERLIST.addToDo(todo);
        try {
            FileWriter writer = new FileWriter("data/save.txt", true);
            writer.write(todo.toString() + "\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void addDeadline(Deadline dl){
        USERLIST.addDeadline(dl);
        try {
            FileWriter writer = new FileWriter("data/save.txt", true);
            writer.write(dl.toString() + "\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void addEvent(Event e){
        USERLIST.addEvent(e);
        try {
            FileWriter writer = new FileWriter("data/save.txt", true);
            writer.write(e.toString() + "\n");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    private static void list(){
        /**
         * userCmd "list"
         * Displays userList vertically.
         */
        if (USERLIST.len() == 0){
            System.out.println(LINEBREAK + "Oops, it seems that your list is empty!\n");
        } else {
            System.out.println(LINEBREAK + "Your list looks like this:\n" + USERLIST.toString() + LINEBREAK);
        }
    }

    private static void mark(String markCmd, int idx){
        try {
            USERLIST.mark(markCmd, idx);
        } catch (UserList.TaskNotInListException e) {
            System.out.println("Sorry, can't find that task.\n");
        } catch (UserList.TaskAlrMarkException e){
            System.out.println("The Task has already been marked.\n");
        }
    }

}
