package twofive.utils;

import org.junit.jupiter.api.Test;
import twofive.exception.*;
import twofive.task.Deadline;
import twofive.task.Event;
import twofive.task.Task;
import twofive.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileParserTest {
    @Test
    public void fileParserTest() throws EmptyStartTimeException, TaskDoneException, EmptyDescriptionException, EmptyEndTimeException, FileNotFoundException, InvalidTaskTypeException, EmptyDeadlineException {
        File sampleTaskFile = new File("junit-fileparser-test.txt");
        FileParser fileParser = new FileParser(sampleTaskFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ArrayList<Task> sampleTasks = new ArrayList<>();
        Task todoTask = new ToDo("read book");
        sampleTasks.add(todoTask);

        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        sampleTasks.add(deadlineTask);

        LocalDateTime startTime = LocalDateTime.parse("2023-08-06 14:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2023-08-06 16:00", formatter);
        Task eventTask = new Event("project meeting", startTime, endTime);
        sampleTasks.add(eventTask);

        assertEquals(sampleTasks, fileParser.parseFile());
    }
}
