// Created by Xiangru Zhou
// 10-15-2016


import javax.swing.*;  
import java.awt.event.*;  
import java.awt.*;
import java.util.Random;


public class Tetris extends JFrame{
	
	   public static void main(String[] args) {			   
	       Tetris startTetris = new Tetris();
	    }

	    static double speedFactor = 0.1; 
	    static int numRowForDifficulty = 20;     
	    static int scoreFactor = 1;      
	    static int shapeCount = 7;            

	    public boolean whetherPause = true;
	    int checkTetrisSize = 0;  // checkTetrisSize = 1: means 10 * 10
                                  // checkTetrisSize = 2: means 20 * 20

	    
	    final static TetrisShape1 tenToTwentySizeShape = new TetrisShape1();
	    final static TetrisShape2 twentyToTwentySizeShape = new TetrisShape2();
	    
	    // used to control fallen speed here. Can change 350 to 500.
	    final static Timer timerForTenToTwenty = new Timer(350, tenToTwentySizeShape.new FallenTimerListener());	    
	    final static Timer timerForTwentyToTwenty = new Timer(350, twentyToTwentySizeShape.new FallenTimerListener());

	    int rightPauseBorderForTenToTwenty = 220;
	    int rightPauseBorderForTwentyToTwenty = 420;
	    int bottomPauseBorderForTenToTwenty = 420;
	    
	    int oriXForTenToTwenty = 73;
	    int oriYForTenToTwenty = 153;
	    int widthForTenToTwenty = 103;
	    int heightForTenToTwenty = 103;
	    int fontSizeForTenToTwenty = 30;

	    int oriXTwentyToTwenty = 173;
	    int oriYTwentyToTwenty = 153;
	    int widthTwentyToTwenty = 103;
	    int heightTwentyToTwenty = 103;
	    int fontSizeTwentyToTwenty = 30;
	    	
