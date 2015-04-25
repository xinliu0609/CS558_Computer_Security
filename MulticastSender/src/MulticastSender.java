
import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class MulticastSender {
  public static void main(String[] args) throws Exception {
    DatagramSocket socket = null;
    DatagramPacket outPacket = null;
    byte[] outBuf = new byte[10000];
    final int PORT = 17012;
    
    
    
    VideoStream vs = null;
    try {
		vs = new VideoStream("movie.mjpeg");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    
    //File image = new File("b.jpg");
    //outBuf = Files.readAllBytes(image.toPath());
    
    try {
    	
      socket = new DatagramSocket();
      
      while (true) {

        int size = vs.getNextFrame(outBuf);
 
        //Send to multicast IP address and port
        InetAddress address = InetAddress.getByName("224.0.0.251");
        outPacket = new DatagramPacket(outBuf, outBuf.length,address, PORT);
        
        System.out.println("sending image");
        socket.send(outPacket);
 
        try {
          Thread.sleep(50);
        } catch (InterruptedException ie) {
        }
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}