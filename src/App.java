import javax.swing.*;


public class App {
    public static void main(String[] args){
      

        int boardWidth=360;
        int boardHeight=640;

        JFrame frame=new JFrame("Flappy Bird");
        //frame.setLocationRelativeTo(null); this will place the window in the center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(boardWidth, boardHeight);

        FlappyBird flappyBird=new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); //this will adjust the main frame to fit the components it contains
        flappyBird.requestFocus();
        frame.setVisible(true);


    } 
}
 