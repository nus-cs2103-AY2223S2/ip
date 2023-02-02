package storage;

import parser.Parser;

import task.Task;

import tasklist.TaskList;

import ui.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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

public class Storage {

	private String dataDirectory = "data";

	private String dataFilePath = dataDirectory + File.separator + "shao.txt";

	private String imageFilePath = "assets" + File.separator + "images" + File.separator;

	private File myDir = new File(dataDirectory);

	private File myFile = new File(dataFilePath);

	/**
	 * Load and retrieve bot ImageView component.
	 * 
	 * @return ImageView
	 */
	public ImageView getBotImageView() {
		try {
			return loadImageFile("bot.png");
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
			return loadImageFile("user.png");
		} catch (FileNotFoundException ex) {
			return new ImageView();
		}
	}

	/**
	 * Read and parse saved file contents.
	 * 
	 * @param tasklist
	 * @param parser
	 * @param ui
	 * @param storage
	 * @param dialogContainer
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
	 * @param lineNum
	 * @param ui
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
	private ImageView loadImageFile(String imageFileName) throws FileNotFoundException {
		Image image = new Image(new FileInputStream(imageFilePath + imageFileName));

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
