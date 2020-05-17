package xmlToJsonParser.utils.templates;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class ANTLRLexerParserTemplate {
	public final ParseTree parse(String inputString) {
		ANTLRInputStream input = new ANTLRInputStream(inputString);
		Lexer lexer = getLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Parser parser = getParser(tokens);
		
		return getParseTree(parser);
	}
	
	protected abstract Lexer getLexer(ANTLRInputStream inputStream);
	
	protected abstract Parser getParser(CommonTokenStream tokens);
	
	protected abstract ParseTree getParseTree(Parser parser);
}
