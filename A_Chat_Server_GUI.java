import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class A_Chat_Server_GUI {
	
	public static JFrame PORTWindow = new JFrame();
    private static JPanel P_PORT = new JPanel();
    public static JTextField TF_PORT = new JTextField(20);
    private static JLabel L_EnterPORT = new JLabel("Unesite PORT: ");
    private static JButton B_ENTER = new JButton("ENTER");
    public static String PORT = "1234";
    private static A_Chat_Server ChatServer;

	 public static void main(String[] args) throws IOException
	    {
	    	BuildPORTWindow();
	    }
	 public static void BuildPORTWindow() // dodao
	    {
	       PORTWindow.setTitle("PORT");
	       PORTWindow.setSize(400, 150);
	       PORTWindow.setLocation(250, 200);
	       PORTWindow.setResizable(true);
	        P_PORT = new JPanel();
	        P_PORT.add(L_EnterPORT);
	        P_PORT.add(TF_PORT);
	        P_PORT.add(B_ENTER);
	        PORTWindow.add(P_PORT);
	        B_ENTER.setEnabled(true);
	        PORT_Action();
	        PORTWindow.setVisible(true);
	        P_PORT.setVisible(true);
	    }
	    
	    public static void PORT_Action()
	    {
	        B_ENTER.addActionListener(
	                new java.awt.event.ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ACTION_B_ENTER();
	            }
	        }
	        );
	    }
	    
	    public static void ACTION_B_ENTER()
	    {
	        if(!TF_PORT.getText().equals(""))
	        {
	            PORT = TF_PORT.getText().trim(); // PORT
	            PORTWindow.setVisible(false);
	            podesi();
	        }
	        else
	        {
	            JOptionPane.showMessageDialog(null, "Molimo, unesite port");
	        }
	    }
	    public static void podesi(){
	    	try
	        {
	            final int PORTn = Integer.parseInt(PORT);	            
	            ChatServer = new A_Chat_Server(PORTn);
	            	            
	            Thread X = new Thread(ChatServer);
	            X.start();
	            
	        }
	        catch(Exception e)
	        {
	            System.out.print(e);
	            JOptionPane.showMessageDialog(null, "Server ne odgovara!");
	            System.exit(0);
	        }
	    }
}
