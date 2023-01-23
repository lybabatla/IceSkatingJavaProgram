import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 215);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow][grow]"));
		
		//create button that will open routine generator window
		JButton btnNewButton = new JButton("Routine Generator");
		btnNewButton.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RoutineWindow checking = new RoutineWindow();
				checking.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny baseline");
		contentPane.add(btnNewButton, "cell 0 1,grow");
		
		//create button that will open checklist window
		JButton btnNewButton_1 = new JButton("Checklist");
		btnNewButton_1.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(176, 196, 222));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				ChecklistWindow check = new ChecklistWindow();
				check.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton_1, "cell 0 2,grow");
	}
}