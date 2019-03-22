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
	// XML을 파싱한 ParseTree에서 XML Attribute인 경우 0번 자식은 속성 이름, 1번 자식은 = 기호, 2번 자식은 속성 값
	
	public static String getElementName(ParseTree tree) {
		return tree.getChild(1).toString();
	}
	// XML을 파싱한 ParseTree에서 XML Element인 경우 0번 자식은 < 기호, 1번 자식은 엘리먼트 이름, 2번 자식은 > 기호
	
	public static String getText(ParseTree tree) {
		String text = Empty; // 임시로 저장할 텍스트 값
		String result = Empty; // 실제 텍스트 값
		String ignore = Empty; // 무시해야 하는 문자
		
		Character ch = null;
		boolean hasMeetNotIgnoringChar; // 무시할 문자가 아닌 문자와 만났는지
		int lastIndexofNotIgnoringChar; // 무시할 문자가 아닌 문자의 마지막 인덱스
		
		for (int j = 0; j < tree.getChildCount(); j++) { // 여러 자식들 중 진짜 텍스트 노드를 찾아야 함
			text = tree.getChild(j).toString();
			ignore = Empty + Space + Tab + CarriageReturn + LineFeed;
			hasMeetNotIgnoringChar = false;
			lastIndexofNotIgnoringChar = -1;
			
			// XML 텍스트 노드에서는 \t, \r, \n등의 특수 문자가 보여져야할 문자로 출력되지만
			// JSON에서는 문자 그대로 '\t', '\r', '\n'이 출력된다.
			// 이렇게 특수문자를 그대로 출력하는 현상을 제거하는 하는 과정을 추가함
			
			for (int k = text.length() - 1; k >= 0; k--) {
				ch = new Character(text.charAt(k));
				if (!ignore.contains(ch.toString())) {
					lastIndexofNotIgnoringChar = k; // 무시할 문자가 아닌 문자의 마지막 인덱스
					break;
				}
			}

			for (int k = 0; k <= lastIndexofNotIgnoringChar; k++) // 마지막 인덱스 뒤의 것들은 자동으로 무시
			{
				ch = new Character(text.charAt(k));
				if (!ignore.contains(ch.toString())) {
					if (ch.equals(Tab) || ch.equals(LineFeed)) { 
						result += Space;
						// 엔터와 탭은 공백으로 대체
					} else {
						result += ch.toString();
					}
					
					if (!hasMeetNotIgnoringChar) {
						hasMeetNotIgnoringChar = true;
						ignore = CarriageReturn.toString(); 
						// 텍스트 노드의 문자와 문자 사이에 공백이나 탭이 들어가는 경우, 공백이나 탭을 넣어주기 위함임
					}
				}
			}

			if (!result.trim().equals(Empty)) {
				return result;
				// 만약 input이 empty라면 텍스트 노드가 아니다.
			}
		}
		
		return Empty;
	}
}
