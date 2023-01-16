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

        Scanner sc = new Scanner(System.in);
        while(true) {
            String line = sc.nextLine();

            if (line.equals("bye")) {
                // exit code

                System.out.println("\tHave a nice day sir.");
                break;

            } else if (line.equals("list")) {
                // show items

                ToDoList.list();

            } else if (line.matches("mark(.*)")) {
                // mark task as done

                try {

                    ToDoList.markDone(Integer.parseInt(line.substring(line.length() - 1)));
                } catch (NumberFormatException e) {
                    System.out.println("Which task have you completed, sir?");
                } catch (NullPointerException e) {
                    System.out.println("Sir, that task does not exist.");
                }

            } else if (line.matches("unmark(.*)")) {
                // unmark tasks

                try {
                    ToDoList.unmark(Integer.parseInt(line.substring(line.length() - 1)));
                } catch (NumberFormatException e) {
                    System.out.println("Which task would you like to unmark sir?");
                } catch (NullPointerException e) {
                    System.out.println("Sir, that task does not exist.");
                }

            } else if (line.matches("todo(.*)")) {
                // add task to list

                try {
                    addToDo(line);
                } catch (JarvisException e) {
                    System.out.println("Please enter the task to do: -todo <task>");
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
                    ToDoList.add(task, time);
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

                ToDoList.add(task, startTime, endTime);

            } else {
                System.out.println("I do not know that command, sir.");
                System.out.println("Perhaps you can add that functionality in for J.A.R.V.I.S(v2.0).");
            }
        }
    }

    public static void addToDo(String line) throws JarvisException {
        try {
            String task = line.substring(5);
            ToDoList.add(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new JarvisException("");
        }
    }
}
