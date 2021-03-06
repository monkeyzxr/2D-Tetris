// Created by Xiangru Zhou
// 10-15-2016

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//this tetris shape class is  for 20 * 20
public class TetrisShape2 extends JPanel{
	
	 java.util.Random showRandomShapes = new java.util.Random(); 
	 public int nextShapeType = showRandomShapes.nextInt(Tetris.shapeCount);
	 public int nextTurnState = showRandomShapes.nextInt(4);

     int shapeType;     
     int turnState; 
     
     int oriXBlock;
     int oriYBlock; 

     private boolean checkFallen = false;     
     int[][] coordinate = new int[23][27];

     public int score = 0;
     public int line = 0;
     public int level = 1;
     public int showline = 0;

     // use 3 dimensions array to represent different shapes. 
     // this idea comes from github
     // [][][] represents as[shape types][turn state][draw shapes]
     private final int shapes[][][] = new int[][][] 
             {             
                 { // Straight-Line-shape
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 1, 1 },
                     
                     { 0, 1, 0, 0,
     	              0, 1, 0, 0, 
     	              0, 1, 0, 0, 
     	              0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 1, 1 },
                     
                     { 0, 1, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0 }
                 },
                 
                 { // S-shape
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       0, 1, 1, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       1, 0, 0, 0, 
                       1, 1, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0,
                       0, 1, 1, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 0, 0, 0, 
                       1, 1, 0, 0,
                       0, 1, 0, 0 } 
                 },
                
                 { // Z-shape
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       0, 1, 1, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 1, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       0, 1, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 1, 0, 0, 
                       1, 1, 0, 0, 
                       1, 0, 0, 0 } 
                 },
                 
                 { // opposite-L-shape
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 0, 0, 0, 
                       1, 1, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 1, 0, 0,
                       1, 0, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 1, 0, 
                       0, 0, 1, 0 } 
                 },
                 
                 { // Square-shape
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 0, 0, 
                       1, 1, 0, 0 } 
                 },
               
                 { // L-shape
                     { 0, 0, 0, 0,
                       1, 0, 0, 0, 
                       1, 0, 0, 0,
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 1, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 1, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 1, 0, 
                       1, 1, 1, 0 } 
                 },
                 
                 { // T-shape
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 1, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 1, 0, 0, 
                       1, 1, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 1, 1, 0,
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0,
                       0, 1, 1, 0, 
                       0, 1, 0, 0 } 
                 },
                 
                 //following are new shapes  
                 /*
                 { 
                 	
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 0, 0, 0 } 
                 },
                 */

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 0, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       0, 1, 0, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0,
                       1, 0, 0, 0, 
                       0, 1, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       0, 1, 0, 0,
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 1, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 0, 0, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 1, 0, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 0, 0, 0,
                       1, 1, 0, 0, 
                       0, 1, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       1, 0, 0, 0, 
                       0, 1, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 1, 0, 0, 
                       1, 0, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 1, 0, 0,
                       0, 0, 1, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0,
                       0, 1, 0, 0, 
                       1, 0, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 0, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 0, 0, 0, 
                       0, 1, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 0, 1, 0,
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0,
                       1, 0, 0, 0, 
                       0, 1, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 0, 0, 
                       1, 1, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 1, 0, 0, 
                       0, 1, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       0, 0, 0, 0,
                       1, 1, 1, 0 },
                     
                     { 0, 0, 0, 0, 
                       0, 1, 0, 0,
                       0, 1, 0, 0, 
                       0, 1, 0, 0 } 
                 },

                 { 
                     { 0, 0, 0, 0,
                       0, 0, 1, 0, 
                       0, 1, 0, 0, 
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 0, 0, 0, 
                       0, 1, 0, 0, 
                       0, 0, 1, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 1, 0,
                       0, 1, 0, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       1, 0, 0, 0,
                       0, 1, 0, 0, 
                       0, 0, 1, 0 } 
                 },
                 
                 { 
                     { 0, 0, 0, 0,
                       0, 0, 0, 0, 
                       0, 0, 1, 0, 
                       1, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       1, 0, 0, 0, 
                       1, 0, 0, 0, 
                       0, 1, 0, 0 },
                     
                     { 0, 0, 0, 0,
                       0, 0, 0, 0,
                       0, 1, 1, 0,
                       1, 0, 0, 0 },
                     
                     { 0, 0, 0, 0, 
                       1, 0, 0, 0,
                       0, 1, 0, 0, 
                       0, 1, 0, 0 } 
                 }, 
                 
             };
                         
     class FallenTimerListener implements ActionListener {
      	@Override
          public void actionPerformed(ActionEvent e) {
              repaint();
              boolean checkToStop = detectStop(oriXBlock, oriYBlock + 1, shapeType, turnState);
              if (checkToStop == false) {
              	oriYBlock ++;
              	winScore();           	
              }            	               
              if (checkToStop == true){
              	if(checkFallen == true){
              		mapToCoori(oriXBlock, oriYBlock, shapeType, turnState);
              		winScore();
                      buildNewBlock();
                      checkFallen = false;            		
              	}
              	checkFallen = true;            	
              }
          }
      }         
     
         TetrisShape2() {
         	buildNewBlock();
         	for (int aa = 0; aa < 21; aa++) {
                 for (int bb = 0; bb < 26; bb++)           	
                 	coordinate[aa][bb] = 0;
                 }                      
             for (int m = 0; m < 21; m++)      	
                 coordinate[m][25] = 2;                
             for (int n = 0; n < 26; n++) {      	
                 coordinate[21][n] = 2;
                 coordinate[0][n] = 2;
                 }
         }        
     
     public void buildNewBlock() { 
     	shapeType = nextShapeType;
         turnState = nextTurnState;
         nextShapeType = showRandomShapes.nextInt(Tetris.shapeCount);
         nextTurnState = showRandomShapes.nextInt(4);
         oriXBlock = 9;
         oriYBlock = 0;

         if (gameOver(oriXBlock, oriYBlock)) {
         	mapToCoori (9, 0, shapeType,turnState);
             Tetris.timerForTwentyToTwenty.stop();
             JOptionPane.showMessageDialog(null,"GAME OVER. TRY AGAIN.", "Your Tetris", JOptionPane.PLAIN_MESSAGE);
             Tetris.timerForTwentyToTwenty.setDelay(600);
             Tetris.shapeCount = 7;
             Tetris.timerForTwentyToTwenty.start();

             for (int i = 0; i < 21; i++) {              
                 for (int j = 0; j < 26; j++) coordinate[i][j] = 0;
             }              
             for (int i = 0; i < 21; i++)               	                
             	coordinate[i][25] = 2;               
             for (int j = 0; j < 26; j++) {               	
             	coordinate[21][j] = 2;
             	coordinate[0][j] = 2;
             }  
             score = 0;
             level = 1;
             showline = 0;
         }
     }
     
     public boolean detectStop(int oriX, int oriY, int blockType, int turnState) {
         for (int m = 0; m < 4; m++) {
             for (int n = 0; n < 4; n++) {
             	if(shapes[blockType][turnState][m * 4 + n] == 1){
             		if(coordinate[oriX + n + 1][oriY + m] == 1)
             			return true;
             		if(coordinate[oriX + n + 1][oriY + m] == 2)
             			return true;
             	}    
             }
         }
         return false;
     }  
     
     
     public void turnClockWise() {  
         int beginState = turnState;
         turnState = (turnState + 1) % 4;           
         boolean checkToStop = detectStop(oriXBlock, oriYBlock, shapeType, turnState);
         if(checkToStop == true)
         	turnState = beginState;
         repaint();
     }
     
     
     public void turnCounterClockWise()  {       	
         int beginState = turnState;
         turnState = (turnState + 3) % 4;           
         boolean checkToStop = detectStop(oriXBlock, oriYBlock, shapeType, turnState);
         if(checkToStop == true)
         	turnState = beginState;
         repaint();
     }
     
     public void moveLeft() {
     	boolean checkToStop = detectStop(oriXBlock - 1, oriYBlock, shapeType, turnState);
     	if(checkToStop == false)
     		oriXBlock--;
         repaint();
     }
     
     public void moveRight() {
     	boolean checkToStop = detectStop(oriXBlock + 1, oriYBlock, shapeType, turnState);
     	if(checkToStop == false)
     		oriXBlock++;
         repaint();
     }
     
     public boolean gameOver(int x, int y){
         if (detectStop(x, y, shapeType, turnState))           	
             return true;
         else
             return false;
     }

     public void winScore(){
     	int currentLine = 0;
     	int totalLine = 0;
     	for(int m = 0; m <= 25; m++){
     		for(int n = 0; n <= 20; n++){
     			if(coordinate[n][m] == 1){
     				currentLine++;
     				if(currentLine == 20){
     					totalLine++;
     					for(int temp1 = m; temp1 > 0; temp1--){
     						for(int temp2 = 0; temp2 <= 10; temp2++){
     							coordinate[temp2][temp1] = coordinate[temp2][temp1-1];
     						}														
     					}
     				}
     			}				
     		}
     		currentLine = 0;
     	}
     	if(totalLine == 1){
     		score = score + 1 * level * Tetris.scoreFactor;
     		line = line + 1;
     		showline = showline + 1;
     	}
     	else if(totalLine == 2){
     		score = score + 2 * level * Tetris.scoreFactor;
     		line = line + 2;
     		showline = showline + 2;	
     	}
     	else if(totalLine == 3){
     		score = score + 3 * level * Tetris.scoreFactor;
     		line = line + 3;
     		showline = showline + 3;
     	}
     	else if(totalLine == 4){
     		score = score + 4 * level * Tetris.scoreFactor;
     		line = line + 4;
     		showline = showline + 4;
     	}
     	
     	if(line >= Tetris.numRowForDifficulty){
     		int delayTime = (int) (Tetris.timerForTwentyToTwenty.getDelay()/(1 + level* Tetris.speedFactor));
     		Tetris.timerForTwentyToTwenty.setDelay(delayTime);
     		level = level + 1;
     		line = 0;
     	}		
     }
     
     public void mapToCoori(int oriX, int oriY, int blockType, int turnState) {
     	int draw = 0;
         for (int m = 0; m < 4; m++) {
             for (int n = 0; n < 4; n++) {
                 if (coordinate[oriX + n + 1][oriY + m] == 0)
                     coordinate[oriX + n + 1][oriY + m] = shapes[blockType][turnState][draw];
                 draw++;
             }
         }
     }

     public void paintCurrentBlock(Graphics g, int width){   	   
   	   for(int a = 0; a < 16; a++){
   		   int currentOriX = (a % 4 + oriXBlock + 1) * width/30;
       	   int currentOriY = (a / 4 + oriYBlock - 4) * width/30;
       	   int length =  width / 30;    	   
   		   if(shapes[shapeType][turnState][a] == 1) {
   			   g.setColor(Color.PINK);
   			   g.fillRect(currentOriX, currentOriY, length, length);
   			   g.setColor(Color.BLACK);
   			   g.drawRect(currentOriX, currentOriY, length, length);
   		   }
   	   }  	   
      }
     
     public void paintNextBlock(Graphics g, int width){    	   
    	   for(int a = 0; a < 16; a++){
    		   int nextOriX = (a % 4 + 1) * width/30+ width * 23/30;
    		   int nextOriY = (a / 4) * width/30 + width/15; 
        	   int length =  width / 30;    	   
    		   if(shapes[nextShapeType][nextTurnState][a] == 1) {
    			   g.setColor(Color.YELLOW);
    			   g.fillRect(nextOriX, nextOriY, length, length);
    			   g.setColor(Color.BLACK);
    			   g.drawRect(nextOriX, nextOriY, length, length);
    		   }
    	   }  	   
       }
     
     public void paintLeftBoundary(Graphics g, int width){
   	   for (int a = 4; a < 26; a++) { 
              int b = 0;
              int leftOriX = (b + 1) * width / 30;
              int leftOriY = (a - 4) * width / 30;
              int lengthA = ( b + 1) * width /30;
              int lengthB = (a - 5) * width / 30;              
              if (coordinate[b][a]==2) { 
                  g.setColor(Color.BLACK);
                  g.drawLine(leftOriX, leftOriY, lengthA, lengthB);
              }
          }    	   
      }
     
     public void paintRightBoundary(Graphics g, int width){
   	   for (int a = 4; a < 26; a++) { 
              int b = 21;
              int rightOriX = b * width / 30;
              int rightOriY = (a - 4) * width /30;
              int lengthA = b * width / 30;
              int lengthB = (a - 5) * width / 30;              
              if (coordinate[b][a]==2) { 
                  g.setColor(Color.BLACK);
                  g.drawLine(rightOriX, rightOriY, lengthA, lengthB);
              }
          }    	   
      }
     
     public void paintBottomBoundary(Graphics g, int width){
   	   for (int a = 0; a < 20; a++) { 
              int b = 25;
              int bottomOriX = (a + 1) * width / 30;
              int bottomOriY = (b - 4) * width / 30;
              int lengthA = (a + 2) * width / 30;
              int lengthB = (b - 4) * width / 30;
              g.setColor(Color.BLACK);
              g.drawLine(bottomOriX, bottomOriY, lengthA, lengthB);              
          }    	   
      }
     
     public void paintTouchedBlock(Graphics g, int width) {
   	   for (int a = 4; a < 26; a++) {
              for (int b = 0; b < 21; b++) {
           	   int touchedBlockOriX = b * width / 30;
           	   int touchedBlockOriY = (a - 4) * width / 30;
           	   int length = width / 30;           	   
                  if (coordinate[b][a] == 1) { 
                      g.setColor(Color.GREEN);
                      g.fillRect(touchedBlockOriX, touchedBlockOriY, length, length);
                      g.setColor(Color.BLACK);
                      g.drawRect(touchedBlockOriX, touchedBlockOriY, length, length);
                  }              
              }
          }   	   
      }
     
     public void setLabels(Graphics g, int width) {
         g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, width/30));
         g.drawString("Next Shape：", width*11/15, width*3/60);

         g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, width*3/120));
         g.drawString("Level:   " + level, width*11/15, width/3);

         g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD,width*3/120));
         g.drawString("Line:     " + showline, width*11/15, width*2/5);

         g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, width*3/120));
         g.drawString("Score:   " + score, width*11/15, width*7/15);  
     }  
     
     public void paintComponent(Graphics g) {       	
         Dimension d = getSize();        
         paintCurrentBlock(g,d.width);
         paintNextBlock(g, d.width);
         paintLeftBoundary(g, d.width);
         paintRightBoundary(g, d.width);
         paintBottomBoundary(g, d.width);
         paintTouchedBlock(g, d.width);
         setLabels(g, d.width);          
      }

}
