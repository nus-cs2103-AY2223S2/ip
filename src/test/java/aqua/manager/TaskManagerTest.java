package aqua.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import aqua.aquatask.AquaTask;


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
        AquaTask task = manager.mark(0, false);
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
        AquaTask task = manager.delete(0);
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





    private static class MarkedTask extends AquaTask {
        MarkedTask() {
            super("+");
        }


        @Override
        public AquaTask mark(boolean isComplete) {
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





    private static class UnMarkedTask extends AquaTask {
        UnMarkedTask() {
            super("-");
        }


        @Override
        public AquaTask mark(boolean isComplete) {
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
