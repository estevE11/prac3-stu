package rmi;

import java.awt.EventQueue;
import common.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


import java.awt.Color;

public class ClientFrameRMI extends JFrame implements ActionListener {

	/* GUI Components HERE.
	 * Other attributes (connections, channels, ...) must be placed in the
	 * "// ----------------- My stuff starts here --------------" section 
	 * around line 280...
	 */
	
	private JPanel contentPane;
	private JButton btnConnect;
	private JTextField name_textField;
	private JButton btnGeography;
	private JButton btnScience;
	private JLabel lblQuestion;
	private JRadioButton rdbtnAnswer1;
	private JRadioButton rdbtnAnswer2;
	private JRadioButton rdbtnAnswer3;
	private JRadioButton rdbtnAnswer4;
	private JButton btnCheck;
	private JButton btnArt;
	private JButton btnPlayNoMore;
	private JLabel lblCorrect;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrameRMI frame = new ClientFrameRMI();
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
	public ClientFrameRMI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("Trivial Solitaire RMI version (by E. Sesa)");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 712, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(this);
		
		name_textField = new JTextField();
		name_textField.setText("write your name here...");
		name_textField.setColumns(10);
		name_textField.addActionListener(this);
		
		btnGeography = new JButton("GEOGRAPHY");
		btnGeography.addActionListener(this);
		btnGeography.setEnabled(false);
		
		btnScience = new JButton("SCIENCE");
		btnScience.addActionListener(this);
		btnScience.setEnabled(false);
		
		lblQuestion = new JLabel("Question goes here");
		lblQuestion.setEnabled(false);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		rdbtnAnswer1 = new JRadioButton("answer 1");
		rdbtnAnswer1.addActionListener(this);
		rdbtnAnswer1.setEnabled(false);
		buttonGroup.add(rdbtnAnswer1);
		
		rdbtnAnswer2 = new JRadioButton("answer 2");
		rdbtnAnswer2.addActionListener(this);
		rdbtnAnswer2.setEnabled(false);
		buttonGroup.add(rdbtnAnswer2);
		
		rdbtnAnswer3 = new JRadioButton("answer 3");
		rdbtnAnswer3.addActionListener(this);
		rdbtnAnswer3.setEnabled(false);
		buttonGroup.add(rdbtnAnswer3);
		
		rdbtnAnswer4 = new JRadioButton("answer 4");
		rdbtnAnswer4.addActionListener(this);
		rdbtnAnswer4.setEnabled(false);
		buttonGroup.add(rdbtnAnswer4);
		
		btnCheck = new JButton("CHECK");
		btnCheck.addActionListener(this);
		btnCheck.setEnabled(false);
		
		btnArt = new JButton("ART");
		btnArt.setEnabled(false);
		btnArt.addActionListener(this);
		
		btnPlayNoMore = new JButton("PLAY NO MORE");
		btnPlayNoMore.addActionListener(this);
		btnPlayNoMore.setEnabled(false);
		
