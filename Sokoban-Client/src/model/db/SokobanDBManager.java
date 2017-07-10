package model.db;

import java.util.HashMap;

import javax.print.DocFlavor.INPUT_STREAM;

public class SokobanDBManager extends ADBManeger{


	@Override
	synchronized public void add(Object o) throws Exception{
		String[] input = ((String)o).split(" ",2);
		switch (input[0]) {
		case "user":{
			Users user= new Users(input[1]);
			super.add(user);
			break;
		}
		case "level":{
			Levels level=new Levels(input[1]);
			super.add(level);
			break;
		}
		case "userlevel":{
			String[] params= (input[1]).split(" ",4);
			UserLevel userlevel=new UserLevel(params[0], params[1],Integer.parseInt(params[2]),Integer.parseInt(params[3]));
			super.add(userlevel);
			break;
		}
		default:
			break;
		}
	}
				
}
		
		