	    public Tetris() { 	    			 
	        final JFrame frame = new JFrame("Tetris");
	        final JMenuBar menu = new JMenuBar();
	        frame.setJMenuBar(menu);

	        final JMenu sizeMenu = new JMenu("Tetris Size");
	        JMenuItem tenToTwentySizeItem = new JMenuItem("10 * 20 Size");
	        JMenuItem twentyToTwentySizeItem = new JMenuItem("20 * 20 Size");	        
	        
	        sizeMenu.add(tenToTwentySizeItem);    
	        sizeMenu.add(twentyToTwentySizeItem); 
	        	       
	        tenToTwentySizeItem.addActionListener (new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		checkTetrisSize = 1;
                    frame.remove(twentyToTwentySizeShape);
                    timerForTwentyToTwenty.stop();
                    frame.add(tenToTwentySizeShape);   
                    timerForTenToTwenty.start();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100,100,400,500);
                    frame.setVisible(true);
                    frame.setResizable(false);                   
                }
            });
	        
	        twentyToTwentySizeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		checkTetrisSize = 2;
                    frame.remove(tenToTwentySizeShape);
                    timerForTenToTwenty.stop();
                    frame.add(twentyToTwentySizeShape);   
                    timerForTwentyToTwenty.start();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100,100,600,500);
                    frame.setVisible(true);
                    frame.setResizable(false);                    
                }
            });	       	              
	        menu.add(sizeMenu);
	                    	    
	        
	        JMenu setMenu = new JMenu("Settings");	        
	        JMenu speedFactorMenu = new JMenu("Speed Factor");
	        JMenuItem pointOneSpeedFactorItem = new JMenuItem("0.1");
	        JMenuItem pointTwoSpeedFactorItem = new JMenuItem("0.2");
	        JMenuItem pointThreeSpeedFactorItem = new JMenuItem("0.3");
	        JMenuItem pointFourSpeedFactorItem = new JMenuItem("0.4");
	        JMenuItem pointFiveSpeedFactorItem = new JMenuItem("0.5");
	        JMenuItem pointSixSpeedFactorItem = new JMenuItem("0.6");
	        JMenuItem pointSevenSpeedFactorItem = new JMenuItem("0.7");
	        JMenuItem pointEightSpeedFactorItem = new JMenuItem("0.8");
	        JMenuItem pointNineSpeedFactorItem = new JMenuItem("0.9");
	        JMenuItem pointTenSpeedFactorItem = new JMenuItem("1.0");
	        speedFactorMenu.add(pointOneSpeedFactorItem);
	        speedFactorMenu.add(pointTwoSpeedFactorItem);
	        speedFactorMenu.add(pointThreeSpeedFactorItem);
	        speedFactorMenu.add(pointFourSpeedFactorItem);
	        speedFactorMenu.add(pointFiveSpeedFactorItem);
	        speedFactorMenu.add(pointSixSpeedFactorItem);
	        speedFactorMenu.add(pointSevenSpeedFactorItem);
	        speedFactorMenu.add(pointEightSpeedFactorItem);
	        speedFactorMenu.add(pointNineSpeedFactorItem);
	        speedFactorMenu.add(pointTenSpeedFactorItem);
	        setMenu.add(speedFactorMenu);

	        pointOneSpeedFactorItem.addActionListener(new ActionListener(){	        	
	        	@Override
                public void actionPerformed(ActionEvent e) {	        		
                    speedFactor = 0.1;
                    }
	            });
	        pointTwoSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.2;
	                }
	            });
	        pointThreeSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.3;
	                }
	            });
	        pointFourSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.4;
	                }
	            });
	        pointFiveSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.5;
	                }
	            });
	        pointSixSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.6;
	                }
	            });
	        pointSevenSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.7;
	                }
	            });
	        pointEightSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.8;
	                }
	            });
	        pointNineSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 0.9;
	                }
	            });
	        pointTenSpeedFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    speedFactor = 1;
	                }
	            });
  

	        JMenu numRowsMenu = new JMenu("# of rows for each level");    
	        JMenuItem twentyLinesItem = new JMenuItem("20 lines");
	        JMenuItem thirtyLinesItem = new JMenuItem("30 lines");
	        JMenuItem fortyLinesItem = new JMenuItem("40 lines");
	        JMenuItem fiftyLinesItem = new JMenuItem("50 lines");
	        numRowsMenu.add(twentyLinesItem);
	        numRowsMenu.add(thirtyLinesItem);
	        numRowsMenu.add(fortyLinesItem);
	        numRowsMenu.add(fiftyLinesItem);
	        setMenu.add(numRowsMenu);

	        twentyLinesItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		numRowForDifficulty = 20;
	                }
	            });
	        thirtyLinesItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		numRowForDifficulty = 30;
	                }
	            });
	        fortyLinesItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		numRowForDifficulty = 40;
	                }
	            });
	        fiftyLinesItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
	        		numRowForDifficulty = 50;
	                }
	            });
	        	        
	        JMenu scoreFactorMenu = new JMenu("Scoring Factor");    
	        JMenuItem oneScoreFactorItem = new JMenuItem("1 * reward");
	        JMenuItem twoScoreFactorItem = new JMenuItem("2 * reward");
	        JMenuItem threeScoreFactorItem = new JMenuItem("3 * reward");
	        JMenuItem fourScoreFactorItem = new JMenuItem("4 * reward");
	        JMenuItem fiveScoreFactorItem = new JMenuItem("5 * reward");
	        JMenuItem sixScoreFactorItem = new JMenuItem("6 * reward");
	        JMenuItem sevenScoreFactorItem = new JMenuItem("7 * reward");
	        JMenuItem eightScoreFactorItem = new JMenuItem("8 * reward");
	        JMenuItem nineScoreFactorItem = new JMenuItem("9 * reward");
	        JMenuItem tenScoreFactorItem = new JMenuItem("10 * reward");
	        scoreFactorMenu.add(oneScoreFactorItem);
	        scoreFactorMenu.add(twoScoreFactorItem);
	        scoreFactorMenu.add(threeScoreFactorItem);
	        scoreFactorMenu.add(fourScoreFactorItem);
	        scoreFactorMenu.add(fiveScoreFactorItem);
	        scoreFactorMenu.add(sixScoreFactorItem);
	        scoreFactorMenu.add(sevenScoreFactorItem);
	        scoreFactorMenu.add(eightScoreFactorItem);
	        scoreFactorMenu.add(nineScoreFactorItem);
	        scoreFactorMenu.add(tenScoreFactorItem);
	        
	        setMenu.add(scoreFactorMenu);

	        oneScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 1;
	                }
	            });
	        twoScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 2;
	                }
	            });
	        threeScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 3;
	                }
	            });
	        fourScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 4;
	                }
	            });
	        fiveScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 5;
	                }
	            });
	        sixScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 6;
	                }
	            });
	        sevenScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 7;
	                }
	            });
	        eightScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 8;
	                }
	            });
	        nineScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 9;
	                }
	            });
	        tenScoreFactorItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    scoreFactor = 10;
	                }
	            });
	        menu.add(setMenu);

	        
	        /* adjust size for elderly players*/
	        JMenu adjustSizeMenu = new JMenu("Adjust Size");
	        
	        JMenuItem initialSizeItem = new JMenuItem("Initial Size");
	        JMenuItem onePointTwoMultiplySizeItem = new JMenuItem("1.2 * Size");
	        JMenuItem onePointFiveMultiplySizeItem = new JMenuItem("1.5 * Size");
	        
	        adjustSizeMenu.add(initialSizeItem);
	        adjustSizeMenu.add(onePointTwoMultiplySizeItem);
	        adjustSizeMenu.add(onePointFiveMultiplySizeItem);
	        
	        JButton tenToTwentySizeQuitButton = new JButton("QUIT");
	        JButton twentyToTwentySizeQuitButton = new JButton("QUIT");
	        

	        initialSizeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    if (checkTetrisSize == 1) {                   	
                        frame.setBounds(100,100,400,500);
                        Rectangle bound1 = frame.getBounds();
                        int buttonOriX1 = bound1.width * 13/20;
        	            int buttonOriY1 = bound1.width * 7/8;
        	            int buttonWidth1 = bound1.width / 4;
        	            int buttonHeight1 = bound1.width / 10;
        	            int buttonFontSize1 = bound1.width /20;
                        
                        tenToTwentySizeQuitButton.setBounds(buttonOriX1, buttonOriY1, buttonWidth1, buttonHeight1);
                        tenToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize1));
                        rightPauseBorderForTenToTwenty = 215;
                        bottomPauseBorderForTenToTwenty = 413;
                        oriXForTenToTwenty = 73;
                        oriYForTenToTwenty = 153;
                        widthForTenToTwenty = 103;
                        heightForTenToTwenty = 103;
                        fontSizeForTenToTwenty = 30;
                    }                     
                    else if (checkTetrisSize == 2) {                                
                        frame.setBounds(100,100,600,500);
                        Rectangle bound2 = frame.getBounds();
                        int buttonOriX2 = bound2.width * 23/30;
        	            int buttonOriY2 = bound2.width * 7/12;
        	            int buttonWidth2 = bound2.width / 6;
        	            int buttonHeight2 = bound2.width / 15;
        	            int buttonFontSize2 = bound2.width /30;
                                               
                        twentyToTwentySizeQuitButton.setBounds(buttonOriX2, buttonOriY2, buttonWidth2, buttonHeight2);
                        twentyToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize2));
                        rightPauseBorderForTwentyToTwenty = 415;
                        bottomPauseBorderForTenToTwenty = 413;
                        oriXTwentyToTwenty = 173;
                        oriYTwentyToTwenty = 153;
                        widthTwentyToTwenty = 103;
                        heightTwentyToTwenty = 103;
                        fontSizeTwentyToTwenty = 30;
                    } 
                    
                  }
	            });

	        onePointTwoMultiplySizeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    if (checkTetrisSize == 1) {                   	
                        frame.setBounds(100,100,480,600);
                        Rectangle bound1 = frame.getBounds();
                        int buttonOriX1 = bound1.width * 13/20;
        	            int buttonOriY1 = bound1.width * 7/8;
        	            int buttonWidth1 = bound1.width / 4;
        	            int buttonHeight1 = bound1.width / 10;
        	            int buttonFontSize1 = bound1.width /20;                       
                                             
                        tenToTwentySizeQuitButton.setBounds(buttonOriX1, buttonOriY1, buttonWidth1, buttonHeight1);
                        tenToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize1));
                        rightPauseBorderForTenToTwenty = 260;
                        bottomPauseBorderForTenToTwenty = 496;
                        oriXForTenToTwenty = 87;
                        oriYForTenToTwenty = 183;
                        widthForTenToTwenty = 123;
                        heightForTenToTwenty = 123;
                        fontSizeForTenToTwenty = 36;
                    }                    
                    else if (checkTetrisSize == 2) {
                        frame.setBounds(100,100,720,600);
                        Rectangle bound2 = frame.getBounds();
                        int buttonOriX2 = bound2.width * 23/30;
        	            int buttonOriY2 = bound2.width * 7/12;
        	            int buttonWidth2 = bound2.width / 6;
        	            int buttonHeight2 = bound2.width / 15;
        	            int buttonFontSize2 = bound2.width /30;
                                                                                               
                        twentyToTwentySizeQuitButton.setBounds(buttonOriX2, buttonOriY2, buttonWidth2, buttonHeight2);
                        twentyToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize2));                     
                        rightPauseBorderForTwentyToTwenty = 500;
                        bottomPauseBorderForTenToTwenty = 496;
                        oriXTwentyToTwenty = 187;
                        oriYTwentyToTwenty = 183;
                        widthTwentyToTwenty = 123;
                        heightTwentyToTwenty = 123;
                        fontSizeTwentyToTwenty = 36;
                    }
                   
	              }
	            });

	        onePointFiveMultiplySizeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    if (checkTetrisSize == 1) {                   	
                        frame.setBounds(100,100,600,750);
                        Rectangle bound1 = frame.getBounds();
                        int buttonOriX1 = bound1.width * 13/20;
        	            int buttonOriY1 = bound1.width * 7/8;
        	            int buttonWidth1 = bound1.width / 4;
        	            int buttonHeight1 = bound1.width / 10;
        	            int buttonFontSize1 = bound1.width /20;
                        
                        tenToTwentySizeQuitButton.setBounds(buttonOriX1, buttonOriY1, buttonWidth1, buttonHeight1);
                        tenToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize1));
                        rightPauseBorderForTenToTwenty = 325;
                        bottomPauseBorderForTenToTwenty = 620;
                        oriXForTenToTwenty = 108;
                        oriYForTenToTwenty = 228;
                        widthForTenToTwenty = 153;
                        heightForTenToTwenty = 153;
                        fontSizeForTenToTwenty = 45;
                    }                   
                    else if (checkTetrisSize == 2) {
                        frame.setBounds(100,100,900,750);
                        Rectangle bound2 = frame.getBounds();
                        int buttonOriX2 = bound2.width * 23/30;
        	            int buttonOriY2 = bound2.width * 7/12;
        	            int buttonWidth2 = bound2.width / 6;
        	            int buttonHeight2 = bound2.width / 15;
        	            int buttonFontSize2 = bound2.width /30;
                        
                        
                        twentyToTwentySizeQuitButton.setBounds(buttonOriX2, buttonOriY2, buttonWidth2, buttonHeight2);
                        twentyToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, buttonFontSize2));
                        rightPauseBorderForTwentyToTwenty = 625;
                        bottomPauseBorderForTenToTwenty = 620;
                        oriXTwentyToTwenty = 233;
                        oriYTwentyToTwenty = 228;
                        widthTwentyToTwenty = 153;
                        heightTwentyToTwenty = 153;
                        fontSizeTwentyToTwenty = 45;
                    }
                  
                  }
	            });

	        tenToTwentySizeQuitButton.setBounds(260, 350, 100, 40);
	        tenToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, 20));

	        tenToTwentySizeQuitButton.addActionListener(new ActionListener() {
	        	@Override
                public void actionPerformed(ActionEvent e) {	        		
                    if (e.getActionCommand().equals("QUIT"))
                        System.exit(0);
                    }
                });
	        tenToTwentySizeShape.setLayout(null);
	        tenToTwentySizeShape.add(tenToTwentySizeQuitButton);

	        twentyToTwentySizeQuitButton.setBounds(460, 350, 100, 40);
	        twentyToTwentySizeQuitButton.setFont(new Font("Serif", Font.PLAIN, 20));

	        twentyToTwentySizeQuitButton.addActionListener(new ActionListener() {
	        	@Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("QUIT"))
                        System.exit(0);
                    }
	            });
	        twentyToTwentySizeShape.setLayout(null);
	        twentyToTwentySizeShape.add(twentyToTwentySizeQuitButton);

	        menu.add(adjustSizeMenu);
	        	        
	        	        
            /* set new shapes*/
	        JMenu newShapesMenu = new JMenu("Add New Shapes");
	        JMenuItem noNewShapeItem = new JMenuItem("No new shapes added");
	        JMenuItem oneNewShapeItem = new JMenuItem("1 new shape added");
	        JMenuItem twoNewShapeItem = new JMenuItem("2 new shapes added");
	        JMenuItem threeNewShapeItem = new JMenuItem("3 new shapes added");
	        newShapesMenu.add(noNewShapeItem);
	        newShapesMenu.add(oneNewShapeItem);
	        newShapesMenu.add(twoNewShapeItem);
	        newShapesMenu.add(threeNewShapeItem);
	       
	        noNewShapeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                	shapeCount = 7;
                    }
	            });

	        oneNewShapeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                	shapeCount = 8;
	                }
	            });

	        twoNewShapeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                	shapeCount = 9;
	                }
	            });

	        threeNewShapeItem.addActionListener(new ActionListener(){
	        	@Override
                public void actionPerformed(ActionEvent e) {
                	shapeCount = 10;
	                }
	            });
	        menu.add(newShapesMenu);
	        	        
	        
	        tenToTwentySizeShape.addMouseListener(new MouseAdapter() { 
	        	@Override
                public void mousePressed(MouseEvent e){  
                    if (!whetherPause && e.getButton() == java.awt.event.MouseEvent.BUTTON1)                
                    	tenToTwentySizeShape.moveLeft();
                    else 
                    	if (!whetherPause && e.getButton() == java.awt.event.MouseEvent.BUTTON3)
                    		tenToTwentySizeShape.moveRight();                 
                    }                   
	            });
	        
	       
	        final JLabel tenTwentyPauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);

	        tenToTwentySizeShape.add(tenTwentyPauseLabel);
	        tenTwentyPauseLabel.setVisible(true);
	        
	        tenToTwentySizeShape.addMouseMotionListener(new MouseAdapter() {
	        	@Override
                public void mouseMoved(MouseEvent e) {	        		
                    if ((e.getX() >= 20 && e.getX() <= rightPauseBorderForTenToTwenty) && e.getY() <= bottomPauseBorderForTenToTwenty){                   	
                    	timerForTenToTwenty.stop();
                    	tenTwentyPauseLabel.setFont(new Font("Serif", Font.PLAIN, fontSizeForTenToTwenty));
                    	tenTwentyPauseLabel.setBounds(oriXForTenToTwenty, oriYForTenToTwenty, widthForTenToTwenty, heightForTenToTwenty);
                    	tenTwentyPauseLabel.setVisible(true);
                        whetherPause = true;
                    }
                    else {                    	
                    	timerForTenToTwenty.start(); 
                    	tenTwentyPauseLabel.setVisible(false);
                        whetherPause = false;
                        }
	                }	        		
	            });  
	        

	        tenToTwentySizeShape.addMouseWheelListener(new MouseAdapter() {
	        	@Override
                public void mouseWheelMoved(MouseWheelEvent e){
                    if (!whetherPause && e.getWheelRotation() < 0)
                    	tenToTwentySizeShape.turnClockWise();
                    else
                    	if(!whetherPause && e.getWheelRotation() >= 0)
                    		tenToTwentySizeShape.turnCounterClockWise();
                    }
	            });

	        
	        twentyToTwentySizeShape.addMouseListener(new MouseAdapter() {
	           	@Override
                public void mousePressed(MouseEvent e){  
                    if (!whetherPause && e.getButton() == java.awt.event.MouseEvent.BUTTON1)                
                    	twentyToTwentySizeShape.moveLeft();
                    else 
                    	if (!whetherPause && e.getButton() == java.awt.event.MouseEvent.BUTTON3)
                    		twentyToTwentySizeShape.moveRight();   
                    }                   
	            });


	        final JLabel twentyTwentyPauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);
	        twentyToTwentySizeShape.add(twentyTwentyPauseLabel);
	        twentyTwentyPauseLabel.setVisible(true);
	        

	        twentyToTwentySizeShape.addMouseMotionListener(new MouseAdapter() {
	        	@Override
                public void mouseMoved(MouseEvent e) {	        		
                    if ((e.getX() >= 20 && e.getX() <= rightPauseBorderForTwentyToTwenty) && e.getY() <= bottomPauseBorderForTenToTwenty){                   	
                    	timerForTwentyToTwenty.stop();
                    	twentyTwentyPauseLabel.setFont(new Font("Serif", Font.PLAIN, fontSizeTwentyToTwenty));
                    	twentyTwentyPauseLabel.setBounds(oriXTwentyToTwenty, oriYTwentyToTwenty, widthTwentyToTwenty, heightTwentyToTwenty);
                    	twentyTwentyPauseLabel.setVisible(true);
                        whetherPause = true;
                    }
                    else {                    	
                    	timerForTwentyToTwenty.start(); 
                    	twentyTwentyPauseLabel.setVisible(false);
                        whetherPause = false;
                        }
	                }	        		
	            });  
  	            	            
	                   
	        twentyToTwentySizeShape.addMouseWheelListener(new MouseAdapter(){
	        	@Override
                public void mouseWheelMoved(MouseWheelEvent e){
	        		if (!whetherPause && e.getWheelRotation() < 0)
	        			twentyToTwentySizeShape.turnClockWise();
                    else
                    	if(!whetherPause && e.getWheelRotation() >= 0)
                    		twentyToTwentySizeShape.turnCounterClockWise();
                    }
	            });	        	        	      	        	        

	        frame.setLayout(new GridLayout());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(100,100,400,500);
	        frame.setVisible(true);
	        frame.setResizable(false);	        
	    }
   
}
