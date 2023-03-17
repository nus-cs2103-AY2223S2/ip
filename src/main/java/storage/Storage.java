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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import parser.Parser;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class Storage {

	private String dataDirectory = "data";

	private String dataFilePath = dataDirectory + File.separator + "shao.txt";

	private File myDir = new File(dataDirectory);

	private File myFile = new File(dataFilePath);

	final private String BOT_IMAGE_URL = "https://i.ibb.co/HrCJ70C/bot.png";

	final private String USER_IMAGE_URL = "https://i.ibb.co/FKkmCs6/user.png";

	/**
	 * Load and retrieve bot ImageView component.
	 * 
	 * @return ImageView
	 */
	public ImageView getBotImageView() {
		try {
			return loadImageFile(BOT_IMAGE_URL);
		} catch (FileNotFoundException ex) {
			return new ImageView();
		}
	}

	/**
	 * Load and retrieve user ImageView component.
	 * 
	 * @return ImageView
	 */
	public ImageView getUserImageView() {
		try {
			return loadImageFile(USER_IMAGE_URL);
		} catch (FileNotFoundException ex) {
			return new ImageView();
		}
	}

	/**
	 * Read and parse saved file contents.
	 * 
	 * @param tasklist        All the tasks that are recorded.
	 * @param parser          A service to parse text input.
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 */
	public void getFile(TaskList tasklist, Parser parser, Ui ui, Storage storage, VBox dialogContainer) {
		try {
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine()) {
				parser.parseAndSetData(myReader.nextLine().trim(), tasklist, ui, storage, dialogContainer);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			createFile(ui);
		}
	}

	/**
	 * Create new file if it does not exists.
	 * 
	 * @param ui A service to render the page of GUI.
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
	 * @param task New task that was just added.
	 * @param ui   A service to render the page of GUI.
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
	 * @param idx    An index of the task in the tasklist which is also the row
	 *               where the task is in the file.
	 * @param isMark
	 * @param ui     A service to render the page of GUI.
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
	 * @param lineNum A row number of the task in file.
	 * @param newLine The new line of contents to replace the old line with.
	 * @param ui      A service to render the page of GUI.
	 */
	private void modifyLineFile(int lineNum, String newLine, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
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
	 * @param lineNum A row number of the task in file.
	 * @param ui      A service to render the page of GUI.
	 */
	public void deleteLineFile(int lineNum, Ui ui) {
		String content = "";
		int curLineNum = 1;

		try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
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

	/**
	 * @param imageFileName
	 * @return ImageView
	 * @throws FileNotFoundException
	 */
	private ImageView loadImageFile(String imageUrl) throws FileNotFoundException {
		Image image = new Image(imageUrl);

		// Setting the image view
		ImageView imageView = new ImageView(image);

		// Setting the position of the image
		imageView.setX(0);
		imageView.setY(0);

		// setting the fit height and width of the image view
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);

		// Setting the preserve ratio of the image view
		imageView.setPreserveRatio(true);

		return imageView;
	}

}
