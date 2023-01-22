import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Crystal {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }


    private static ArrayList<Task> readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> temp = new ArrayList<Task>();
        while (s.hasNext()) {
            String t = s.nextLine();
            if (t.startsWith("T")) {
                if (t.contains("| 0 |")) {
                    String description = t.replace("T | 0 |", "");
                    Task n = new Todo(description.trim());
                    n.isDone = true;
                    temp.add(n);
                } else {
                    String description = t.replace("T | 1 |", "");
                    Task n = new Todo(description.trim());
                    n.isDone = false;
                    temp.add(n);
                }

            } else if (t.startsWith("D")) {
                if (t.contains("| 0 |")) {
                    String description = t.replace("D | 0 |", "");
                    int index = description.lastIndexOf("|");
                    String time = description.substring(description.lastIndexOf("|") + 1);
                    description = description.replace(description.substring(index), "");
                    Task n = new Deadline(description.trim(), time.trim());
                    n.isDone = true;
                    temp.add(n);
                } else {
                    String description = t.replace("D | 1 |", "");
                    int index = description.lastIndexOf("|");
                    String time = description.substring(description.lastIndexOf("|") + 1);
                    description = description.replace(description.substring(index), "");
                    Task n = new Deadline(description.trim(), time.trim());
                    n.isDone = false;
                    temp.add(n);
                }
            } else if (t.startsWith("E")) {
                if (t.contains("| 0 |")) {
                    String description = t.replace("E | 0 |", "");
                    int index = description.lastIndexOf("|");
                    int index2 = description.lastIndexOf("-");
                    String time = description.substring(description.lastIndexOf("|") + 1);
                    int index3 = time.lastIndexOf("-");
                    time = time.replace(time.substring(index3), "");
                    String endtime = description.substring(description.lastIndexOf("-") + 1);
                    description = description.replace(description.substring(index), "");
                    Task n = new Event(description.trim(), time.trim(), endtime.trim());
                    n.isDone = true;
                    temp.add(n);
                } else {
                    String description = t.replace("E | 1 |", "");
                    int index = description.lastIndexOf("|");
                    int index2 = description.lastIndexOf("-");
                    String time = description.substring(description.lastIndexOf("|") + 1);
                    int index3 = time.lastIndexOf("-");
                    time = time.replace(time.substring(index3), "");
                    String endtime = description.substring(description.lastIndexOf("-") + 1);
                    description = description.replace(description.substring(index), "");
                    Task n = new Event(description.trim(), time.trim(), endtime.trim());
                    n.isDone = false;
                    temp.add(n);
                }
            }

        }
        return temp;
    }


    public static void main(String[] args) {

        String file2 = "/repos/Independentproject/myfiles/Crystal.txt";
        String base = "/repos/Independentproject";
        String relative = new File(base).toURI().relativize(new File(file2).toURI()).getPath();

        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        ArrayList<Task> list = new ArrayList<>();
        try {
            list = readFileContents(file2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        while (true) {

            try {
                if (input.equals("list")) {
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Here are your current tasks:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(" ____________________________________________________________");

                } else if (input.contains("unmark")) {
                    String getnum = input.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(getnum);
                    Task t = list.get(num - 1);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Alright, I've marked this task as not done: ");
                    t.isDone = false;
                    System.out.println(t.toString());
                    System.out.println(" ____________________________________________________________");

                } else if (input.contains("mark")) {
                    String getnum = input.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(getnum);
                    Task t = list.get(num - 1);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Alright, I've marked the task as done: ");
                    t.isDone = true;
                    System.out.println(t.toString());
                    System.out.println(" ____________________________________________________________");

                } else if (input.contains("todo")) {
                    String s = input.replace("todo", "");
                    if (s.length() == 0) {
                        throw new CrystalException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    System.out.println(" ____________________________________________________________");
                    Todo t = new Todo(s);
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(t.toString());
                    list.add(t);
                    System.out.println("Current number of tasks : " + list.size());
                    System.out.println(" ____________________________________________________________");

                } else if (input.contains("deadline")) {
                    System.out.println(" ____________________________________________________________");
                    String s = input.replace("deadline", "");
                    String[] arr = s.split("/");
                    String subs = arr[0];
                    String n = arr[1];
                    String subsubs = n.replace("by", "");
                    Deadline d = new Deadline(subs, subsubs);
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(d.toString());
                    list.add(d);
                    System.out.println("Current number of tasks : " + list.size());
                    System.out.println(" ____________________________________________________________");

                } else if (input.contains("event")) {
                    System.out.println(" ____________________________________________________________");
                    String s = input.replace("event", "");
                    String[] arr = s.split("/");
                    String subs = arr[0];
                    String n = arr[1];
                    String subsubs = n.replace("from", "");
                    String t = arr[2];
                    String sublastsub = t.replace("to", "");
                    Event e = new Event(subs, subsubs, sublastsub);
                    System.out.println("Alright, I've added this task: ");
                    System.out.println(e.toString());
                    list.add(e);
                    System.out.println("Current number of tasks: " + list.size());
                    System.out.println(" ____________________________________________________________");

                } else if (input.equals("bye")) {
                    System.out.println(" ____________________________________________________________");
                    System.out.println(" Thank You. Please come by again~!");
                    System.out.println(" ____________________________________________________________");
                    break;

                } else if (input.contains("delete")) {
                    String getnum = input.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(getnum);
                    System.out.println(" ____________________________________________________________");
                    System.out.println("Alright, I've removed this task: ");
                    Task item = list.get(num - 1);
                    list.remove(num - 1);
                    System.out.println(item.toString());
                    System.out.println("Current number of tasks: " + list.size());
                    System.out.println(" ____________________________________________________________");

                } else {
                    throw new CrystalException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (CrystalException e) {
                System.out.println(" ____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println(" ____________________________________________________________");
                input = sc.nextLine();
                continue;

            }
            input = sc.nextLine();


        }


        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).toString().startsWith("[T]")) {
                String s = list.get(i).toString().replace("[T]", "T ");
                if (list.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |"); //task done
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    str.append(s + "\n");
                }

            } else if (list.get(i).toString().startsWith("[D]")) {
                String s = list.get(i).toString().replace("[D]", "D ");
                if (list.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |");
                    s = s.replace("(by:", "|");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    s = s.replace("(by:", "|");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                }

            } else if (list.get(i).toString().startsWith("[E]")) {
                String s = list.get(i).toString().replace("[E]", "E ");
                if (list.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |");
                    s = s.replace("(from:", "|");
                    s = s.replace("to:", "-");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    s = s.replace("(from:", "|");
                    s = s.replace("to:", "-");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                }
            }


        }


        //Write to file
        try {
            writeToFile(relative, str + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}