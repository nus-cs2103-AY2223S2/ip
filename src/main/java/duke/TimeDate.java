package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The class to convert date given in string to Date class
 */
public class TimeDate {
    private final String[] dayOfWeek = new String[]{"FRIDAY", "MONDAY", "SATURDAY",
        "SUNDAY", "THURSDAY", "TUESDAY", "WEDNESDAY"};

    private final String[] dayOfWeekAbbreviation = new String[]{"FRI", "MON", "SAT",
        "SUN", "THU", "TUE", "WED"};

    /**
     * A method to convert the String type to date type
     *
     * @param input a String input of date
     * @return A LocalDate object
     */
    public LocalDate convertDateInput(String input) {
        input.replace("/", "-");
        for (int i = 0; i < dayOfWeek.length; i++) {
            if (input.equalsIgnoreCase(dayOfWeek[i]) || input.equalsIgnoreCase(dayOfWeekAbbreviation[i])) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.with(dayOfWeek);
                if (nextDate.isBefore(today)) {
                    nextDate = nextDate.plusDays(7);
                }
                return nextDate;
            }
        }
        return LocalDate.parse(input);
    }

    /**
     * A method to convert a String type to a LocalTime type
     * @param input a String input of time
     * @return a LocalTime object converted from String type
     */
    public LocalTime convertTimeInput(String input) {
        String hour = input.substring(0, 2);
        String mins = input.substring(2, 4);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(mins));
        return time;
    }

    /**
     * A method to handle types of deadlines, task with date only or task with date and time
     * @param taskArrayList an arraylist containing all the tasks
     * @param description   a description of the task
     * @return Deadline object
     */
    public Deadline deadlineWithDateTime(ArrayList<Task> taskArrayList, String description) {
        TimeDate converter = new TimeDate();
        String des = description.substring(description.indexOf(" ")).trim();
        String[] deadline = des.split("/by");
        String[] timeExists = deadline[1].trim().split(" ");
        if (timeExists.length > 1) {
            String dateInString = timeExists[0];
            String timeInString = timeExists[1];
            LocalDate date = converter.convertDateInput(dateInString);
            LocalTime time = converter.convertTimeInput(timeInString);
            Deadline dead = new Deadline(deadline[0].trim(), date, time);
            taskArrayList.add(dead);
            return dead;
        } else {
            String dateInString = deadline[1].trim();
            LocalDate date = converter.convertDateInput(dateInString);
            Deadline dead = new Deadline(deadline[0].trim(), date);
            taskArrayList.add(dead);
            return dead;
        }
    }

    /**
     * A method to return a list of tasks before the deadline given
     * @param tasks an arraylist containing the list of tasks
     * @param date  indicating the deadline date the user wants
     * @return an arraylist of type deadline
     */
    public ArrayList<Deadline> checkDeadlineTask(ArrayList<Task> tasks, LocalDate date) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline singleTask = ((Deadline) tasks.get(i));
                String[] s = singleTask.toString().split(":");
                TimeDate converter = new TimeDate();
                LocalDate dueDate = converter.convertDateInput(s[1].replace(")", "").trim());
                if (dueDate.isBefore(date)) {
                    deadlineTasks.add(singleTask);
                }
            }
        }
        return deadlineTasks;
    }
}
