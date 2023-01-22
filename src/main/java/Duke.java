import java.util.*;

public class Duke {
    public static int taskCounter = 0;
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String newLine = System.getProperty("line.separator");
        ArrayList<Task> toStore = new ArrayList<>(100);
        System.out.println("-------------------------------------------------------" +
                newLine + "Hello! Jak Sie Masz! I am Borat.\n What I do for you Premier Azamat?");

        String inData;
        boolean exit = false;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();
        String[] arrofStr = inData.split(" ", 2);

        while(!exit) {
            switch (arrofStr[0]) {
                case "bye":
                    exit = true;
                    System.out.println("Chenquieh. Hope to see you again Premier Azamat!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list my premier:");
                    int counter = 0;
                    for (Task i : toStore) {
                        counter++;
                        System.out.println(counter + ". " + i.toString());
                    }
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ", 2);
                    break;
                default:
                    try {
                    validateCmd(arrofStr);
                    } catch(MissingDescriptionException e) {
                        System.out.println(e.toString());
                        inData = scan.nextLine();
                        arrofStr = inData.split(" ", 2);
                    }
                    switch (arrofStr[0]) {
                        case "unmark": {
                            int indx = Integer.parseInt(arrofStr[1]) - 1;
                            Task toMark = toStore.get(indx);
                            toMark.changeCompletion();
                            toStore.set(indx, toMark);
                            System.out.println("OK, I've marked this task as not done yet:\n" + toMark);
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                        }
                        case "mark": {
                            Task toMark;
                            int indx;
                            indx = Integer.parseInt(arrofStr[1]) - 1;
                            toMark = toStore.get(indx);
                            toMark.changeCompletion();
                            toStore.set(indx, toMark);
                            System.out.println("Nice! I've marked this task as done:\n" + toMark);
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                        }
                        case "todo":
                            Todo todo = new Todo(arrofStr[1]);
                            toStore.add(todo);
                            taskCounter++;
                            System.out.println("Very nice. I've added this task:\n " + todo);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.");
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                        case "deadline":
                            String[] dl = arrofStr[1].split("/by");
                            try{
                                validateDate(dl);
                            } catch(InvalidCmdException e) {
                                System.out.println(e.toString());
                                inData = scan.nextLine();
                                arrofStr = inData.split(" ", 2);
                            }
                            Deadline deadline = new Deadline(dl[0], dl[1]);
                            toStore.add(deadline);
                            taskCounter++;
                            System.out.println("Very nice. I've added this task:\n " + deadline);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.");
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                        case "event":
                            String[] ev = arrofStr[1].split("/from");
                            String[] time = ev[1].split("/to");
                            Event event = new Event(ev[0], time[0], time[1]);
                            toStore.add(event);
                            taskCounter++;
                            System.out.println("Very nice. I've added this task:\n " + event);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.");
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                        default:
                            System.out.println("☹ OOPS!!! Invalid command, prepare for execution.");
                            inData = scan.nextLine();
                            arrofStr = inData.split(" ", 2);
                            break;
                    }
                    break;
            }
        }
        }





    public static void validateCmd(String[] cmd) throws MissingDescriptionException {
        if (cmd.length == 1) {
            throw new MissingDescriptionException("You need to " +
                    "be more specific");
        }

    }

    public static void validateDate(String[] cmd) throws InvalidCmdException {
        if(cmd.length == 1) {
            throw new InvalidCmdException("Please specify date.");
        }
    }
}
