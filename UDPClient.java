
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Random;

public class UDPClient 
{
    DatagramSocket Socket;

    public UDPClient() 
    {

    }

    public void createAndListenSocket() throws ClassNotFoundException, InterruptedException 
    {
        try 
        {
            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];
            String sentence = "Client 1 is up";
            byte[] data = sentence.getBytes();
            boolean flag = true; 
            CreatePacket packet = new CreatePacket(data, flag, data.length);
            
         while(true)
         {            
            //Serialize to send
        	 
        	 Random rnd = new Random();
             int timeout = rnd.nextInt(30);
             Thread.sleep(timeout*1000); 
        	 
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(packet);
            out.close();
            buffer.close();
            
            DatagramPacket sendPacket = new DatagramPacket(buffer.toByteArray(), buffer.size(), IPAddress, 9876);
            Socket.send(sendPacket);
            System.out.println("Message sent from client");
           
            
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            Socket.receive(incomingPacket);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(incomingPacket.getData());
            ObjectInputStream in = new ObjectInputStream(bis);
            Object receivedPacket = in.readObject();
            in.close();
            
            
            InetAddress IPAddress1 = incomingPacket.getAddress();
            int port = incomingPacket.getPort();
             
            System.out.println();
            System.out.println("Response from server: ");
            System.out.println("Message : " + receivedPacket.toString());
            System.out.println("Client IP: "+ IPAddress1.getHostAddress());
            System.out.println("Client port: "+ port);
            
            
         } 
            //String response = new String(incomingPacket.getData());
            //System.out.println("Response from server:" + response);
            //Socket.close();
        }
        catch (UnknownHostException e) 
        {
            e.printStackTrace();
        } 
        catch (SocketException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException 
    {
        UDPClient client = new UDPClient();
        client.createAndListenSocket();
    }
}
