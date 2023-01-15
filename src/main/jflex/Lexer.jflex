package com.mrojas;

import java_cup.runtime.*;

%%
%public
%class AgendaLexer
%cup
%line
%column

whitespace = [ \t\n]
LineTerminator = \r|\n|\r\n

%{
    StringBuffer string = new StringBuffer();
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}

%eofval{
    return symbol(ParserSym.EOF);
%eofval}

%%
"," {return symbol(ParserSym.COMMA);}
(\+?(502)?)?[ ]?[0-9]{4}[ ]?[0-9]{4} { return symbol(ParserSym.TELEFONO, yytext()); }
//("http"|"https")("://")("www.")?[a-zA-Z0-9-]+\.[a-zA-Z]{2,6} {return symbol(ParserSym.LINK, yytext());}
("http"|"https")("://")("www.")?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}([-a-zA-Z0-9()@:%_\+.~#?&//=]*) {return symbol(ParserSym.LINK, yytext());}
([A-Za-z0-9+_.-]*[A-Za-z0-9]@([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}) { return symbol(ParserSym.CORREO, yytext()); }
[A-Za-z0-9+_-]+([ ]*[A-Za-z0-9+_-]*)* { return symbol(ParserSym.PALABRA, yytext()); }

{whitespace}+ { /* ignorar */ }
{LineTerminator}+ { /* ignorar */ }
    /* error fallback */
    [^]                              { throw new Error("Illegal character <"+
                                                        yytext()+">"); }