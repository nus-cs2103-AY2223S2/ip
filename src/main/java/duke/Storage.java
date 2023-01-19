package duke;

import java.io.PrintStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

class Storage {
    
    private String directory = System.getProperty("user.dir");
    private java.io.File path = new java.io.File(directory + "/dukeList.txt");
    private TaskList<Task> tasks = new TaskList<>();

    boolean isDirectory() {
        return path.exists();
    }

    void createDirectory() {
        try {
            if (!isDirectory()) {
                path.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    void writeToFile(String listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(directory + "/dukeList.txt",false);
            fileWriter.write(listOfTasks);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    void readFromFile() {
        try {
          String path = System.getProperty("user.dir") + "/dukeList.txt";
          Scanner scanner = new Scanner(new File(path));
          String inputFromFile = scanner.useDelimiter("\\A").next();
          System.out.println("String " + inputFromFile);
          String[] inputArr = inputFromFile.substring(1,inputFromFile.length()-1).split(",");
          for (String task : inputArr) {
            
            if (("" + task.charAt(0)).equals(" ")) {
                task = task.substring(1);
            }
            if (isSymbol(task, "X") || isSymbol(task, " ")) {
                rephraseNoDate(task);
            } else if (isSymbol(task, "T")) {
                rephraseToDo(task);
            } else if (isSymbol(task, "D")) {
                rephraseDeadline(task);
            } else if (isSymbol(task, "E")) {
                rephraseEvents(task);
            }
          }
        } catch(IOException e) {
            this.createDirectory();
        }
    }

    boolean isSymbol(String task, String symbol) {
        if (("" + task.charAt(1)).equals(symbol) || ("" + task.charAt(2)).equals(symbol)) {
            return true;

        } else {
            return false;
        }
    }

    void markTask(String task) {
        boolean isMark = ("" + task.charAt(1)).equals("X") ? true : false;
        if (isMark) {
            this.tasks = Parser.mark(this.tasks.numberOfTasks() - 1, this.tasks);
        } 
    }

    void rephraseNoDate(String input) {
        this.tasks = this.tasks.add(new Task(input.substring(4)));
        markTask(input);
    }

    void rephraseToDo(String input) {
        this.tasks = Parser.toDo(input.substring(7),this.tasks);
        markTask(input);
    }

    void rephraseDeadline(String input) {
        int indexOfBracket = input.indexOf(" (");
        this.tasks = Parser.deadline(input.substring(7, indexOfBracket), input.substring(indexOfBracket + 6, input.length() - 1), this.tasks);
        markTask(input);
    }

    void rephraseEvents(String input) { 
        int indexOfFrom = input.indexOf("(from: ");
        int indexOfTo = input.indexOf("(to: ");
        this.tasks = Parser.events(input.substring(8, indexOfFrom), input.substring(indexOfFrom + 6, indexOfTo - 1), input.substring(indexOfTo + 4, input.length() - 1), this.tasks);
        markTask(input);
    }
    
    TaskList<Task> getTasks() {
        return this.tasks;
    }
    
}
