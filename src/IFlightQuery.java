import java.util.*;
/**
 * IFlightQuery：定義「查詢航班」的介面
 */
interface IFlightQuery{
	/**
	 * 查詢：符合 q 的航班，有哪些旅客
	 */
	public ArrayList<String> queryFlight(String q);
}