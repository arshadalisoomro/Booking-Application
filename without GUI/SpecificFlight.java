import java.lang.*;
import java.util.*;
import java.io.*;

class SpecificFlight{
// 實體變數 ****************************************************
	private ArrayList<Booking> bookings = new ArrayList<Booking>(0);
	private String flight;
// 建構子 ****************************************************
	/**
	 * 初始化 航班名稱 : flight
	 */
	public SpecificFlight(String flight){
		this.flight = flight;
	}
// 實體方法 ****************************************************
	/**
	 * 取得 航班名稱
	 */
	public String getFlight(){
		return this.flight;
	}
	/**
	 * 建立與 Booking 的連接，並儲存在 ArrayList bookings
	 */
	public void addLinkToBooking(Booking bookOne){
		bookings.add(bookOne);
	}
	/**
	 * 顯示 該名航班 所有的 Booking information
	 */
	public void getAllBookingOfSpecificFlight(){
		for( int i=0, iLen=bookings.size(); i<iLen; i++ ){
			bookings.get(i).showInfoOfBooking();
		}
	}
}