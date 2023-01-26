import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
        public static void main (String[]args) {
            Tasklist list = new Tasklist();
            try {
                Storage.loadFile(list);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

                Ui.greet();
                Scanner sc = new Scanner(System.in);
                //echo

                while (true) {
                    try {
                    String input = sc.nextLine();

                    if (Parser.is_Bye(input)) {
                        Ui.displayByeMessage();
                        break;
                    }

                    //list
                    else if (Parser.is_List(input)) {
                        Duke.executeList(list);
                    }

                    //mark
                    else if (Parser.is_Mark(input)) {
                        Duke.executeMark(input, list);
                    }

                    //unmark
                    else if (Parser.is_Unmark(input)) {
                        Duke.executeUnmark(input, list);
                    }

                    //delete
                    else if (Parser.is_Delete(input)) {
                        Duke.executeDelete(input, list);
                    }

                    //todo
                    else if (Parser.is_toDo(input)) {
                        Duke.executeToDo(input, list);
                    }

                    //deadline
                    else if (Parser.is_Deadline(input)) {
                        Duke.executeDeadline(input, list);
                    }

                    //event
                    else if (Parser.is_Event(input)) {
                        Duke.executeEvent(input, list);
                    } else {
                        System.out.println("Invalid Input!");
                        //throw new DukeException("Invalid Input!");
                    }
                    try {
                        Storage.saveToFile(list);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                sc.close();
    }

        public static void executeList(Tasklist list) {
            Ui.displayList(list);
        }

        public static void executeMark(String input, Tasklist list) {
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
                list.get(index - 1).mark();
            } else {
                Ui.displayInvalidIndexMessage();
            }
        }

        public static void executeUnmark(String input, Tasklist list) {
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
            list.get(index - 1).unmark();
            } else {
            Ui.displayInvalidIndexMessage();
            }
        }

        public static void executeDelete(String input, Tasklist list) {
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
            Ui.displayDelete(index - 1, list);
            } else {
            Ui.displayInvalidIndexMessage();
            }
        }

    public static void executeDeadline(String input, Tasklist list) throws DukeException {
        Deadline deadline = Parser.parseDeadline(input);
        list.add(deadline);
        Ui.displayAddTask(list.get(list.size() - 1), list);
    }

    public static void executeEvent(String input, Tasklist list) throws DukeException {
        Event event = Parser.parseEvent(input);
        list.add(event);
        Ui.displayAddTask(list.get(list.size() - 1), list);
        }

    public static void executeToDo(String input, Tasklist list) {
        String todo = Parser.getTodo(input);
        list.add(new ToDo(todo));
        Ui.displayAddTask(list.get(list.size() - 1), list);
    }








}
