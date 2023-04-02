import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MatrixPanel extends JPanel {

	// TODO: add a variable that stores a reference to the class that handles songs (like Mp3Player or MusicCollection or MusicHandler or whatever you called it in part 1)
	// This variable will be set in the setSongs method that you would also need to add.
	private Matrix matrix = null;
	private Matrix matrix2 = null;
	private PlayerThread helperThread  = null;
	// GUI-related stuff
	private JPanel topPanel, bottomPanel, centerPanel;

	// Buttons in the top panel
	private JButton enterMatrixButton1;
	private JButton enterMatrixButton2;
	private JButton calculateButton;
	private JButton determinantButton;
	private JButton inverseButton;
	private JButton exitButton;
	private JButton addMatrixButton;
	private JButton subtractMatrixButton;
	private JButton multiplyMatrixButton;
	private JButton echolonButton;
	private JButton transposeButton;
	private JButton GaussianEliminationButton;
	private JTextField rowBox, colBox, colBox2, rowBox2; // for searching by keyword

	// Table in the central panel
	private JTable table1 = null, table2 = null;
	// Buttons in the bottom panel for operations

	private Thread currThread = null;	// the current thread that is playing the song
	private boolean add = false;
	private boolean subtract = false;
	private boolean multiply = false;
	private boolean echolon = false;
	private boolean determinant = false;
	private boolean inverse = false;
	private boolean transpose = false;
	private boolean GaussianElimination = false;

	public Mp3PlayerPanel() {
		this.setLayout(new BorderLayout());

		// create buttons
		rowBox = new JTextField(10);
		colBox = new JTextField(10);
		colBox2 = new JTextField(10);
		rowBox2 = new JTextField(10);
		enterMatrixButton1 = new JButton("Create Matrix");
		enterMatrixButton2 = new JButton("Create Matrix 2");
		exitButton = new JButton("Exit");
		calculateButton = new JButton("Calculate");
		echolonButton = new JButton("Echolon");
		determinantButton = new JButton("Determinant");
		inverseButton = new JButton("Inverse");
		addMatrixButton = new JButton("Add Matrix");
		subtractMatrixButton = new JButton("Subtract Matrix");
		multiplyMatrixButton = new JButton("Multiply Matrix");
		transposeButton = new JButton("Transpose");
		GaussianEliminationButton = new JButton("Gaussian Elimination");

		// add a listener for each button
		enterMatrixButton1.addActionListener(new MyButtonListener());
		enterMatrixButton2.addActionListener(new MyButtonListener());
		exitButton.addActionListener(new MyButtonListener());
		calculateButton.addActionListener(new MyButtonListener());
		echolonButton.addActionListener(new MyButtonListener());
		determinantButton.addActionListener(new MyButtonListener());
		inverseButton.addActionListener(new MyButtonListener());
		addMatrixButton.addActionListener(new MyButtonListener());
		subtractMatrixButton.addActionListener(new MyButtonListener());
		multiplyMatrixButton.addActionListener(new MyButtonListener());
		transposeButton.addActionListener(new MyButtonListener());
		GaussianEliminationButton.addActionListener(new MyButtonListener());

		// Create panels: top, center, bottom
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 3));
		this.add(topPanel, BorderLayout.NORTH);

		// create the bottom panel and add three buttons to it
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(exitButton);
		this.add(bottomPanel, BorderLayout.SOUTH);

		// the panel in the center that shows mp3 songs
		centerPanel = new JPanel();
		centerPanel.add(echolonButton);
		centerPanel.add(determinantButton);
		centerPanel.add(inverseButton);
		centerPanel.add(addMatrixButton);
		centerPanel.add(subtractMatrixButton);
		centerPanel.add(multiplyMatrixButton);
		centerPanel.add(transposeButton);
		centerPanel.add(GaussianEliminationButton);


		this.add(centerPanel, BorderLayout.CENTER);


		// FileChooser: set the default directory to the current directory
	}

	// TODO: Add a setSongs method that sets the Mp3Player variable, so that GUI has access to it.

	/**
	 * Create a table that shows the title and the artist of each song in the
	 * center panel.
	 *
	 */

	/** A inner listener class for buttons. **/
	class MyButtonListener implements ActionListener {
		public Matrix matrixFill(int rows, int cols){
			Matrix matrix = new Matrix(rows, cols);
			for(int i = 0; i < matrix.getHeight(); i++){
				for(int j = 0; j < matrix.getWidth(); j++){
					if(table1.getValueAt(i, j) != null) {
						matrix.setElement(i, j, Float.parseFloat(table1.getValueAt(i, j).toString()));
					}
					else{
						matrix.setElement(i, j, 0);
					}
				}
			}
			return matrix;
		}
		public Matrix matrix2Fill(int rows, int cols){
			Matrix matrix = new Matrix(rows, cols);
			for(int i = 0; i < matrix.getHeight(); i++){
				for(int j = 0; j < matrix.getWidth(); j++){
					if(table2.getValueAt(i, j) != null) {
						matrix.setElement(i, j, Float.parseFloat(table2.getValueAt(i, j).toString()));
					}
					else{
						matrix.setElement(i, j, 0);
					}
				}
			}
			return matrix;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addMatrixButton){
				add = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				topPanel.add(enterMatrixButton2);
				topPanel.add(rowBox2);
				topPanel.add(colBox2);
				bottomPanel.add(calculateButton);
			}
			if(e.getSource() == subtractMatrixButton){
				subtract = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				topPanel.add(enterMatrixButton2);
				topPanel.add(rowBox2);
				topPanel.add(colBox2);
				bottomPanel.add(calculateButton);
			}
			if(e.getSource() == multiplyMatrixButton){
				multiply = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				topPanel.add(enterMatrixButton2);
				topPanel.add(rowBox2);
				topPanel.add(colBox2);
				bottomPanel.add(calculateButton);
			}
			if(e.getSource() == inverseButton){
				inverse = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.setLayout(new GridLayout(1, 3));
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				bottomPanel.add(calculateButton);
			}
			if(e.getSource() == transposeButton){
				transpose = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.setLayout(new GridLayout(1, 3));
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				bottomPanel.add(calculateButton);
			}
			if(e.getSource() == GaussianEliminationButton){
				GaussianElimination = true;
				centerPanel.removeAll();
				centerPanel.repaint();
				topPanel.setLayout(new GridLayout(1, 3));
				topPanel.add(enterMatrixButton1);
				topPanel.add(rowBox);
				topPanel.add(colBox);
				bottomPanel.add(calculateButton);
			}
			//global buttons
			if (e.getSource() == enterMatrixButton1) {
				if(table1 != null)
					centerPanel.remove(table1);

				int cols = Integer.parseInt(colBox.getText());
				int rows = Integer.parseInt(rowBox.getText());
				matrix = new Matrix(rows, cols);
				table1 = new JTable(rows, cols);
				centerPanel.add(table1);
				table1.setRowHeight(25);
			}
			if (e.getSource() == enterMatrixButton2) {
				if(table2 != null)
					centerPanel.remove(table2);

				int cols = Integer.parseInt(colBox2.getText());
				int rows = Integer.parseInt(rowBox2.getText());
				matrix2 = new Matrix(rows, cols);

				table2 = new JTable(rows, cols);
				centerPanel.add(table2);
				table2.setRowHeight(25);
			}
			if (e.getSource() == calculateButton) {
				if(add){
					if(matrix == null || matrix2 == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					matrix2 = matrix2Fill(matrix2.getHeight(), matrix2.getWidth());
					matrix = Operations.add(matrix, matrix2);
					matrix2 = null;
					centerPanel.remove(table2);
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
				if(subtract){
					if(matrix == null || matrix2 == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					matrix2 = matrix2Fill(matrix2.getHeight(), matrix2.getWidth());
					matrix = Operations.subtract(matrix, matrix2);
					matrix2 = null;
					centerPanel.remove(table2);
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
				if(multiply){
					if(matrix == null || matrix2 == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					matrix2 = matrix2Fill(matrix2.getHeight(), matrix2.getWidth());
					try {
						matrix = Operations.multiply(matrix, matrix2);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
					matrix2 = null;
					centerPanel.remove(table2);
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
				if(inverse){
					if(matrix == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					try {
						matrix = Operations.invert(matrix);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
				if(transpose){
					if(matrix == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					matrix = Operations.transpose(matrix);
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
				if(GaussianElimination){
					if(matrix == null){
						return;
					}
					matrix = matrixFill(matrix.getHeight(), matrix.getWidth());
					matrix = Operations.gaussJordanElimination(matrix);
					centerPanel.remove(table1);
					table1 = new JTable(matrix.getHeight(), matrix.getWidth());
					for(int i = 0; i < matrix.getHeight(); i++){
						for(int j = 0; j < matrix.getWidth(); j++){
							table1.setValueAt(matrix.getElement(i, j), i, j);
						}
					}
					centerPanel.add(table1);
				}
			}
			else if (e.getSource() == exitButton) {
				
				System.exit(-1);
			}
			
			updateUI();

		} // actionPerformed

	} // ButtonListener
	
}
