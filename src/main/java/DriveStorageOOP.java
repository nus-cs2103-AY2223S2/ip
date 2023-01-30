import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
public class DriveStorageOOP {



    public static boolean createFile() {
        try {
            File storage = new File("duke.txt");
            if (storage.createNewFile()) {
                System.out.println("File created: " + storage.getName());
                return true;

            } else {
                System.out.println("File already exists. " +
                        "No action is needed. Any past tasks will be loaded into this session as well.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;

        }
    }

    public static void loadTasks(TaskStorage taskStorage) {

        try {
            File myObj = new File("duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] nextLine = myReader.nextLine().split(" ", 3);
                Boolean completed = nextLine[0].equals("1") ? true : false;
                String firstWord = nextLine[1];

                if (firstWord.equals("deadline")) {
                    String bodyMessage = nextLine[2];
                    DeadLine newTask = new DeadLine("deadline", bodyMessage, completed);
                    taskStorage.addTask(newTask);
                    continue;

                }
                if (firstWord.equals("event")) {
                    String bodyMessage = nextLine[2];
                    Event newTask = new Event("event", bodyMessage, completed);
                    taskStorage.addTask(newTask);
                    continue;
                }

                if (firstWord.equals("todo")) {
                    String bodyMessage = nextLine[2];
                    ToDo newTask = new ToDo("todo", bodyMessage, completed);
                    taskStorage.addTask(newTask);
                }
            }

            myReader.close();
        }   catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void changeTaskStatus(String taskData) {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);

            StringBuffer buffer = new StringBuffer();

            String completed = taskData.split(" ", 2)[0];
            String description =   taskData.split(" ", 2)[1];

            String updatedDescription = completed.equals("1") ? "0 " + description : "1 " + description;

            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskData)) {
                    buffer.append(updatedDescription + "\n");
                } else {
                    buffer.append(nextLine + "\n");
                }
            }

            String fileContents = buffer.toString();
            sc.close();
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(fileContents);
            writer.close();
            System.out.println("updated hard drive!");
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void deleteTask(String taskData) {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);

            StringBuffer buffer = new StringBuffer();

            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskData)) {

                } else {
                    buffer.append(nextLine + "\n");
                }

            }
            String fileContents = buffer.toString();

            sc.close();

            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");

            writer.append(fileContents);
            writer.close();
            System.out.println("updated hard drive!");
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }




    public static void addTask(String input) {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);

            StringBuffer buffer = new StringBuffer();

            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + "\n");
            }
            String fileContents = buffer.toString();

            sc.close();

            //Replacing the old line with new line
            fileContents = fileContents + input + "\n";

            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(fileContents);
            writer.close();
        }

        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}




