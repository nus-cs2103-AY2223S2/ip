import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */


        String computer =
                "              ,------------------------------------,\n" +
                "              |  /------------------------------\\  |\n" +
                "              | |J.A.R.V.I.S(v1.0)      CS2103(T)| |\n" +
                "              | |                                | |\n" +
                "              | | *HOW CAN I HELP YOU MR STARK?* | |\n" +
                "              | |                                | |\n" +
                "              | | *command list                  | |\n" +
                "              | |  - todo        -list           | |\n" +
                "              | |  - deadline    -markdone       | |\n" +
                "              | |  - event       -unmark         | |\n" +
                "              |  \\______________________________/  |\n" +
                "              |____________________________________|\n" +
                "            ,------\\_____     []     _______/---,\n" +
                "          /             /______________\\      / |\n" +
                "        /___________________________________ /  | ___\n" +
                "        |credits: www.asciiart.eu/computers |   |    )\n" +
                "        |  _ _ _                 [-------]  |   |   (\n" +
                "        |  o o o                 [-------]  |  /    _)_\n" +
                "        |__________________________________ |/     /  /\n" +
                "    /-------------------------------------/|      ( )/\n" +
                "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n" +
                "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        System.out.println(computer);
        System.out.println("Please enter a command Mr Stark.");

        ToDoList toDo = new ToDoList(100);

        Scanner sc = new Scanner(System.in);
        while(true) {
            String line = sc.nextLine();

            if (line.equals("bye")) {
                // exit code

                System.out.println("\tHave a nice day sir.");
                break;

            } else if (line.equals("list")) {
                // show items

                toDo.list();

            } else if (line.matches("mark(.*)")) {
                // mark task as done

                try {
                    int taskNum = Integer.parseInt(line.substring(line.length() - 1));
                    validate(toDo, taskNum);
                    toDo.markDone(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println("Which task have you completed, sir?");
                } catch (NoTaskFoundException e) {
                    System.out.println("Sir, that task does not exist.");
                }

            } else if (line.matches("unmark(.*)")) {
                // unmark tasks

                try {
                    int taskNum = Integer.parseInt(line.substring(line.length() - 1));
                    validate(toDo, taskNum);
                    toDo.unmark(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println("Which task would you like to unmark sir?");
                } catch (NoTaskFoundException e) {
                    System.out.println("Sir, that task does not exist.");
                }

            } else if (line.matches("todo(.*)")) {
                // add task to list

                try {
                    addToDo(toDo, line);
                } catch (JarvisException e) {
                    System.out.println("\tPlease enter in format 'todo <task>'");
                }

            } else if (line.matches("deadline(.*)")) {
                // add task to list, with deadline

                // check format
                if (!line.contains("/")) {
                    System.out.println("\tPlease enter in format 'deadline <task> /<deadline>'");
                } else {
                    int firstSlash = line.indexOf("/");
                    String task = line.substring(9, firstSlash);
                    String time = line.substring(firstSlash + 1);
                    toDo.add(task, time);
                }

            } else if (line.matches("event(.*)")) {
                // add task to list, with event timing

                int firstSlash = line.indexOf("/");

                // check format
                if (firstSlash == -1 || !line.substring(firstSlash + 1).contains("/")) {
                    System.out.println("\tPlease enter in format 'event <task> /<start>/<end>'");
                    continue;
                }

                int secondSlash = line.substring(firstSlash + 1).indexOf("/") + firstSlash + 1;
                String startTime = line.substring(firstSlash + 1, secondSlash);
                String endTime = line.substring(secondSlash + 1);
                String task = line.substring(6, firstSlash);

                toDo.add(task, startTime, endTime);

            } else if (line.matches("delete(.*)")) {
                // mark task as done

                try {
                    int taskNum = Integer.parseInt(line.substring(line.length() - 1));
                    validate(toDo, taskNum);
                    toDo.delete(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println("Which task would you like to delete, sir?");
                } catch (NoTaskFoundException e) {
                    System.out.println("Sir, you may not delete nonexistent tasks.");
                }

            } else {
                System.out.println("I do not know that command, sir.");
                System.out.println("Perhaps you can add that functionality for J.A.R.V.I.S(v2.0).");
            }
        }
    }

    public static void addToDo(ToDoList toDo, String line) throws JarvisException {
        try {
            String task = line.substring(5);
            toDo.add(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new JarvisException("");
        }
    }

    public static void validate(ToDoList toDo, int num) throws NoTaskFoundException {
        if (num > toDo.getCount()) {
            throw new NoTaskFoundException("");
        }
    }
}
