import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONArray;

public class Xml2Json {

	private static final String Attribute = "[54";
	private static final String Attribute2 = "[85";
	private static final String Attribute3 = "[41";
	private static final String Text = "[61";
	private static final String Element = "[64";
	private static final String RootElement = "[23";
	//private static ArrayList<ParseTree> hasParsed = new ArrayList<ParseTree>();
	
	private String FilePath = null;
	
	public Xml2Json(String FilePath) throws Exception
	{
		this.FilePath = FilePath;
	}
	
	public Xml2Json(File XMLFile) throws Exception
	{
		FilePath = XMLFile.getAbsolutePath();
	}
	
	public PrettyJSONObject Parse() throws Exception
	{
		byte[] InputStream = Files.readAllBytes(Paths.get(FilePath));
		String InputString = RemoveBOM(InputStream);
		
		ANTLRInputStream input = new ANTLRInputStream(InputString);
		XMLLexer lex = new XMLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        XMLParser g = new XMLParser(tokens); 
        RuleContext tree = g.document();
        
        System.out.println(tree.toStringTree());
        
        PrettyJSONObject jsonObj = Parse(tree);
        
        return jsonObj;
	}
	
	@SuppressWarnings({ "unchecked" })
	private PrettyJSONObject Parse(ParseTree tree)
    {
    	PrettyJSONObject jsonObj = new PrettyJSONObject();

    	for(int i = 0; i < tree.getChildCount(); i++)
    	{
    		String Identifier = tree.getChild(i).toString();
    		//System.out.println(Identifier);
    		if(Identifier.contains(Attribute) || Identifier.startsWith(Attribute2) || Identifier.startsWith(Attribute3))
    		{
    			//System.out.println(tree.getParent().getChild(1));
    			//System.out.println("�Ӽ�");
				jsonObj.put(/*"@" + */tree.getChild(i).getChild(0), tree.getChild(i).getChild(2));
    		}
    		
    		else if(Identifier.contains(Text))
    		{
    			//System.out.println("�ؽ�Ʈ");
    			String text = "";
    			String input = "";
    			String avoid = "";
    			Character ch = null;
    			boolean hasMeetNotAvoidingChar;
    			int LastIndexofNotAvoidingChar;
    			
    			for(int j = 0; j < tree.getChild(i).getChildCount(); j++)
    			{
    				text = tree.getChild(i).getChild(j).toString();
    				avoid = "\r\n\t ";
    				hasMeetNotAvoidingChar = false;
    				LastIndexofNotAvoidingChar = -1;
    				
    				for(int k = text.length() - 1; k >= 0; k--)
    				{
    					ch = new Character(text.charAt(k));
    					if(!avoid.contains(ch.toString()))
    					{
    						LastIndexofNotAvoidingChar = k; //������ ���ڰ� �ƴ� ������ ������ �ε���
    						break;
    					}
    				}
    				
    				for(int k = 0; k <= LastIndexofNotAvoidingChar; k++) //������ �ε��� ���� �͵��� �ڵ����� ����
    				{
    					ch = new Character(text.charAt(k));
    					if(!avoid.contains(ch.toString()))
    					{
    						if(ch.toString().equals("\t") | ch.toString().equals("\n")) //���Ϳ� ���� �������� ��ü
    							input += " ";
    						else input += ch.toString();
    						if(!hasMeetNotAvoidingChar)
    						{
    							hasMeetNotAvoidingChar = true;
    							avoid = "\r"; //�ؽ�Ʈ ����� ���ڿ� ���� ���̿� �����̳� ���� ���� ���, �����̳� ���� �־��ֱ� ������
    						}
    					}
    				}
    				
    				if(input != "") //���� input�� empty��� ���������� ���� �ؽ�Ʈ ����̰ų� ������ ���� �ؽ�Ʈ ����� ����.
    					jsonObj.put("#text", input);
    			}
    		}
    		
    		else if(Identifier.contains(RootElement) || Identifier.contains(Element))
    		{ 
    			JSONArray OverlappedValue = new JSONArray();
    			boolean hasChildren = false;
    			
    			if(jsonObj.containsKey(tree.getChild(i).getChild(1).toString()))
    			{
    				//System.out.println("�ߺ��� ��� " + tree.getChild(i).getChild(1));
    				OverlappedValue.add(jsonObj.get(tree.getChild(i).getChild(1).toString()));
    				jsonObj.remove(tree.getChild(i).getChild(1).toString());
    				//if(hasParsed.contains(tree.getChild(i)))
    					//hasParsed.remove(tree.getChild(i));
    			}
    			
    			jsonObj.put(tree.getChild(i).getChild(1).toString(), null);
    			
    			for(int j = 0; j < tree.getChild(i).getChildCount(); j++)
    			{		    				
    				String tempIdentifier = tree.getChild(i).getChild(j).toString();
    				if(tempIdentifier.contains(Element) || tempIdentifier.contains(Text) || tempIdentifier.contains(Attribute) || tempIdentifier.contains(Attribute2) || tempIdentifier.contains(Attribute3))
    				{	//json������ �Ӽ��� �ڽ�����
    					hasChildren = true;
    					break;
    				}
    			}
    			
    			//if(!hasParsed.contains(tree.getChild(i).hashCode()))
    			{
	    			if(hasChildren)
	    			{
						jsonObj.put(tree.getChild(i).getChild(1).toString(), Parse(tree.getChild(i)));
	    				//hasParsed.add(tree.getChild(i));
	    			}
	    			
	    			else if(!hasChildren)
	    			{
	    				jsonObj.put(tree.getChild(i).getChild(1).toString(), new JSONArray());
	    				//hasParsed.add(tree.getChild(i));
	    			}
	    			
	    			if(OverlappedValue.size() > 0)
	    			{
	    				OverlappedValue.add(jsonObj.get(tree.getChild(i).getChild(1).toString()));
	    				jsonObj.put(tree.getChild(i).getChild(1).toString(), OverlappedValue);
	    				//hasParsed.add(tree.getChild(i));
	    			}
    			}
    		}
    	}
    	return jsonObj;
    }
	
