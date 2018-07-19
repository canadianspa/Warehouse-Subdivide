package Viewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Warehouse;

public class WarehouseViewer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public WarehouseViewer(Warehouse w) {
		setTitle(w.getName());
		setBounds(500, 500, 700, 700);
		contentPane = new WarehouseDesign(w);
		contentPane.setLayout(new FlowLayout());
		
		setContentPane(contentPane);
	}

}
