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
		fileName = null;
		fileAddress = null;
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

	public void save() {
		System.out.println(fileAddress + fileName);
		try (FileWriter fileWriter = new FileWriter(fileAddress + fileName)) {
			fileWriter.write(notepad.textArea.getText());
			notepad.setTitle(fileName);
		} catch (IOException e) {
			System.out.println("Unable to save the file!");
			throw new RuntimeException(e);
		}

	}

	public void saveAs() {
		if (fileName == null) {
			saveAs();
		} else {
			fileDialog = new FileDialog(notepad, "Save As", FileDialog.SAVE);
			fileDialog.setVisible(true);

			if (fileDialog.getFile() != null) {
				fileName = fileDialog.getFile();
				fileAddress = fileDialog.getDirectory();
				notepad.setTitle(fileName);
			}
			try (FileWriter fileWriter = new FileWriter(fileAddress + fileName)) {
				fileWriter.write(notepad.textArea.getText());
				notepad.setTitle(fileName);
			} catch (IOException e) {
				System.out.println("Unable to save the file!");
				throw new RuntimeException(e);
			}
		}
	}

	public void exit() {
		System.exit(0);
	}
}
