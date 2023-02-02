package duke.functions;

import duke.exceptions.*;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    public void handleInput(String input) throws InvalidCommandException {
        String[] split = input.split(" ", 2);
        String cmd = split[0];
        switch (cmd) {
            case "list":
                System.out.println(this.list.toString());
                break;
            case "mark":
                mark(split);
                break;
            case "unmark":
                unmark(split);
                break;
            case "delete":
                delete(split);
                break;
            case "deadline":
                deadline(split);
                break;
            case "event":
                event(split);
                break;
            case "todo":
                todo(split);
                break;
            case "bye":
                bye(split);
                break;
            case "find":
                find(split);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void parseDatabase(Scanner fileReader, TaskList dl) {
        int index = -1;
        while (fileReader.hasNextLine()) {
            String input = fileReader.nextLine();
            String[] split = input.split("\\|");
            String cmd = split[0];
            String status = split[1];
            String taskName = split[2];
            index++;
            switch (cmd) {
                case "T":
                    dl.insertToDo(taskName, true);
                    if (status.equals("X")) {
                        dl.mark(index);
                    }
                    break;
                case "D":
                    String deadline = "a";
                    dl.insertDeadline(taskName, deadline, true);
                    if (status.equals("X")) {
                        dl.mark(index);
                    }
                    break;
                case "E":
                    String startTime = split[3];
                    String endTime = split[3];
                    dl.insertEvent(taskName, startTime, endTime, true);
                    if (status.equals("X")) {
                        dl.mark(index);
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void mark(String[] split) throws InvalidCommandException {
        try {
            if (split.length > 1) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            this.list.mark(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            System.out.println("Please input a number after the command.");
        }
    }

    private void unmark(String[] split) throws InvalidCommandException {
        try {
            if (split.length > 1) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            this.list.unMark(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            System.out.println("Please input a number after the command.");
        }
    }

    private void delete(String[] split) {
        try {
            if (split.length > 1) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            this.list.deleteTask(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            System.out.println("Please input a number after the command.");
        }
    }

    private void deadline(String[] split) {
        try {
            String[] secondSplit = split[1].split("/by ");
            if (secondSplit.length != 2) {
                throw new InvalidArgumentsException();
            }
            this.list.insertDeadline(secondSplit[0], secondSplit[1]);
        } catch (InvalidArgumentsException | DateTimeFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void event(String[] split) {
        try {
            String[] secondSplit = split[1].split("/from |/to ", 3);
            System.out.println(secondSplit);
            if (secondSplit.length != 3) {
                throw new InvalidArgumentsException();
            }
            this.list.insertEvent(secondSplit[0], secondSplit[1], secondSplit[2]);
        } catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void todo(String[] split) {
        try {
            if (split.length != 2) {
                throw new InvalidArgumentsException();
            }
            String todoName = split[1];
            this.list.insertToDo(todoName);
        } catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void find(String[] split) {
        try {
            if (split.length != 2) {
                throw new InvalidArgumentsException();
            }
            String name = split[1];
            ArrayList<Task> results = this.list.findMatchingTasks(name);
            if (!results.isEmpty()) {
                System.out.println("Here are the tasks in your list:");
                int index = 0;
                for (Task task : results) {
                    System.out.printf("%d.%s%n", index, task);
                    index++;
                }
            } else {
                System.out.println("There are no matching tasks currently!");
            }
        }catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void bye(String[] split) throws InvalidCommandException {
        try {
            if (split.length != 1) {
                throw new InvalidArgumentsException();
            }
            exit();
        } catch (InvalidArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void exit() {
        String exitMsg = Ui.format("Bye. Come back soon!");
        System.out.println(exitMsg);
        System.exit(1);
    }
}
