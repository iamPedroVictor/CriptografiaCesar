package servicos;

import java.io.FileWriter;

public class FileIO {
	public static void Save(String fileString, String data) {
		try(FileWriter file = new FileWriter(fileString)) {
			file.write(data);
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
