package it.furno.umberto.model;

import java.util.ArrayList;

public class TestLinea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ordine o = new Ordine();
		LineaOrdine l = new LineaOrdine("a", 3);
		l.setOrdine(o);
		LineaOrdine l2 = new LineaOrdine("f", 4);
		l2.setOrdine(o);
		o.addLinea(l2);
		o.addLinea(l);
		ArrayList<LineaOrdine> mm= o.getLinea();
	
			for(LineaOrdine c: mm){
				System.out.println(c);
			}
			
			System.out.println(o);
	}

}
