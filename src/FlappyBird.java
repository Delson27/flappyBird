import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener,KeyListener {

    int boardWidth=360;
    int boardHeight=640;
 
    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

   //for birdImage
   int birdX=boardWidth/8;
   int birdY=boardHeight/2;
   int birdWidth=34;
   int birdHeight=24;

   class Bird{
    int x=birdX;
    int y=birdY;
    int width=birdWidth;
    int height=birdHeight;
    Image img;

    Bird(Image img){
        this.img=img;
    }
   }
   Bird bird;


   //Pipes
   int pipeX=boardWidth;
   int pipeY=0;
   int pipeWidth=64;
   int pipeHeight=512;

   class Pipe{
    int x=pipeX;
    int y=pipeY;
    int width=pipeWidth;
    int height=pipeHeight;
    Image img;
    boolean passed=false; //this we are using to check if flappy bird passed the pipe or not

    public Pipe(Image img){
        this.img=img;
    }
   }
   


   //game logic
   int velocityX=-4; //used to move the pipes to left (simulates bird moving right)
   int velocityY=0;
   int gravity=1;
    
   ArrayList<Pipe> pipes;
   Random ran=new Random();

   Timer gamerLoop,placePipesTimer;
   boolean gameOver=false;
   double score=0;
   

    public FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));  //belongs to JPanel
        setFocusable(true); //belongs to Jpanel and allows keyEvents now
        addKeyListener(this);
        //setBackground(Color.BLUE); case insensitive 
        
        //load images
        backgroundImage=new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage=new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage=new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        //creating bird object
        bird=new Bird(birdImage);
        //creating game timer object
        gamerLoop=new Timer(1000/60, this);  //this will make my game update every ~16.67 milliseconds and here this=ActionListener
        gamerLoop.start();

        pipes=new ArrayList<Pipe>(); //creating a object of ArrayList to hold all the pipes
        //creating place pipes timer object
        //this will call placePipes() every 1.5 seconds
        
        placePipesTimer=new Timer(1500, new ActionListener() {  //anonymous class syntax used here
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
      });
        placePipesTimer.start();
        

    }
    public void placePipes(){
        int randomPipeY=(int)(pipeY-pipeHeight/4-Math.random()*(pipeHeight/2));  //this will give each pipe differnt y length
        int openingSpace=boardHeight/4; //this is the space between the top and bottom pipe

        Pipe topPipe= new Pipe(topPipeImage);
        topPipe.y=randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe= new Pipe(bottomPipeImage);
        bottomPipe.y=topPipe.y+pipeHeight+openingSpace; //this will place the bottom pipe below the top pipe
        pipes.add(bottomPipe);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); //always use this first. This clears the background and avoids weird visual bugs.
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0,boardWidth,boardHeight,null);
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);
        //pipes
        for(Pipe p : pipes){
            g.drawImage(p.img, p.x, p.y, p.width, p.height, null);
        }
        //Score 
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,32));
        if(gameOver){
            g.drawString("Game Over: "+String.valueOf((int)score),10,35);// because it expects only string
        }
        else{
            g.drawString(String.valueOf((int)score),10,35); //because it expects only string
        }
         
    }
    public void move(){
        //bird
        velocityY+=gravity; 
        bird.y+=velocityY;
        bird.y=Math.max(bird.y,0);
        //pipes
        for(Pipe p: pipes){
            p.x+=velocityX; //moving the pipe to left
            if(!p.passed && bird.x>p.x+p.width){
                p.passed=true; //if bird has passed the pipe then we set passed to true
                score+=0.5; //because we are considering  top and bottom pipe as one set basically 0.5*2=1
            }
            if(checkCollision(bird, p)){ //if bird collides with pipe
                gameOver=true; //if bird collides with pipe then game is over
            }
        }

        if(bird.y>boardHeight){
            gameOver=true; //if bird goes below the board height then game is over
        }
       
    }

    public boolean checkCollision(Bird a, Pipe b){  // we make use of Axis Aligned Bounding Box (AABB) collision detection
        return a.x < b.x + b.width &&  //checks if bird's left side is to the left of pipe's right side
               a.x + a.width > b.x &&  //checks if bird's right side is to the right of pipe's left side
               a.y < b.y + b.height && //checks if bird's top side is below pipe's bottom side
               a.y + a.height > b.y;  //checks if birds bottom side is above pipe's top side
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         move(); 
         repaint(); //This will calll paintComponent() and thus draw() and thus update the game state. if we dont do this paintComponent is called only once
         if(gameOver){
            placePipesTimer.stop(); //stop the timer that places pipes
            gamerLoop.stop(); //stop the game loop
         }
    }

     @Override
    public void keyPressed(KeyEvent e) {
         if(e.getKeyCode()==KeyEvent.VK_SPACE){
            velocityY=-9;
            if(gameOver){
                //reset the game
                bird.y=birdY; //reset bird position
                velocityY=0; //reset velocity
                pipes.clear(); //clear all the pipes
                score=0; //reset score
                gameOver=false; //reset game over state
                gamerLoop.start(); //restart the game loop
                placePipesTimer.start(); //restart the pipe placement timer

            }
         }
    }
      @Override
    public void keyTyped(KeyEvent e) {}
      
   
    @Override
    public void keyReleased(KeyEvent e) {}
    


}