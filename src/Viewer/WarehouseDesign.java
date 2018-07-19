package Viewer;

import java.awt.Graphics;
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
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

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

		int lasty = (int) ((maxu / (maxd + maxu)) * this.getHeight()) - 10;
		int  lastx = (int) ((maxl / (maxr + maxl)) * this.getWidth()) + 10;

		int drawn = 0;
		int toDraw = 0;
		int countZonePainted =0;

		//draw the normalised shape
		for(int i = 0; i < w.getMagnitude().length; i ++)
		{
			char dir = w.getDirections()[i];
			int mag = w.getMagnitude()[i];
			//draw zones on
			toDraw += mag;


			ArrayList<Double> drawRatioBuffer = new ArrayList<Double>();
			for(int z : w.getZoneLocs())
			{
				if(z > drawn && z <= toDraw)
				{
					drawRatioBuffer.add(((double) z - (double) drawn) / ((double) toDraw - (double) drawn));
				}
			}
			mag /= largerFactor;


			if(dir == 'u')
			{
				g.drawLine(lastx, lasty, lastx, lasty - mag);
				for(double d: drawRatioBuffer)
				{
					g.drawString(zoneNames.get(countZonePainted), lastx,  lasty - (int) (mag*d));
					countZonePainted ++;
				}
				lasty = lasty - mag;
			}
			else if(dir == 'd')
			{
				g.drawLine(lastx, lasty, lastx, lasty + mag);
				for(double d: drawRatioBuffer)
				{

					g.drawString(zoneNames.get(countZonePainted), lastx,  lasty + (int) (mag*d));
					countZonePainted ++;

				}
				lasty = lasty + mag;
			}
			else if(dir == 'l')
			{
				g.drawLine(lastx, lasty, lastx - mag, lasty);
				for(double d: drawRatioBuffer)
				{

					g.drawString(zoneNames.get(countZonePainted), lastx - (int) (mag*d),  lasty );
					countZonePainted ++;

				}
				lastx = lastx - mag;
			}
			else if(dir == 'r')
			{
				g.drawLine(lastx, lasty, lastx + mag, lasty);
				for(double d: drawRatioBuffer)
				{
				
					g.drawString(zoneNames.get(countZonePainted), lastx + (int) (mag*d),  lasty );
					countZonePainted ++;

				}
				lastx = lastx + mag;
			}
			drawn = toDraw;

		}

	}



}
