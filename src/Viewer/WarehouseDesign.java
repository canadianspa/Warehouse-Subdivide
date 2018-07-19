package Viewer;

import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Warehouse;

public class WarehouseDesign extends JPanel {

	
	Warehouse w;
	/**
	 * Create the panel.
	 */
	public WarehouseDesign(Warehouse w) {
		
		
		this.w = w;

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
        System.out.println(maxu);
        System.out.println(maxd);
        System.out.println(maxl);
        System.out.println(maxr);

        double ySizeFactor = (maxu + maxd) / (double)this.getHeight();
        double xSizeFactor = (maxr + maxl) / (double) this.getWidth();
        System.out.println(ySizeFactor);
        System.out.println(xSizeFactor);
        
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());
        
        double largerFactor = Math.max(xSizeFactor, ySizeFactor);
        largerFactor *= 1.1;
       
        int lasty = (int) ((maxu / (maxd + maxu)) * this.getHeight()) - 10;
        int  lastx = (int) ((maxl / (maxr + maxl)) * this.getWidth()) + 10;
       
        //draw the normalised shape
        for(int i = 0; i < w.getMagnitude().length; i ++)
        {
        	char dir = w.getDirections()[i];
        	int mag = w.getMagnitude()[i];
        	mag /= largerFactor;

        	if(dir == 'u')
        	{
        		g.drawLine(lastx, lasty, lastx, lasty - mag);
        		lasty = lasty - mag;
        	}
        	else if(dir == 'd')
        	{
        		g.drawLine(lastx, lasty, lastx, lasty + mag);
        		lasty = lasty + mag;
        	}
        	else if(dir == 'l')
        	{
        		g.drawLine(lastx, lasty, lastx - mag, lasty);
        		lastx = lastx - mag;
        	}
        	else if(dir == 'r')
        	{
        		g.drawLine(lastx, lasty, lastx + mag, lasty);
        		lastx = lastx + mag;
        	}
        }
       
    }
	

}
