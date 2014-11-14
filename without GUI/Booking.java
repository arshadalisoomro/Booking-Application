import java.lang.*;
import java.util.*;
import java.io.*;

class Booking{
// 實體變數 ****************************************************
	private Passenger passenger;
	private SpecificFlight flight;
	private int seatNum;
// 建構子 ****************************************************
	/**
	 * Booking 建構子
	 */
	public Booking(Passenger passenger, SpecificFlight flight, int seatNum){
		this.passenger = passenger;
		this.passenger.addLinkToBooking(this); // 指向 Passenger
		this.flight = flight;
		this.flight.addLinkToBooking(this); // 指向 SpecificFlight
		this.seatNum = seatNum;
	}
// 實體方法 ****************************************************
	/**
	 * 顯示 Booking 內的所有資訊：旅客姓名、航班、座位
	 */
	public void showInfoOfBooking(){
		System.out.println("> passenger : "+passenger.getName());
		System.out.println("> flight : "+flight.getFlight());
		System.out.println("> seatNum : "+seatNum);
	}
}