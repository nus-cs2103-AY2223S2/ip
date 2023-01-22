import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String dog = "⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⣀⣀⣀⣀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣰⣿⣿⡻⠟⠋⠉⠉⣻⠟⠉⠉⠉⠛⢯⡛⢿⣿⣷⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⣠⣴⠾⠛⢋⣿⠟⠋⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠈⠂⣹⣿⡈⠙⠻⢶⣄⡀⠀\n" +
                "⣸⠏⠀⠀⠀⣾⣋⣀⣀⡀⠀⠀⠀⢸⠁⠀⠀⢀⣀⣀⣀⡀⠀⠈⠻⣧⠀⠀⠀⠉⠻⣦\n" +
                "⢿⡀⠀⣿⣿⠟⣫⣽⣿⣿⣿⣿⣶⣶⣶⡶⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⢀⣿\n" +
                "⠸⣧⠀⠈⣿⢸⣿⣿⣿⣿⣿⣿⣿⠁⢹⡇⣼⣿⣿⣿⣿⣿⣿⣿⠁⣼⡇⠀⠀⠀⣼⠇\n" +
                "⠀⠹⣷⡀⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⠏⠀⣿⡇⠀⣠⡾⠋⠀\n" +
                "⠀⠀⠈⢿⣿⢿⡿⠿⠿⣿⠟⠉⠀⠀⠀⠀⠙⠛⢿⡿⠿⠛⠉⠀⠀⡿⣷⣾⠏⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠋⠘⣷⠀⢀⡿⢰⣾⣟⣛⣿⣿⣷⡄⠀⢻⣆⠀⠀⠀⢰⡇⠘⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠹⣧⣼⠃⠈⣧⣼⣿⣇⣈⣿⠃⠀⠀⣿⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣆⠀⠈⠙⢿⡛⠉⠁⠀⠀⣠⡿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡿⢶⣶⣾⣿⣶⣤⣤⣶⢿⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⡼⠁⠉⠏⠁⠈⢹⣠⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⠀⣸⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣤⣤⡾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⢶⣾⣶⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("Good ta see yer dawg, Duke's at yer service.\n" + dog);
        TaskList taskList = new TaskList();

        // initialisation check
        // (Note: runtest.sh won't create a folder within bin)
        try {
            File dir = new File("./data/");
            File f = new File("data/duke.txt");
            if (!dir.exists() && !f.exists()) {
//                System.out.println("test");
                dir.mkdir(); f.createNewFile();
            }
            else if (!f.exists()) {
                f.createNewFile();
            }
            else if (!dir.exists() && f.exists()) {
                // TODO: move file to data directory if file exists but not directory
            }
            else {
//              Dir + file exists, update taskList arr
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    String[] inputArr;
                    inputArr = input.split("\\|");
                    if (inputArr[0].equals("T") || inputArr[0].equals("D") || inputArr[0].equals("E")) {
                        taskList.loadTasks(inputArr, inputArr[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            String[] inputArr;
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                System.out.println("See yer again RUFF!");
                break;
            } else if (inputArr[0].equals("list")) {
                System.out.println("Here are the tasks in your list dawg:");
                taskList.printList();
            } else if (inputArr[0].equals("mark")) {
                try {
                    taskList.markStatus(inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("The task is marked, dawg");
            } else if (inputArr[0].equals("unmark")){
                try {
                    taskList.unMarkStatus(inputArr[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Gotcha dawg, unmarked.");
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                try {
                    taskList.addTasks(inputArr, inputArr[0]);
                } catch (EmptyDescException | IOException e) {
                    e.printStackTrace();
                }
            } else if (inputArr[0].equals("delete")) {
                int inputIndex = Integer.parseInt(inputArr[1]);
                try {
                    taskList.deleteTasks(inputIndex);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new UnknownCommandException("test");
                } catch (UnknownCommandException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
