import javax.swing.*;

public class Notepad extends JFrame {
	JTextArea textArea;
	JScrollPane scrollPane;
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuColor;
	JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem;

	public Notepad() {
		try {
			/* Set look and feel to Nimbus */
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// Update the UI to use the chosen look and feel
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
		         UnsupportedLookAndFeelException e) {
			// Throw a RuntimeException with details of the exception if an error occurs
			throw new RuntimeException(e);
		}
		SwingUtilities.invokeLater(this::initializeUI);
		SwingUtilities.invokeLater(this::createTextArea);
		SwingUtilities.invokeLater(this::createMenuBar);
		SwingUtilities.invokeLater(this::createFileItems);
	}

	private void initializeUI() {
		this.setTitle("Notepad");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void createTextArea() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
	}

	private void createMenuBar() {
		menuBar = new JMenuBar();

		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		menuFormat = new JMenu("Format");
		menuBar.add(menuFormat);
		menuColor = new JMenu("Color");
		menuBar.add(menuColor);

		this.setJMenuBar(menuBar);
	}

	private void createFileItems() {
		/* File Menu*/
		newItem = new JMenuItem("New");
		menuFile.add(newItem);
		openItem = new JMenuItem("Open");
		menuFile.add(openItem);
		saveItem = new JMenuItem("Save");
		menuFile.add(saveItem);
		saveAsItem = new JMenuItem("SaveAs");
		menuFile.add(saveAsItem);
		exitItem = new JMenuItem("Exit");
		menuFile.add(exitItem);

		/* newItem action listener */
		newItem.addActionListener(newItemListener -> {
			new Functions(this).newFile();
		});
		/* openItem action listener */
		openItem.addActionListener(openItemListener -> {
			new Functions(this).openFile();
		});
		/* saveItem action listener */
		saveItem.addActionListener(saveItem -> {
			new Functions(this).save();
		});
		/* saveAsItem action listener */
		saveAsItem.addActionListener(saveAsItemListener -> {
			new Functions(this).saveAs();
		});
		exitItem.addActionListener(exitItemListener -> {
			new Functions(this).exit();
		});
	}

}
