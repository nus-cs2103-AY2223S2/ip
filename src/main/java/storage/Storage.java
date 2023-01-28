package storage;

import exception.TaskParseException;
import task.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    public static class MikiLoadException extends Exception {
        protected MikiLoadException(String message) {
            super(message);
        }
    }

    private final String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    private void createSaveDir() {
        File dir = new File(dataPath);
        if (!dir.mkdirs()) {
            //no perms/dir locked? consider warning user
        }
    }

    public String[] listSaves() throws MikiLoadException {
        createSaveDir();
        File dir = new File(dataPath);
        if (dir == null) {
            throw new MikiLoadException("(default save location is missing?!)");
        }
        File[] saves = dir.listFiles();
        String[] filenames = new String[saves.length];
        for (int i = 0; i < saves.length; i++) {
            filenames[i]  = saves[i].getName();
        }
        return filenames;
    }

    public void save(String pathString, TaskList tasks) throws IOException {
        createSaveDir();
        Path path = FileSystems.getDefault().getPath(dataPath).resolve(pathString);
        BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        try {
            bw.write(Integer.toString(tasks.size()));
            for (int i = 0; i < tasks.size(); i++) {
                String[] repres = tasks.get(i).save();
                bw.newLine();
                bw.write(Integer.toString(repres.length));
                for (int j = 0; j < repres.length; j++) {
                    bw.newLine();
                    bw.write(repres[j]);
                }
            }
        } finally {
            bw.close();
        }
    }

    public void load(String pathString, TaskList tasks) throws IOException, MikiLoadException {
        createSaveDir();
        tasks.clear();
        Path path = FileSystems.getDefault().getPath(dataPath).resolve(pathString);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        try {
            int numTasks = Integer.parseInt(br.readLine());
            for (int i = 0; i < numTasks; i++) {
                int taskLines = Integer.parseInt(br.readLine());
                String[] repres = new String[taskLines];
                for (int j = 0; j < taskLines; j++) {
                    repres[j] = br.readLine();
                }
                switch (repres[0].charAt(0)) {
                    case 'T':
                        tasks.add(Todo.parseLoad(repres));
                        break;
                    case 'D':
                        tasks.add(Deadline.parseLoad(repres));
                        break;
                    case 'E':
                        tasks.add(Event.parseLoad(repres));
                        break;
                    default:
                        //back-compat? try to handle
                }
            }
        } catch (NumberFormatException | TaskParseException ex) {
            throw new MikiLoadException("this file is corrupt...");
        } finally {
            br.close();
        }
    }
}
