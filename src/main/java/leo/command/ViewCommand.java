package leo.command;

import leo.leoexception.LeoException;
import leo.leoexception.NoDateFoundException;
import leo.leoexception.NoTaskFoundException;
import leo.storage.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewCommand extends Command {

    public ViewCommand(Storage s, String c) {
        super(s,c);
    }

    @Override
    public String execute() throws LeoException {
        try {
            String date = command.substring(5);
            LocalDate day = convertString(date);
            TaskList view = viewTask(storage, day);
            String response = "Here is what you have on " + convertToString(day) + ":\n";
            return response + view.display();
        } catch (IndexOutOfBoundsException e) {
            throw new NoDateFoundException();
        }
    }

    private LocalDate convertString(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(str, formatter);
    }

    public TaskList viewTask(Storage s, LocalDate d) throws NoTaskFoundException {
        List<Task> viewList = new ArrayList<>();
        int dataLength = s.getDataLength();
        if (dataLength == 0) {
            throw new NoTaskFoundException();
        }

        for (int i = 0; i < dataLength; i++) {
            Task t = s.getTask(i);
            if (t.getType() == TaskType.DEADLINE) {
                DeadlineTask task = (DeadlineTask) t;
                if (task.sameDay(d)) {
                    viewList.add(task);
                }
            } else if (t.getType() == TaskType.EVENT) {
                EventTask task = (EventTask) t;
                if (task.withinDate(d)) {
                    viewList.add(task);
                }
            }
        }

        return new TaskList(viewList);
    }

    public String convertToString(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd");
        return formatter.format(d);
    }
}
