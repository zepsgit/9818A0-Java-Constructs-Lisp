package sExpressions;

import static org.junit.jupiter.api.Assertions.*;
import static sExpressions.SExp.cons;
import static sExpressions.SExp.list;
import static sExpressions.SExp.symbol;

import org.junit.jupiter.api.Test;

import sExpressions.SExp.Cons;
import sExpressions.SExp.Nil;
import sExpressions.SExp.Symbol;

class A0Test {

	@Test
	void test() {
		//Test for A0SExp
		assertEquals(1, A0SExp.countPartitions(12, 12));
		assertEquals(1, A0SExp.countPartitions(10, 1));
		assertEquals(1, A0SExp.countPartitions(0, 0));
		assertEquals(0, A0SExp.countPartitions(0, 9));
		assertEquals(0, A0SExp.countPartitions(8, 9));
		assertEquals(6, A0SExp.countPartitions(4, 3));
		assertEquals(3, A0SExp.countPartitions(3, 2));
		
		//Test for length
		Symbol x=new Symbol("hello");
		Symbol y=new Symbol("hello world!");
		Cons z=new Cons(x,y);
		Cons t=new Cons(z,x);
		Cons k=new Cons(t,z);
		Nil w=new Nil();
		assertEquals(5, A0SExp.length(x));
		assertEquals(12, A0SExp.length(y));
		assertEquals(39, A0SExp.length(k));
		assertEquals(0, A0SExp.length(w));
		
		//Test for height
		assertEquals(0, A0SExp.height(w));
		assertEquals(1, A0SExp.height(x));
		assertEquals(2, A0SExp.height(z));
		assertEquals(3, A0SExp.height(t));
		assertEquals(4, A0SExp.height(k));
		
		//Test for lookup
		Symbol x1=new Symbol("hello");
		Symbol y1=new Symbol("world");
		Cons l1=new Cons(x1, y1);
		Symbol x2=new Symbol("hel");
		Symbol y2=new Symbol("wor");
		Cons l2=new Cons(x2, y2);
		Symbol x3=new Symbol("he");
		Symbol y3=new Symbol("wo");
		Cons l3=new Cons(x3, y3);
		Symbol x4=new Symbol("h");
		Cons y4=new Cons(l1,x4);
		Cons l4=new Cons(x4, y4);
		Symbol x5=new Symbol("nil");
		Nil y5=new Nil();
		SExp list=SExp.list(l1, l2, l3, l4);
		
		assertEquals(y1, A0SExp.lookup(x1, list));
		assertEquals(y2, A0SExp.lookup(x2, list));
		assertEquals(y3, A0SExp.lookup(x3, list));
		assertEquals(y4, A0SExp.lookup(x4, list));
		assertEquals(y5, A0SExp.lookup(x5, list));
		
		//Test for replace
		Symbol r=new Symbol("replaced");
		Cons _l1=new Cons(r, y1);
		Cons _y4=new Cons(_l1, x4);
		Cons _l4=new Cons(x4, _y4);
		SExp _list=SExp.list(_l1, l2, l3, _l4);
		
		assertEquals(_list, A0SExp.replace(x1, r, list));
		
		SExp lst = list( symbol("A"), cons( symbol("B"), symbol("A") )) ;
		SExp result = A0SExp.replace( symbol("A"), symbol("C"), lst ) ;
		SExp expected = list( symbol("C"), cons( symbol("B"), symbol("C") )) ;
		assertEquals(result, expected);
		
	}

}
