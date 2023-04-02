import java.io.*;
import java.net.*;

public class finalversion{
	public static void main(String[] args){
		try{
			Socket s=new Socket("localhost",50000); //initialise input and output streams associated with the socket
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 	DataInputStream in=new DataInputStream(s.getInputStream());
			BufferedReader init = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = "";
			
			Integer nRec=0;
			String[] sysInfo=null; //
			Integer currentID = 0;
			Integer read =0;
			String[] info=null;
			String servertype=null;
			Integer servercore=0;
			Integer serverid = 0;
			String servertype1=null;
			Integer servercore1=0;
			Integer serverid1 = 0;
			
			dout.write(("HELO\n").getBytes()); //initialisiing interaction between ds-server and ds-client
			dout.flush();
			str = in.readLine(); //receive ok
			
			dout.write(("AUTH natalie1\n").getBytes()); //authenticating user
			dout.flush();
			str = in.readLine(); //receive ok
		
			dout.write(("REDY\n").getBytes());//send redy for server to start sending jobs
			dout.flush();
			str = in.readLine(); //receive JOBN information
			
			String[] serverType = null; //string array to save the server types 
			Integer[] serverID = null; //integer array to save server ids 
			
			while(!str.contains("NONE")){
				if(str.contains("ERR")){
					break;
				}
				else if(str.contains("JOBN")|| str.contains("JOBP")){
			
					String[] jobInfo=str.split("\\s+");
					Integer jobID = Integer.parseInt(jobInfo[2]);
			
						if(read==0){ //read integer ensures the program only uses gets all once and when this process is done, read =1. 
							dout.write(("GETS All\n").getBytes()); //sends GETS ALL message 
							dout.flush();
							System.out.println("GETS All");
							str=in.readLine(); // receive DATA message
							System.out.println("RCVD"+str);
							info = str.split("\\s+"); //split the received information
							nRec=Integer.parseInt(info[1]); //nRec is number of servers available
							System.out.println(nRec);
							dout.write(("OK\n").getBytes()); //send ok back to server 
							System.out.println("SENT OK");
							dout.flush();
			
							str=in.readLine(); //receive all server information
							String[] serverInfo = str.split("\\s+"); //split the server information where there are spaces		
			
							serverType = new String[nRec]; //servertype array has maximum length of number of servers
							serverID = new Integer[nRec]; //serverid array has maximum length of number of servers
							servertype=serverInfo[0]; //assigning 0 index value from serverInfo into server type
							servercore=Integer.parseInt(serverInfo[4]);//assigning 4 index value from serverInfo into servercore and converting the string value into integer 
							serverid = Integer.parseInt(serverInfo[1]);//assigning 1 index value from serverInfo into serverid and converting the string value into integer
							serverType[0] = servertype; //store the first value in servertype into serverType array to compare which serverType is largest later in for loop
							serverID[0] = serverid; //store the first value in serverid into server ID array to compare which serverType is largest later in for loop
			
							int index =0; //changes to the newest id value of a server if core later server is bigger than previous server 
			
								for(int i=1; i<nRec; i++){ //cycle through rest of server information to check which server's core is the largest 
									str=in.readLine(); 
									String[] serverlargest = str.split("\\s+");
									servertype1=serverlargest[0];
									servercore1=Integer.parseInt(serverlargest[4]);
									serverid1 = Integer.parseInt(serverlargest[1]);
									serverType[i] = servertype1;
									serverID[i] = serverid1;
			
										if(servercore<servercore1){
											servertype=servertype1;
											System.out.println(servertype);
											servercore=servercore1;
											index = i;
		
										}
								}
		
								for(int i= index; i<serverType.length; i++){ //check the number of the largest server type available 
									System.out.println(i);
									System.out.println(serverID[i]);
									System.out.println(serverType[i]);
										if(servertype.equals(serverType[i])){
											serverid = serverID[i];
										}
			
								}
			
							dout.write(("OK\n").getBytes());
							dout.flush();
							str=in.readLine();
							read = 1; //change value after reading all server info once
						}
						if(currentID>serverid){
							currentID=0; //cycle through the largest possible number of the largest server type and when reaching maximum change to 0
						}
					dout.write(("SCHD "+jobID+" "+servertype+" "+currentID+"\n").getBytes()); //schedule the job to the cycled largest server type
					dout.flush();
					currentID++; //increment the serverid
					str=in.readLine(); //
					System.out.println("RCVD"+str);
				
				}
			
			dout.write(("REDY\n").getBytes()); //send next job
			dout.flush();
			str=in.readLine();
			}
			dout.write(("QUIT\n").getBytes()); // exit program interaction
			dout.flush();
			str=in.readLine();
			dout.close(); //close output
			s.close();//close socket
		}catch(Exception e){System.out.println(e);
		}
	}
}

