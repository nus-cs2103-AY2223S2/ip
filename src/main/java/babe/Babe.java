package babe;

import java.util.ArrayList;
import java.util.Scanner;

import babe.exception.NonsenseInputException;
import babe.task.Task;

/**
 * <h1> Hi Babe! </h1>
 * Babe is a chat bot created as part of the individual project (iP) under the course CS2109T.
 * It is a duplicate of Duke with some personal flair.
 *
 * @author Shan Hern Hng
 * @version 1.0
 * @since 17 January 2023
 */
public class Babe {

    /**
     * A TaskList object for this Babe.
     */
    private TaskList taskList = new TaskList();

    public static void main(String[] args) {

        Babe babe = new Babe();
        Ui.welcomeUser();
        Storage.initializeStorage(babe.taskList);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                ArrayList<String> parseOutputs = Parser.parse(scanner.nextLine());
                String instruction = parseOutputs.get(0);

                if (instruction.equals("bye")) {
                    scanner.close();
                    Ui.sayBye();
                    System.exit(0);

                } else if (instruction.equals("list")) {
                    Ui.printList(babe.taskList);

                } else if (instruction.equals("mark")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    babe.taskList.changeStatus(true, index);
                    Storage.save(babe.taskList);

                } else if (instruction.equals("unmark")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    babe.taskList.changeStatus(false, index);
                    Storage.save(babe.taskList);

                } else if (instruction.equals("todo")) {
                    babe.taskList.addToDo(parseOutputs.get(1), true);
                    Storage.save(babe.taskList);

                } else if (instruction.equals("deadline")) {
                    babe.taskList.addDeadline(parseOutputs.get(1), parseOutputs.get(2), true);
                    Storage.save(babe.taskList);
                    
                } else if (instruction.equals("event")) {
                    babe.taskList.addEvent(parseOutputs.get(1),
                            parseOutputs.get(2),
                            parseOutputs.get(3),
                            true);
                    Storage.save(babe.taskList);

                } else if (instruction.equals("delete")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    babe.taskList.deleteTask(index);
                    Storage.save(babe.taskList);

                } else if (instruction.equals("find")) {
                    String searchKey = parseOutputs.get(1);
                    ArrayList<Task> foundTasks = babe.taskList.findTasks(searchKey);
                    Ui.notifyFindResults(foundTasks);

                } else {
                    throw new NonsenseInputException();
                }
            } catch (Exception e) {
                Ui.notifyException(e);
            }
        }
    }

}
