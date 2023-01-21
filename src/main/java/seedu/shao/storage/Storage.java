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
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.stream.Stream;

import seedu.shao.task.Task;
import seedu.shao.task.Todo;
import seedu.shao.parser.Parser;
import seedu.shao.task.Deadline;
import seedu.shao.task.Event;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

public class Storage {

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

	private String dataDirectory = "data";

	private String sep = File.separator;

	private String dataFilePath = dataDirectory + sep + "shao.txt";

	private File myDir = new File(dataDirectory);

	private File myFile = new File(dataFilePath);

	public void getFile(TaskList tasklist, Parser parser) {
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				fetchData(myReader.nextLine().trim(), tasklist, parser);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			try {
				myDir.mkdirs();
				myFile.createNewFile();
			} catch (IOException ex) {
				Ui.printError("Something went wrong while creating a new file.");
			}
		}
	}

	private void fetchData(String input, TaskList tasklist, Parser parser) {
		String inputLower = input.toLowerCase();
		if (inputLower.isBlank())
			return;
		String[] inputArr = inputLower.split("\\|");
		TaskType operationType = inputLower.startsWith("t")
				? TaskType.TODO
				: inputLower.startsWith("d")
						? TaskType.DEADLINE
						: TaskType.EVENT;

		Task newTask = null;

		switch (operationType) {
			case TODO:
				newTask = new Todo(inputArr[2]);
				break;

			case DEADLINE:
				newTask = new Deadline(inputArr[2], parser.parseDateTimeStr(inputArr[3]));
				break;

			case EVENT:
				newTask = new Event(inputArr[2],
						new LocalDateTime[] { parser.parseDateTimeStr(inputArr[3]),
								parser.parseDateTimeStr(inputArr[4]) });
				break;

			default:
				break;
		}
		if (inputArr[1].equals("1"))
			newTask.markAsDone();
		tasklist.add(newTask);
	}

	public <T extends Task> void saveNewData(T task) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath, true))) {
			bw.write(task.getSavedFormat());
			bw.newLine();
		} catch (IOException e) {
			Ui.printError("Something went wrong while saving the new task.");
		}
	}

	public void markSavedTask(int idx, boolean isMark) {
		try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
			String line = lines.skip(idx).findFirst().get();
			modifyLineFile(dataFilePath, idx + 1,
					line.replaceFirst("[01]", isMark ? "1" : "0"));
		} catch (IOException ex) {
			Ui.printError("Something went wrong while marking the task status.");
		}
	}

	private void modifyLineFile(String filePath, int lineNum, String newLine) {
		String content = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		int curLineNum = 1;

		try {
			reader = new BufferedReader(new FileReader(myFile));
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
			writer = new FileWriter(myFile);
			writer.write(content);
		} catch (IOException e) {
			Ui.printError("Something went wrong while modifying the file.");
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				Ui.printError("Something went wrong while modifying the file.");
			}
		}
	}

	public void deleteLineFile(int lineNum) {
		String content = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		int curLineNum = 1;

		try {
			reader = new BufferedReader(new FileReader(myFile));
			String line = reader.readLine();

			while (line != null) {
				if (curLineNum != lineNum) {
					content += line + System.lineSeparator();
				}
				line = reader.readLine();
				curLineNum += 1;
			}
			writer = new FileWriter(myFile);
			writer.write(content);
		} catch (IOException e) {
			Ui.printError("Something went wrong while deleting a line from the file.");
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				Ui.printError("Something went wrong while deleting a line from the file.");
			}
		}
	}

}
