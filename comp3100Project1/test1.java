import java.io.*;
import java.net.*;



public class test1{
	public static void main(String[] args){
		try{
			Socket s=new Socket("localhost",50000);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 	DataInputStream in=new DataInputStream(s.getInputStream());
			BufferedReader init = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
			
			Integer nRec=0;
			String[] sysInfo=null;
			//String[] sysType=null;
			//Integer sysID=null;
			Integer currentID = 0;
			Integer read =0;
			String[] info=null;
			String servertype=null;
			Integer servercore=0;
			Integer serverid = 0;
			String servertype1=null;
			Integer servercore1=0;
			Integer serverid1 = 0;
			
			dout.write(("HELO\n").getBytes());
			dout.flush();
			str = in.readLine();
			
		
			dout.write(("AUTH natalie1\n").getBytes());
			dout.flush();
			str = in.readLine();
		
			dout.write(("REDY\n").getBytes());
			dout.flush();
			str = in.readLine();
			
			//int read = 0;
		
		
			String[] serverType = null;
			Integer[] serverID = null; 
			
			while(!str.contains("NONE")){
			if(str.contains("ERR")){
			break;
			}
			else if(str.contains("JOBN")|| str.contains("JOBP")){
			
			String[] jobInfo=str.split("\\s+");
			Integer jobID = Integer.parseInt(jobInfo[2]);
			
			
			if(read==0){
			dout.write(("GETS All\n").getBytes());
			
			dout.flush();
			System.out.println("GETS All");
			str=in.readLine();
			System.out.println("RCVD"+str);
			info = str.split("\\s+");
			nRec=Integer.parseInt(info[1]);
			System.out.println(nRec);
			dout.write(("OK\n").getBytes()); //then prints out the servers 
			System.out.println("SENT OK");
			dout.flush();
			
			str=in.readLine();
			String[] serverInfo = str.split("\\s+");			
			
			serverType = new String[nRec];
		//	System.out.println(serverType.length);
			serverID = new Integer[nRec];
			servertype=serverInfo[0];
		//	System.out.println("line 72");
			servercore=Integer.parseInt(serverInfo[4]);
		//	System.out.println("line 74");
			serverid = Integer.parseInt(serverInfo[1]);
		//	System.out.println("line 76");
			serverType[0] = servertype;
		//	System.out.println("line 78");
			serverID[0] = serverid;
		//	System.out.println("line 80");
			
		//	dout.write(("OK\n").getBytes());
		//	str=in.readLine();
			
			
			int index =0;
			
			
			
			
			for(int i=1; i<nRec; i++){
			str=in.readLine();
		//	System.out.println(str);
			String[] serverlargest = str.split("\\s+");
		//	System.out.println(serverlargest.length);	
			servertype1=serverlargest[0];
		//	System.out.println("Line 91");
			servercore1=Integer.parseInt(serverlargest[4]);
			//System.out.println("Line 93");
			serverid1 = Integer.parseInt(serverlargest[1]);
			//System.out.println("Line 95");
			
			serverType[i] = servertype1;
		//	System.out.println(serverType[i]);
			//System.out.println("Line 98");
			
			serverID[i] = serverid1;
		//	System.out.println(serverID[i]);
			
			if(servercore<servercore1){

			servertype=servertype1;
			System.out.println(servertype);
			servercore=servercore1;
		//	serverid = serverid1;
			index = i;
		//	System.out.println(index);
			}
			}
			//System.out.println(serverType.length);
		//	System.out.println(servertype);
			for(int i= index; i<serverType.length; i++){
			System.out.println(i);
			System.out.println(serverID[i]);
			System.out.println(serverType[i]);
			if(servertype.equals(serverType[i])){
			 serverid = serverID[i];
			 
	//		 System.out.println(index);
		//	 System.out.println(serverid);
			}
			
			}
			
			dout.write(("OK\n").getBytes());
			dout.flush();
			str=in.readLine();
			read = 1;
			}
								
		//	dout.write(("OK\n").getBytes());
		//	dout.flush();
		//	str=in.readLine();
		//	System.out.println("RCVD"+str);
			if(currentID>serverid){
			currentID=0;
			}
			dout.write(("SCHD "+jobID+" "+servertype+" "+currentID+"\n").getBytes());
			dout.flush();
			currentID++;
			str=in.readLine();
			System.out.println("RCVD"+str);
			
			}
			
			dout.write(("REDY\n").getBytes());
			dout.flush();
			str=in.readLine();
			}
			dout.write(("QUIT\n").getBytes());
			dout.flush();
			str=in.readLine();
			dout.close();
			s.close();
		}catch(Exception e){System.out.println(e);
		}
	}
}
