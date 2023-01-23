import task.Deadlines;
import task.Events;
import task.TaskManager;
import task.ToDos;
import ui.WelcomeUI;

import java.util.*;

public class ChatBot {
    protected Scanner input;
    protected TaskManager taskManager;
    protected String[] inputArr;


    public ChatBot(Scanner input, WelcomeUI welcomeUI) {
        System.out.println(welcomeUI);

        //initialise scanner
        this.input = input;

        //initialise array of tasks
        this.taskManager = new TaskManager();
    }

    public void run() {
        //main logic loop
        loop: while(input.hasNextLine()) {

            //remove leading and trailing whitespaces
            String ip = input.nextLine().trim();

            //convert user input to String array
            this.inputArr = ip.split(" ", 2);
            int len = inputArr.length;

            //if blank input is given
            if(ip.isBlank()) {
                System.out.println("No command given, please give me one!");
            }
            //if arr length = 1, cases: list, bye, other single word commands
            if (len == 1) {
                String command = inputArr[0];

                switch (command) {
                    case "list":
                        taskManager.displayList();
                        break;
                    case "bye":
                        System.out.println("ByeBye! Come play with me again!");
                        break loop;
                    case "menu":
                        //NOT WORKING
                        taskManager.getTaskMenu();
                        break;
                    case "":
                        continue;
                    default:
                        System.out.println("I haven't learnt this command yet!\n" +
                                "Type menu to see the commands I know.");
                        break;
                }
            } else if (len == 2) {
                //cases: check 1, uncheck 2, event project meeting, etc
                //format: keyword{whitespace}command

                String fst = inputArr[0];
                String snd = inputArr[1];
                Boolean isNum = taskManager.isNumeric(snd);

                //if snd is numeric : cases are check/uncheck etc
                if (isNum) {
                    try {
                        int index = Integer.parseInt(snd) - 1;
                        switch (fst) {
                            case "check":
                                taskManager.checkTask(index);
                                break;
                            case "uncheck":
                                taskManager.uncheckTask(index);
                                break;
                            case "delete":
                                taskManager.deleteTask(index);
                                break;
                            default:
                                System.out.println("I haven't learnt this command yet!\n" +
                                        "Type menu to see the commands I know.");
                                break;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Item does not exist in list! Please check your list again.");
                    }

                } else { //snd is a string input
                    switch (fst) {
                        case "todo":
                            ToDos todo = new ToDos(snd);
                            taskManager.addTaskToList(todo);
                            break;
                        case "event":
                            String[] arr = snd.split(" /from ");
                            String[] time = arr[1].split(" /to ");
                            String start = time[0];
                            String end = time[1];

                            Events event = new Events(arr[0], start, end);
                            taskManager.addTaskToList(event);
                            break;
                        case "deadline":
                            String[] tmp = snd.split(" /by ");
                            Deadlines deadline = new Deadlines(tmp[0], tmp[1]);
                            taskManager.addTaskToList(deadline);
                            break;
                        default:
                            System.out.println("I haven't learnt this command yet!\n" +
                                    "Type menu to see the commands I know.");
                            break;
                    }
                }
            }
        }
    }
}
