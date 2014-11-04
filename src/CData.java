import java.lang.*;
import java.util.*;
import java.io.*;
/**
 * CData：存取 Passenger|Booking|SpecificFlight 的資料
 */
class CData{
// 實體變數 ******************************************************
	/**
	 * PassengerMap：Passenger Table(有 primary key)
	 * BookingAry：Booking Table(沒有 primary key，故用 Arry 來儲存)
	 * FlightMap：SpecificFlight Table(有 primary key)
	 */
	private TreeMap<Integer, String[]> PassengerMap = new TreeMap<Integer, String[]>();
	private ArrayList<Integer[]> BookingAry = new ArrayList<Integer[]>();
	private TreeMap<Integer, String[]> FlightMap = new TreeMap<Integer, String[]>();
// 實體方法 ******************************************************
	/**
	 * 回傳：PassengerMap 變數
	 */
	public TreeMap<Integer, String[]> getPessenger(){
		return PassengerMap;
	}
	/**
	 * 回傳：BookingAry 變數
	 */
	public ArrayList<Integer[]> getBookingAry(){
		return BookingAry;
	}
	/**
	 * 回傳：getFlightMap 變數
	 */
	public TreeMap<Integer, String[]> getFlightMap(){
		return FlightMap;
	}
	/**
	 * 新增一筆資料：PassengerMap
	 */
	public void saveToPassengerMap(Integer key, String[] valueAry){
		PassengerMap.put(key, valueAry);
	}
	/**
	 * 新增一筆資料：BookingAry
	 */
	public void saveToBookingMap(Integer PID, Integer FID){
		BookingAry.add(new Integer[]{PID, FID});
	}
	/**
	 * 新增一筆資料：FlightMap
	 */
	public void saveToFlightMap(Integer key, String[] valueAry){
		FlightMap.put(key, valueAry);
	}
	/**
	 * 利用 PID 來取得該旅客姓名與手機的資料，相當於以下的 SQL 語法
	 * SELECT "PNAME", "PPHONE" FROM "Passenger Table" WHERE "PID"=$PID;
	 */
	public String[] getPValue(Integer PID){
		return PassengerMap.get(PID);
	}
	/**
	 * 利用 PID 來取得該旅客的 FID，相當於以下的 SQL 語法
	 * SELECT "FID" FROM "Booking Table" WHERE "PID"=$PID;
	 */
	public Integer getFID(Integer PID){
		Integer FID = -1;
		for( int i=0, iLen=BookingAry.size(); i<iLen; i++ ){
			Integer[] intAry = BookingAry.get(i);
			if( intAry[0] == PID ){
				FID = intAry[1];
				break;
			}
		}
		return FID;
	}
	/**
	 * 利用 FID 來取得該旅客的 PID，相當於以下的 SQL 語法
	 * SELECT "PID" FROM "Booking Table" WHERE "FID"=$FID;
	 */
	public Integer getPID(Integer FID){
		Integer PID = -1;
		for( int i=0, iLen=BookingAry.size(); i<iLen; i++ ){
			Integer[] intAry = BookingAry.get(i);
			if( intAry[1] == FID ){
				PID = intAry[0];
				break;
			}
		}
		return PID;
	}
	/**
	 * 利用 FID 來取得該航班的名稱，相當於以下的 SQL 語法
	 * SELECT "FNAME" FROM "SpecificFlight Table" WHERE "FID"=$FID;
	 */
	public String getFName(Integer FID){
		String[] strAry = FlightMap.get(FID);
		return strAry[0];
	}
}