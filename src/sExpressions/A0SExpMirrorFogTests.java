package sExpressions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import sExpressions.SExp;
import static sExpressions.SExp.* ;

/**
 * 
 */

/**  Fog Mirror tests for A0Exp.
 * @author theo
 *
 */
public class A0SExpMirrorFogTests {

	@Test
	void testLength0() {
		SExp lst = list(  ) ;
		int result = A0SExp.length( lst ) ;
		int expected = 0 ;
		assertEquals( expected, result ) ;
	}


	@Test
	void testHeight0() {
		SExp lst = nil() ;
		int result = A0SExp.height( lst ) ;
		int expected = 0 ;
		assertEquals( expected, result ) ;
	}


	@Test
	void replace() {
		SExp lst = list( symbol("A"), cons( symbol("B"), symbol("A") )) ;
		SExp result = A0SExp.replace( symbol("A"), symbol("C"), lst ) ;
		SExp expected = list( symbol("C"), cons( symbol("B"), symbol("C") )) ;
		assertEqualSExp( expected, result ) ;
	}


	private void assertEqualSExp(SExp expected, SExp result) {
		if( ! LispExamples.equal( expected, result) ) {
			throw new org.opentest4j.AssertionFailedError(
					"expected <"
				    + expected.toString()
				    + "> but was <"
				    + result.toString()
				    + ">") ; }
		
	}
}
