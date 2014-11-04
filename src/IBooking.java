/**
 * IBooking：定義「旅客訂位」的介面
 */
interface IBooking{
	/**
	 * 提交：旅客的相關資訊與選擇的航班
	 */
	public String[] submitBooking();
	/**
	 * 儲存：旅客的相關資訊與選擇的航班
	 */
	public void saveBooking(String[] PStrAry, String[] FStrAry);
}