import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String bannerLine = "_".repeat(30);
    private final List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ,ggg, ,ggggggg,                                                               ,ggg,\n"
                    + "dP\"\"Y8,8P\"\"\"\"\"Y8b              ,dPYb,                                         dP\"\"8I\n"
                    + "Yb, `8dP'     `88              IP'`Yb                                        dP   8\n"
                    + " `\"  88'       88              I8  8I      gg               gg              dP    88\n"
                    + "     88        88              I8  8bgg,   \"\"               \"\"             ,8'    88\n"
                    + "     88        88    ,gggg,gg  I8 dP\" \"8   gg    ,gggggg,   gg             d88888888   gg     gg    ,gggg,gg   ,ggg,,ggg,,ggg,    ,ggg,\n"
                    + "     88        88   dP\"  \"Y8I  I8d8bggP\"   88    dP\"\"\"\"8I   88       __   ,8\"     88   I8     8I   dP\"  \"Y8I  ,8\" \"8P\" \"8P\" \"8,  i8\" \"8i\n"
                    + "     88        88  i8'    ,8I  I8P' \"Yb,   88   ,8'    8I   88      dP\"  ,8P      Y8   I8,   ,8I  i8'    ,8I  I8   8I   8I   8I  I8, ,8I\n"
                    + "     88        Y8,,d8,   ,d8b,,d8    `Yb,_,88,_,dP     Y8,_,88,_    Yb,_,dP       `8b,,d8b, ,d8I ,d8,   ,d8b,,dP   8I   8I   Yb, `YbadP'\n"
                    + "     88        `Y8P\"Y8888P\"`Y888P      Y88P\"\"Y88P      `Y88P\"\"Y8     \"Y8P\"         `Y8P\"\"Y88P\"888P\"Y8888P\"`Y88P'   8I   8I   `Y8888P\"Y888\n"
                    + "                                                                                            ,d8I'\n"
                    + "                                                                                          ,dP'8I\n"
                    + "                                                                                         ,8\"  8I\n"
                    + "                                                                                         I8   8I\n"
                    + "                                                                                         `8, ,8I\n"
                    + "                                                                                          `Y8P\"\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        greet();
        commandLoop();
    }

    public void greet() {
        printInBanner("Greetings humans~\n'Tis I! Nakiri Ayame!\nWhat can I do for you?");
    }

    public void commandLoop() {
        Scanner input = new Scanner(System.in);
        String cmd;
        boolean acceptingInputs = true;
        while (acceptingInputs) {
            cmd = input.nextLine();
            String[] args = cmd.split(" ");
            if (args.length == 0) {
                continue;
            }

            String cmdVerb = args[0];
            switch (cmdVerb) {
                case "bye":
                    acceptingInputs = false;
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    if (args.length != 2) {
                        addTask(args);
                        continue;
                    }

                    int idx;
                    try {
                        idx = Integer.parseInt(args[1]) - 1;
                    } catch (NumberFormatException e) {
                        addTask(args);
                        continue;
                    }

                    changeTaskStatus(idx, true);
                    break;
                case "unmark":
                    if (args.length != 2) {
                        addTask(args);
                        continue;
                    }

                    try {
                        idx = Integer.parseInt(args[1]) - 1;
                    } catch (NumberFormatException e) {
                        addTask(args);
                        continue;
                    }

                    changeTaskStatus(idx, false);
                    break;
                default:
                    addTask(args);
                    break;
            }
        }
        printInBanner("Otsunakiri~\nByebye!~");
    }

    private void printInBanner(String message) {
        System.out.println(bannerLine);
        System.out.println(message);
        System.out.println(bannerLine);
    }

    private void printTasks() {
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                toPrint.append("\n");
            }
            toPrint.append(i + 1).append(": ").append(taskList.get(i));
        }
        printInBanner(toPrint.toString());
    }

    private void addTask(String[] args) {
        String cmd = String.join(" ", args);
        taskList.add(new Task(cmd));
        printInBanner("added: " + cmd);
    }

    private void changeTaskStatus(int idx, boolean done) {
        if (idx < 0 || idx >= taskList.size()) {
            printInBanner("Can't do that yo~\nInvalid index supplied~");
            return;
        }

        if (done) {
            taskList.get(idx).markAsDone();
            printInBanner("Yatta! You have done this task!\n" + taskList.get(idx));
        } else {
            taskList.get(idx).unmarkAsDone();
            printInBanner("Neee! Are you kidding me?\n" + taskList.get(idx));
        }
    }
}
