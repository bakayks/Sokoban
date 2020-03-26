import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This is ClientSocket class
 * It has only one method  getLevelsFromServer() with we can get a new level,
 * if it is not locally then we call a method that accesses the server
 */
public class ClientSocket {
    /**
     *This method connects to the server via a socket and gets an int array
     */
    public int[][] getLevelsFromServer(int numberOfLevel){
        int[][] newLevel = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            socket = new Socket("157.245.219.46", 4445);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            out.writeInt(numberOfLevel);
            out.flush();
            System.out.println("Отправили запрос на получения массива на сервер");
            newLevel = new int[in.readInt()][];
            for (int i = 0; i < newLevel.length; ++i) {
                newLevel[i] = new int[in.readInt()];
                for(int x = 0; x < newLevel[i].length; x++){
                    newLevel[i][x] = in.readInt();
                    System.out.print(newLevel[i][x]);
                }
                System.out.println();
            }
            System.out.println("Получили массив из сервера. level" + numberOfLevel);
        }catch (IOException ioe){
            ioe.printStackTrace();
            newLevel = null;
        }finally {
            try{
                if(out != null)
                    out.close();
                if(in != null)
                    in.close();
                if(socket != null)
                    socket.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }
        socket = null;
        in = null;
        out = null;
        return newLevel;
    }
}
