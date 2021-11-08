package module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class Actions {
	
	public User save(User u, String path)
    {
    	try 
    	{
    	   File f = new File(path);
           if(!f.exists())
               f.createNewFile();
           
           if(u.id == "")
        	   u.id = getNextId(f);
           
           if(idIsDuplicate(u.id, f.getPath()))
           {
        	   u.id = ""; 
        	   return u;
           }
           
           FileWriter fileWriter = new FileWriter(f, true);
           BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           bufferedWriter.write(userData(u));
           bufferedWriter.close();
           fileWriter.close();
    	} 
    	catch (IOException e) 
    	{
    		u.id = "";
    		return u;
    	}
    	return u;
    }

    private boolean idIsDuplicate(String newId, String path)
    {
    	try 
    	{  		
    	   File f = new File(path);
           if(!f.exists())
               return false;
           
           BufferedReader reader = new BufferedReader(new FileReader(f));
           
           String currentLine;
           
           while((currentLine = reader.readLine()) != null)
           {
        	   String id = currentLine.split("\\|")[0];
        	   if(id.equals(newId))
        		   return true;
           }
           
           reader.close();
    	} 
    	catch (IOException e) 
    	{
    		return true;
    	}
    	return false;
    }
    public int delete(String id, String path)
    {
 	    int countOfActions = 0;
    	try 
    	{
    	   File f = new File(path);
    	   File tempF = new File("D:\\Users\\User\\Desktop\\UsersTmp.txt");
           if(!f.exists())
               return -1;
           
           BufferedReader reader = new BufferedReader(new FileReader(f));
           BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
           
           String currentLine;
           
           while((currentLine = reader.readLine()) != null)
           {
        	   String[] s = currentLine.split("\\|");
        	   if(s[0].endsWith(id)) 
        		   countOfActions++;
        	   else writer.write(currentLine+ System.getProperty("line.separator"));
           }
           
           writer.close();
           reader.close();
           
           Files.copy(tempF.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
           Files.delete(tempF.toPath());
    	} 
    	catch (IOException e) 
    	{
     	    System.out.println("");
    		return -1;
    	}
    	if(countOfActions != 1)
    		return -1;
    	return 1;
    }
    
    public int update(User updatedUser, String path)
    {
 	    int countOfActions = 0;
    	try 
    	{  		
    	   String data = userData(updatedUser);
    	   File f = new File(path);
           if(!f.exists())
               return -1;
           
           File tempF = new File("D:\\Users\\User\\Desktop\\UsersTmp.txt");
           
           BufferedReader reader = new BufferedReader(new FileReader(f));
           BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
           
           String currentLine;
           
           while((currentLine = reader.readLine()) != null)
           {
        	   String id = currentLine.split("\\|")[0];
        	   if(id.equals(updatedUser.id))
        	   {
        		   countOfActions++;
        		   writer.write(data + System.getProperty("line.separator"));
        	   }
        	   
        	   else writer.write(currentLine + System.getProperty("line.separator"));
           }
           
           writer.close();
           reader.close();

           Files.copy(tempF.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
           Files.delete(tempF.toPath());
    	} 
    	catch (IOException e) 
    	{
    		return -1;
    	}
    	if(countOfActions != 1)
    		return -1;
    	return 1;
    }
    
    public String userData(User u)
    {
    	return u.id + "|" + u.name + "|" + u.surname + "|" + u.phoneNumber + "|" + u.email + "|" + u.address + "|" + u.password;
    }
    
    private String getNextId(File f)
    {
    	String uniqueId = UUID.randomUUID().toString();
    	return uniqueId.substring(0, 3);
    }
}
