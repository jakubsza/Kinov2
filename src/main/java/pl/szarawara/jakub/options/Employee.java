//package pl.szarawara.jakub.options;
//
//import pl.szarawara.jakub.options.usingMethods.UsingMethodsEmployee;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Scanner;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//public class Employee extends UsingMethodsEmployee{
//
//	public Employee(EntityManagerFactory entityManagerFactory, EntityManager entityManager){
//		this.entityManagerFactory=entityManagerFactory;
//		this.entityManager=entityManager;
//		data = new Scanner(System.in);
//	}
//
//	public void pracownik(){
///*
//		System.out.println("Zaloguj si�\n"
//				+ "Podaj numer ID");
//
//		//wyszukiwanie login�w pracownik�w
//		TypedQuery<Pracownicy> query1 = entityManager.createQuery("select p from Pracownicy p",Pracownicy.class);
//		List<Pracownicy> pracownicy = query1.getResultList();
//
//		boolean jest_kod=false;
//		String numer = null;
//
//		//podawanie numeruID i sprawdzanie czy istnieje taki pracownik
//		while(!jest_kod){
//
//			numer=data.next();
//
//			if(numer.toUpperCase().equals("X"))jest_kod=true;
//
//			else{
//
//			for(Pracownicy pomoc : pracownicy)
//					if (pomoc.getNumerID().equals(numer)){
//						jest_kod=true;
//					}
//
//				if(!jest_kod){
//
//					System.out.println("Podany numerID jest niepoprawny. Podaj inny numer lub nacisnij 'X', gdy ju� nie chcesz"
//							+ " si� logowa�");
//
//				}
//			}
//		}
//
//			jest_kod=false;
//
//			System.out.println("Podaj has�o");
//
//			//podawanie i sprawdzanei has�a
//			while(!jest_kod){
//
//				String haslo=data.next();
//
//				if(numer.toUpperCase().equals("X"))jest_kod=true;
//
//				else{
//
//				for(Pracownicy pomoc : pracownicy)
//						if (pomoc.getNumerID().equals(numer) && pomoc.getHaslo().equals(haslo)){
//							jest_kod=true;
//						}
//
//					if(!jest_kod){
//
//						System.out.println("Podany has�ow jest niepoprawna. Podaj inne has�o lub nacisnij 'X', gdy ju� nie chcesz"
//								+ " si� logowa�");
//
//					}
//				}
//		}
//
//			*/
//		zarzadzajKinem();
//
//
//		}
//
//
//	private void zarzadzajKinem() {
//
//		boolean koniec=false;
//
//		while(!koniec){
//
//			System.out.println("Wybierz co chcesz zrobi�\n"
//					+ "1 - dodaj bilet\n"
//					+ "2 - dodaj film\n"
//					+ "3 - dodaj sal�\n"
//					+ "4 - dodaj wy�wietlenia\n"
//					+ "5 - dodaj kody\n");
//
//
//			boolean ok=false;
//			int wynik = 0;
//
//			//try-catch dla podania z�ych danych
//			while(!ok){
//			try{
//				wynik=Integer.parseInt(data.next());
//			}
//				catch(NumberFormatException e){
//					System.out.println("Wybrano z�y numer, podaj jeszcze raz");
//					continue;
//				}
//			if (wynik==1 || wynik==2 || wynik==3 || wynik==4 || wynik==5)ok=true;
//			else System.out.println("Wybrano z�y numer, podaj jeszcze raz");
//
//			}
//
//			switch(wynik){
//			case 1: bilet(); break;
//			case 2: film();break;
//			case 3: sala();break;
//			case 4: wyswietlenia();break;
//			case 5: kody();break;
//			}
//
//			System.out.println("Je�li chcesz si� wylogowa� naci�nij 'X', je�li nie, to naci�nij dowolny klawisz");
//
//			String coDalej= data.next();
//
//			if(coDalej.toUpperCase().equals("X"))koniec=true;
//
//		}
//
//	}
//
//	public void bilet(){
//
//		String nazwa;
//		double cena = 0;
//
//		System.out.println("Podaj rodzaj biletu");
//
//		nazwa= data.next();
//
//		System.out.println("Podaj cen� biletu");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			cena=Double.parseDouble(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�� cen�, podaj jeszcze raz");
//				continue;
//			}
//		if(cena>0)ok=true;
//		else System.out.println("Cena jest ujemna, prosz� poda� inn�");
//		}
//
//		Bilety bilet = new Bilety(nazwa,cena);
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(bilet);
//
//		entityManager.getTransaction().commit();
//	}
//
//	public void film(){
//
//		String tytul;
//		int czasTrwania = 0;
//		int ograniczenia = 0;
//
//		System.out.println("Podaj tytu� filmu");
//
//		tytul= data.next();
//
//		System.out.println("Podaj czas trwania filmu");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			czasTrwania=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(czasTrwania>0)ok=true;
//		else System.out.println("Czas jest ujemny, prosz� poda� inny");
//		}
//
//		ok=false;
//
//		System.out.println("Podaj ograniczenia wiekowe dla filmu");
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			ograniczenia=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(ograniczenia>=0)ok=true;
//		else System.out.println("Ograniczenia wiekowe nie mog� by� ujemne, prosz� poda� inne");
//		}
//
//
//		Film film = new Film(tytul, czasTrwania, ograniczenia);
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(film);
//
//		entityManager.getTransaction().commit();
//
//	}
//
//	public void sala(){
//
//		int rzedy = 0;
//		int kolumny = 0;
//
//		System.out.println("Podaj liczb� rz�d�w");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			rzedy=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(rzedy>0)ok=true;
//		else System.out.println("Liczba rz�d�w nie mo�e by� ujemna, prosz� poda� inn�");
//		}
//
//		System.out.println("Podaj liczb� kolumn");
//
//		ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			kolumny=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(kolumny>0)ok=true;
//		else System.out.println("Liczba kolumn nie mo�e by� ujemna, prosz� poda� inn�");
//		}
//
//		Sala sala = new Sala(rzedy,kolumny);
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(sala);
//
//		entityManager.getTransaction().commit();
//
//	}
//
//	public Sala salaPomoc(){
//
//		int rzedy = 0;
//		int kolumny = 0;
//
//		System.out.println("Podaj liczb� rz�d�w");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			rzedy=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(rzedy>0)ok=true;
//		else System.out.println("Liczba rz�d�w nie mo�e by� ujemna, prosz� poda� inn�");
//		}
//
//		System.out.println("Podaj liczb� kolumn");
//
//		ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			kolumny=Integer.parseInt(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�y czas, podaj jeszcze raz");
//				continue;
//			}
//		if(kolumny>0)ok=true;
//		else System.out.println("Liczba kolumn nie mo�e by� ujemna, prosz� poda� inn�");
//		}
//
//		Sala sala = new Sala(rzedy,kolumny);
//
//		return sala;
//
//	}
//
//	public void wyswietlenia(){
//
//		Calendar dzien = Calendar.getInstance();
//		Calendar godzina = Calendar.getInstance();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
//
//		String dzienS;
//		String godzinaS;
//
//		System.out.println("Podaj dzie� wy�wietlenia filmu w formacie dd/MM/yyyy");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			dzienS= data.next();
//			dzien.setTime(sdf1.parse(dzienS));
//		}
//			catch(ParseException e){
//				System.out.println("Wybrano z�� dat�, podaj jeszcze raz");
//				continue;
//			}
//		ok=true;
//		}
//
//		System.out.println("Podaj godzine wy�wietlenia filmu w formacie HH:mm:ss");
//
//		ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			godzinaS= data.next();
//			godzina.setTime(sdf2.parse(godzinaS));
//		}
//			catch(ParseException e){
//				System.out.println("Wybrano z�� godzin�, podaj jeszcze raz");
//				continue;
//			}
//		ok=true;
//		}
//		//dzien = new GregorianCalendar(2017,4,12);
//		//godzina = new GregorianCalendar(0,0,0,12,0);
//
//		Sala sala = salaPomoc();
//
//		Wyswietlenia wyswietlenia = new Wyswietlenia(dzien,godzina,sala);
//
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(sala);
//		entityManager.persist(wyswietlenia);
//
//		entityManager.getTransaction().commit();
//
//	}
//
//	public void kody(){
//
//		String kod;
//		double znizka = 0;
//
//		System.out.println("Podaj kod zni�ki");
//
//		kod= data.next();
//
//		System.out.println("Podaj procent zni�ki");
//
//		boolean ok=false;
//
//		//try-catch dla podania z�ych danych
//		while(!ok){
//		try{
//			znizka=Double.parseDouble(data.next());
//		}
//			catch(NumberFormatException e){
//				System.out.println("Wybrano z�� cen�, podaj jeszcze raz");
//				continue;
//			}
//		if(znizka>0 && znizka<=1)ok=true;
//		else System.out.println("Zni�ka jest ujemna, prosz� poda� inn�");
//		if(znizka>1) System.out.println("Zni�ka nie mo�e mo�e by� wi�ksza ni� 100%, prosz� poda� inn�");
//		}
//
//		Bilety kody = new Bilety(kod,znizka);
//
//		entityManager.getTransaction().begin();
//
//		entityManager.persist(kody);
//
//		entityManager.getTransaction().commit();
//
//	}
//}
