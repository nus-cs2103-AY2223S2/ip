import java.io.*;
import java.util.stream.Stream;


public class Save {
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    public static void saveTaskList(TaskList toDoList) {
        try {
            FileWriter writer = new FileWriter( "./data/duke.txt");
            String msg = "   your to-do list: ";
            for (int i = 0; i < toDoList.size(); i++) {
                msg += "\n   " + toDoList.get(i).toString();
            }
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            System.out.println("    Sorry i have encountered an error..");
        }
    }

    public static void loadTaskList() {
        String file = "./data/duke.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Stream<String> content = reader.lines();
            content.forEach(s -> System.out.println(s));
            System.out.println("\n" + separator);
        } catch (FileNotFoundException e1) {
            try {
                File file1 = new File(file);
                file1.getParentFile().mkdirs();
                file1.createNewFile();

                FileWriter writer = new FileWriter(file);
                writer.write("   You do not have a to-do list yet!\n Create one by adding your tasks here!");
                writer.close();
                System.out.println("   Sorry i have encountered an error..");
            } catch(IOException e2) {
                System.out.println("Sorry i have encountered an error..");
            }
        }

    }
}