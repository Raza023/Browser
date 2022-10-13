
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class FileReaderWriter
{
    public void ReadFile()
    {
        try
        {
            File f= new File("Data.data");
            FileReader fr= new FileReader(f);
            BufferedReader br= new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null)
            {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public String ReadHomePage()
    {
        try
        {
            File f= new File("Homepage.data");
            FileReader fr= new FileReader(f);
            BufferedReader br= new BufferedReader(fr);
            String line = br.readLine();
            br.close();
            fr.close();
            return line;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "https://www.google.com";
        }
    }
    
    public void WriteFile(String str)
    {
        try
        {
            PrintWriter pw=new PrintWriter(new FileWriter(new File("Data.data"),true));                             //history
            pw.println(str);
            pw.close();
        }
        catch (Exception ex)
        {
                ex.printStackTrace();
        }
    }
    public void ftwrite(String str)
    {
        try
        {
            PrintWriter pw=new PrintWriter(new FileWriter(new File("Favorite.data"),true));
            pw.println(str);
            pw.close();
            
        } catch (Exception ex) {
                ex.printStackTrace();
        }
    }
    
    public void fwwrite(String str)
    {
        try
        {
            PrintWriter pw=new PrintWriter(new FileWriter(new File("Firewall.data"),true));
            pw.println(str);
            pw.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void hpwrite(String str)
    {
        try
        {
            PrintWriter pw=new PrintWriter(new FileWriter(new File("Homepage.data"),false));
            pw.println(str);
            pw.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void clearFile(String file)
    {
        try 
        {
            PrintWriter pw=new PrintWriter(new FileWriter(new File(file)),false);
            pw.flush();
            pw.close();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
