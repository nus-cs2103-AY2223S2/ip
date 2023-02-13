package aqua.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import aqua.usertask.UserTask;


public class TaskManagerTest {
    @Test
    public void addTest() {
        TaskManager manager = new TaskManager();
        manager.add(new MarkedTask());
        assertEquals(
            "1. +",
            manager.toString()
        );
    }


    @Test
    public void markTest() {
        TaskManager manager = new TaskManager();
        manager.add(new MarkedTask());
        UserTask task = manager.mark(0, false);
        assertEquals("-", task.toString());
        assertEquals(
            "1. -",
            manager.toString()
        );
    }


    @Test
    public void deleteTest() {
        TaskManager manager = new TaskManager();
        manager.add(new MarkedTask());
        UserTask task = manager.delete(0).task;
        assertEquals("+", task.toString());
        assertEquals(
            "",
            manager.toString()
        );
    }


    @Test
    public void saveToFileTest() {
        TaskManager manager = new TaskManager();
        manager.add(new MarkedTask());
        Path path = manager.getSavePath();
        // delete previous save file
        if (path.toFile().exists()) {
            if (!path.toFile().delete()) {
                fail("Failed to delete previously saved file");
            }
        }
        // save
        try {
            manager.saveToFile();
        } catch (IOException ioEx) {
            fail(ioEx);
        }
        // check contents
        try (InputStream inStream = new FileInputStream(path.toFile())) {
            String actual = new String(inStream.readAllBytes());
            String expected = "+";
            assertEquals(expected, actual);
        } catch (FileNotFoundException fnfEx) {
            fail("Save file not created", fnfEx);
        } catch (IOException ioEx) {
            fail(ioEx);
        }
    }


    @Test
    public void filter_zeroMatch_emptyMap() {
        TaskManager manager = new TaskManager();
        manager.add(new MarkedTask());
        manager.add(new MarkedTask());
        manager.add(new UnMarkedTask());
        manager.add(new UnMarkedTask());
        manager.add(new UnMarkedTask());
        manager.add(new MarkedTask());
        manager.add(new MarkedTask());
        manager.add(new UnMarkedTask());
        LinkedHashMap<Integer, UserTask> map = manager.filter("44.5");
        assertTrue(map.isEmpty());
    }


    @Test
    public void filter_someMatch_filteredMap() {
        TaskManager manager = new TaskManager();
        /* 1 */ manager.add(new MarkedTask());
        /* 2 */ manager.add(new MarkedTask());
        /* 3 */ manager.add(new UnMarkedTask());
        /* 4 */ manager.add(new UnMarkedTask());
        /* 5 */ manager.add(new UnMarkedTask());
        /* 6 */ manager.add(new MarkedTask());
        /* 7 */ manager.add(new MarkedTask());
        /* 8 */ manager.add(new UnMarkedTask());
        LinkedHashMap<Integer, UserTask> map = manager.filter("+");
        List<Integer> numList = List.of(1, 2, 6, 7);
        assertEquals(4, map.size());
        // check for order as well
        int i = 0;
        for (Map.Entry<Integer, UserTask> entry : map.entrySet()) {
            assertEquals(numList.get(i), entry.getKey());
            assertEquals("+", entry.getValue().getName());
            i++;
        }
    }





    private static class MarkedTask extends UserTask {
        MarkedTask() {
            super("+");
        }


        @Override
        public UserTask mark(boolean isComplete) {
            if (isComplete) {
                return new MarkedTask();
            }
            return new UnMarkedTask();
        }


        @Override
        public boolean isComplete() {
            return true;
        }


        @Override
        public String getReloadString() {
            return "+";
        }


        @Override
        public String toString() {
            return "+";
        }
    }





    private static class UnMarkedTask extends UserTask {
        UnMarkedTask() {
            super("-");
        }


        @Override
        public UserTask mark(boolean isComplete) {
            if (isComplete) {
                return new MarkedTask();
            }
            return new UnMarkedTask();
        }


        @Override
        public boolean isComplete() {
            return false;
        }


        @Override
        public String getReloadString() {
            return "-";
        }


        @Override
        public String toString() {
            return "-";
        }
    }
}
