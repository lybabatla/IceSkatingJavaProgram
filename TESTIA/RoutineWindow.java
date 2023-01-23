import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RoutineWindow extends JFrame 
{
	private JTextField moveField;
	private JTextField codeField;
	private RoutineGenerator temp;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoutineWindow frame = new RoutineWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	//returns whether or not the string has letters
	private boolean hasLetters(String id) {
		char[] characters = id.toCharArray();
		boolean has = false;
		for (int i = 0; i < characters.length; i++)
			if (isLetter(characters[i]))
				has = true;
		return has;
	}

	//returns whether or not a character is a letter or not
	private boolean isLetter(char c) {
		String[] num = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		boolean is = true;
		String character = c + "";
		if (character.contains("."))
			character = character.substring(0, character.indexOf("."))
					+ character.substring(character.indexOf(".") + 1);
		for (int i = 0; i < num.length; i++)
			if (character.equals(num[i]))
				is = false;
		return is;
	}
	
	public RoutineWindow() {
		setTitle("Routine Generator");
		
		temp = new RoutineGenerator();
		

		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 544, 347);
		getContentPane().setLayout(new MigLayout("", "[152.00][61.00,grow][grow]", "[][][][34.00][][][-50.00][34.00]"));
		
		//adds button calls add method from RoutineGenerator class
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(hasLetters(moveField.getText()) && !codeField.getText().equals(""))
				{
					temp.add(moveField.getText(), codeField.getText());
					JOptionPane.showMessageDialog(null, "The move " + "(" + moveField.getText() + 
							" , " + codeField.getText() + ") has been added to your routine");
				}
				else
				{
					//if either or both of the text fields are empty this message will pop up
					JOptionPane.showMessageDialog(null, "Please enter a move to add in the text fields");
				}

				//resets the text fields to empty
				moveField.setText("");
				codeField.setText("");
				
			}
		});
		
				JLabel lblNewLabel = new JLabel("Routine Generator");
				lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 30));
				getContentPane().add(lblNewLabel, "cell 0 0 3 1,alignx center");
		btnNewButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(176, 196, 222));
		getContentPane().add(btnNewButton, "cell 0 1,growx");

		JLabel lblMoveName = new JLabel("Move Name: ");
		getContentPane().add(lblMoveName, "cell 1 1,alignx trailing");

		moveField = new JTextField();
		getContentPane().add(moveField, "cell 2 1,growx");
		moveField.setColumns(10);

		//calls the delete method from the RoutineGenerator class
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(hasLetters(moveField.getText()) && !codeField.getText().equals(""))
				{
					if(temp.find2(moveField.getText(), codeField.getText()) == true)
					{
						temp.delete(moveField.getText(), codeField.getText());
						JOptionPane.showMessageDialog(null, "The move " + "(" + moveField.getText() 
						+ " , " + codeField.getText() + ") has been deleted from your routine");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Could not find this move to remove. "
								+ "Please enter an existing move.", "Error Found", JOptionPane.ERROR_MESSAGE); 
					}
			    }
				else if(!hasLetters(moveField.getText()) || codeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a move to remove in the text fields.");
				}
				
				temp.printArrayList();
				
				moveField.setText("");
				codeField.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(176, 196, 222));
		getContentPane().add(btnNewButton_1, "cell 0 2,growx");

		JLabel lblCodeName = new JLabel("Code Name: ");
		getContentPane().add(lblCodeName, "cell 1 2,alignx trailing");

		//will not let the length of the information in the code text field be more than 6 
		codeField = new JTextField();
		codeField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (!(codeField.getText().length() < 6)) 
				{
					arg0.consume();
				}
			}
		});
		getContentPane().add(codeField, "cell 2 2,growx");
		codeField.setColumns(10);

		//calls the replace method from the RoutineGenerator Class
		JButton btnNewButton_3 = new JButton("Replace");
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//fix the bugs remaining
				if(!hasLetters(moveField.getText()) || codeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Step 1: Please enter in a new move name and new code name to replace with "
							+ "\nStep 2: After you enter the data into the text fields,"
							+ " the program will prompt you to enter in an index to replace");
				}
				else
				{
					int index = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter in a valid "
							+ "index to replace", "Index", JOptionPane.YES_NO_CANCEL_OPTION));
					temp.replace(index, moveField.getText(), codeField.getText());
				}
				//temp.replace(index, moveField.getText(), codeField.getText());
				moveField.setText("");
				codeField.setText("");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 3 2 5,grow");
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Move", "Code"}) {
			public boolean isCellEditable(int r, int c)
			{
				return false;
			}
		}
		);
		
		scrollPane.setViewportView(table_1);
		btnNewButton_3.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton_3.setBackground(new Color(176, 196, 222));
		getContentPane().add(btnNewButton_3, "cell 0 4,growx");

		//calls the display moves method from this class
		JButton btnNewButton_4 = new JButton("Display Moves");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				displayMoves();
			}
			
		});
		
		//calls the find method from the RoutineGenerator class
		//displays the information in a JTable
		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(hasLetters(moveField.getText()) && codeField.getText() != "")
				{
					if((temp.find2(moveField.getText(), codeField.getText())) == true)
					{
						Object[][] findDisplay = new Object[1][2];
						findDisplay[0][0] = moveField.getText();
						findDisplay[0][1] = codeField.getText();
						table_1.setModel(new DefaultTableModel(findDisplay, new String[] {"Move", "Code"}));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Could not find this move in the routine. "
								+ "Please enter an existing move.", "Error Found", JOptionPane.ERROR_MESSAGE);toString();
						table_1.setModel(new DefaultTableModel(null, new String[] {"Move", "Code"}));
					}
			    }
				else if(!hasLetters(moveField.getText()) || codeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a move to find in the text fields.");
				}
			
			
			moveField.setText("");
			codeField.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton_2.setBackground(new Color(176, 196, 222));
		getContentPane().add(btnNewButton_2, "cell 0 3,growx");
		
		btnNewButton_4.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton_4.setBackground(new Color(176, 196, 222));
		getContentPane().add(btnNewButton_4, "cell 0 5,growx");
		
		//calls the clear file method from the RoutineGenerator class
		//sets the JTable to be empty
		JButton btnNewButton_6 = new JButton("Clear Routine File");
		btnNewButton_6.setBackground(new Color(176, 196, 222));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				temp.clearFile();
				JOptionPane.showMessageDialog(null, "Your routine has been cleared");
				displayMoves();
				table_1.setModel(new DefaultTableModel(null, new String[] {"Move", "Code"}));
			}
		});
		btnNewButton_6.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		getContentPane().add(btnNewButton_6, "cell 0 7,grow");

	}
	
	//allows the moves to be displayed in a JTable
	private void displayMoves()
	{
		Object[][] routineDisplay = new Object[temp.getArrayList().size()][2];
		for(int i = 0; i < temp.getArrayList().size(); i++)
		{
			
				routineDisplay[i][0] = temp.getArrayList().get(i).getName();
				routineDisplay[i][1] = temp.getArrayList().get(i).getCode();
		}
		
		table_1.setModel(new DefaultTableModel(routineDisplay, new String[] {"Move", "Code"}));
	}
}
