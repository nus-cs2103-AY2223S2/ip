import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> storeTasks = getFileContents();
        Scanner sc = new Scanner(System.in);
        int numElem = storeTasks.size();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "How may I be of service to you? \nEnter your command:");
        String commandToEcho = sc.nextLine();
        while (!commandToEcho.equals("bye")) {
            if(commandToEcho.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                if(numElem==0) {
                    System.out.println("You do not have any tasks for now!");
                }
                for (int i = 0; i < numElem; i++) {
                    System.out.println(String.format("%d. %s",i+1,storeTasks.get(i)));
                }
            } else if (commandToEcho.length() >=4 && commandToEcho.substring(0, 4).equals("mark") ) {
                    int intTaskIndex = getTaskIndex(commandToEcho);
                    storeTasks.get(intTaskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n " + storeTasks.get(intTaskIndex).toString());
            } else if (commandToEcho.length() >=6 && commandToEcho.substring(0, 6).equals("unmark")) {
                int intTaskIndex = getTaskIndex(commandToEcho);
                storeTasks.get(intTaskIndex).markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n " + storeTasks.get(intTaskIndex).toString());
            } else if (commandToEcho.length()>=6 && commandToEcho.substring(0, 6).equals("delete")) {
                int intTaskIndex = getTaskIndex(commandToEcho);
                Task taskRemoved = storeTasks.get(intTaskIndex);
                storeTasks.remove(intTaskIndex);
                numElem--;
                System.out.println("Noted. I've removed this task: \n" + taskRemoved.toString() + String.format("\n Now you have %d tasks in the list.",numElem));
            } else {
                Task currentTask;
                if (commandToEcho.length() >= 4 && commandToEcho.substring(0, 4).equals("todo")) {
                    if (commandToEcho.equals("todo")) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
//                        throw new DukeException();
                    } else {
                        System.out.println("Got it. I've added this task:");
                        String desc = getDescToDo(5, commandToEcho);
                        currentTask = new Todo(desc);
                        storeTasks.add(currentTask);
                        System.out.println(currentTask);
                        numElem++;
                        System.out.println(String.format("Now you have %d task(s) in the list.", numElem));
                    }
                } else if (commandToEcho.length() >= 8 && commandToEcho.substring(0, 8).equals("deadline")) {
                    if (commandToEcho.equals("deadline")) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        try {
                            String desc = getDesc(9, commandToEcho);
                            LocalDate byWhen = getByWhen(commandToEcho);
                            currentTask = new Deadline(desc, byWhen);
                            storeTasks.add(currentTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(currentTask);
                            numElem++;
                            System.out.println(String.format("Now you have %d task(s) in the list.", numElem));
                        } catch (Exception e) {
                        }
                    }
                } else if (commandToEcho.length() >= 5 && commandToEcho.substring(0, 5).equals("event")) {
                    if (commandToEcho.equals("event")) {
                        System.out.println("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        try {
                            String desc = getDesc(6, commandToEcho);
                            LocalDate from = getFrom(commandToEcho);
                            LocalDate to = getTo(commandToEcho);
                            if (to.isAfter(from)) {
                                currentTask = new Event(desc, from, to);
                                storeTasks.add(currentTask);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(currentTask);
                                numElem++;
                                System.out.println(String.format("Now you have %d task(s) in the list.", numElem));
                            } else {
                                System.out.println("ERROR!! From date cannot be BEFORE To date!");
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            commandToEcho = sc.nextLine();
        }
        saveTasks(storeTasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    static int getTaskIndex(String commandToEcho) {
        String taskIndex = "";
        int toMinus = 1;
        char fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        while (fromBack != (' ')) {
            taskIndex = fromBack + taskIndex;
            toMinus++;
            fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        }
        int intTaskIndex = Integer.parseInt(taskIndex) - 1;
        return intTaskIndex;
    }
    static String getDescToDo(int startIndex, String commandToEcho) {
        String subString = commandToEcho.substring(startIndex);
        String desc = "";
        int index = 0;
        char front = subString.charAt(index);
        while (index < subString.length() - 1) {
            desc = desc + front;
            index++;
            front = subString.charAt(index);
        }
        desc = desc + front;
        return desc;
    }

    static String getDesc(int startIndex, String commandToEcho) {
        String subString = commandToEcho.substring(startIndex);
        String desc = "";
        int index = 0;
        char front = subString.charAt(index);
        while (front != ('/')) {
            desc = desc + front;
            index++;
            front = subString.charAt(index);
        }
        return desc;
    }
    static LocalDate getByWhen(String commandToEcho) throws DateTimeParseException {
        String[] arrOfStr = commandToEcho.split("/by")[1].split(" ");
        String strDate = arrOfStr[1];
        try {
            LocalDate date = LocalDate.parse(strDate);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("INVALID DATE!!! Please enter date in YYYY/MM/DD format");
            throw e;
        }
    }

    static LocalDate getFrom(String commandToEcho) throws DateTimeParseException {
        String[] arrOfStr = commandToEcho.split("/from")[1].split(" ");
        try {
            LocalDate from = LocalDate.parse(arrOfStr[1]);
            return from;
        } catch (DateTimeParseException e) {
            System.out.println("INVALID 'From' DATE!!! Please enter date in YYYY/MM/DD format");
            throw e;
        }
    }

    static LocalDate getTo(String commandToEcho) throws DateTimeParseException {
        String[] arrOfStr = commandToEcho.split("/from")[1].split(" ");
        try {
            LocalDate to = LocalDate.parse((arrOfStr[3]));
            return to;
        } catch (DateTimeParseException e) {
            System.out.println("INVALID 'To' DATE!!! Please enter date in YYYY/MM/DD format");
            throw e;
        }
    }

    private static ArrayList<Task> getFileContents() throws FileNotFoundException {
        File folder = new File("data");
        if (!folder.exists()) {
            throw new FileNotFoundException("Folder does not exist!");
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        Scanner sc = new Scanner(f);
        ArrayList<Task> storeTasks = new ArrayList<Task>();
        int numElem = 0;
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            String[] arrOfDetails = currentLine.split("\\|");
            String type = arrOfDetails[0];
            char marker = arrOfDetails[2].charAt(0);
            boolean isMarked = (marker=='X') ? true : false;
            String desc = arrOfDetails[1];
            switch (type) {
            case "T":// T|desc|X
                storeTasks.add(new Todo(desc));
                break;
            case "D": //D|desc|X|byWhen
                LocalDate byWhen = LocalDate.parse(arrOfDetails[3]);
                storeTasks.add(new Deadline(desc,byWhen));
                break;
            case "E": //D|desc|X|from|to
                LocalDate from = LocalDate.parse(arrOfDetails[3]);
                LocalDate to = LocalDate.parse(arrOfDetails[4]);
                storeTasks.add(new Event(desc,from,to));
                break;
            }
            if (isMarked) {
                storeTasks.get(numElem).markAsDone();
            }
            numElem++;
        }
        return storeTasks;
    }

    private static void saveTasks(ArrayList<Task> storeTasks) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
            fw = new FileWriter("data/duke.txt", true);
            for (Task t: storeTasks) {
                String type = t.getType();
                String content = "";
                switch (type) {
                case "T": // T|desc|X
                    content = String.format("%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon());
                    break;
                case "D": //D|desc|X|from
                    Deadline deadlineTask = (Deadline) t;
                    content = String.format("%s|%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon(),deadlineTask.getByWhen());
                    break;
                case "E": //D|desc|X|from|to
                    Event eventTask = (Event) t;
                    content = String.format("%s|%s|%s|%s|%s",t.getType(),t.getDesc(),t.getStatusIcon(),eventTask.getFrom(),eventTask.getTo());
                    break;
                }
                fw.write(content + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error with saving TODO task");
        }
    }
}
