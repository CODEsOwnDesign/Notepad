import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Functions {
	Notepad notepad;
	FileDialog fileDialog;
	String fileName, fileAddress;

	public Functions(Notepad notepad) {
		this.notepad = notepad;
	}

	public void newFile() {
		notepad.textArea.setText("");
		notepad.setTitle("Untitled File");
	}

	public void openFile() {
		fileDialog = new FileDialog(notepad, "Open", FileDialog.LOAD);
		fileDialog.setVisible(true);

		if (fileDialog.getFile() != null) {
			fileName = fileDialog.getFile();
			fileAddress = fileDialog.getDirectory();
			notepad.setTitle(fileName);
		}
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress + fileName));
			notepad.textArea.setText("");
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				notepad.textArea.append(line + "\n");
			}
			bufferedReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(notepad, "File Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	public void saveAs() {
		fileDialog = new FileDialog(notepad, "Save", FileDialog.SAVE);
		fileDialog.setVisible(true);

		if (fileDialog != null) {
			fileName = fileDialog.getFile();
			fileAddress = fileDialog.getDirectory();
			notepad.setTitle(fileName);

			try (FileWriter fileWriter = new FileWriter(fileAddress + fileName)) {
				fileWriter.write(notepad.textArea.getText());
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Unable to save the file!");
				throw new RuntimeException(e);
			}
		}
	}
}
