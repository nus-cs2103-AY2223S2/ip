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
 * @version 1.0
 * @since 17 January 2023
 */
public class Babe {

    /**
     * A TaskList object for this Babe.
     */
    public TaskList taskList = new TaskList();

    public static void main(String[] args) {

        Babe chatBot = new Babe();
        Ui.welcomeUser();
        Storage.initializeStorage(chatBot.taskList);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                ArrayList<String> parseOutputs = Parser.parse(scanner.nextLine());

//                String instruction = chatBot.userInput.get(0);
//                int inputLength = chatBot.userInput.size();

                if (parseOutputs.get(0).equals("bye")) {
                    scanner.close();
                    Ui.sayBye();
                    System.exit(0);

                } else if (parseOutputs.get(0).equals("list")) {
                    Ui.printList(chatBot.taskList);

                } else if (parseOutputs.get(0).equals("mark")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    chatBot.taskList.changeStatus(true, index);
                    Storage.save(chatBot.taskList);

                } else if (parseOutputs.get(0).equals("unmark")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    chatBot.taskList.changeStatus(false, index);
                    Storage.save(chatBot.taskList);

                } else if (parseOutputs.get(0).equals("todo")) {
                    chatBot.taskList.addToDo(parseOutputs.get(1), true);
                    Storage.save(chatBot.taskList);

                } else if (parseOutputs.get(0).equals("deadline")) {
                    chatBot.taskList.addDeadline(parseOutputs.get(1), parseOutputs.get(2), true);
                    Storage.save(chatBot.taskList);
                } else if (parseOutputs.get(0).equals("event")) {

                    chatBot.taskList.addEvent(parseOutputs.get(1),
                            parseOutputs.get(2),
                            parseOutputs.get(3),
                            true);
                    Storage.save(chatBot.taskList);

                } else if (parseOutputs.get(0).equals("delete")) {
                    int index = Integer.parseInt(parseOutputs.get(1));
                    chatBot.taskList.deleteTask(index);
                    Storage.save(chatBot.taskList);

                } else {
                    throw new NonsenseInputException();
                }
            } catch (Exception e) {
                Ui.notifyException(e);
            }
        }
    }

}
