import java.io.File;  // Import the File class
import java.io.IOException;

public class FileManager {
    public static void createFile() {
        try {
            File saveData = new File("saved_data.txt");
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readFile() {
        //void initializeTrie(String fileName) {
        //        trie = new Trie();
        //
        //        try {
        //            InputStream is = new FileInputStream(fileName);
        //            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        //            String line;
        //            while (buf.ready()) {
        //                line = buf.readLine().trim();
        //                trie.insert(line);
        //            }
        //        } catch (Exception e) {
        //            System.out.println("Error opening file.");
        //        }
        //    }
    }

    public static void writeFile() {

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
