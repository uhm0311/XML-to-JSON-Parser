package xmlToJsonParser.utils;

import org.antlr.v4.runtime.tree.ParseTree;

public class XMLParseTreeIdentifier {
	
	private XMLParseTreeIdentifier() {
	}
	
	private static final String Attribute = "[54";
	private static final String Attribute2 = "[85";
	private static final String Attribute3 = "[41";
	private static final String[] Attributes = new String[] { Attribute, Attribute2, Attribute3 };

	private static final String Root = "[23";
	private static final String Element = "[64";
	private static final String[] Elements = new String[] { Root, Element };
	
	private static final String Text = "[61";
	
	public static boolean isAttribute(ParseTree tree) {
		String identifier = tree.toString();
		
		for (int i = 0; i < Attributes.length; i++) {
			if (identifier.startsWith(Attributes[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isText(ParseTree tree) {
		return  tree.toString().startsWith(Text);
	}
	
	public static boolean isRoot(ParseTree tree) {
		return tree.toString().startsWith(Root);
	}
	
	public static boolean isElement(ParseTree tree) {
		String identifier = tree.toString();
		
		for (int i = 0; i < Elements.length; i++) {
			if (identifier.startsWith(Elements[i])) {
				return true;
			}
		}
		
		return false;
	}
}
