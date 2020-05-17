package xmlToJsonParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONArray;

import xmlToJsonParser.json.PrettyJSONObject;
import xmlToJsonParser.utils.BOMRemover;
import xmlToJsonParser.utils.XMLParseTreeIdentifier;
import xmlToJsonParser.utils.XMLParseTreeReader;
import xmlToJsonParser.utils.templates.ANTLRLexerParserTemplate;
import xmlToJsonParser.utils.templates.XMLLexerParser;

public class XMLtoJSONParser {
	private String xmlFilePath = null;

	public XMLtoJSONParser(String xmlFilePath) throws Exception {
		this.xmlFilePath = xmlFilePath;
	}

	public XMLtoJSONParser(File xmlFile) throws Exception {
		xmlFilePath = xmlFile.getAbsolutePath();
	}

	public PrettyJSONObject Parse() throws Exception {
		byte[] inputStream = Files.readAllBytes(Paths.get(xmlFilePath));
		String inputString = new String(BOMRemover.removeBOM(inputStream));

		ANTLRLexerParserTemplate lexerParser = new XMLLexerParser();
		ParseTree tree = lexerParser.parse(inputString);

		System.out.println(tree.toStringTree());
		PrettyJSONObject jsonObj = Parse(tree); // Ʈ���� ��Ʈ���� ����

		return jsonObj;
	}

	private PrettyJSONObject Parse(ParseTree tree) {
		PrettyJSONObject jsonObj = new PrettyJSONObject();

		for (int i = 0; i < tree.getChildCount(); i++) { // ��� �ڽ��� ��ȸ
			ParseTree child = tree.getChild(i);

			if (XMLParseTreeIdentifier.isAttribute(child)) {
				jsonObj.put(XMLParseTreeReader.getAttributeName(child), XMLParseTreeReader.getAttributeValue(child));
			} else if (XMLParseTreeIdentifier.isText(child)) {
				jsonObj.put(XMLParseTreeReader.TextKey, XMLParseTreeReader.getText(child));
			} else if (XMLParseTreeIdentifier.isElement(child)) {
				boolean hasChild = false; // �ڽ��� ������Ʈ�̸� �ڽ��� �ڽ��� ������ ���ɼ��� �ִ�.

				for (int j = 0; j < child.getChildCount(); j++) {
					ParseTree childOfChild = child.getChild(j);

					if ((XMLParseTreeIdentifier.isAttribute(childOfChild)
							|| XMLParseTreeIdentifier.isElement(childOfChild)
							|| XMLParseTreeIdentifier.isText(childOfChild))
							&& !XMLParseTreeIdentifier.isRoot(childOfChild)) { 
						// �ڽ��� �ڽ��� ��Ʈ�� ���� ����.
						hasChild = true;
						break;
					}
				}

				if (hasChild) { // �ڽ��� �ڽ��� ������ ���ȣ��
					jsonObj.put(XMLParseTreeReader.getElementName(child), Parse(child));
				}

				else if (!hasChild) { // �ڽ��� �ڽ��� ������ �� ���� �ڽ����� ��´�.
					jsonObj.put(XMLParseTreeReader.getElementName(child), new JSONArray());
				}
			}
		}
		return jsonObj;
	}

}
