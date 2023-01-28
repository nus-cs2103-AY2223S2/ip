package main;
import java.io.*;
import java.util.stream.Stream;


public class Storage {
    private String filePath;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTaskList(TaskList tl) {
        try {
            FileWriter writer = new FileWriter( filePath);
            String msg = "   your to-do list: ";
            for (int i = 0; i < tl.size(); i++) {
                msg += "\n   " + tl.get(i).toString();
            }
            writer.write(msg);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TaskList loadTaskList() {
        File file = new File(this.filePath);
        if (file.exists()) {
            TaskList tl = new TaskList();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                Stream<String> content = reader.lines();
                content.forEach(s -> System.out.println(s));
                System.out.println("\n" + separator);
            } catch (Exception e) {
                System.out.println("file empty");
            }
            return tl;
        } else {
            try {

                file.getParentFile().mkdirs();
                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                writer.write("   You do not have a to-do list yet!\n Create one by adding your hachi.tasks here!");
                writer.close();
                System.out.println("   Sorry i have encountered an error..");
            } catch (IOException e2) {
                System.out.println("   Sorry i have encountered an error..");
            }
            return new TaskList();
        }

    }
}
