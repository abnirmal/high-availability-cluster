import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.*;

import org.omg.IOP.Encoding;
public final class CreatePacket implements Serializable 
{
    /**bb
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte[] stringValue;
    private boolean flagValue;
    private int length; 


    public CreatePacket(byte[] stringValue, boolean flagValue, int length)
    {
        this.stringValue = stringValue;
        this.flagValue = flagValue;
        this.length = length;

    }
    
    public CreatePacket(byte[] stringValue, int length)
    {
    	this.stringValue = stringValue; 
    	this.length = length; 
    }

    public byte[] getString()
    {
        return stringValue;
    }

    public boolean getDouble()
    {
        return flagValue;
    }
    
    public int getLength()
    {
    	return length; 
    }
    
    
    @Override
    public String toString() 
    {
    	String formatted = new String(stringValue); 
        String value = formatted + "\nFlag : " + flagValue + "\nLength : " + length;
        return value;
    }
}