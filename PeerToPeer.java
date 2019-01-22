import java.io.IOException;
import java.net.*;

public class PeerToPeer 
{
  private boolean run = true;
  
  public static void main(String[] args) throws IOException 
  {
    startServer();
    startSender();
  }

  public static void startSender() throws UnknownHostException
  {
    InetAddress ipAddress = InetAddress.getByName();
    (new Thread() 
    {
        @Override
        public void run() 
        {
            byte data[] = "Client 1 is up".getBytes();
            DatagramSocket socket = null;
            try 
            {
                socket = new DatagramSocket();
                socket.setBroadcast(true);
            } 
            catch (SocketException ex) 
            {
                ex.printStackTrace();
            }
            DatagramPacket sendPacket = new DatagramPacket(data,data.length,ipAddress,9876);
            while (true) 
            {
                try 
                {
//                  System.out.println(new String(packet.getData()));
                	Thread.sleep(5000);            
                    socket.send(sendPacket);
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                } 
                catch (InterruptedException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
        }).start();
    }


  public static void startServer() 
  {
    (new Thread() 
    {
        @Override
        public void run() 
        {
                //byte data[] = new byte[0];
                DatagramSocket serverSocket = null;
                try 
                {
                	serverSocket = new DatagramSocket(9090);
                    //socket.setBroadcast(true);;
                } 
                catch (SocketException ex) 
                {
                    ex.printStackTrace();
                    //parent.quit();
                }
                DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                while (true) 
                {
                	try 
                	{
                		serverSocket.receive(receivePacket);
	                    String temp = new String(receivePacket.getData());
	                    
	                    System.out.println(temp);
	                    //System.out.println("Message received ..."+ temp);
                	} 
                	catch (IOException ex) 
                	{
	                    ex.printStackTrace();
	                    //parent.quit();
                	}
                }
            }
    }).start();
 }
}