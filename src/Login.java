import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Login extends JFrame{
    void login(){
        ImageIcon image = new ImageIcon("logo.jpg");
        this.setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit of the application when hitting the X button on top right
        this.setSize(400, 400); //Set the size of the login page frame
        this.setVisible(true); //Sets visabi lity to true
        this.setIconImage(image.getImage());
    }
}
