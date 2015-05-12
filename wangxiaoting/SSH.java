package Shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
 

public class SSH {
    
    public static void main(String[] args)
    {
            String hostname = "192.168.5.102";
            String username = "root";
            String password = "leadtone";
            
            Connection conn = new Connection(hostname);
            Session sess = null;
            try
            {
               
                conn.connect();
               
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false)
                        throw new IOException("Authentication failed.");
               
                sess = conn.openSession();
                //sess.execCommand("uname -a && date && uptime && who");
                sess.execCommand("deleteuser -c 2 -P 123456 -i 7  &&  deleteuser -c 2 -P 123456 -i 8");
                //same
                sess.execCommand("deleteuser -c 2 -P 123456 -i 7  \n deleteuser -c 2 -P 123456 -i 8");
                // sess.execCommand("listuser -c 9189 -A admin -P 123456  ");
                System.out.println("Here is some information about the remote host:");
                InputStream stdout =    sess.getStdout() ;
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                StringBuilder sb = new StringBuilder();
                while (true)
                {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    sb.append(line);
                }
               
                //  System.out.println("ExitCode: " + sess.getExitStatus());
                System.out.println(sb.toString());
            }catch (IOException e){
                e.printStackTrace(System.err); System.exit(2);
            }finally{
                
                sess.close();
               
                conn.close();
            }
        }
    
    
    public static String remoteRunCmd(String  cmd)
    {
            String hostname = "192.168.5.102";
            String username = "root";
            String password = "leadtone";
            
            Connection conn = new Connection(hostname);
            Session sess = null;
            try
            {
               
                conn.connect();
               
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false)
                        throw new IOException("Authentication failed.");
               
                sess = conn.openSession();
                //sess.execCommand("uname -a && date && uptime && who");
               // sess.execCommand("deleteuser -c 2 -P 123456 -i 10 ");
                // sess.execCommand("listuser -c 9189 -A admin -P 123456  ");
                sess.execCommand( cmd);
                InputStream stdout =    sess.getStdout() ;
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                StringBuilder sb = new StringBuilder();
                while (true)
                {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    sb.append(line);
                }
               
                //  System.out.println("ExitCode: " + sess.getExitStatus());
               return sb.toString();
            }catch (IOException e){
                return "";
            }finally{
                
                sess.close();
               
                conn.close();
            }
        }
    
}