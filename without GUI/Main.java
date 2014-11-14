import java.lang.*;
import java.util.*;
import java.io.*;

class Main{
	public static void main(String[] args){
		SpecificFlight f1 = new SpecificFlight("Air One");
		SpecificFlight f2 = new SpecificFlight("Air Two");
		
		Passenger p1 = new Passenger("Kevin");
		Passenger p2 = new Passenger("John");
		Passenger p3 = new Passenger("Mary");
		
		p1.makeBooking(f1, 1);
		p1.makeBooking(f2, 1);
		p2.makeBooking(f1, 2);
		
		System.out.println("> getAllBookingOfPassenger(p1) : ");
		p1.getAllBookingOfPassenger();
		
		System.out.println("> getAllBookingOfPassenger(p2) : ");
		p2.getAllBookingOfPassenger();
		
		System.out.println("> getAllBookingOfPassenger(p3) : ");
		p3.getAllBookingOfPassenger();
		
		System.out.println("> getAllBookingOfSpecificFlight(f1) : ");
		f1.getAllBookingOfSpecificFlight();
	}
}