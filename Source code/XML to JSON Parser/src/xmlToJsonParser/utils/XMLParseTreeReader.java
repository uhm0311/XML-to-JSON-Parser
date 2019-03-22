package xmlToJsonParser.utils;

import org.antlr.v4.runtime.tree.ParseTree;

public class XMLParseTreeReader {	
	private XMLParseTreeReader() {
	}
	
	public static final String TextKey = "#text";
	
	private static final String Empty = "";
	private static final Character Space = ' ';
	private static final Character Tab = '\t';
	private static final Character CarriageReturn = '\r';
	private static final Character LineFeed = '\n';
	private static final Character DoubleQuotes = '\"';
	
	public static String getAttributeName(ParseTree tree) {
		return tree.getChild(0).toString();
	}
	
	public static String getAttributeValue(ParseTree tree) {
		return tree.getChild(2).toString().replace(DoubleQuotes.toString(), Empty);
	}
	// XML�� �Ľ��� ParseTree���� XML Attribute�� ��� 0�� �ڽ��� �Ӽ� �̸�, 1�� �ڽ��� = ��ȣ, 2�� �ڽ��� �Ӽ� ��
	
	public static String getElementName(ParseTree tree) {
		return tree.getChild(1).toString();
	}
	// XML�� �Ľ��� ParseTree���� XML Element�� ��� 0�� �ڽ��� < ��ȣ, 1�� �ڽ��� ������Ʈ �̸�, 2�� �ڽ��� > ��ȣ
	
	public static String getText(ParseTree tree) {
		String text = Empty; // �ӽ÷� ������ �ؽ�Ʈ ��
		String result = Empty; // ���� �ؽ�Ʈ ��
		String ignore = Empty; // �����ؾ� �ϴ� ����
		
		Character ch = null;
		boolean hasMeetNotIgnoringChar; // ������ ���ڰ� �ƴ� ���ڿ� ��������
		int lastIndexofNotIgnoringChar; // ������ ���ڰ� �ƴ� ������ ������ �ε���
		
		for (int j = 0; j < tree.getChildCount(); j++) { // ���� �ڽĵ� �� ��¥ �ؽ�Ʈ ��带 ã�ƾ� ��
			text = tree.getChild(j).toString();
			ignore = Empty + Space + Tab + CarriageReturn + LineFeed;
			hasMeetNotIgnoringChar = false;
			lastIndexofNotIgnoringChar = -1;
			
			// XML �ؽ�Ʈ ��忡���� \t, \r, \n���� Ư�� ���ڰ� ���������� ���ڷ� ��µ�����
			// JSON������ ���� �״�� '\t', '\r', '\n'�� ��µȴ�.
			// �̷��� Ư�����ڸ� �״�� ����ϴ� ������ �����ϴ� �ϴ� ������ �߰���
			
			for (int k = text.length() - 1; k >= 0; k--) {
				ch = new Character(text.charAt(k));
				if (!ignore.contains(ch.toString())) {
					lastIndexofNotIgnoringChar = k; // ������ ���ڰ� �ƴ� ������ ������ �ε���
					break;
				}
			}

			for (int k = 0; k <= lastIndexofNotIgnoringChar; k++) // ������ �ε��� ���� �͵��� �ڵ����� ����
			{
				ch = new Character(text.charAt(k));
				if (!ignore.contains(ch.toString())) {
					if (ch.equals(Tab) || ch.equals(LineFeed)) { 
						result += Space;
						// ���Ϳ� ���� �������� ��ü
					} else {
						result += ch.toString();
					}
					
					if (!hasMeetNotIgnoringChar) {
						hasMeetNotIgnoringChar = true;
						ignore = CarriageReturn.toString(); 
						// �ؽ�Ʈ ����� ���ڿ� ���� ���̿� �����̳� ���� ���� ���, �����̳� ���� �־��ֱ� ������
					}
				}
			}

			if (!result.trim().equals(Empty)) {
				return result;
				// ���� input�� empty��� �ؽ�Ʈ ��尡 �ƴϴ�.
			}
		}
		
		return Empty;
	}
}
