import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] storeTasks = new Task[100];
        int numElem = 0;
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
                for (int i = 0; i < numElem; i++) {
                    System.out.println(String.format("%d. %s",i+1,storeTasks[i]));
                }
            } else if (commandToEcho.length() >=4 && commandToEcho.substring(0, 4).equals("mark") ) {
                    int intTaskIndex = getTaskIndex(commandToEcho);
                    storeTasks[intTaskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n " + storeTasks[intTaskIndex].toString());
            } else if (commandToEcho.length() >=6 && commandToEcho.substring(0, 6).equals("unmark")) {
                int intTaskIndex = getTaskIndex(commandToEcho);
                storeTasks[intTaskIndex].markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n " + storeTasks[intTaskIndex].toString());
            } else {
                System.out.println("Got it. I've added this task:");
                Task currentTask;
                if(commandToEcho.length()>=4 && commandToEcho.substring(0, 4).equals("todo")) {
                    String desc = getDescToDo(5,commandToEcho);
                    currentTask = new Todo(desc);
                    storeTasks[numElem] = currentTask;
                    System.out.println(currentTask);
                } else if (commandToEcho.length()>=8 && commandToEcho.substring(0, 8).equals("deadline")) {
                    String desc = getDesc(9,commandToEcho);
                    String byWhen = getByWhen(commandToEcho);
                    currentTask = new Deadline(desc,byWhen);
                    storeTasks[numElem] = currentTask;
                    System.out.println(currentTask);
                } else if (commandToEcho.length()>=5 && commandToEcho.substring(0, 5).equals("event")) {
                    String desc = getDesc(6,commandToEcho);
                    String from = getFrom(commandToEcho);
                    String to = getTo(commandToEcho);
                    currentTask = new Event(desc,from,to);
                    storeTasks[numElem] = currentTask;
                    System.out.println(currentTask);
                }
                numElem++;
                System.out.println(String.format("Now you have %d task(s) in the list.",numElem));
            }
            commandToEcho = sc.nextLine();
        }
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
    static String getByWhen(String commandToEcho) {
        String byWhen = "";
        int toMinus = 1;
        char fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        while (fromBack != ('/')) {
            byWhen = fromBack + byWhen;
            toMinus++;
            fromBack = commandToEcho.charAt(commandToEcho.length() - toMinus);
        }
        return byWhen;
    }

    static String getFrom(String commandToEcho) {
        String from = "";
        int index = 0;
        String req = "";
        char front = commandToEcho.charAt(index);
        while (front != ('/')) {
            index++;
            front = commandToEcho.charAt(index);
        }
        while (!from.equals("/from ")) {
            from = from + front;
            index++;
            front = commandToEcho.charAt(index);
        }
        while (front != ('/')) {
            req = req + front;
            index++;
            front = commandToEcho.charAt(index);
        }
        return req;
    }

    static String getTo(String commandToEcho) {
        int commandSize = commandToEcho.length();
        String from = "";
        int index = 0;
        String req = "";
        char front = commandToEcho.charAt(index);
        int numSlash=0;
        while (front != ('/') || numSlash!=1) {
            if(front == ('/')) {
                numSlash++;
            }
            index++;
            front = commandToEcho.charAt(index);
        }
        while (!from.equals("/to ")) {
            from = from + front;
            index++;
            front = commandToEcho.charAt(index);
        }
        while (index < commandSize-1) {
            req = req + front;
            index++;
            front = commandToEcho.charAt(index);
        }
        req = req + front;
        return req;
    }
}
