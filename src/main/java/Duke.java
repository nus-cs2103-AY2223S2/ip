import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke\nWhat can I do for you?");
        Duke duke = new Duke();
        boolean isBye = false;
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        while (!isBye){
            String answer = sc.nextLine();
            String[] wordArr = answer.split(" ", 2);

            System.out.println("___________________");
            if (answer.startsWith("mark")){
                //get index
                String index = wordArr[1];
                //markTask
                System.out.println(taskList.markTask(index));
            }
            else if (answer.startsWith("unmark")){
                String index = wordArr[1];
                System.out.println(taskList.unmarkTask(index));
            }
            else if (answer.equals("bye")){
                isBye = true;
                System.out.println("Goodbye");
            }
            else if (answer.equals("list")){ //return list of tasks
                System.out.println(taskList.listTasks());
            }
            else if (wordArr[0].equals("todo")){
                //parse string
                String desc = wordArr[1];
                //addTask to taskList
                System.out.println(taskList.addTask(desc));
            }
            else if (wordArr[0].equals("deadline")){
                String desc = wordArr[1].split("/by", 2)[0];
                String by = wordArr[1].split("/by", 2)[1];
                System.out.println(taskList.addTask(desc, by));

            }
            else if (wordArr[0].equals("event")){
                String desc = wordArr[1].split(" ", 2)[0];
                String from = wordArr[1].split("/from", 2)[1].split("/to", 2)[0];
                String to = wordArr[1].split("/to", 2)[1];
                System.out.println(taskList.addTask(desc, from, to));
            }
            System.out.println("___________________");
        }

    }

}
