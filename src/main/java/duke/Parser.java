package duke;

public class Parser {
    public boolean checkEnd(String command) {
        if (command.equalsIgnoreCase("bye")) {
            return true;
        }
        return false;
    }

    public void parse(String command, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            // check if user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                ui.showGoodbye();
            }
            // check if user inputs "list" and display items in list
            else if (command.equalsIgnoreCase("list")) {
                if (taskList.getSize() == 0) {
                    System.out.println("Your list is empty!");
                } else {
                    for (int i = 0; i < taskList.getSize(); i++) {
                        System.out.println(i + 1 + ". " + taskList.getTask(i));
                    }
                }
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                try {
                    // Mark task as done
                    if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("mark")) {
                        int index = Integer.parseInt(command.replaceAll("mark ", "")) - 1;
                        if (taskList.getTask(index).isDone()) {
                            throw new DukeException("marked");
                        } else {
                            taskList.getTask(index).mark();
                        }
                    }
                    // Unmark task
                    else if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("unmark")) {
                        int index = Integer.parseInt(command.replaceAll("unmark ", "")) - 1;
                        if (taskList.getTask(index).isDone()) {
                            taskList.getTask(index).unmark();
                        } else {
                            throw new DukeException("unmarked");
                        }
                    }
                } catch (DukeException e) {
                    e.MarkedException();
                }
            }
            // Delete a task
            else if (command.toLowerCase().startsWith("delete ")) {
                int index = Integer.parseInt(command.replaceAll("delete ", "")) - 1;
                Task tempTask = taskList.getTask(index);
                taskList.deleteTask(index);
                System.out.println("Noted. I've removed this task:" + "\n" + tempTask.toString());
            } else {
                try {
                    Task newTask;

                    // Create new ToDo task
                    if (command.toLowerCase().startsWith("todo ")) {
                        String description = command.substring(command.indexOf(" ") + 1);

                        if (description.equals("")) {
                            throw new DukeException("todo");
                        } else {
                            newTask = new ToDo(command.substring(command.indexOf(" ") + 1));
                        }
                    }
                    // Create new Deadline task
                    else if (command.toLowerCase().startsWith("deadline ")) {
                        String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
                        String by = command.substring(command.indexOf("/by") + 4);

                        System.out.println(description + " - " + by);

                        if (description.equals("")) {
                            throw new DukeException("deadline");
                        } else if (by.equals("")) {
                            throw new DukeException("empty time");
                        } else {
                            newTask = new Deadline(description, by);
                        }
                    }
                    // Create new Event task
                    else if (command.toLowerCase().startsWith("event ")) {
                        String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/from"));
                        String from = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
                        String to = command.substring(command.indexOf("/to") + 4);

                        if (description.equals("")) {
                            throw new DukeException("event");
                        } else if (from.equals("") || to.equals("")) {
                            throw new DukeException("empty time");
                        } else {
                            newTask = new Event(description, from, to);
                        }
                    } else {
                        throw new DukeException("wrong");
                    }
                    taskList.addTask(newTask);
                    ui.showTaskAdded(newTask);
                } catch (DukeException e) {
                    e.WrongCommandException();
                    e.EmptyDescriptionException();
                    e.EmptyTimeException();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            storage.writeToFile(taskList);
        }
    }
}

