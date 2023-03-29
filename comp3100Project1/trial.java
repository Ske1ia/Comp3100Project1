import java.io.*;
import java.net.*;

public class sample{
	public static void main(String[] args){
		try{
			Socket s=new Socket("localhost",50000);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 	DataInputStream in=new DataInputStream(s.getInputStream());
			BufferedReader init = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
			
			Integer nRec=null;
			String[] sysInfo=null;
			String sysType=null;
			Integer sysID=null;
			
			dout.write(("HELO\n").getBytes());
			str = in.readLine();
		
			dout.write(("AUTH natalie1\n").getBytes());
			str = in.readLine();
		
			dout.write(("REDY\n").getBytes());
			str = in.readLine();
		
			while(!str.contains("NONE")){
			if(str.contains("JOBN")|| str.contains("JOBP")){
			String[] jobInfo=str.split("\\s+");
			Integer jobID = Integer.parseInt(jobInfo[2]);
			Integer jobCores=Integer.parseInt(jobInfo[4]);
			Integer jobMemory=Integer.parseInt(jobInfo[5]);
			Integer jobDisk=Integer.parseInt(jobInfo[6]);
			System.out.println(jobID);
			System.out.println(jobCores);
			System.out.println(jobMemory);
			System.out.println(jobDisk);
			
			if(jobCores>4){
			sysType="super-silk";
			sysID=0; 
			}
			if(jobCores<3){
			sysType="joon";
			sysID=1;
			}
			
			dout.write(("GETS All\n").getBytes());
			System.out.println("GETS All");
			str=in.readLine();
			System.out.println("RCVD"+str);
			dout.write(("OK\n").getBytes()); //then prints out the servers 
			System.out.println("SENT OK");
			dout.flush();
			
		//	str=in.readLine();
		//	String[] info = str.split("\\s+"); //splits DATA (no. server) (?) 
		//	nRec=Integer.parseInt(info[1]);
		//	sysInfo=str.split("\\s+");
		//	//sysType="juju";
		//	sysType=sysInfo[0]; 
		//	sysID=0;
		//	
		//	dout.write(("OK\n").getBytes());
		//	str=in.readLine();
		//	
		//	dout.write(("SCHD "+jobID+" "+sysType+" "+sysID+"\n").getBytes());
		//	dout.flush();
		//	str=in.readLine();
			
			String[] info = str.split("\\s+"); //splits DATA (no. server) (?) 
			nRec=Integer.parseInt(info[1]);
			
			//System.out.println("RCVD");
			for(int i =0; i<nRec; i++){ //print servers available
			str=in.readLine();
			System.out.println(str);
			}
			sysInfo=str.split("\\s+"); //
		//	sysType="juju"; //medium/juju Within in the DATA (no.server) (?)
		//	sysType=sysInfo[0];
		//	if(jobCores>4){
		//	sysType="super-silk";
		///	sysID=0;
		//	}else{
		//	sysType="joon";
		//	sysID=0; 
		//	}
			
			
			
		//	if(jobCores==2 || jobCores<2){
		//	sysType="juju";
		//	sysID=1;
		//	}else{
		//	sysType="juju";
		//	sysID=0; 
		//	}
			
			System.out.println(sysType);
			sysID=0; //ID of server
			
			
									
			dout.write(("OK\n").getBytes());
			dout.flush();
			str=in.readLine();
			System.out.println("RCVD"+str);
			
			dout.write(("SCHD "+jobID+" "+sysType+" "+sysID+"\n").getBytes());
			dout.flush();
			str=in.readLine();
			System.out.println("RCVD"+str);
			
			}
			dout.write(("REDY\n").getBytes());
		//	dout.flush();
			str=in.readLine();
			}
		
			dout.close();
			s.close();
		}catch(Exception e){System.out.println(e);}
	}
}
