import java.util.*;
/**
 * IPassengerQuery：定義「查詢旅客」的介面
 */
interface IPassengerQuery{
	/**
	 * 查詢：符合 q 的旅客，有訂位哪些航班
	 */
	public ArrayList<String> queryPassenger(String q);
}