package babe;

import java.util.ArrayList;
import java.util.Scanner;

import babe.exception.NonsenseInputException;

/**
 * <h1> Hi Babe! </h1>
 * Babe is a chat bot created as part of the individual project (iP) under the course CS2109T.
 * It is a duplicate of Duke with some personal flair.
 *
 * @author Shan Hern Hng
 * @version 2.0
 * @since 17 January 2023
 */
public class Babe {

    /**
     * A TaskList object for this Babe.
     */
    private TaskList taskList = new TaskList();

    /**
     * Displays a Welcome message to users of Babe.
     *
     * @return A String that is the welcome message displayed.
     */
    public String welcomeUser() {
        // Displays Welcome message
        return Ui.welcomeUser();
    }

    /**
     * Checks whether there is an existing save file, and creates it if it does not exist.
     * This method will notify the user in the event it has to create a save file.
     *
     * @return A String that is the notification for creation of save file.
     */
    public String initializeStorage() {
        String result = "";
        boolean isFilePresent = Storage.initializeStorage(this.taskList);
        if (!isFilePresent) {
            result = Ui.notifyCreateSaveFile();
        }
        return result;

    }

    /**
     * Processes user's input and executes commands specified by user's input to produce a response.
     *
     * @param input A String that is the user's input.
     * @return A String that is the message displaying the result of the user's command.
     */
    public String getResponse(String input) {
        String output = "";

        try {
            ArrayList<String> parseOutputs = Parser.parse(input);
            String instruction = parseOutputs.get(0);

            if (instruction.equals("bye")) {
                output = Ui.sayBye();
                System.exit(0);

            } else if (instruction.equals("list")) {
                output = Ui.printList(this.taskList);

            } else if (instruction.equals("mark")) {
                int index = Integer.parseInt(parseOutputs.get(1));
                String taskString = this.taskList.markTask(index);
                Storage.save(this.taskList);
                output = Ui.notifyMark(taskString);

            } else if (instruction.equals("unmark")) {
                int index = Integer.parseInt(parseOutputs.get(1));
                String taskString = this.taskList.unmarkTask(index);
                Storage.save(this.taskList);
                output = Ui.notifyUnmark(taskString);

            } else if (instruction.equals("todo")) {
                String taskString = this.taskList.addToDo(parseOutputs.get(1), false);
                Storage.save(this.taskList);
                output = Ui.notifyAddTask(taskString, this.taskList.length());

            } else if (instruction.equals("deadline")) {
                String taskString = this.taskList.addDeadline(parseOutputs.get(1), parseOutputs.get(2), false);
                Storage.save(this.taskList);
                output = Ui.notifyAddTask(taskString, this.taskList.length());

            } else if (instruction.equals("event")) {
                String taskString = this.taskList.addEvent(parseOutputs.get(1),
                        parseOutputs.get(2),
                        parseOutputs.get(3),
                        false);
                Storage.save(this.taskList);
                output = Ui.notifyAddTask(taskString, this.taskList.length());

            } else if (instruction.equals("delete")) {
                ArrayList<String> indices = new ArrayList<>(parseOutputs.subList(1, parseOutputs.size()));
                ArrayList<String> removedTasks = this.taskList.deleteTasks(indices);
                Storage.save(this.taskList);
                output = Ui.notifyDelete(removedTasks, this.taskList.length());

            } else if (instruction.equals("find")) {
                String searchKey = parseOutputs.get(1);
                ArrayList<String> foundTasks = this.taskList.findTasks(searchKey);
                output = Ui.notifyFindResults(foundTasks);

            } else {
                throw new NonsenseInputException();
            }
        } catch (Exception e1) {
            output = Ui.notifyException(e1);
        } catch (AssertionError e2) {
            output = Ui.notifyError(e2);
        }

        return output;
    }


    public static void main(String[] args) {

        Babe babe = new Babe();
        Ui.welcomeUser();
        boolean isFilePresent = Storage.initializeStorage(babe.taskList);
        if (!isFilePresent) {
            Ui.notifyCreateSaveFile();
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            babe.getResponse(scanner.nextLine());
        }
    }

}
