// Generated from C:\Users\User\Google 드라이브\단국대\프로그래밍\ANTLR\workspace\xml to json\XMLParser.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XMLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN=7, CDATA=2, SLASH=13, CharRef=5, SEA_WS=6, SPECIAL_CLOSE=11, CLOSE=10, 
		DTD=3, Name=16, EQUALS=14, PI=18, S=17, SLASH_CLOSE=12, TEXT=9, COMMENT=1, 
		XMLDeclOpen=8, EntityRef=4, STRING=15;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", 
		"'<'", "XMLDeclOpen", "TEXT", "'>'", "SPECIAL_CLOSE", "'/>'", "'/'", "'='", 
		"STRING", "Name", "S", "PI"
	};
	public static final int
		RULE_document = 0, RULE_prolog = 1, RULE_element = 2, RULE_reference = 3, 
		RULE_attribute = 4, RULE_chardata = 5, RULE_misc = 6;
	public static final String[] ruleNames = {
		"document", "prolog", "element", "reference", "attribute", "chardata", 
		"misc"
	};

	@Override
	public String getGrammarFileName() { return "XMLParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public XMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DocumentContext extends ParserRuleContext {
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public List<MiscContext> misc() {
			return getRuleContexts(MiscContext.class);
		}
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public MiscContext misc(int i) {
			return getRuleContext(MiscContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_la = _input.LA(1);
			if (_la==XMLDeclOpen) {
				{
				setState(14); prolog();
				}
			}

			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << SEA_WS) | (1L << PI))) != 0)) {
				{
				{
				setState(17); misc();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23); element();
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << SEA_WS) | (1L << PI))) != 0)) {
				{
				{
				setState(24); misc();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrologContext extends ParserRuleContext {
		public TerminalNode SPECIAL_CLOSE() { return getToken(XMLParser.SPECIAL_CLOSE, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode XMLDeclOpen() { return getToken(XMLParser.XMLDeclOpen, 0); }
		public PrologContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog; }
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prolog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(XMLDeclOpen);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(31); attribute();
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37); match(SPECIAL_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public List<TerminalNode> PI() { return getTokens(XMLParser.PI); }
		public List<TerminalNode> CDATA() { return getTokens(XMLParser.CDATA); }
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public TerminalNode PI(int i) {
			return getToken(XMLParser.PI, i);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode COMMENT(int i) {
			return getToken(XMLParser.COMMENT, i);
		}
		public TerminalNode CDATA(int i) {
			return getToken(XMLParser.CDATA, i);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode Name(int i) {
			return getToken(XMLParser.Name, i);
		}
		public List<TerminalNode> Name() { return getTokens(XMLParser.Name); }
		public ReferenceContext reference(int i) {
			return getRuleContext(ReferenceContext.class,i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(XMLParser.COMMENT); }
		public ChardataContext chardata(int i) {
			return getRuleContext(ChardataContext.class,i);
		}
		public List<ChardataContext> chardata() {
			return getRuleContexts(ChardataContext.class);
		}
		public List<ReferenceContext> reference() {
			return getRuleContexts(ReferenceContext.class);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_element);
		int _la;
		try {
			int _alt;
			setState(92);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39); match(OPEN);
				setState(40); match(Name);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(41); attribute();
					}
					}
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(47); match(CLOSE);
				setState(48); match(OPEN);
				setState(49); match(SLASH);
				setState(50); match(Name);
				setState(51); match(CLOSE);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52); match(OPEN);
				setState(53); match(Name);
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(54); attribute();
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60); match(CLOSE);
				setState(62);
				_la = _input.LA(1);
				if (_la==SEA_WS || _la==TEXT) {
					{
					setState(61); chardata();
					}
				}

				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(69);
						switch (_input.LA(1)) {
						case OPEN:
							{
							setState(64); element();
							}
							break;
						case EntityRef:
						case CharRef:
							{
							setState(65); reference();
							}
							break;
						case CDATA:
							{
							setState(66); match(CDATA);
							}
							break;
						case PI:
							{
							setState(67); match(PI);
							}
							break;
						case COMMENT:
							{
							setState(68); match(COMMENT);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(72);
						_la = _input.LA(1);
						if (_la==SEA_WS || _la==TEXT) {
							{
							setState(71); chardata();
							}
						}

						}
						} 
					}
					setState(78);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(79); match(OPEN);
				setState(80); match(SLASH);
				setState(81); match(Name);
				setState(82); match(CLOSE);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83); match(OPEN);
				setState(84); match(Name);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(85); attribute();
					}
					}
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(91); match(SLASH_CLOSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode CharRef() { return getToken(XMLParser.CharRef, 0); }
		public TerminalNode EntityRef() { return getToken(XMLParser.EntityRef, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !(_la==EntityRef || _la==CharRef) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(XMLParser.Name, 0); }
		public TerminalNode STRING() { return getToken(XMLParser.STRING, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(Name);
			setState(97); match(EQUALS);
			setState(98); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChardataContext extends ParserRuleContext {
		public TerminalNode SEA_WS() { return getToken(XMLParser.SEA_WS, 0); }
		public TerminalNode TEXT() { return getToken(XMLParser.TEXT, 0); }
		public ChardataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chardata; }
	}

	public final ChardataContext chardata() throws RecognitionException {
		ChardataContext _localctx = new ChardataContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_chardata);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==SEA_WS || _la==TEXT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MiscContext extends ParserRuleContext {
		public TerminalNode SEA_WS() { return getToken(XMLParser.SEA_WS, 0); }
		public TerminalNode PI() { return getToken(XMLParser.PI, 0); }
		public TerminalNode COMMENT() { return getToken(XMLParser.COMMENT, 0); }
		public MiscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_misc; }
	}

	public final MiscContext misc() throws RecognitionException {
		MiscContext _localctx = new MiscContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_misc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << SEA_WS) | (1L << PI))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\24k\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\5\2\22\n\2\3\2\7\2\25\n"+
		"\2\f\2\16\2\30\13\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\3\3\3\7\3#\n"+
		"\3\f\3\16\3&\13\3\3\3\3\3\3\4\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\3\4\5\4A\n\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4H\n\4\3\4\5\4K\n\4\7\4M\n\4\f\4\16\4P\13\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\4\5\4_\n\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\2\t\2\4\6\b\n\f\16\2\5\3\2\6\7\4\2"+
		"\b\b\13\13\5\2\3\3\b\b\24\24s\2\21\3\2\2\2\4 \3\2\2\2\6^\3\2\2\2\b`\3"+
		"\2\2\2\nb\3\2\2\2\ff\3\2\2\2\16h\3\2\2\2\20\22\5\4\3\2\21\20\3\2\2\2\21"+
		"\22\3\2\2\2\22\26\3\2\2\2\23\25\5\16\b\2\24\23\3\2\2\2\25\30\3\2\2\2\26"+
		"\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\35\5\6\4\2\32"+
		"\34\5\16\b\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36"+
		"\3\3\2\2\2\37\35\3\2\2\2 $\7\n\2\2!#\5\n\6\2\"!\3\2\2\2#&\3\2\2\2$\"\3"+
		"\2\2\2$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'(\7\r\2\2(\5\3\2\2\2)*\7\t\2\2"+
		"*.\7\22\2\2+-\5\n\6\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3"+
		"\2\2\2\60.\3\2\2\2\61\62\7\f\2\2\62\63\7\t\2\2\63\64\7\17\2\2\64\65\7"+
		"\22\2\2\65_\7\f\2\2\66\67\7\t\2\2\67;\7\22\2\28:\5\n\6\298\3\2\2\2:=\3"+
		"\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>@\7\f\2\2?A\5\f\7\2@?\3"+
		"\2\2\2@A\3\2\2\2AN\3\2\2\2BH\5\6\4\2CH\5\b\5\2DH\7\4\2\2EH\7\24\2\2FH"+
		"\7\3\2\2GB\3\2\2\2GC\3\2\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2\2HJ\3\2\2\2I"+
		"K\5\f\7\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LG\3\2\2\2MP\3\2\2\2NL\3\2\2\2"+
		"NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\t\2\2RS\7\17\2\2ST\7\22\2\2T_\7\f\2"+
		"\2UV\7\t\2\2VZ\7\22\2\2WY\5\n\6\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2"+
		"\2\2[]\3\2\2\2\\Z\3\2\2\2]_\7\16\2\2^)\3\2\2\2^\66\3\2\2\2^U\3\2\2\2_"+
		"\7\3\2\2\2`a\t\2\2\2a\t\3\2\2\2bc\7\22\2\2cd\7\20\2\2de\7\21\2\2e\13\3"+
		"\2\2\2fg\t\3\2\2g\r\3\2\2\2hi\t\4\2\2i\17\3\2\2\2\16\21\26\35$.;@GJNZ"+
		"^";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}