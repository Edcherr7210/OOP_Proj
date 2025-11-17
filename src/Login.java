import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Login extends JFrame{
        public Login(){
            ImageIcon image = new ImageIcon("logo.jpg");
            this.setTitle("Login Page");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit of the application when hitting the X button on top right
            this.setSize(700, 700); //Set the size of the login page frame
            this.setVisible(true); //Sets visability to true
            this.setIconImage(image.getImage());
        }
}
