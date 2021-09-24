package sExpressions;


import static sExpressions.SExp.cons;

import sExpressions.SExp;
import sExpressions.SExp.Symbol;

public class LispExamples {
	
	public static boolean equal(SExp x, SExp y) {
		if( x.isNil() ) return y.isNil() ;
		else if( x.isAtomic() ) return x.eq(y) ;
		else if( y.isAtomic() ) return false ;
		else return equal( x.first(), y.first() )
			     && equal( x.rest(), y.rest() ) ;
	}
	
	public static int size(SExp x)
	{
		if( x.isAtomic() ) return 1 ;
		else return 1 + size( x.first() ) + size( x.rest() ) ;
	}
	
	public static String listNotation( SExp x) {
		if( x.isAtomic() && ! x.isNil() ) return x.toString() ;
		else return "(" + finishListNotation(x) ;
	}
	
	private static String finishListNotation( SExp x) {
		if( x.isNil() )
			return ")" ;
		else if( x.isAtomic() )
			return ". " + x.toString() + ")" ;
		else
			return listNotation( x.first() )
					+ (x.rest().isNil() ? "" : " ")
					+ finishListNotation( x.rest() ) ;
	}
}