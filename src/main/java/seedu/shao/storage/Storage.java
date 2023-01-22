package seedu.shao.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import seedu.shao.parser.Parser;
import seedu.shao.task.Task;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

public class Storage {

	private String dataDirectory = "data";

	private String dataFilePath = dataDirectory + File.separator + "shao.txt";

	private File myDir = new File(dataDirectory);

	private File myFile = new File(dataFilePath);

	public void getFile(TaskList tasklist, Parser parser, Ui ui) {
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				parser.parseData(myReader.nextLine().trim(), tasklist, ui);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			createFile(ui);
		}
	}

	private void createFile(Ui ui) {
		try {
			myDir.mkdirs();
			myFile.createNewFile();
		} catch (IOException ex) {
			ui.printError("Something went wrong while creating a new file.");
		}
	}

	public <T extends Task> void saveNewData(T task, Ui ui) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath, true))) {
			bw.write(task.getSavedFormat());
			bw.newLine();
		} catch (IOException e) {
			ui.printError("Something went wrong while saving the new task.");
		}
	}

	public void markSavedTask(int idx, boolean isMark, Ui ui) {
		try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
			String line = lines.skip(idx).findFirst().get();
			modifyLineFile(dataFilePath, idx + 1,
					line.replaceFirst("[01]", isMark ? "1" : "0"), ui);
		} catch (IOException ex) {
			ui.printError("Something went wrong while marking the task status.");
		}
	}

	private void modifyLineFile(String filePath, int lineNum, String newLine, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile));
				FileWriter writer = new FileWriter(myFile)) {
			String line = reader.readLine();

			while (line != null) {
				if (curLineNum == lineNum) {
					content += newLine + System.lineSeparator();
				} else {
					content += line + System.lineSeparator();
				}
				line = reader.readLine();
				curLineNum += 1;
			}
			writer.write(content);
		} catch (IOException e) {
			ui.printError("Something went wrong while modifying the file.");
		}
	}

	public void deleteLineFile(int lineNum, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile));
				FileWriter writer = new FileWriter(myFile)) {
			String line = reader.readLine();

			while (line != null) {
				if (curLineNum != lineNum) {
					content += line + System.lineSeparator();
				}
				line = reader.readLine();
				curLineNum += 1;
			}
			writer.write(content);
		} catch (IOException e) {
			ui.printError("Something went wrong while deleting a line from the file.");
		}
	}

}
