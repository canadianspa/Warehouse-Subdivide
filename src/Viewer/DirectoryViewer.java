package Viewer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Directory;
import Model.Node;
import Model.Warehouse;
import Model.Zone;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;

public class DirectoryViewer extends JFrame {

	protected JPanel contentPane;
	private JTextField addField;
	private JTextField removeField;
	private JTextField findField;
	protected JPanel panel_2;
	protected JFrame openedWarehouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {



		Directory canSpa = new Directory("Canadian Spa Company"); 
		Node ccanspa = new Node(canSpa); 
		Directory l1 = new Directory("Canada House");
		Directory l2 = new Directory("Verran");
		Node nl1 = new Node(l1,ccanspa); 
		Node nl2 = new Node(l2,ccanspa); 
		char[] dir = {'u','r','u','l','d'};
		int[] mag = {100 , 200,100,300,200};
		char[] dir2 = {'u','r','d','l','d'};
		int[] mag2 = {300 , 500,100,100,300};
		int[][] zoneLocs = {{150,200},{50,150},{150,300},{200,200}};
		Warehouse w1 = new Warehouse("Below Customer Service",dir,mag,zoneLocs); 
		Warehouse w2 = new Warehouse("Below Sales",dir2,mag2,zoneLocs); 
		Warehouse w3 = new Warehouse("Parts",dir,mag,zoneLocs); 
		Node nw1 = new Node(w1,nl1); 
		Node nw2 = new Node(w2,nl1); 
		Node nw3 = new Node(w3,nl1);
	
		Zone z1 = new Zone("A1");
		Zone z2 = new Zone("A2");
		Zone z3 = new Zone("B1");
		Zone z4 = new Zone("D1");
		
		Node nz1 = new Node(z1,nw1);
		Node nz2 = new Node(z2,nw1);
		Node nz3 = new Node(z3,nw1);
		Node nz4 = new Node(z4,nw1);
		
		
		//Warehouse w = new Warehouse("CanadaHouse",dir,mag);
		//Node n = new Node(w);

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

	public void openNewViewer(Node n)
	{

		if(openedWarehouse != null)
		{
			openedWarehouse.dispose();
		}
		DirectoryViewer frame = new DirectoryViewer(n);
		frame.setVisible(true);
		setVisible(false);
		dispose();
	

	}
	/**
	 * Create the frame.
	 */
	public DirectoryViewer(Node n) {
		setTitle(n.getData().getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1425, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 10, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(1, n.getChildren().size(), 0, 0));

		JLabel lblNewLabel = new JLabel(n.getData().getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.NORTH);
		UIManager.put("Label.font", new Font("Dialog", Font.BOLD, 20));
		UIManager.put("Button.font", new Font("Dialog", Font.BOLD, 20));
		panel_2 = new JPanel();
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
				openNewViewer(no);

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
						openNewViewer(no);

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
				openNewViewer(n.getParent());

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
				openNewViewer(n.getRoot());

			}
		});
		panel_2.add(btnNewButton);

		for(int i = 0; i < n.getChildren().size(); i ++)
		{
			JButton childButton = new JButton(n.getChildren().get(i).getData().getName());
			childButton.addActionListener(new ActionListener() {
				private int anonVar;
				public void actionPerformed(ActionEvent arg0) {
					openNewViewer(n.getChildren().get(anonVar));


				}
				private ActionListener init(int var){
					anonVar = var;
					return this;
				}
			}.init(i));
			panel.add(childButton);
		}

		if(n.getData() instanceof Warehouse)
		{
			panel_2.setLayout(new GridLayout(0, 3, 0, 0));

			JLabel lblNewLabel_4 = new JLabel("See Warehouse");
			panel_2.add(lblNewLabel_4);


			panel_2.add(new JPanel());

			JButton seeButton = new JButton("Open");
			seeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(openedWarehouse != null)
					{
						openedWarehouse.dispose();
					}
					ArrayList<String> zoneNames = new ArrayList<String>();
					for(Node z: n.getChildren())
					{
						zoneNames.add(z.getData().getName());
						
					}
					WarehouseViewer frame = new WarehouseViewer((Warehouse) (n.getData()),zoneNames);
					frame.setVisible(true);
					openedWarehouse = frame;


				}
			});
			panel_2.add(seeButton);
		}

	}

}
