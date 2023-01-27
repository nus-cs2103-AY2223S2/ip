package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class TaskList {
    Ui userInterface = new Ui();

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public void showList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int no = i + 1;
            System.out.println(no + "." + tasks.get(i));
        }
    }

    public void mark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size()) {
            throw new TaskNotExist();
        }
        userInterface.setMarkAsDone();
        taskArrayList.get(index).mark();
        System.out.println(taskArrayList.get(index).toString());
    }

    public void unMark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size()) {
            throw new TaskNotExist();
        }
        userInterface.setUnMarkTask();
        taskArrayList.get(index).unmarked();
        System.out.println(taskArrayList.get(index).toString());
    }

    public void toDo(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (description == null) {
            throw new MissingDescription();
        }
        Task newTask = new ToDo(description);
        userInterface.setAddedTask();
        taskArrayList.add(newTask);
        System.out.println(newTask);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
    }

    public void deadline(ArrayList<Task> taskArrayList, String inputType) throws MissingDescription {
        try {
            DateStringConverter converter = new DateStringConverter();
            if (!inputType.contains(" ")) {
                throw new MissingDescription();
            }
            String des = inputType.substring(inputType.indexOf(" ")).trim();
            String[] deadline = des.split("/by");
            String[] timeExists = deadline[1].trim().split(" ");
            if (timeExists.length > 1) {
                String dateInString = timeExists[0];
                String timeInString = timeExists[1];
                LocalDate date = converter.convertDateInput(dateInString);
                LocalTime time = converter.convertTimeInput(timeInString);
                Deadline dead = new Deadline(deadline[0].trim(), date, time);
                taskArrayList.add(dead);
                System.out.println(dead);
            } else {
                String dateInString = deadline[1].trim();
                LocalDate date = converter.convertDateInput(dateInString);
                Deadline dead = new Deadline(deadline[0].trim(), date);
                taskArrayList.add(dead);
                System.out.println(dead);
            }
            userInterface.setAddedTask();
            System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
        } catch (DateTimeException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty and please enter in this date format (YYYY-MM-DD OR YYYY/MM/DD) or " +
                    "Day of the week (E.g. Monday, Tuesday, Wednesday).\n" +
                    "If time is provided, please key in this format 1200 after entering the date");
        }
    }

    public void event(ArrayList<Task> taskArrayList, String inputType) throws MissingDescription {
        if (!inputType.contains(" ")) {
            throw new MissingDescription();
        }
        String des = inputType.substring(inputType.indexOf(" "));
        String[] events = des.split("/");
        Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
        taskArrayList.add(e);
        userInterface.setAddedTask();
        System.out.println(e);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");

    }

    public void delete(ArrayList<Task> taskArrayList, String inputType) {
        try {
            Storage storage = new Storage("./userRecords/duke.txt");
            if (!inputType.contains(" ")) {
                throw new DukeException("OOPS!! Please indicate the task index to delete!");
            }

            String[] index = inputType.split(" ");
            if (index[1].equalsIgnoreCase("all")) {
                storage.deleteAll(taskArrayList);
                System.out.println("Noted: I've removed all tasks");
            } else {
                int deleteIndex = Integer.parseInt(index[1]);
                if (deleteIndex > taskArrayList.size() || deleteIndex <= -1) {
                    throw new DukeException("OOPS!! The index requested to be deleted does not exist!");
                } else {
                    System.out.println("Noted: I've removed this task");
                    Task whichTask = taskArrayList.get(deleteIndex - 1);
                    System.out.println(whichTask);
                    taskArrayList.remove(deleteIndex - 1);
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println("Oh no either file or the task doesn't exist. Please try again");
        }
    }

    public void deadlineChecker(ArrayList<Task> taskArrayList, String inputType) {
        try {
            String[] index = inputType.split("/");
            DateStringConverter converter = new DateStringConverter();
            LocalDate deadline = converter.convertDateInput(index[1].trim());
            ArrayList<Deadline> deadlineTasks = checkDeadlineTask(taskArrayList, deadline);
            System.out.println("Here is the list before this deadline: " + deadline);
            for (int i = 0; i < deadlineTasks.size(); i++) {
                System.out.println(deadlineTasks.get(i));
            }
        } catch (Exception e) {
            System.out.println("I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD");
        }
    }

    private ArrayList<Deadline> checkDeadlineTask(ArrayList<Task> tasks, LocalDate date) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline singleTask = ((Deadline) tasks.get(i));
                String[] s = singleTask.toString().split(":");
                DateStringConverter converter = new DateStringConverter();
                LocalDate dueDate = converter.convertDateInput(s[1].replace(")", "").trim());
                if (dueDate.isBefore(date)) {
                    deadlineTasks.add(singleTask);
                }
            }
        }
        return deadlineTasks;
    }
}
