package xmlToJsonParser.utils;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import xmlToJsonParser.antlr.XMLLexer;
import xmlToJsonParser.antlr.XMLParser;

public class XMLLexerParser extends ANTLRLexerParserTemplate {
	@Override
	protected Lexer getLexer(ANTLRInputStream inputStream) {
		// TODO Auto-generated method stub
		return new XMLLexer(inputStream);
	}

	@Override
	protected Parser getParser(CommonTokenStream tokens) {
		// TODO Auto-generated method stub
		return new XMLParser(tokens);
	}

	@Override
	protected ParseTree getParseTree(Parser parser) {
		// TODO Auto-generated method stub
		if (parser != null && parser instanceof XMLParser) {
			return ((XMLParser)parser).document();
		} else {
			return null;
		}
	}

}
