import java.lang.*;
import java.util.*;
import java.io.*;

class Passenger{
// 實體變數 ****************************************************
	private ArrayList<Booking> bookings = new ArrayList<Booking>(0);
	private String name;
// 建構子 ****************************************************
	/**
	 * 初始化 乘客名稱 : name
	 */
	public Passenger(String name){
		this.name = name;
	}
// 實體方法 ****************************************************
	/**
	 * 建立 Booking Instance，並指向它
	 */
	public void makeBooking(SpecificFlight flight, int seatNum){
		new Booking(this, flight, seatNum);
	}
	/**
	 * 取得 乘客名稱
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * 建立與 Booking 的連接，並儲存在 ArrayList bookings
	 */
	public void addLinkToBooking(Booking bookOne){
		bookings.add(bookOne);
	}
	/**
	 * 顯示 該名乘客 所有的 Booking information
	 */
	public void showAllBookingOfPassenger(){
		for( int i=0, iLen=bookings.size(); i<iLen; i++ ){
			bookings.get(i).showInfoOfBooking();
		}
	}
	/**
	 * 取得 該名乘客 所有的 Booking information
	 */
	public ArrayList<String> getAllBookingOfPassenger(){
		ArrayList<String> resultAry = new ArrayList<String>();
		for( int i=0, iLen=bookings.size(); i<iLen; i++ ){
			String[] strAry = bookings.get(i).getInfoOfBooking();
			resultAry.add("旅客姓名："+strAry[0]+"\n航班名稱："+strAry[1]+"\n座位號碼："+strAry[2]+"\n\n");
		}
		return resultAry;
	}
}