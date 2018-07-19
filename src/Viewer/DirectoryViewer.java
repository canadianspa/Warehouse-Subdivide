package Viewer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Directory;
import Model.Node;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class DirectoryViewer extends JFrame {

	private JPanel contentPane;
	private JTextField addField;
	private JTextField removeField;
	private JTextField findField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {



		Directory canSpa = new Directory("Canadian Spa"); 
		Node ccanspa = new Node(canSpa); 
		Directory l1 = new Directory("l1");
		Directory l2 = new Directory("l2");
		Node nl1 = new Node(l1,ccanspa); 
		Node nl2 = new Node(l2,ccanspa); 
		Directory w1 = new Directory("w1"); 
		Directory w2 = new Directory("w2"); 
		Directory w3 = new Directory("w3"); 
		Node nw1 = new Node(w1,nl1); 
		Node nw2 = new Node(w2,nl2); 
		Node nw3 = new Node(w3,nl2); 


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DirectoryViewer frame = new DirectoryViewer(ccanspa);
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
	public DirectoryViewer(Node n) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(1, n.getChildren().size(), 0, 0));

		JLabel lblNewLabel = new JLabel(n.getData().getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Add");
		panel_2.add(lblNewLabel_1);


		addField = new JTextField();
		panel_2.add(addField);
		addField.setColumns(10);

		JButton addButton = new JButton("Submit");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Directory d = new Directory(addField.getText());
				Node no = new Node(d,n);
				DirectoryViewer frame = new DirectoryViewer(n);
				frame.setVisible(true);
				setVisible(false);
			}
		});


		panel_2.add(addButton);

		JLabel lblRemoveDirectory = new JLabel("Remove");
		panel_2.add(lblRemoveDirectory);

		removeField = new JTextField();
		panel_2.add(removeField);
		removeField.setColumns(10);

		JButton removeButton = new JButton("Submit");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Node> it = n.getChildren().iterator();
				while (it.hasNext()) {
					Node no = it.next(); 
					if(no.getData().getName().equals(removeField.getText()))
					{
						it.remove();
						DirectoryViewer frame = new DirectoryViewer(n);
						frame.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		panel_2.add(removeButton);

		JLabel lblFindItem = new JLabel("Find Item");
		panel_2.add(lblFindItem);

		findField = new JTextField();
		panel_2.add(findField);
		findField.setColumns(10);

		JButton findButton = new JButton("Submit");
		panel_2.add(findButton);
		
		JLabel lblUpOneDirectory = new JLabel("Up one Directory");
		panel_2.add(lblUpOneDirectory);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JButton button = new JButton("Go");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectoryViewer frame = new DirectoryViewer(n.getParent());
				frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panel_2.add(button);
		
		JLabel lblNewLabel_2 = new JLabel("Home");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectoryViewer frame = new DirectoryViewer(n.getRoot());
				frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panel_2.add(btnNewButton);

		for(int i = 0; i < n.getChildren().size(); i ++)
		{
			JButton childButton = new JButton(n.getChildren().get(i).getData().getName());
			childButton.addActionListener(new ActionListener() {
				private int anonVar;
				public void actionPerformed(ActionEvent arg0) {
					DirectoryViewer frame = new DirectoryViewer(n.getChildren().get(anonVar));
					frame.setVisible(true);
					setVisible(false);
					dispose();

				}
				private ActionListener init(int var){
					anonVar = var;
					return this;
				}
			}.init(i));
			panel.add(childButton);
		}


	}

}
