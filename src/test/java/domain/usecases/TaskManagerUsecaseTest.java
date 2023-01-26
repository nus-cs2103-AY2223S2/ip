package domain.usecases;

import core.exceptions.InvalidArgumentException;
import core.utils.fp.ThrowingFunction;
import domain.entities.DataSaver;
import domain.entities.core.ExitStatus;
import domain.entities.core.IdentifiedCommandable;
import domain.entities.core.Writable;
import domain.entities.taskmanager.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class TaskManagerUsecaseTest {
    @Mock
    private Writable writer;

    @Mock
    private DataSaver dataSaver;

    @Mock
    private Writable errorWriter;

    @Mock
    private ArrayList<Task> tasks;

    private TaskManagerUsecase sut;

    @BeforeEach
    public void setUp() {
        sut = new TaskManagerUsecase(writer, tasks, dataSaver, errorWriter);
    }

    @Test
    void getAddTaskCommand_invocation_shouldReturnCommandableWithCorrectId() {
        final String id = "add";
        final IdentifiedCommandable command = sut.getAddTaskCommand(
                (list) -> Mockito.mock(Task.class), id
        );
        assert command.getId().equals(id);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAddTaskCommand_invocation_shouldInvokeTaskSupplier() {
        final String id = "add";
        final String[] tokens = {"hello", "world"};
        final ThrowingFunction<String[], Task, InvalidArgumentException>
                taskSupplier = Mockito.mock(ThrowingFunction.class);
        final IdentifiedCommandable command = sut.getAddTaskCommand(
                taskSupplier, id
        );
        command.execute(tokens);
        Assertions.assertDoesNotThrow(() -> Mockito.verify(taskSupplier, Mockito.times(1)).apply(tokens));
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAddTaskCommand_invocation_shouldWriteMessageToErrorWriterOnException() throws InvalidArgumentException {
        final String id = "add";
        final String[] tokens = {"hello", "world"};
        final ThrowingFunction<String[], Task, InvalidArgumentException>
                taskSupplier = Mockito.mock(ThrowingFunction.class);
        Mockito.doThrow(new InvalidArgumentException("Message")).when(taskSupplier).apply(tokens);
        final IdentifiedCommandable command = sut.getAddTaskCommand(
                taskSupplier, id
        );
        command.execute(tokens);
        Mockito.verify(errorWriter, Mockito.times(1)).writeln(Mockito.anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAddTaskCommand_invocation_shouldReturnFinishCurrentIterationOnException() throws InvalidArgumentException {
        final String id = "add";
        final String[] tokens = {"hello", "world"};
        final ThrowingFunction<String[], Task, InvalidArgumentException>
                taskSupplier = Mockito.mock(ThrowingFunction.class);
        Mockito.doThrow(new InvalidArgumentException("Message")).when(taskSupplier).apply(tokens);
        final IdentifiedCommandable command = sut.getAddTaskCommand(
                taskSupplier, id
        );
        Assertions.assertEquals(ExitStatus.finishCurrentIteration, command.execute(tokens));
    }

    @Test
    void getAddTaskCommand_invocation_shouldAddTaskToTasks() {
        final String id = "add";
        final String[] tokens = {"hello", "world"};
        final Task task = Mockito.mock(Task.class);
        final IdentifiedCommandable command = sut.getAddTaskCommand(
                (list) -> task, id
        );
        command.execute(tokens);
        Mockito.verify(tasks, Mockito.times(1)).add(task);
    }

    @Test
    void getListTodoCommand_invocation_shouldReturnCommandableWithCorrectId() {
        final String id = "list";
        final IdentifiedCommandable command = sut.getListTodoCommand();
        Assertions.assertEquals(id, command.getId());
    }

    @Test
    void getListTodoCommand_invocation_shouldWriteTasksToWriter() {
        final String id = "list";
        final int count = 5;
        final IdentifiedCommandable command = sut.getListTodoCommand();
        final Task task = Mockito.mock(Task.class);

        Mockito.when(tasks.size()).thenReturn(count);
        Mockito.when(tasks.get(Mockito.anyInt())).thenReturn(task);
        Mockito.when(task.toString()).thenReturn("Task");

        command.execute(new String[]{});
        Mockito.verify(writer, Mockito.times(count)).writeln(Mockito.anyString());
    }

    @Test
    void getListTodoCommand_invocation_shouldReturnFinishCurrentIteration() {
        final String id = "list";
        final IdentifiedCommandable command = sut.getListTodoCommand();
        Assertions.assertEquals(ExitStatus.finishCurrentIteration, command.execute(new String[]{}));
    }
}
