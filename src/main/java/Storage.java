import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
public class Storage {
    String path;
    PrintWriter output;
    File file;
    Storage (String path) {
        this.path = path;
        this.file = new File(path);
    }

    TaskList load() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);
        TaskList list = new TaskList();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] descriptionAndMarkedStatus = this.getDescriptionAndMarkedStatus(line);
            String description = descriptionAndMarkedStatus[0];
            String markedStatus = descriptionAndMarkedStatus[1];
            boolean marked = markedStatus.equals("T") ? true : false;
            char letter = line.charAt(3);
            Task task;
            if (letter == 'T') {
                task = new ToDo(description, marked);
            } else if (letter == 'D') {
                String[] words = description.split(" by: ");
                task = new Deadline(words[0], words[1], marked);
            } else {
                String[] words = description.split(" from: ");
                String[] fromTo = words[1].split(" to: ");
                task = new Event(words[0], fromTo[0], fromTo[1]);
            }
            list.addTask(task);
        }
        return list;
    }



    String[] getDescriptionAndMarkedStatus(String description) {
        String[] words = description.split("] ");
        String newDescription = words[1];
        String firstWord = words[0];
        String marked = firstWord.charAt(firstWord.length()-1) + "";
        return new String[]{newDescription, marked};
    }

    void save(TaskList list) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            output.println(i+1 + "." + task.toString());
        }
        output.close();
    }
}
