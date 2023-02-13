package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void emptyListTest(){
        TaskList emptyList = new TaskList();
        assertEquals(emptyList.getSize(), 0);
        assertEquals(emptyList.toString(), "Empty List");
    }

    @Test
    public void addTaskTest() {
        TaskList lst = new TaskList();
        lst.addTask();

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[  ] [Priority Level: LOW] Test\n");

        lst.addTask();
        assertEquals(lst.getSize(), 2);
        assertEquals(lst.toString(), "[  ] [Priority Level: LOW] Test\n" +
                "[  ] [Priority Level: LOW] Test\n");
    }

    @Test
    public void deleteTaskTest() {
        TaskList lst = new TaskList();
        lst.addTask();
        lst.addTask();
        lst.deleteTask(1);

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[  ] [Priority Level: LOW] Test\n");

        lst.deleteTask(1);
        assertEquals(lst.getSize(), 0);
        assertEquals(lst.toString(), "Empty List");

        lst.addTask();
        lst.addTask();
        lst.deleteTask(2);

        assertEquals(lst.getSize(), 1);
        assertEquals(lst.toString(), "[  ] [Priority Level: LOW] Test\n");

        lst.deleteTask(1);
        assertEquals(lst.getSize(), 0);
        assertEquals(lst.toString(), "Empty List");
    }

    @Test
    public void findTest() {
        TaskList lst = new TaskList();
        lst.addTask();
        String goalFoundOne = "Finding in progress...\n" +
                "Found 1 task with this keyword\n" + "1. [  ] [Priority Level: LOW] Test\n";

        String goalFoundNone = "Finding in progress...\n" +
                "Didn't manage to find any tasks with this keyword!\n";

        String goalFoundTwo = "Finding in progress...\n" +
                "Found 2 tasks with this keyword\n" + "1. [  ] [Priority Level: LOW] Test\n" +
                "2. [  ] [Priority Level: LOW] Dummy test\n";

        String goalFoundDummy = "Finding in progress...\n" +
                "Found 1 task with this keyword\n" + "1. [  ] [Priority Level: LOW] Dummy test\n";

        assertEquals(lst.find("Test"), goalFoundOne);
        assertEquals(lst.find("est"), goalFoundOne);
        assertEquals(lst.find("test"), goalFoundNone);
        assertEquals(lst.find("dummy"), goalFoundNone);
        lst.deleteTask(1);
        assertEquals(lst.find("Test"), goalFoundNone);

        lst.addTask();
        lst.addDummyTask();

        assertEquals(lst.find("Test"), goalFoundOne);
        assertEquals(lst.find("est"), goalFoundTwo);
        assertEquals(lst.find(" "), goalFoundDummy);

        lst.deleteTask(1);
        assertEquals(lst.find("est"), goalFoundDummy);

        lst.deleteTask(1);
        assertEquals(lst.find("est"), goalFoundNone);
    }
}

