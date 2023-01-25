import java.util.*;

public class Duke {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = " _____  _               _____   _\n"
                + "|  ___|| |     _    _  |  ___| | |  _\n"
                + "| |    | |    | |  | | | |     | |/ /\n"
                + "| |___ | |___ | |__| | | |___  |    \\\n"
                + "|_____||_____||______| |_____| |_| \\_\\\n";

        System.out.println(logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Howdy! I'm Cluck!\n" +
                "    What can I cluck-a-doodle-do for you?\n");
        System.out.println("    ____________________________________________________________");
        boolean loop = true;
        List<Task> toDoList = new LinkedList<>();
        Scanner sc = new Scanner(System.in);



        while (loop) {
            String input = sc.nextLine();
            String[] words = input.split(" ");

                switch (words[0]) {
                    case ("bye"):
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Buh-cluck, see ya!");
                        System.out.println("    ____________________________________________________________");
                        loop = false;
                        break;

                    case ("list"):
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < toDoList.size(); i++) {
                            System.out.println("    " + (i + 1) + ": " + toDoList.get(i).toString());
                        }
                        System.out.println("    ____________________________________________________________");
                        break;

                    case ("mark"):
                        if (words.length == 1) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Mucka blucka - Buh cluck! Which task do you wanna mark?   ");
                            System.out.println("    ____________________________________________________________");
                        }
                        else if (isNumeric(words[1])) {
                            Integer itemNumber = Integer.parseInt(words[1]);
                            if (itemNumber > toDoList.size() || itemNumber <= 0) {
                                System.out.println("    ____________________________________________________________");
                                System.out.println("    That's not...? In the list...? Buh caw?");
                                System.out.println("    ____________________________________________________________");
                            } else {
                                toDoList.get(itemNumber - 1).mark();
                                System.out.println("    ____________________________________________________________");
                                System.out.println(String.format("    Marked it! Cluck-a-doodle-done!\n     %s", toDoList.get(itemNumber - 1).toString()));
                                System.out.println("    ____________________________________________________________");
                            }
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Ya gotta give me a working number, bucko!");
                            System.out.println("    ____________________________________________________________");
                        }
                        break;

                    case ("unmark"):
                        if (words.length == 1) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Which task do you wanna unmark? Muckah buck!");
                            System.out.println("    ____________________________________________________________");
                        }
                        else if (isNumeric(words[1])) {
                            Integer itemNumber = Integer.parseInt(words[1]);
                            if (itemNumber > toDoList.size() || itemNumber <= 0) {
                                System.out.println("    ____________________________________________________________");
                                System.out.println("    That's not...? In the list...? Buh caw?");
                                System.out.println("    ____________________________________________________________");
                            } else {
                                toDoList.get(itemNumber - 1).unmark();
                                System.out.println("    ____________________________________________________________");
                                System.out.println(String.format("    Unmarked it! Cluckiddy cluck!\n     %s", toDoList.get(itemNumber - 1).toString()));
                                System.out.println("    ____________________________________________________________");
                            }
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Ya gotta give me a working number, bucko!");
                            System.out.println("    ____________________________________________________________");
                        }
                        break;

                    default:
                        toDoList.add(new Task(input));
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    added: " + input);
                        System.out.println("    ____________________________________________________________");

                }
        }
    }
}
