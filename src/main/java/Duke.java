import java.util.Scanner;
import dukeexceptions.DukeExceptions;
import dukeexceptions.UnknownCommandException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private static File file;

    public static final String PATH = "./data/";

    private static void fileSetup() {
        File directory = new File(PATH);
        try {
            if(!directory.exists()){
                directory.mkdir();
            }

            file = new File(PATH, "duke.txt");

            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    private static void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(taskList.saveTaskList());

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        TaskList taskList = new TaskList();
        fileSetup();

        String intro = "  ________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);

        //start of bot
        while(true){
            String str= sc.nextLine();
            String[] splitStr = str.split(" ");
            String requestContent = str.split(" ", 2).length == 2
                    ? str.split(" ", 2)[1]
                    : "";

            //END
            if(splitStr[0].equals("bye")) {
                break;
            }

            try {

                //List Command
                if (splitStr[0].equals("list")) {
                    taskList.list();
                    continue;
                }

                //mark
                else if (splitStr[0].equals("mark")) {
                    int index = Integer.parseInt(splitStr[1]) - 1;
                    taskList.setDone(index);
                    continue;
                }

                //unmark
                else if (splitStr[0].equals("unmark")) {
                    int index = Integer.parseInt(splitStr[1]) - 1;
                    taskList.setNotDone(index);
                    continue;
                }

                else if (splitStr[0].equals("todo")) {
                    taskList.addToDo(requestContent);
                    continue;
                }

                else if (splitStr[0].equals("deadline")) {
                    taskList.addDeadline(requestContent);
                    continue;
                }

                else if (splitStr[0].equals("event")) {
                    taskList.addEvent(requestContent);
                    continue;
                }

                else if (splitStr[0].equals("delete")) {
                    taskList.delete(requestContent);
                    continue;
                }

                else {
                    taskList.unknownCommand();
                }
            } catch(DukeExceptions exceptions) {
                String reply = "  ________________________________\n"
                        + exceptions.toString() + "\n"
                        + "  ________________________________\n";
                System.out.println(reply);
            }

        }

        save(taskList);

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}
