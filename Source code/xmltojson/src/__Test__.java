import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;

public class __Test__ {
    public static void main(String args[]) throws Exception {
		File selectedFile = null;
		String FilePath = "";
		
		JFileChooser Opener = new JFileChooser();
		Opener.removeChoosableFileFilter(Opener.getFileFilter());
		Opener.setFileFilter(new FileNameExtensionFilter("xml", "XML"));
		//Opener.addChoosableFileFilter(new FileNameExtensionFilter("txt", "TEXT"));
		Opener.setCurrentDirectory(new File(new File("C:\\").getCanonicalPath()));
		int result2 = Opener.showOpenDialog(null);
		
		if (result2 == JFileChooser.APPROVE_OPTION) 
		{
		    selectedFile = Opener.getSelectedFile();
		    FilePath = selectedFile.getAbsolutePath();
		}
		else return;
		
        Xml2Json xml2json = new Xml2Json(FilePath);
        JSONObject jsonObj = xml2json.Parse();
        
        JFileChooser Saver = new JFileChooser();
		Saver.removeChoosableFileFilter(Saver.getFileFilter());
		Saver.setFileFilter(new FileNameExtensionFilter("js", "JavaScript"));
		//Saver.addChoosableFileFilter(new FileNameExtensionFilter("txt", "TEXT"));
		Saver.setSelectedFile(new File(FilePath.substring(0, FilePath.length() - 4) + ".js"));
		result2 = Saver.showSaveDialog(null);
		
		if (result2 == JFileChooser.APPROVE_OPTION) 
		{
		    selectedFile = Saver.getSelectedFile();
		    FilePath = selectedFile.getAbsolutePath();
		    
		    if(FilePath.length() <= 3 || (!FilePath.substring(FilePath.length() - 3, FilePath.length()).equals(".js") && !FilePath.substring(FilePath.length() - 4, FilePath.length()).equals(".txt")))
	    		FilePath += ".js";
		    
	        FileWriter out = new FileWriter(FilePath);
	        out.write("var jsonObj = " + jsonObj.toJSONString() + ";");
	        out.close();
	        
	        FileReader in = new FileReader(FilePath);
	        
	        int ch = in.read();
	        while(ch != -1)
	        {
	        	System.out.print((char)ch);
	        	ch = in.read();
	        }
	        
	        in.close();
		}
		else return;
    }
}