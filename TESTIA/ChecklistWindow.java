import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChecklistWindow extends JFrame {

	// Update File - adds new data to the existing file which saves and stores the
	// options client enters
	// Load File - enters the data stored/saved in the file into the GUI checklist

	private JPanel contentPane;
	private double result;
	private HashMap<String, ButtonGroup> buttons;
	private HashMap<String, JRadioButton> radioButtons;
	private File fileCheck;


	// Save changes Button
	// create arraylist with all of the button groups in it
	// find the selection for that button group and then add it into the file
	// if move is selected as complete then put "c" into file
	// if move is selected as in progress then put "i" into file
	// if move is selected as not started then put "n" into file

	// Update changes Button
	// take the denoted letters for that selection then select the appropriate radio
	// button for that button group

	private JProgressBar progressBar;
	private double i;
	private double j;
	private double k;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private ArrayList<Object> tempList;
	private int letters;
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChecklistWindow frame = new ChecklistWindow();
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
	public ChecklistWindow() {
		setTitle("Skating Checklist");
	    letters = 97;
		fileCheck = new File("file_checklist.txt");
		setBounds(100, 100, 665, 765);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][-46.00][grow][-17.00][grow][][grow]", "[19.00][19.00][][][][][][][][][][][][][][][][][][][][][][50.00][32.00][27.00][15.00][]"));

	
		createHashMapButtonGroup();
		createHashMapRadioButtons();

		
				btnNewButton_2 = new JButton("Update Checklist");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						loadFileCheck();
						checkProgress();
					}
				});
				
				JButton btnNewButton_3 = new JButton("Save Changes");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						saveFileCheck();
						JOptionPane.showMessageDialog(null, "The changes you made have been saved");
					}
				});
				btnNewButton_3.setBackground(new Color(176, 196, 222));
				btnNewButton_3.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
				contentPane.add(btnNewButton_3, "cell 1 0,grow");

				
				JLabel lblNewLabel_15 = new JLabel("Skating Checklist");
				lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_15.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
				contentPane.add(lblNewLabel_15, "cell 3 0 4 1,alignx center");
				btnNewButton_2.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
				btnNewButton_2.setBackground(new Color(176, 196, 222));
				contentPane.add(btnNewButton_2, "cell 1 1,grow");

		JLabel lblComplete = new JLabel("Complete");
		lblComplete.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		contentPane.add(lblComplete, "cell 3 1,alignx center");

		JLabel lblIncomplete = new JLabel("In Progress");
		lblIncomplete.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		contentPane.add(lblIncomplete, "cell 5 1,alignx center");

		JLabel lblInProgress = new JLabel("Not Started");
		lblInProgress.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		contentPane.add(lblInProgress, "cell 7 1,alignx center");

		JLabel lblMoveA = new JLabel("Axel Jump");
		lblMoveA.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveA, "cell 1 2");

		JLabel lblMoveA_1 = new JLabel("Loop Jump");
		lblMoveA_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveA_1, "cell 1 3");

		JLabel lblMoveC = new JLabel("Toeloop Jump");
		lblMoveC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveC, "cell 1 4");

		JLabel lblMoveD = new JLabel("Salchow Jump");
		lblMoveD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveD, "cell 1 5");

		JLabel lblMoveE = new JLabel("Flip Jump");
		lblMoveE.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveE, "cell 1 6");

		JLabel lblMoveF = new JLabel("Lutz Jump");
		lblMoveF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblMoveF, "cell 1 7");

		JLabel lblNewLabel = new JLabel("Sit Spin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel, "cell 1 8");

		JLabel lblNewLabel_1 = new JLabel("Upright Spin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_1, "cell 1 9");

		JLabel lblNewLabel_2 = new JLabel("Layback Spin");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_2, "cell 1 10");

		JLabel lblNewLabel_3 = new JLabel("Camel Spin");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_3, "cell 1 11");

		JLabel lblNewLabel_4 = new JLabel("Flying Upright Spin");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_4, "cell 1 12");

		JLabel lblNewLabel_5 = new JLabel("Flying Layback Spin");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_5, "cell 1 13");

		JLabel lblNewLabel_6 = new JLabel("Flying Camel Spin");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_6, "cell 1 14");

		JLabel lblNewLabel_7 = new JLabel("Flying Sit Spin");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_7, "cell 1 15");

		JLabel lblNewLabel_8 = new JLabel("Change Foot Upright Spin");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_8, "cell 1 16");

		JLabel lblNewLabel_9 = new JLabel("Change Foot Layback Spin");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_9, "cell 1 17");

		JLabel lblNewLabel_10 = new JLabel("Change Foot Camel Spin");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_10, "cell 1 18");

		JLabel lblNewLabel_11 = new JLabel("Change Foot Sit Spin");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_11, "cell 1 19");

		JLabel lblNewLabel_12 = new JLabel("Combination Spin");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_12, "cell 1 20");

		JLabel lblNewLabel_13 = new JLabel("Flying Combination Spin");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_13, "cell 1 21");

		JLabel lblNewLabel_14 = new JLabel("Change Foot Combination Spin");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel_14, "cell 1 22,aligny baseline");

		JLabel lblFlyingChangeFoot = new JLabel("Flying Change Foot Combination");
		lblFlyingChangeFoot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblFlyingChangeFoot, "cell 1 23");
		


		int col = 3;
		int row = 2;
		for (int z = 1; z < 23; z++) 
		{
			for (int b = 1; b < 4; b++) 
			{
				if(b == 1)
				{
					col = 3;
				}
				else if(b == 2)
				{
					col = 5;
				}
				else
				{
					col = 7;
				}
				
				radioButtons.get(String.valueOf((char) letters) + b).addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						checkProgress();
					}

				});
				radioButtons.get(String.valueOf((char) letters) + b).setBackground(new Color(176, 224, 230));
				contentPane.add(radioButtons.get(String.valueOf((char) letters) + b), "cell" + col + " " + row + ",alignx center");
			}
			row++;
			letters++;
		}
		
		letters = 97;

		// Purpose is to group the three buttons in one row so that only one can be selected

		for (int s = 1; s < 23; s++) 
		{
			for (int t = 1; t < 4; t++) 
			{
				buttons.get("group" + s).add(radioButtons.get(String.valueOf((char) letters) + t));
			}
			letters++;
		}
		
		letters = 97;

		progressBar = new JProgressBar();
		progressBar.setValue((int) result);
		progressBar.repaint();

		JButton btnNewButton = new JButton("Check Progress");
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.setFont(new Font("Lucida Handwriting", Font.PLAIN, 33));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkProgress();
				String vari = "";
				if (!Double.toString(result).equals("") && !Double.toString(result).equals("0.0")) {
					if ((Double.toString(result).substring(2, 3).equals("."))) {
						vari = (Double.toString(result).substring(0, 5) + "%");
					} 
					else
					{
						vari = (Double.toString(result).substring(0, 4) + "%");
					}
				} 
				else 
				{
					vari = ("0.00" + "%");
				}

				j = 0;
				
				for(int w = 1; w < 23; w++)
				{
					if(buttons.get("group" + w).isSelected(radioButtons.get(String.valueOf((char) letters) + 2).getModel()) == true)
					{
						j++;
					}
					letters++;
				}
				letters = 97;

				k = 0;
				
				for(int w = 1; w < 23; w++)
				{
					if(buttons.get("group" + w).isSelected(radioButtons.get(String.valueOf((char) letters) + 3).getModel()) == true)
					{
						k++;
					}
					letters++;
				}
				letters = 97;
				

				JOptionPane.showMessageDialog(null,
						"Number of Moves Completed: " + (int) i + "\n" + "Number of Moves In Progress: " + (int) j
								+ "\n" + "Number of Moves Not Completed: " + (int) k);
			}

		});

		contentPane.add(btnNewButton, "cell 0 24 9 1,grow");
		contentPane.add(progressBar, "cell 0 25 8 1,grow");
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);

	}
	
	private void createHashMapButtonGroup() 
	{
		buttons = new HashMap<String, ButtonGroup>();
		for (int i = 1; i < 23; i++) 
		{
			buttons.put("group" + i, new ButtonGroup());
		}
	}

	private void createHashMapRadioButtons() 
	{
		radioButtons = new HashMap<String, JRadioButton>();
		int letters = 97;

		for (int i = 1; i < 67; i++) 
		{
			for (int a = 1; a < 4; a++) 
			{
				
				radioButtons.put(String.valueOf((char) letters) + a, new JRadioButton(""));
			}
			letters++;
		}
	}

	private void checkProgress() 
	{
		// get number complete, divide by number of total moves
		i = 0;
		
		for(int w = 1; w < 23; w++)
		{
			JRadioButton temp = radioButtons.get(String.valueOf((char) letters) + 1);
			if(buttons.get("group" + w).isSelected(temp.getModel()) == true)
			{
				i++;
			}
			letters++;
		}
		letters = 97;

		// Calculating progress in terms of a percent
		// calculate the percent completion
		// number of checks under column complete divided by total moves
		// moves completes/total moves
		result = (i / 22) * 100;

		// prints percent on the progress bar
		String per = Double.toString(result);
		if (per.equals("100.0")) {
			progressBar.setString("100" + "%");

		} 
		else if (!per.equals("") && !per.equals("0.0")) 
		{
			if (per.equals("50.0")) {
				progressBar.setString("50" + "%");
			} 
			else if ((per.substring(2, 3).equals("."))) {
				progressBar.setString(per.substring(0, 5) + "%");
			} 
			else {
				progressBar.setString(per.substring(0, 4) + "%");
			}
		} 
		else 
		{
			progressBar.setString("0.00" + "%");
		}
		progressBar.setValue((int) result);
		progressBar.repaint();
	}
	

	// Load File - enters the data stored/saved in the file into the GUI checklist
	
	private void loadFileCheck() 
	{
		letters = 97;
		int x = 1;
		Scanner scans;
		try {
			scans = new Scanner(fileCheck);
			while (scans.hasNextLine()) 
			{
				
				String temp = scans.nextLine();
				if(!temp.equals("null") && temp.substring(1).equals("1"))
				{
					buttons.get("group" + x).setSelected(radioButtons.get(String.valueOf((char) letters) + 1).getModel(), true);
				}
				else if(!temp.equals("null") && temp.substring(1).equals("2"))
				{
					buttons.get("group" + x).setSelected(radioButtons.get(String.valueOf((char) letters) + 2).getModel(), true);
				}
				else if(!temp.equals("null") && temp.substring(1).equals("3"))
				{
					buttons.get("group" + x).setSelected(radioButtons.get(String.valueOf((char) letters) + 3).getModel(), true);
				}
				letters++;
				x++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		checkProgress();
	}
	
	// Save File - adds new data to the existing file which saves and stores the options client enters

	private void saveFileCheck() {
		letters = 97;
		fileCheck.delete();
		try 
		{
			fileCheck.createNewFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		try 
		{
			PrintWriter writeCheck = new PrintWriter(fileCheck);

			for (int p = 1; p < 23; p++) 
			{
				if(buttons.get("group" + p).isSelected(radioButtons.get(String.valueOf((char) letters) + 1).getModel()) == true)
				{
					writeCheck.println(String.valueOf((char) letters) + 1);
				}
				else if(buttons.get("group" + p).isSelected(radioButtons.get(String.valueOf((char) letters) + 2).getModel()) == true)
				{
					writeCheck.println(String.valueOf((char) letters) + 2);
				}
				else if(buttons.get("group" + p).isSelected(radioButtons.get(String.valueOf((char) letters) + 3).getModel()) == true)
				{
					writeCheck.println(String.valueOf((char) letters) + 3);
				}
				else
				{
					writeCheck.println("null");
				}
				letters++;
			}

			writeCheck.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.print("Error: " + e.getMessage());
		}

	}
}