		lblCorrect = new JLabel("CORRECT!");
		lblCorrect.setForeground(Color.GREEN);
		lblCorrect.setVisible(false);
		lblCorrect.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnAnswer4)
						.addComponent(rdbtnAnswer1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnConnect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(name_textField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGeography)
							.addGap(18)
							.addComponent(btnScience)
							.addGap(18)
							.addComponent(btnArt))
						.addComponent(lblQuestion)
						.addComponent(btnCheck)
						.addComponent(btnPlayNoMore)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnAnswer3)
								.addComponent(rdbtnAnswer2))
							.addGap(120)
							.addComponent(lblCorrect)))
					.addContainerGap(371, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConnect)
						.addComponent(name_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGeography)
						.addComponent(btnScience)
						.addComponent(btnArt))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(lblQuestion)
							.addGap(18)
							.addComponent(rdbtnAnswer1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnAnswer2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnAnswer3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnAnswer4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCheck)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(btnPlayNoMore)
							.addGap(26))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(109)
							.addComponent(lblCorrect)
							.addContainerGap())))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void actionPerformed(ActionEvent arg0) {
		try {
			if (arg0.getSource() == rdbtnAnswer4) {
				rdbtnAnswer4actionPerformed(arg0);
			}
			if (arg0.getSource() == rdbtnAnswer3) {
				rdbtnAnswer3actionPerformed(arg0);
			}
			if (arg0.getSource() == rdbtnAnswer2) {
				rdbtnAnswer2actionPerformed(arg0);
			}
			if (arg0.getSource() == rdbtnAnswer1) {
				rdbtnAnswer1actionPerformed(arg0);
			}
			if (arg0.getSource() == btnCheck) {
				btnCheckactionPerformed(arg0);
			}
			if (arg0.getSource() == btnPlayNoMore) {
				btnPlayNoMoreactionPerformed(arg0);
			}
			if (arg0.getSource() == btnArt) {
				btnArtactionPerformed(arg0);
			}
			if (arg0.getSource() == btnScience) {
				btnScienceactionPerformed(arg0);
			}
			if (arg0.getSource() == btnGeography) {
				btnGeographyactionPerformed(arg0);
			}
			if (arg0.getSource() == btnConnect) {
				btnConnectactionPerformed(arg0);
			}
			if (arg0.getSource() == this.name_textField) {
				btnConnectactionPerformed(arg0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	protected void btnConnectactionPerformed(ActionEvent arg0) throws Exception {
		/* COMPLETE */
		this.id = getServer().Hello();
		JOptionPane.showMessageDialog(this, "Connection established with ID: " + id, "Connected succesfuly", JOptionPane.INFORMATION_MESSAGE);
		btnGeography.setEnabled(true);
		btnScience.setEnabled(true);
		btnArt.setEnabled(true);
		btnConnect.setEnabled(false);
		name_textField.setEnabled(false);
		this.enableRadioButtons();
	}
	
	protected void btnGeographyactionPerformed(ActionEvent arg0) throws RemoteException, Exception {
		/* COMPLETE */
		Question q = getServer().next(id, "GEO");
		this.setQuestion(q);
	}
	
	protected void btnScienceactionPerformed(ActionEvent arg0) throws RemoteException, Exception {
		/* COMPLETE */
		Question q = getServer().next(id, "SCIENCE");
		this.setQuestion(q);
	}
	
	protected void btnArtactionPerformed(ActionEvent arg0) throws RemoteException, Exception {
		/* COMPLETE */
		Question q = getServer().next(id, "ART");
		this.setQuestion(q);
	}
	
	protected void btnPlayNoMoreactionPerformed(ActionEvent arg0) throws RemoteException, Exception {
		/* COMPLETE */
		getServer().stop(id);
		System.exit(0);
	}
	
	protected void rdbtnAnswer1actionPerformed(ActionEvent arg0) {
		/* COMPLETE */
		this.selectAnswer(1);
	}
	
	protected void rdbtnAnswer2actionPerformed(ActionEvent arg0) {
		/* COMPLETE */
		this.selectAnswer(2);
	}
	
	protected void rdbtnAnswer3actionPerformed(ActionEvent arg0) {
		/* COMPLETE */
		this.selectAnswer(3);
	}
	
	protected void rdbtnAnswer4actionPerformed(ActionEvent arg0) {
		/* COMPLETE */
		this.selectAnswer(4);
	}
		
	
	protected void btnCheckactionPerformed(ActionEvent arg0) {
		/* COMPLETE */
		this.checkAnswer();
	}
	
	
	// ----------------- My stuff starts here --------------
	
	/* COMPLETE
	 * add here other non-GUI attributes and helper methods 
	 */

	private int id;
	private Question currentQuestion;
	private int currentSelected = -1;

	private static TrivialSolitaire server;

	private static TrivialSolitaire getServer() {
		if (server != null)
			return server;
		try {
			server = (TrivialSolitaire) Naming.lookup("rmi://localhost:1998/TrivialSolitaire");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "Error connecting to server", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return server;	
	}

	private void setQuestion(Question question) {
		if (question == null) {
			JOptionPane.showMessageDialog(this, "NO MORE QUESTIONS OF THIS KIND", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		lblQuestion.setText(question.getTheQuestion());
		this.setAnswers(question.getAnswers());

		lblCorrect.setVisible(false);
		
		this.buttonGroup.clearSelection();

		btnCheck.setEnabled(true);
		btnPlayNoMore.setEnabled(true);

		this.currentQuestion = question;
		this.selectAnswer(-1);
	}

	private void selectAnswer(int answer) {
		this.currentSelected = answer;
	}

	private void checkAnswer() {
		if (this.currentSelected == this.currentQuestion.getCorrect()) {
			lblCorrect.setText("Correct!");
		} else {
			lblCorrect.setText("Incorrect, the correct answer was " + this.currentQuestion.getCorrect());
		}
		lblCorrect.setVisible(true);
	}

	private void setAnswers(String[] answers) {
		rdbtnAnswer1.setText(answers[0]);
		rdbtnAnswer2.setText(answers[1]);
		rdbtnAnswer3.setText(answers[2]);
		rdbtnAnswer4.setText(answers[3]);
	}

	private void enableRadioButtons() {
		rdbtnAnswer1.setEnabled(true);
		rdbtnAnswer2.setEnabled(true);
		rdbtnAnswer3.setEnabled(true);
		rdbtnAnswer4.setEnabled(true);
	}
}
