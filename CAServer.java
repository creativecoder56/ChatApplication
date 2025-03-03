import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CAServer {
    private ServerSocket serverSocket;
    public CAServer(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new person joined the chat");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread=new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){

        }
    }
    public void closeServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(1234);
        CAServer server = new CAServer(serverSocket);
        server.startServer();
    }
}