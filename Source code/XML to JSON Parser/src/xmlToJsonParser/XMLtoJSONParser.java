package xmlToJsonParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONArray;

import xmlToJsonParser.json.PrettyJSONObject;
import xmlToJsonParser.utils.ANTLRLexerParserTemplate;
import xmlToJsonParser.utils.BOMRemover;
import xmlToJsonParser.utils.XMLLexerParser;

public class XMLtoJSONParser {

	private static final String Attribute = "[54";
	private static final String Attribute2 = "[85";
	private static final String Attribute3 = "[41";
	private static final String Text = "[61";
	private static final String Element = "[64";
	private static final String RootElement = "[23";
	// private static ArrayList<ParseTree> hasParsed = new ArrayList<ParseTree>();

	private String filePath = null;

	public XMLtoJSONParser(String filePath) throws Exception {
		this.filePath = filePath;
	}

	public XMLtoJSONParser(File xmlFile) throws Exception {
		filePath = xmlFile.getAbsolutePath();
	}

	public PrettyJSONObject Parse() throws Exception {
		byte[] inputStream = Files.readAllBytes(Paths.get(filePath));
		String inputString = new String(BOMRemover.removeBOM(inputStream));

		ANTLRLexerParserTemplate lexerParser = new XMLLexerParser();
		ParseTree tree = lexerParser.parse(inputString);

		System.out.println(tree.toStringTree());
		PrettyJSONObject jsonObj = Parse(tree);

		return jsonObj;
	}

	@SuppressWarnings({ "unchecked" })
	private PrettyJSONObject Parse(ParseTree tree) {
		PrettyJSONObject jsonObj = new PrettyJSONObject();

		for (int i = 0; i < tree.getChildCount(); i++) {
			String identifier = tree.getChild(i).toString();
			// System.out.println(Identifier);
			if (identifier.startsWith(Attribute) || identifier.startsWith(Attribute2) || identifier.startsWith(Attribute3)) {
				// System.out.println(tree.getParent().getChild(1));
				// System.out.println("속성");
				jsonObj.put(/* "@" + */tree.getChild(i).getChild(0), tree.getChild(i).getChild(2));
			}

			else if (identifier.startsWith(Text)) {
				// System.out.println("텍스트");
				String text = "";
				String input = "";
				String avoid = "";
				Character ch = null;
				boolean hasMeetNotAvoidingChar;
				int LastIndexofNotAvoidingChar;

				for (int j = 0; j < tree.getChild(i).getChildCount(); j++) {
					text = tree.getChild(i).getChild(j).toString();
					avoid = "\r\n\t ";
					hasMeetNotAvoidingChar = false;
					LastIndexofNotAvoidingChar = -1;

					for (int k = text.length() - 1; k >= 0; k--) {
						ch = new Character(text.charAt(k));
						if (!avoid.contains(ch.toString())) {
							LastIndexofNotAvoidingChar = k; // 무시할 문자가 아닌 문자의 마지막 인덱스
							break;
						}
					}

					for (int k = 0; k <= LastIndexofNotAvoidingChar; k++) // 마지막 인덱스 뒤의 것들은 자동으로 무시
					{
						ch = new Character(text.charAt(k));
						if (!avoid.contains(ch.toString())) {
							if (ch.toString().equals("\t") | ch.toString().equals("\n")) // 엔터와 탭은 공백으로 대체
								input += " ";
							else
								input += ch.toString();
							if (!hasMeetNotAvoidingChar) {
								hasMeetNotAvoidingChar = true;
								avoid = "\r"; // 텍스트 노드의 문자와 문자 사이에 공백이나 탭이 들어가는 경우, 공백이나 탭을 넣어주기 위함임
							}
						}
					}

					if (input != "") // 만약 input이 empty라면 정상적이지 않은 텍스트 노드이거나 내용이 없는 텍스트 노드인 경우다.
						jsonObj.put("#text", input);
				}
			} else if (identifier.startsWith(RootElement) || identifier.startsWith(Element)) {
				JSONArray overlappedValues = new JSONArray();
				boolean hasChildren = false;

				// XML 상의 중복된 자식 노드는 그대로 표시되지만
				// JSON에서 key가 중복될 경우 값을 덮어 씌운다.
				// 이를 해결하기 위해 OverlappedValues를 통해 중복된 자식 노드를 처리하는 과정을 추가
				/*
				 * if(jsonObj.containsKey(tree.getChild(i).getChild(1).toString())) {
				 * //System.out.println("중복된 노드 " + tree.getChild(i).getChild(1));
				 * overlappedValues.add(jsonObj.get(tree.getChild(i).getChild(1).toString()));
				 * jsonObj.remove(tree.getChild(i).getChild(1).toString());
				 * //if(hasParsed.contains(tree.getChild(i)))
				 * //hasParsed.remove(tree.getChild(i)); }
				 * 
				 * jsonObj.put(tree.getChild(i).getChild(1).toString(), null);
				 */

				for (int j = 0; j < tree.getChild(i).getChildCount(); j++) {
					String tempIdentifier = tree.getChild(i).getChild(j).toString();
					if (tempIdentifier.startsWith(Element) || tempIdentifier.startsWith(Text) || tempIdentifier.startsWith(Attribute) || tempIdentifier.startsWith(Attribute2) || tempIdentifier.startsWith(Attribute3)) { 
						// json에서는 속성도 자식으로
						hasChildren = true;
						break;
					}
				}

				// if(!hasParsed.contains(tree.getChild(i).hashCode()))
				{
					if (hasChildren) {
						jsonObj.put(tree.getChild(i).getChild(1).toString(), Parse(tree.getChild(i)));
						// hasParsed.add(tree.getChild(i));
					}

					else if (!hasChildren) {
						jsonObj.put(tree.getChild(i).getChild(1).toString(), new JSONArray());
						// hasParsed.add(tree.getChild(i));
					}

					if (overlappedValues.size() > 0) {
						overlappedValues.add(jsonObj.get(tree.getChild(i).getChild(1).toString()));
						jsonObj.put(tree.getChild(i).getChild(1).toString(), overlappedValues);
						// hasParsed.add(tree.getChild(i));
					}
				}
			}
		}
		return jsonObj;
	}
}