	private String RemoveBOM(byte[] InputStream) throws Exception
    {
		int StartIndex = -1;
		String[] BOM = BOMArray();
		
		String hexdata = byteArrayToHex(InputStream);
		
		System.out.println(hexdata);
		
		for(int i = 0; i < BOM.length; i++)
		{
			if(hexdata.startsWith(BOM[i]))
			{
				StartIndex = BOM[i].length();
				break;
			}
		}
		
		if(StartIndex > 0)
			hexdata = hexdata.substring(StartIndex, hexdata.length());
		
		System.out.println(hexdata);
		
		return new String(hexToByteArray(hexdata));
    }
	
	private String byteArrayToHex(byte[] ba) 
	{
		if (ba == null || ba.length == 0)
	        return null;
	 
	    StringBuffer sb = new StringBuffer(ba.length * 2);
	    String hexNumber;
	    for (int x = 0; x < ba.length; x++) 
	    {
	        hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	    return sb.toString().toUpperCase();
	} 
	
	private byte[] hexToByteArray(String hex) 
	{
	    if (hex == null || hex.length() == 0) 
	        return null;
	 
	    byte[] ba = new byte[hex.length() / 2];
	    for (int i = 0; i < ba.length; i++) 
	        ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	    
	    return ba;
	}
	
	private String[] BOMArray()
	{
		final String UTF8 = "EFBBBF";
		final String UTF16BE = "FEFF";
		final String UTF16LE = "FFFE";
		final String UTF32BE = "0000FEFF";
		final String UTF32LE = "FFFE0000";
		final String UTF7Base = "2B2F76";
		final String UTF7_1 = UTF7Base + "38";
		final String UTF7_2 = UTF7Base + "39";
		final String UTF7_3 = UTF7Base + "2B";
		final String UTF7_4 = UTF7Base + "2F";
		final String UTF7_5 = UTF7Base + "382D";
		final String UTF1 = "F7644C";
		final String UTFEBCDIC = "DD736673";
		final String SCSU = "0EFEFF";
		final String BOCU1 = "FBEE28";
		final String GB18030 = "84319533";
		
		ArrayList<String> BOMList = new ArrayList<String>();
		
		BOMList.add(UTF8);
		BOMList.add(UTF16BE);
		BOMList.add(UTF16LE);
		BOMList.add(UTF32BE);
		BOMList.add(UTF32LE);
		BOMList.add(UTF7_1);
		BOMList.add(UTF7_2);
		BOMList.add(UTF7_3);
		BOMList.add(UTF7_4);
		BOMList.add(UTF7_5);
		BOMList.add(UTF1);
		BOMList.add(UTFEBCDIC);
		BOMList.add(SCSU);
		BOMList.add(BOCU1);
		BOMList.add(GB18030);
		
		String[] tempArray = BOMList.toArray(new String[BOMList.size() - 1]);
		
		return tempArray;
	}
}
