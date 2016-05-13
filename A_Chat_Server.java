import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class A_Chat_Server implements Runnable //promenjena klasa, nije vise ovde main, ova je morala da bude runnable da bi mogla da uhvati
											   //unos sa GUI-a. GUI je u drugoj klasi. Slicna struktura kao za klijente
{
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    private int PORT;
   
    public A_Chat_Server(int PORT){
    	this.PORT = PORT;
    }
    
    public static void AddUserName(Socket X) throws IOException
    {
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);
        
        for(int i = 1; i <= A_Chat_Server.ConnectionArray.size(); i++)
        {
            Socket TEMP_SOCK = (Socket)A_Chat_Server.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" + CurrentUsers);
            OUT.flush();
        }
    }
    
    
	@SuppressWarnings("deprecation")
	@Override
	public void run(){
		try
        {
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Cekanje novih klijenata...");
            
            while(true)
            {
                Socket SOCK = SERVER.accept();
                ConnectionArray.add(SOCK);
                
                System.out.println("Klijent konektovan sa adrese: " + SOCK.getLocalAddress().getHostName());
                
                AddUserName(SOCK);
                
                A_Chat_Server_Return CHAT = new A_Chat_Server_Return(SOCK);
                Thread X = new Thread(CHAT);
                X.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
	}
}
