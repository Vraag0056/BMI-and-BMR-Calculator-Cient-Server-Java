import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {
   
    public static void main(String[] args) throws IOException {
        
        ServerSocket ss = new ServerSocket(7778);
        System.out.println("Waiting for client...");
        while(true){
        try{
        Socket s =ss.accept();
        System.out.println("Success");
        ServerThread st = new ServerThread(s);
        st.start();
        }
        catch (Exception e)
        {
                System.out.println(e);
        }
        
        }

    }
    
}

class ServerThread extends Thread{
    Socket s1 =null;
    static double BMI(double weight,double height){
        return (weight/(height*height));
    }
    static double BMR(String gen,double weight,double height,int age){
        if(gen.equals("male")){
            return (88.362 +(13.397 * weight)+(4.799 * height*100)-(5.677 * age));
        }
        return (447.593 + (9.247*weight)+(3.098*height*100)-(4.330*age));
    }
    public ServerThread(Socket s){
        s1=s;
    }
    public void run(){
        try {
            double weight;
            Scanner sc = new Scanner(s1.getInputStream());
        
        String gen = sc.nextLine();
        weight =sc.nextDouble();
        double height = sc.nextDouble();
        int age = sc.nextInt();

        PrintStream p = new PrintStream(s1.getOutputStream());
        p.println(BMI(weight,height));
        p.println(BMR(gen,weight,height,age));
        
        s1.close();

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
