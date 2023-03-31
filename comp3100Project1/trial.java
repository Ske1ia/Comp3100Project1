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
			Integer currentID = 0;
			
			dout.write(("HELO\n").getBytes());
			str = in.readLine();
		
			dout.write(("AUTH natalie1\n").getBytes());
			str = in.readLine();
		
			dout.write(("REDY\n").getBytes());
			str = in.readLine();
			
			//int read = 0;
		
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
			
			
			if(jobCores>4){ //allow string name of each server type to equal to an int and cycle through each server, if jobcore is more than assigned servercore, the server type will go up a server
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
			
			str=in.readLine();
			String[] serverType = null;
			String[] serverID = null; 
			
			if(int read=0){
			String[] info = str.split("\\s+");
			nRec=Integer.parseInt(info[1]);
			serverType = new String[nrec];
			serverID = new String[nrec];
			dout.write(("OK\n").getBytes());
			str=in.readLine();
			String[] serverInfo = str.split("\\s+");
			int index =0;
			servertype=serverInfo[0];
			servercore=Integer.parseInt(serverInfo[4]);
			serverid = Integer.parseInt(serverInfo[1]);
			serverType[0] = servertype;
			ServerID[0] = serverid;
			
			for(i=1; i<7; i++){
			str=in.readLine();
			String[] serverlargest = str.split("\\s+");	
			servertype1=serverlargest[0];
			servercore1=serverlargest[4];
			serverid1 = serverlargest[1];
			serverType[i] = servertype;
			ServerID[i] = serverid;
			if(servercore<servercore1){
			servertype=serverlargest[0];
			servercore=serverlargest[4];
			serverid = serverlargest[1];
			index = i;
			}
			}
			for loop(i = index; i<serverType.length; i++){
			if(serverType[i] == servertype){
			 serveid = serverID[i]
			}
			
			read = 1;
			}
			
			//readlin
			//string[] serverType = null;
			//String[] serverID = null;
			
			//if (read = 0){
			//string split
			//nrec 
			//serverType = new String[nrec];
			//serverID = new String[nrec];
			// send ok
			//read str
			//string split
			//int index = 0;
			//servertype=string[0];
			//servercore=string[4];
			//serverid = string[1];
			//serverType[0] = servertype;
			//ServerID[0] = serverid;
			//for loop (i=1; i<7)
			//read str
			//string slit
			//servertype=string[0];
			//servercore=string[4];
			//serverid = string[1];
			//serverType[i] = servertype;
			//ServerID[i] = serverid;
			//if(core1 > core)
			//servertype=string[0];
			//servercore=string[4];
			//serverid = string[1];
			//index = i;
			//close if
			//close for loop
			//
			// for loop(i = index; i<serverType.length; i++){
			//if(serverType[i] == servertype){
			// serveid = serverID[i]
			//end if
			
			//read = 1;
			
			
			
		//	str=in.readLine();
		//	String[] info = str.split("\\s+"); //splits DATA (no. server) (?) 
		//	nRec=Integer.parseInt(info[1]);
		//	sysInfo=str.split("\\s+");
		//	//sysType="juju";
		//	sysType=sysInfo[0]; 
		//	sysID=0;
		//	
			String[] serverType= 
		
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
			
			//if (count < i) 
			//count++;
			}
			
			dout.write(("REDY\n").getBytes());
		//	dout.flush();
			str=in.readLine();
			}
			//count=0;
			dout.close();
			s.close();
		}catch(Exception e){System.out.println(e);}
	}
}
