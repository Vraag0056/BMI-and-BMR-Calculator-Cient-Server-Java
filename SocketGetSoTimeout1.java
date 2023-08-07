import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.net.SocketTimeoutException;  
  
public class SocketGetSoTimeout1 extends Thread{  
        private ServerSocket socket;  
        public SocketGetSoTimeout1()  throws IOException {  
          socket = new ServerSocket(1408);  
          socket.setSoTimeout(10000);      
          System.out.println("Socket time out in "+socket.getSoTimeout()+"ms");  
      }  
      public void run() {  
        while (true) {  
          try {  
            System.out.println("Connecting .........");  
            Socket client = socket.accept();  
            System.out.println("Connected to port........."+client.getRemoteSocketAddress());  
            client.close();  
          } catch (SocketTimeoutException s) {  
            System.out.println("Socket timed out!");  
  
            break;  
          } catch (IOException e) {  
            e.printStackTrace();  
            break;  
          }  
        }  
      }  
      public static void main(String[] args) {  
        try {  
          Thread t = new SocketGetSoTimeout1();  
          t.start();  
        } catch (IOException e) {  
          e.printStackTrace();  
        }  
      }  
    }  