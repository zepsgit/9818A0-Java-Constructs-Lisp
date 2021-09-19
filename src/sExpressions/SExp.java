package sExpressions;

/** Lisp-style SExpressions
 * There are three kinds of SExpressions
 * nil()
 * symbol( s ) where s is a string.
 * cons( x, y) where x and y are (smaller) SExpressions.
 *  */
public sealed interface SExp
permits SExp.Symbol, SExp.Nil, SExp.Cons {
	
	/** True for symbols and Nil. False for cons pairs. */
	boolean isAtomic() ;
	
	/** True for Nil. False for symbols and cons pairs. */
	boolean isNil() ;
	
	/* True if both are Nil
	 * or both are symbols based on the equal strings
	 * or both are the same cons pair.
	 */
	boolean eq( SExp other ) ;
	
	/** The first item of a cons pair.
	 * Requires this object is a cons pair.
	 */
	default SExp first() {
		throw new IllegalArgumentException("first requires an SExp.Cons") ;
	}

	/** The second item of a cons pair
	 * Requires this object is a cons pair.
	 */
	default SExp rest() {
		throw new IllegalArgumentException("rest requires an SExp.Cons") ;
	}
	
	/** Record class for Symbols. */
	public static record Symbol( String value ) implements SExp {
		public boolean isAtomic() { return true ; }
		
		public boolean isNil() { return false ; }
		
		public boolean eq( SExp other ) {
			if( other instanceof Symbol otherSymbol) {
				return this.value.equals( otherSymbol.value() ) ;
			} else {
				return false ;
			}
		}
		
		@Override public String toString() {
			if( value.matches( "(\\p{Alpha})+" ) && !"Nil".equals( value ) )
				return value ;
			else 
				return "\"" + value + "\"" ; 
		}
	}
	
	/** Record class for Nil. */
	public static record Nil() implements SExp {
		
		public boolean isAtomic() { return true ; }
		
		public boolean isNil() { return true ; }
		
		public boolean eq( SExp other ) {
			return other instanceof Nil ;
		}
		
		@Override public String toString() {
			return "Nil" ;
		}
	
		private static final Nil theNil = new Nil() ;
		
	}
	
	/** Record class for Cons pairs. */
	public static record Cons( SExp first, SExp rest ) implements SExp {
		
		public boolean isAtomic() { return false ; }
		
		public boolean isNil() { return false ; }
		
		public boolean eq( SExp other) {
			return this == other ;
		}
		
		@Override public String toString() {
			return "(" + first.toString() + " . " + rest.toString() + ")"; 
		}
	}
	
	/** Create a symbol */
	public static SExp.Symbol symbol( String value ) {
		return new Symbol( value ) ;
	}
	
	/** Create nil */
	public static SExp.Nil nil() {
		return Nil.theNil ;
	}
	
	/** Create a cons pair */
	public static SExp.Cons cons( SExp first, SExp rest ) {
		return new Cons( first, rest ) ;
	}
	
	/** Create a representation of a list.
	 * The empty list is represented by Nil.
	 * A list of length n+1 is represented by a cons pair where
	 * the first item of the pair is the first item of the list
	 * and the second item of the pair represents the rest of the
	 * list.
	 * @param items  The items to be put in the list.
	 * @return
	 */
	public static SExp list( SExp ...items) {
		return listHelper( items, 0 ) ;
	}
	
	private static SExp listHelper( SExp[] items, int i) {
		if( i == items.length ) return nil() ;
		else return cons( items[i], listHelper(items, i+1) ) ;
	}
	
	/** Convert an SExp to a String using Lisp's list notation.
	 * An SExpression representing a list (a b c) is converted to 
	 * the string "(a b c)", for example. Nil is converted to a string
	 * "()".  Symbols are converted according to their toString method.
	 * When a nested series of one or more cons cells is not terminated
	 * by a null, the final atom is preceded by a dot. For example
	 * (a . b) converts to "(a . b)", (a . (b . c)) converts to "(a b . c)",
	 * (a . (b . (c . d))) converts to "(a b c . d), etc.
	 * @param x
	 * @return
	 */
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
