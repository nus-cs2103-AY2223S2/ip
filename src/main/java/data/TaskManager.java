package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import errors.DukeInvalidCommandException;
import task.Event;
import task.Task;
import timeslot.FreeTimeBlock;
import ui.Response;


/**
 * Storage medium to manage tasks created by the user
 * @author Nicholas Lee
 */
public class TaskManager {

    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {}

    /**
     * Gets the list of the users tasks
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Used to mark a task as complete.
     *
     * @param index the index of the task to mark as complete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void markTaskComplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(true);
    }

    /**
     * Used to mark a task incomplete.
     *
     * @param index the index of the task to mark as complete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void markTaskIncomplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(false);
    }



    /**
     * This method is used to add a task from the task list.
     *
     * @param newTask A Task object
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }


    /**
     * This method is used to delete a task from the task list.
     *
     * @param index the index of the task to delete
     * @throws DukeInvalidCommandException if the task is not found
     */
    public void deleteTask(int index) throws DukeInvalidCommandException {
        try {
            this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
    }

    /**
     * Filters tasks in the main TaskManager based on the provided keyword by the user.
     * @param keyword The keyword to filter the tasks by.
     * @return A new TaskManager object containing only the tasks that contain
     *     the keyword as a substring their details.
     */
    public TaskManager filterTasks(String keyword) {
        TaskManager taskView = new TaskManager();
        for (Task task: this.taskList) {
            String details = task.getDetails();
            if (details.contains(keyword)) {
                taskView.addTask(task);
            }
        }
        return taskView;
    }

    private ArrayList<Event> getEventsAfterNow() {
        LocalDateTime currentTime = LocalDateTime.now();
        // note that this is temporary
        LocalDateTime dateTime = LocalDateTime.of(2023, 2, 10, 9, 30);
        return taskList.stream()
                .filter(task -> {
            if (task instanceof Event) {
                LocalDateTime endTime = ((Event) task).getEndTime();
                return endTime.compareTo(dateTime) > 0;
            }
            return false;
        }).map(task -> (Event) task)
                .sorted(Comparator.comparing(Event::getStartTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public ArrayList<FreeTimeBlock> getFreeTimes(int desiredFreeTime) {
        ArrayList<Event> eventsList = getEventsAfterNow();
//        eventsList.forEach(event -> System.out.println(event.toString()));
        ArrayList<FreeTimeBlock> freeTimeBlocks = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.of(2023, 2, 10, 9, 30);

        if (
                (eventsList.size() > 0) &&
                (eventsList.get(0)
                .getStartTime()
                .compareTo(dateTime) > 0)) {
            freeTimeBlocks.add(new FreeTimeBlock(null, eventsList.get(0).getStartTime()));
        }

        if (eventsList.size() < 2) {

            if (eventsList.isEmpty()) {
                freeTimeBlocks.add(new FreeTimeBlock(null, null));
                freeTimeBlocks.forEach(freeTimeBlock -> System.out.println(freeTimeBlock.toString()));
                return freeTimeBlocks;
            }

            Event event = eventsList.get(0);
            freeTimeBlocks.add(new FreeTimeBlock(event.getEndTime(), null));
            freeTimeBlocks.forEach(freeTimeBlock -> System.out.println(freeTimeBlock.toString()));
            return freeTimeBlocks;
        }

        LocalDateTime freeBlockStart = eventsList.get(0).getEndTime();
        assert freeBlockStart != null;
        LocalDateTime freeBlockEnd;

        for (Event event: eventsList.subList(1, eventsList.size())) {

            if (freeBlockStart.compareTo(event.getStartTime()) < 0) {
                freeBlockEnd = event.getStartTime();
                freeTimeBlocks.add(new FreeTimeBlock(freeBlockStart, freeBlockEnd));
            }

            if (event.getEndTime().compareTo(freeBlockStart) > 0) {
                freeBlockStart = event.getEndTime();
            }
        }

        freeTimeBlocks.add(new FreeTimeBlock(freeBlockStart, null));

        //        filteredFreeTimeBlocks.forEach(freeTimeBlock -> System.out.println(freeTimeBlock.toString()));

        return freeTimeBlocks.stream()
                .filter((freeTimeBlock -> freeTimeBlock.isValidSlot(desiredFreeTime)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}