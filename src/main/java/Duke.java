import javax.swing.*;
import java.security.spec.ECField;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.io.*;
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private static String FILEPATH = "./data/duke.txt";

    private enum DukeCommand {
        BYE, LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        LISTDATE

    }


    private void inputResponse() {

        while (true) {
            String input;
            input = this.sc.nextLine();
            String[] userInput = input.split(" ", 2);
            String dukeQuery = userInput[0];
            DukeCommand dukeCommand= DukeCommand.valueOf(dukeQuery.toUpperCase());

            try {
                switch (dukeCommand) {
                    case BYE :
                        this.storeData();
                        System.out.println("\tBye. Hope to see you again soon!");
                        printLine();
                        this.sc.close();
                        return;
                    case LIST :
                        this.displayList();
                        break;
                    case MARK :
                        this.markComplete(userInput);
                        break;
                    case UNMARK :
                        this.markInComplete(userInput);
                        break;
                    case TODO :
                        this.addTodo(userInput);
                        break;
                    case DEADLINE :
                        this.addDeadline(userInput);
                        break;
                    case EVENT :
                        this.addEvent(userInput);
                        break;
                    case DELETE :
                        this.deleteTask(userInput);
                        break;
                    case LISTDATE:
                        this.displayTasksWithDates(userInput);
                        break;
                    default :
                        throw new DukeInvalidCommandException();

                }
            } catch (DukeException e) {
                System.out.printf("\t%s\n", e);
            } catch (IOException e) {
                System.out.println("Error while storing data..");
            }
            this.printLine();
        }

    }

    private void displayList() {
        int listSize = this.list.size();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1).toString());
        }
    }

    private void markComplete(String[] userInput) throws DukeInvalidArgumentsException, DukeMissingArgumentException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.size()) {
                throw new DukeTaskArgumentException();
            }
            if (list.get(taskIndex - 1).getStatus()) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeMarked = this.list.get(taskIndex - 1);
            taskToBeMarked.changeStatus();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + taskToBeMarked.toString());
        } catch (NumberFormatException e){
            throw new DukeInvalidArgumentsException();
        } catch (IndexOutOfBoundsException e) {
            String task = "mark";
            throw new DukeMissingArgumentException(task);
        }
    }

    private void markInComplete(String[] userInput) throws DukeMissingArgumentException, DukeInvalidArgumentsException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.size()) {
                throw new DukeTaskArgumentException();
            }
            if (list.get(taskIndex - 1).getStatus() == false) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeUnmarked = this.list.get(taskIndex - 1);
            taskToBeUnmarked.changeStatus();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + taskToBeUnmarked.toString());
        }  catch(IndexOutOfBoundsException e) {
            String task = "unmark";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private void addTodo(String[] userInput) throws DukeMissingArgumentException{
        try {
            Todo todo = new Todo(userInput[1]);
            this.list.add(todo);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + todo.toString());
            displayTasks();
        } catch (IndexOutOfBoundsException e) {
            String task = "todo";
            throw new DukeMissingArgumentException(task);
        }

    }

    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            String deadlineInfo[] = userInput[1].split("/by");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String deadlineText = deadlineInfo[0].trim();
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineInfo[1].trim(), formatter);


            Deadlines deadline = new Deadlines(deadlineText, deadlineDate);
            this.list.add(deadline);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + deadline.toString());
            displayTasks();
        } catch(IndexOutOfBoundsException e) {
            String task = "deadline";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            System.out.println("\tInvalid Date format (Required format: DD/MM/YYYY HH:MM)");
        }
    }

    private void addEvent(String[] userInput) throws DukeMissingArgumentException{
        try {
            String eventInfo[] = userInput[1].split("/from|/to");
            String eventText = eventInfo[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime eventFrom = LocalDateTime.parse(eventInfo[1].trim(), formatter);
            LocalDateTime eventTo = LocalDateTime.parse(eventInfo[2].trim(), formatter);

            Event event = new Event(eventText, eventFrom, eventTo);
            this.list.add(event);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + event.toString());
            displayTasks();
        } catch (IndexOutOfBoundsException e) {
            String task = "event";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            System.out.println("\tInvalid Date format (Required format: DD/MM/YYYY HH:MM)");
        }
    }

    private void displayTasks() {
        int listSize = list.size();
        System.out.println(String.format("\tNow you have %d tasks in the list.", listSize));
    }

    private void deleteTask(String[] userInput) throws DukeTaskArgumentException, DukeMissingArgumentException, DukeInvalidArgumentsException{
        try{
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.size()) {
                throw new DukeTaskArgumentException();
            }
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + this.list.get(taskIndex - 1).toString());
            this.list.remove(taskIndex - 1);
            displayTasks();
        } catch(IndexOutOfBoundsException e) {
            String task = "delete";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private void loadData() {
        File data = new File(this.FILEPATH);
        try {
            Scanner fileSc = new Scanner(data);
            while(fileSc.hasNextLine()) {
                String fileData = fileSc.nextLine();
                String[] taskData = fileData.split("\\|");
                readData(taskData);
            }
        } catch(FileNotFoundException e) {
            this.createFile();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void createFile() {
        File dir = new File("./data");
        File newFile = new File(this.FILEPATH);
        dir.mkdir();
        try{
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void readData(String[] taskData) throws DukeDataException {
        String taskType = taskData[0];
        String taskStatus = taskData[1];
        String taskInfo = taskData[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Task loadTask = null;

        if(taskType.equals("T")) {
            loadTask = new Todo(taskInfo);
        } else if (taskType.equals("D")) {
            LocalDateTime taskTime = LocalDateTime.parse(taskData[3].trim(), formatter);

            loadTask = new Deadlines(taskInfo, taskTime);
        } else if (taskType.equals("E")){
            LocalDateTime taskFrom = LocalDateTime.parse(taskData[3].trim(), formatter);
            LocalDateTime taskTo = LocalDateTime.parse(taskData[4].trim(), formatter);
            loadTask = new Event(taskInfo, taskFrom, taskTo);
        } else {
            throw new DukeDataException();
        }

        if(taskStatus.equals("1")){
            loadTask.changeStatus();
        }
        this.list.add(loadTask);
    }

    private void storeData() throws IOException{
        FileWriter writer = new FileWriter(this.FILEPATH, false);
        BufferedWriter buffer = new BufferedWriter(writer);
        for(int i = 0; i < this.list.size(); i++) {
            buffer.write(this.list.get(i).writeFile());
            buffer.newLine();
        }
        buffer.close();
        writer.close();
    }

    private void displayTasksWithDates(String[] userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userInput[1], formatter);
        int counter = 1;
        for(int i = 0; i < this.list.size(); i++) {
            String taskType = this.list.get(i).getTaskType();
            LocalDate taskDate = this.list.get(i).getDate().toLocalDate();
            if(taskType.equals("D") || taskType.equals(("E"))){
                if(date.equals(taskDate)) {
                    System.out.println(String.format("\t%d. %s", counter, this.list.get(i).toString()));
                }
            }

        }
    }

    private void printLine() {
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args)  {
        Duke dukeObj = new Duke();
        dukeObj.loadData();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        dukeObj.printLine();
        dukeObj.inputResponse();
    }
}
