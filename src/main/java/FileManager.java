import java.io.*;
import java.util.ArrayList;

/*
* Saves data into file in the format of [T][B][Description], where T is type of task,
* B is int representing bool isMarked, description is details of the task.
 */
public class FileManager {
    public static final String fileName = "saved_data.txt";

    public static int createFile() {
        int code = -1;
        try {
            File saveData = new File(fileName);
            code = saveData.createNewFile() ? 1 : 0;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return code;
    }

    public static ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }

    public static void writeFile(Data data) {
        ArrayList<String> save = new ArrayList<>();
        for (int i = 0; i < data.getSize(); i++) {
            Task task = data.getEntry(i);
            String isMarked = task.isMarked() ? "1" : "0";
            String entry = task.getTag() + isMarked + task.getDescription();
            save.add(entry);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String entry : save) {
                writer.write(entry);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Data populateList() {
        createFile();
        ArrayList<String> savedData = readFile();
        Data data = addEntry(savedData);
        return data;
    }

    private static Data addEntry(ArrayList<String> savedData) {
        Data data = new Data();
        for (String entry : savedData) { //entry: [T][B][Description]
            char type = entry.charAt(0);
            int marked = Character.getNumericValue(entry.charAt(1));
            String description = entry.substring(2);
            try {
                if (type == 'T') {
                    ToDo todo = new ToDo();
                    todo.genDscp(description);
                    if (marked == 1) {
                        todo.mark();
                    }
                    data.addFileEntry(todo);
                } else if (type == 'D') {
                    Deadline deadline = new Deadline();
                    deadline.genDscp(description);
                    if (marked == 1) {
                        deadline.mark();
                    }
                    data.addFileEntry(deadline);
                } else {
                    Event event = new Event();
                    event.genDscp(description);
                    if (marked == 1) {
                        event.mark();
                    }
                    data.addFileEntry(event);
                }

            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return data;
    }
}

//public static Maze readMaze(String fileName) throws IOException {
//		FileReader fin = new FileReader(fileName);
//		BufferedReader bin = new BufferedReader(fin);
//
//		Maze maze = new Maze();
//
//		List<String> input = new ArrayList<>();
//		String line;
//		while ((line = bin.readLine()) != null) {
//			if (line.isEmpty()) {
//				break; // end of input
//			}
//			if (maze.columns > 0 && line.length() != maze.columns) {
//				throw new IOException("Invalid input format");
//			}
//			maze.columns = line.length();
//			maze.rows++;
//			input.add(line);
//		}
//
//		if (maze.rows % 2 == 0 || maze.columns % 2 == 0) {
//			throw new IOException("Invalid input format");
//		}
//
//		maze.rooms = new Room[maze.rows / 2][maze.columns / 2];
//		for (int i = 1; i < maze.rows - 1; i += 2) {
//			for (int j = 1; j < maze.columns - 1; j += 2) {
//				maze.rooms[i / 2][j / 2] = new Room(
//						input.get(i - 1).charAt(j) == WALL, // north: i-1
//						input.get(i + 1).charAt(j) == WALL, // south: i+1
//						input.get(i).charAt(j + 1) == WALL, // east: j+1
//						input.get(i).charAt(j - 1) == WALL  // west: j-1
//				);
//			}
//		}
//
//		assert (!bin.ready());
//		bin.close();
//
//		return maze;
//	}
