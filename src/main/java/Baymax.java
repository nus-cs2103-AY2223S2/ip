import java.nio.channels.ScatteringByteChannel;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Baymax {
    public static void main(String[] args) {
        System.out.println("Hello, I am Baymax your personal Chatbot Companion. \nWhat can I do for you today?");
        makeDecision();
        System.out.println("See you soon!");
    }

    public static void makeDecision(){
        String exit = "bye";
        String outputL = "list";
        String mark = "mark";
        String unmark = "unmark";
        String to = "todo";
        String dead = "deadline";
        String event = "event";
        String delete = "delete";

        List<Task> myList = new ArrayList<>();
        try {
            File file = new File("./data/Baymax.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (data.charAt(1) == "T".charAt(0)) {
                    Task todo = new Todo(data.substring(7));
                    if (data.charAt(4) == "X".charAt(0)) {
                        todo.markAsDone();
                    }
                    myList.add(todo);
                }
                if (data.charAt(1) == "D".charAt(0)) {
                    String one = data.split(" " + "[(]" + "by: ")[0].substring(7);
                    String two = data.split(" " + "[(]" + "by: ")[1];
                    int l = two.length();
                    two = two.substring(0, l - 2);
                    Task deadline = new Deadline(one, two);
                    if (data.charAt(4) == "X".charAt(0)) {
                        deadline.markAsDone();
                    }
                    myList.add(deadline);
                }
                if (data.charAt(1) == "E".charAt(0)) {
                    String one = data.split(" " + "[(]" + "from: ")[0].substring(7);
                    String two = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[0];
                    String three = data.split(" " + "[(]" + "from: ")[1].split(" to: ")[1];
                    int l = three.length();
                    three = three.substring(0, l - 2);
                    Task even = new Event(one, two, three);
                    if (data.charAt(4) == "X".charAt(0)) {
                        even.markAsDone();
                    }
                    myList.add(even);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner input = new Scanner(System.in);
        String currentInput;
        int Counter = 0;
        while (true) {
            currentInput = input.nextLine();
            if (exit.equals(currentInput)) {
                break;
            } else if (outputL.equals(currentInput)) {
                int index = 1;
                for (Task s : myList) {
                    System.out.println((index++) + ": " + s.toString());
                }
            } else if (mark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsDone();
                t.markAsDoneMessage();
            } else if (unmark.equals(currentInput.split("\\s+")[0])) {
                Task t = myList.get(Integer.valueOf(currentInput.split("\\s+")[1]) - 1);
                t.markAsNotDone();
                t.markAsNotDoneMessage();
            }
            else if (delete.equals(currentInput.split("\\s+")[0])) {
                myList.remove(Integer.valueOf(currentInput.split(" ",2)[1]) - 1);
                System.out.println("Done. I've deleted the task");
            } else {
                    if (to.equals(currentInput.split("\\s+", 2)[0])) {
                        Task todo = new Todo(currentInput.split(" ", 2)[1]);
                        myList.add(todo);
                        System.out.println("Done. I've added the task: " + todo.toString());
                    } else if (dead.equals(currentInput.split("\\s+", 2)[0])) {
                        Task deadline = new Deadline(currentInput.split(" ", 2)[1].split(" /by ")[0], currentInput.split(" ", 2)[1].split(" /by ")[1]);
                        myList.add(deadline);
                        System.out.println("Done. I've added the deadline: " + deadline.toString());
                    } else if (event.equals(currentInput.split("\\s+", 2)[0])) {
                        Task even = new Event(currentInput.split(" ", 2)[1].split(" /from ", 2)[0], currentInput.split(" ", 2)[1].split(" /from ", 2)[1].split(" /to ", 2)[0], currentInput.split(" ", 2)[1].split(" /from ", 2)[1].split(" /to ", 2)[1]);
                        myList.add(even);
                        System.out.println("Done. I've added the Event: " + even.toString());
                    }
            }
        }
        try {
            FileWriter writer = new FileWriter("./data/Baymax.txt");
            for(Task task: myList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

