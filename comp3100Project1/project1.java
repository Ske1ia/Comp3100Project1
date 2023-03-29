import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;

public class project1{
	public static void main(String[] args){
		try{
			Integer nRec=null;
			String[] sysInfo=null;
			String sysType=null;
			Integer sysID=null;
			int count=0; 
			//co
			Socket s=new Socket("localhost",50000);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 	DataInputStream in=new DataInputStream(s.getInputStream());
			BufferedReader init = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
		
	//		dout.writeUTF("Hello Server");
		//	dout.writeUTF("
			dout.write(("HELO\n").getBytes());
			str = in.readLine();
			dout.flush();
			
			dout.write(("AUTH natalie1\n").getBytes());
			dout.flush();
			str = in.readLine();
			//	String str=(String)in.readLine();
		//	System.out.println("message="+str);
			String username = System.getProperty("user.name");
			System.out.println(username);
			dout.flush();
			
			
			dout.write(("REDY\n").getBytes());
			dout.flush();
			str = in.readLine();
			
			//dout.write(("GETS All\n").getBytes());
			//System.out.println("GETS All");
			//dout.flush();
			
			//str = in.readLine();
			while(!str.contains("NONE")){
			if(str.contains("JOBN")|| str.contains("JOBP")){
			String[] jobInfo=str.split("\\s+");
			Integer jobID = Integer.parseInt(jobInfo[2]);
			Integer jobCores=Integer.parseInt(jobInfo[4]);
			Integer jobMemory=Integer.parseInt(jobInfo[5]);
			Integer jobDisk=Integer.parseInt(jobInfo[6]);
			
			dout.write(("GETS All\n").getBytes());
			System.out.println("GETS All");
			dout.flush();
			str=in.readLine();
			System.out.println("RCVD"+str);
			dout.write(("OK\n").getBytes());
			System.out.println("SENT OK");
			dout.flush();
			
			//if(nRec ==null){
			String[] info = str.split("\\s+");
			nRec=Integer.parseInt(info[1]);
			//System.out.println("RCVD");
			for(int i =0; i<nRec; i++){
			str=in.readLine();
			System.out.println(str);
			}
			sysInfo=str.split("\\s+");
			sysType=sysInfo[0];
			sysID=0;
									
			dout.write(("OK\n").getBytes());
			dout.flush();
			str=in.readLine();
			System.out.println("RCVD"+str);
			
			dout.write(("SCHD "+jobID+" "+sysType+" "+sysID+"\n").getBytes());
			dout.flush();
			str=in.readLine();
			System.out.println("RCVD"+str);
			count++;
			}
			dout.write(("REDY\n").getBytes());
			dout.flush();
			str=in.readLine();
			
			}
			
			
		
			//dout.write(("OK\n").getBytes());
			//dout.flush();
			//str=in.readLine();
			
			

			//str = in.readLine();
			
			//dout.write(("OK\n").getBytes());
			//dout.flush();
			//str = in.readLine();
			
			dout.write(("QUIT\n").getBytes());
			str = in.readLine();
	//		dout.writeUTF("Bye");
			dout.flush();
		//	String strs=(String)in.readUTF();
		//	System.out.println("message="+str);
			dout.close();
			s.close();
		}catch(Exception e){System.out.println(e);}
	}
}

