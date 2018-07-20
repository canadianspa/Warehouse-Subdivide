package Viewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Model.Warehouse;

public class WarehouseDesign extends JPanel {


	Warehouse w;
	ArrayList<String> zoneNames;
	/**
	 * Create the panel.
	 * @param zoneNames 
	 */
	public WarehouseDesign(Warehouse w, ArrayList<String> zoneNames) {
		super();
		this.w = w;
		this.zoneNames = zoneNames;
		setBackground(Color.white);
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;    
		//find highest left right up and down, to allow me to normalise 
		double maxr = 0; 
		double maxl = 0;
		double maxu = 0;
		double maxd = 0;
		int curr = 0; 
		int curl = 0;
		int curu = 0;
		int curd = 0;

		for(int i = 0; i < w.getMagnitude().length; i ++)
		{
			char dir = w.getDirections()[i];
			int mag = w.getMagnitude()[i];
			if(dir == 'u')
			{
				curu += mag;
				curd -= mag;
				if(curu > maxu)
				{
					maxu = curu;
				}
			}
			else if(dir == 'd')
			{
				curd += mag;
				curu -= mag;
				if(curd > maxd)
				{
					maxd = curd;
				}
			}
			else if(dir == 'l')
			{
				curl += mag;
				curr -= mag;
				if(curl > maxl)
				{
					maxl = curl;
				}
			}
			else if(dir == 'r')
			{
				curr += mag;
				curl -= mag;
				if(curr > maxr)
				{
					maxr = curr;
				}
			}
		}


		double ySizeFactor = (maxu + maxd) / (double)this.getHeight();
		double xSizeFactor = (maxr + maxl) / (double) this.getWidth();




		double largerFactor = Math.max(xSizeFactor, ySizeFactor);
		largerFactor *= 1.1;

		int firsty = (int) ((maxu / (maxd + maxu)) * this.getHeight()) - 10;
		int  firstx = (int) ((maxl / (maxr + maxl)) * this.getWidth()) + 10;

		int drawn = 0;
		int toDraw = 0;

		
	
		g2D.setColor(Color.black);
		
		int lastx = firstx;
		int lasty = firsty;
		//draw the normalised shape
		for(int i = 0; i < w.getMagnitude().length; i ++)
		{
			char dir = w.getDirections()[i];
			int mag = w.getMagnitude()[i];
			toDraw += mag;

			mag /= largerFactor;

			g2D.setStroke(new BasicStroke(8F));
			if(dir == 'u')
			{
				g2D.drawLine(lastx, lasty, lastx, lasty - mag);
				lasty = lasty - mag;
			}
			else if(dir == 'd')
			{
				g2D.drawLine(lastx, lasty, lastx, lasty + mag);			
				lasty = lasty + mag;
			}
			else if(dir == 'l')
			{
				g2D.drawLine(lastx, lasty, lastx - mag, lasty);
				lastx = lastx - mag;
			}
			else if(dir == 'r')
			{
				g2D.drawLine(lastx, lasty, lastx + mag, lasty);
				lastx = lastx + mag;
			}
			drawn = toDraw;

		}

		//draw zones
		g2D.setColor(Color.red);
		for(int i = 0; i < w.getZoneLocs().length; i ++)
		{
			g2D.setFont(new Font("Dialog", Font.BOLD, 25));
			g2D.drawString(zoneNames.get(i), firstx + (int) (w.getZoneLocs()[i][0] / largerFactor)  , firsty - (int) (w.getZoneLocs()[i][1] / largerFactor));
		}

	}



}
