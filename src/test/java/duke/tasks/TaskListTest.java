package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {
    private static TaskList testList;

    public void initTestList() {
        if (testList == null) {
            testList = new TaskList();
            testList.add(new ToDo("test todo", false));
            testList.add(new Deadline("test deadline", "1999/11/19", false));
            testList.add(new Event("test event", "2023/01/01", "2023/12/31", true));
            testList.add(new ToDo("read book", false));
            testList.add(new ToDo("return book", true));
        }
    }

    @Test
    public void testStringConversion() {
        initTestList();

        String expectedString = "1. " + testList.get(0).toString() + "\n"
                + "2. " + testList.get(1).toString() + "\n"
                + "3. " + testList.get(2).toString() + "\n"
                + "4. " + testList.get(3).toString() + "\n"
                + "5. " + testList.get(4).toString();

        assertEquals(expectedString, testList.toString());
    }

    @Test
    public void testFindTask() {
        initTestList();

        String findKeyword = "book";
        assertEquals(testList.get(3), testList.find(findKeyword).get(0));
        assertEquals(testList.get(4), testList.find(findKeyword).get(1));
    }

    @Test
    public void testCountTasks() {
        initTestList();

        assertEquals(5, testList.countTasks());
    }

}
