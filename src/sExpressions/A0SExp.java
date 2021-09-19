package sExpressions;
import sExpressions.SExp.Symbol;
import sExpressions.SExp.Cons;
import sExpressions.SExp.Nil;
public class A0SExp {
	/** Count the number of ways to partition a set of size n into k nonempty subsets.
	*
	* @param n The size of the set
	* @param k The size of the partitions
	* @require 0 <= k and k <= n
	* @ensure result equals the number of ways to partition a set of size n
	*/
	public static int countPartitions(int n, int k) {
		if (k==0 || n==0 || k > n) return 0;
		if (k==1 || k==n) return 1;
		return k * countPartitions(n-1, k) + countPartitions(n-1, k-1);
	}
	
	/**
	 * 
	 * @param x The input parameter need to be a SExp type
	 * SExp type can be one of Nil, Symbol and the combination of different Symbols
	 * @return the length of x
	 */

	public static int length(SExp x) {
		
		if (x instanceof Symbol) {
			if (x.toString().charAt(0)=='\"') return x.toString().length()-2;
			else return x.toString().length();
		}
		
		if (x instanceof Cons) {
			
			return length(x.first()) + length(x.rest());
		}
		return 0; // if x instanceof Nil return 0;
	}
	
	/**
	 * 
	 * @param x Input consist of one of Nil Cons and Symbol
	 * @return Height of x
	 * For Nil, Height=0
	 * For Symbol, Height=1
	 * For Cons (a, b), Height=maxHeightOf(a,b)+1
	 */
	public static int height(SExp x) {
		if (x instanceof Symbol) return 1;
		if (x instanceof Cons) {
			
			return  Math.max(height(x.first()), height(x.rest())) + 1;
		}
		return 0; //Height of Nil is 0
	}
	
	/**
	 * 
	 * @param s Input Symbol type which acts as an index
	 * @param list Input dot pairs for look up
	 * @return the rest part of the dot pair which starts with s
	 * For example, list=((a0, b0),(a1, b1),(a2, b2)), function will return
	 * b0 when s is a0
	 * all a(s) are symbols
	 */
	public static SExp lookup(Symbol s, SExp list) {
		
		if (height(list) > 1) {
			if (s.toString()==list.first().first().toString())
				return list.first().rest();
			else {
				return lookup(s, list.rest());
			}
		}
		return SExp.nil();
		
	}
	
	/**
	 * 
	 * @param a The specific symbol need to be replaced.
	 * @param x The specific symbol with which symbol a was replaced.
	 * @param y The SExp data which contains the occurrence of a
	 * @return a SExp in which all the occurrence of a was replaced with x
	 */
	public static SExp replace(Symbol a, SExp x, SExp y) {
		if (height(y) > 1) {
			if (y.first() == a ) {
				return new Cons(x, replace(a, x, y.rest()));
			}
			else {
				if (y.first() instanceof Symbol) {
					return new Cons (y.first(), replace(a, x, y.rest()));
				}
				else 
					return new Cons (replace(a, x, y.first()), replace(a, x, y.rest()));
		
			}
		}
		else {
			if (y==a) {
				return x;
			}
			else return y;
		}
	}
	
	//manual test
	public static void main(String args[]) {
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
		//Symbol y=new Symbol("replaced");
		SExp list=SExp.list(l1, l2, l3, l4);
		
		Symbol r=new Symbol("replaced");
		Cons _l1=new Cons(r, y1);
		Cons _y4=new Cons(_l1, x4);
		Cons _l4=new Cons(x4, _y4);
		SExp _list=SExp.list(_l1, l2, l3, _l4);
		
		System.out.println(_list);
		System.out.println(list);
		System.out.println(replace(x1, r, list));
	}
}