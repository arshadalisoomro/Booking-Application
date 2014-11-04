Booking-Application  
===================  
  
A simple booking for specific flight application.  
  
1. Files structure  
/ src  
 │─ IPassengerQuery.java  
 │─ IBooking.java  
 │─ IFlightQuery.java  
 │─ CData.java  
 └─ CGUI.java  
  
2. Detail for files structure  
● 本系統並沒有用到資料庫來儲存資料，亦沒有用I/O的方式來存取資料。本系統在執行時，把使用者輸入的旅客資料存放在記憶體。  
● CData主要負責存取記憶體上的資料。  
● CGUI主要負責與使用者之間的互動，其繼承了CData，並且實作了IBooking、IPassengerQuery、IFlightQuery。  
  
3. UML Class Diagram  
![image](https://github.com/thwang1206/Booking-Application/blob/master/UML%20Class%20Diagram.png)  
  
4. ER Diagram  
![image](https://github.com/thwang1206/Booking-Application/blob/master/UML%20Class%20Diagram.png)  

