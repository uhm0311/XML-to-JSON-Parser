/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

grammar Xml;

parse : (nCNode | hCNode)+;

nCNode : '<' (INT | Char)+ (WS attribute)* '/>' | ('<' (INT | Char)+ (WS attribute)* '>' '</' (INT | Char)+ '>'); 

hCNode : '<' (INT | Char)+ (WS attribute)* '>' (hCNode | nCNode | tCNode)+ '</' (INT | Char)+ '>';

tCNode : (INT | Char)+;

attribute : (INT | Char)+ '=' '"' (INT | Char)* '"';

INT : ('0'..'9')+;

Char : ('a'..'z' | 'A'..'Z')+;
                        
WS : (' '+ | '\u000C' | '\t' | '\n' | '\r') {skip();};

XMLDoc : ('<?xml' (WS | '-' | '.' | Char | INT | '=' | '"')* '?>') {skip();};