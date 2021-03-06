import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.theeyetribe.client.*;
import com.theeyetribe.client.GazeManager.ApiVersion;
import com.theeyetribe.client.GazeManager.ClientMode;
import com.theeyetribe.client.data.*;
import com.theeyetribe.client.reply.*;
import com.theeyetribe.client.request.*;

public class TETSimple
{
	
    public static void main(String[] args)
    {
    	
    	final GazeManager gm = GazeManager.getInstance();
    	gm.activate(ApiVersion.VERSION_1_0, ClientMode.PUSH);
    	
    	GazeListener gazeListener = new GazeListener(new Frame());
        
        gm.addGazeListener(gazeListener);
        
        //TODO: Do awesome gaze control wizardry
        
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });
    }
    
    private static class GazeListener implements IGazeListener
    {
    	Frame frame;
    	
    	public GazeListener(Frame frame){
    		this.frame = frame;
    	}
    	
        @Override
        public void onGazeUpdate(GazeData gazeData)
        {
            frame.setCoordinates(gazeData.smoothedCoordinates.x, gazeData.smoothedCoordinates.y);
            frame.repaint();
        	System.out.println(gazeData.toString());
        }
    }
    
    private static class Frame extends JPanel{
    	
    	JFrame frame;
    	
    	Random random;
    	
    	double x;
    	double y;
    	double xBuff;
    	double yBuff;
    	double killx;
    	double killy;
    	
    	public Frame(){
    		
    		super();
    		
    		frame = new JFrame();
    		
    		random = new Random();
    		
    		x = 0;
    		y = 0;
    		xBuff = -50;
    		yBuff = -50;
    		killx = 100;
    		killy = 100;
    		
    		
    		
    		
    		this.setPreferredSize(new Dimension(1400,800));
    		this.setBackground(Color.darkGray);
    		this.setVisible(true);
    		
    		frame.setSize(new Dimension(1400,800));
    		frame.add(this);
    		frame.pack();
    		frame.setVisible(true);
    		
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    	}
    	
    	@Override
    	public void paintComponent(Graphics graphics){
    		
    		super.paintComponent(graphics);
    		
    		if(x < killx + 100 && x > killx - 100 && 
    				y < killy + 100 && y > killy - 100){
    			killx = random.nextInt(1400);
    			killy = random.nextInt(800);
    		
    		}
    		
    		graphics.setColor(Color.blue);
    		graphics.fillRect((int)(killx), (int)(killy), 20, 20);
    		
    		graphics.setColor(Color.red);
    		graphics.fillOval((int) (x), (int)(y), 100, 100);
    		
    	
    	}
    	
    	public void setCoordinates(double x, double y){
    		this.x = x + xBuff;
    		this.y = y + xBuff;
    	}
    	
    }
}