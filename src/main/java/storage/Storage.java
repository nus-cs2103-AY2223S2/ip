package storage;

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

import parser.Parser;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class Storage {

	private String dataDirectory = "data";

	private String dataFilePath = dataDirectory + File.separator + "shao.txt";

	private File myDir = new File(dataDirectory);

	private File myFile = new File(dataFilePath);

	/**
	 * Read and parse saved file contents.
	 * 
	 * @param tasklist
	 * @param parser
	 * @param ui
	 */
	public void getFile(TaskList tasklist, Parser parser, Ui ui) {
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				parser.parseAndSetData(myReader.nextLine().trim(), tasklist, ui);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			createFile(ui);
		}
	}

	/**
	 * Create new file if it does not exists.
	 * 
	 * @param ui
	 */
	private void createFile(Ui ui) {
		try {
			myDir.mkdirs();
			myFile.createNewFile();
		} catch (IOException ex) {
			ui.printError("Something went wrong while creating a new file.");
		}
	}

	/**
	 * Add new task into file.
	 * 
	 * @param task
	 * @param ui
	 */
	public <T extends Task> void saveNewData(T task, Ui ui) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath, true))) {
			bw.write(task.getSavedFormat());
			bw.newLine();
		} catch (IOException e) {
			ui.printError("Something went wrong while saving the new task.");
		}
	}

	/**
	 * Mark or unmark task in file.
	 * 
	 * @param idx
	 * @param isMark
	 * @param ui
	 */
	public void markSavedTask(int idx, boolean isMark, Ui ui) {
		try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
			String line = lines.skip(idx).findFirst().get();
			modifyLineFile(idx + 1,
					line.replaceFirst("[01]", isMark ? "1" : "0"), ui);
		} catch (IOException ex) {
			ui.printError("Something went wrong while marking the task status.");
		}
	}

	/**
	 * Update content in file by line number.
	 * 
	 * @param lineNum
	 * @param newLine
	 * @param ui
	 */
	private void modifyLineFile(int lineNum, String newLine, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile));) {
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
			FileWriter writer = new FileWriter(myFile);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			ui.printError("Something went wrong while modifying the file.");
		}
	}

	/**
	 * Delete content in file by line number.
	 * 
	 * @param lineNum
	 * @param ui
	 */
	public void deleteLineFile(int lineNum, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile));) {
			String line = reader.readLine();

			while (line != null) {
				if (curLineNum != lineNum) {
					content += line + System.lineSeparator();
				}
				line = reader.readLine();
				curLineNum += 1;
			}
			FileWriter writer = new FileWriter(myFile);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			ui.printError("Something went wrong while deleting a line from the file.");
		}
	}

}
