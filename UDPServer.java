import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class UDPServer 
{
    DatagramSocket socket = null;

    public UDPServer() 
    {

    }
    public void createAndListenSocket() throws ClassNotFoundException 
    {
        try 
        {
            socket = new DatagramSocket(9876);
            byte[] incomingData = new byte[1024];

            while (true) 
            {
            		
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                CreatePacket toSendPacket = new CreatePacket(incomingData, incomingData.length);
                ArrayList <CreatePPacket> clients = new ArrayList<CreatePacket>(4);
                
               ByteArrayInputStream bis = new ByteArrayInputStream(incomingPacket.getData());
               ObjectInputStream in = new ObjectInputStream(bis);
               Object receivedPacket = in.readObject();
               in.close();
               
               clients.add(toSendPacket); 

               InetAddress IPAddress = incomingPacket.getAddress();
               int port = incomingPacket.getPort();
                
               System.out.println(); 
               System.out.println("" + receivedPacket.toString());
               System.out.println("Client IP: "+ IPAddress.getHostAddress());
               System.out.println("Client port: "+ port);
               P
             //  String reply = "Thank you for the message";
             //  byte[] data = reply.getBytes();
                
               
               DatagramPacket replyPacket = new DatagramPacket(incomingData, incomingData.length, IPAddress, port);
               Thread.sleep(30*1000);
                socket.send(replyPacket);
                //socket.close();
            }
        } 
        catch (SocketException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException i) 
        {
            i.printStackTrace();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException 
    {
        UDPServer server = new UDPServer();
        server.createAndListenSocket();
    }
}
