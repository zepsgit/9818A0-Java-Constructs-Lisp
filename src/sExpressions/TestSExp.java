import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sExpressions.SExp;
import static sExpressions.SExp.*;

class TestSExp {

	@Test
	void testNil() {
		SExp x = nil() ;
		SExp y = new SExp.Nil() ;
		assertEquals( true, x.isAtomic() ) ;
		assertEquals( true, x.isNil()  ) ;
		assertEquals( true, x.isNil() ) ;
		assertEquals( true, x.eq( y ) ) ;
		assertEquals( true, y.eq( x ) ) ;
		assertEquals( "Nil", x.toString() ) ;
	}

	@Test
	void testSymbol() {
		SExp a = symbol( "abcd" ) ;
		SExp b = symbol( "abcd" ) ;
		SExp c = symbol( "Hello World" ) ;
		SExp d = symbol( "Nil" ) ;
		SExp y = nil();
		assertEquals( true, a.isAtomic() ) ;
		assertEquals( false, b.isNil() ) ;
		assertEquals( false, d.isNil() ) ;
		assertEquals( true, a.eq( a ) ) ;
		assertEquals( true, a.eq( b ) ) ;
		assertEquals( true, b.eq( a ) ) ;
		assertEquals( false, a.eq( c ) ) ;
		assertEquals( false, c.eq( a ) ) ;
		assertEquals( false, a.eq( y ) ) ;
		assertEquals( false, y.eq( a ) ) ;
		assertEquals( "abcd", a.toString() ) ;
		assertEquals( b.toString(), "abcd" ) ;
		assertEquals( c.toString(), "\"Hello World\"" ) ;
		assertEquals( d.toString(), "\"Nil\"" ) ;
	}

	@Test
	void testCons() {
		SExp a = cons( symbol( "abcd" ), cons( symbol( "def" ), nil() ) ) ;
		SExp b = cons( symbol( "abcd" ), cons( symbol( "def" ), nil() )  );
		SExp c = a ;
		SExp s = symbol( "abcd" ) ;
		assertEquals( false, a.isAtomic() ) ;
		assertEquals( false, b.isNil() ) ;
		
		assertEquals( true, a.eq( a ) ) ;
		assertEquals( true, a.eq( c ) ) ;
		assertEquals( false, a.eq( b ) ) ;
		assertEquals( false, b.eq( a ) ) ;
		assertEquals( false, a.eq( s ) ) ;
		assertEquals( false, s.eq( a ) ) ;
		assertEquals( false, a.eq( nil() ) ) ;
		assertEquals( false, nil().eq( a ) ) ;
		assertEquals( "(abcd . (def . Nil))", a.toString() ) ;
	}
	
	@Test
	void testLispNotation() {
		SExp a = list( symbol( "abcd" ), symbol( "def" )  ) ;
		SExp b = cons( symbol( "Not" ), cons( symbol("exactly"), cons( symbol("a"), symbol("list") ) ) );
		SExp c = cons( symbol("dotted"), symbol("pair" ) ) ;
		SExp d = list( list( symbol("lists"), symbol("nested") ),
					   list( symbol("in"), symbol("lists") ) ) ;
		SExp s = symbol( "Hello" ) ;
		assertEquals( "(abcd def)", LispExamples.listNotation(a) ) ;
		assertEquals( "(Not exactly a . list)", LispExamples.listNotation(b) ) ;
		assertEquals( "(dotted . pair)", LispExamples.listNotation(c) ) ;
		assertEquals( "((lists nested) (in lists))", LispExamples.listNotation(d) ) ;
		assertEquals( "Hello", LispExamples.listNotation(s) ) ;
		assertEquals( "()", LispExamples.listNotation( nil() ) ) ;
		
		
	}
	
	

}
