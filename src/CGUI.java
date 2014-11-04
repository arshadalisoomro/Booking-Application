import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 * CGUI：主要執行的類別(有用到視窗元件)
 * 繼承：CData
 * 實作：IBooking, IPassengerQuery, IFlightQuery
 */
public class CGUI extends CData implements IBooking, IPassengerQuery, IFlightQuery{
// 實體變數 ******************************************************
	private JFrame w;
	private JPanel contentPane;
	private JTextPane PResult_textPane;
	private JTextPane FResult_textPane;
	private JTextField PQuery_text;
	private JTextField BName_text;
	private JTextField BPhone_text;
	private JComboBox BFlight_comboBox;
	private JComboBox FFlight_comboBox;
	private JButton PQuery_btn;
	private JButton FSubmit_btn;
	/**
	 * flightStrAry：所有航班的字串陣列
	 */
	private String[] flightStrAry = {"GE332-2014/11/11 07:00am", "BR192-2014/11/11 07:30am", "B7502-2014/11/11 08:00am", "NH1186-2014/11/11 08:40am", "CI9222-2014/11/11 09:00am"};
// 建構子 ******************************************************
	/**
	 * CGUI 建構子：產生 GUI
	 */
	public CGUI(){
		/**
		 * 初始化 JFrame
		 */
		w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setTitle("Booking Application");;
        w.setBounds(100, 100, 600, 400);
		/**
		 * 產生 CardLayout 的 JPanel
		 * 一共有四張卡片：HomePanel、BookingPanel、QPassengerPanel、QFlightPanel
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		w.setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		/**
		 * HomePanel：主頁面，可以讓使用者選擇三種操作
		 */
		JPanel HomePanel = new JPanel();
		contentPane.add(HomePanel, "name_259276249528539");
		// 旅客訂位
		JButton Booking_btn = new JButton("旅客訂位");
		Booking_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		Booking_btn.addActionListener(new GoPanelListener("name_259276278482402"));
		// 查詢旅客
		JButton QPassenager_btn = new JButton("查詢旅客");
		QPassenager_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		QPassenager_btn.addActionListener(new GoPanelListener("name_259276313752002"));
		// 查詢航班
		JButton QFlight_btn = new JButton("查詢航班");
		QFlight_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		QFlight_btn.addActionListener(new GoPanelListener("name_259276348037181"));
		// 讓 HomePanel 內的三個按鈕置中
		GroupLayout gl_HomePanel = new GroupLayout(HomePanel);
		gl_HomePanel.setHorizontalGroup(
			gl_HomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HomePanel.createSequentialGroup()
					.addGap(140)
					.addGroup(gl_HomePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(QPassenager_btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(Booking_btn, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(QFlight_btn, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
					.addGap(140))
		);
		gl_HomePanel.setVerticalGroup(
			gl_HomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HomePanel.createSequentialGroup()
					.addGap(31)
					.addComponent(Booking_btn, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(QPassenager_btn, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(QFlight_btn, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(32))
		);
		HomePanel.setLayout(gl_HomePanel);
		/**
		 * BookingPanel：旅客訂位的頁面，可以讓使用者進行航班的訂位
		 */
		JPanel BookingPanel = new JPanel();
		contentPane.add(BookingPanel, "name_259276278482402");
		GridBagLayout gbl_BookingPanel = new GridBagLayout();
		gbl_BookingPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_BookingPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_BookingPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_BookingPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		BookingPanel.setLayout(gbl_BookingPanel);
		// 回到上一頁(主頁面)
		JButton BBack_btn = new JButton("< 旅客訂位");
		BBack_btn.addActionListener(new BackHomeListener());
		BBack_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_BBack_btn = new GridBagConstraints();
		gbc_BBack_btn.anchor = GridBagConstraints.WEST;
		gbc_BBack_btn.gridwidth = 2;
		gbc_BBack_btn.insets = new Insets(0, 0, 5, 5);
		gbc_BBack_btn.gridx = 0;
		gbc_BBack_btn.gridy = 0;
		BookingPanel.add(BBack_btn, gbc_BBack_btn);
		// 水平間隔
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.gridwidth = 6;
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 0;
		gbc_horizontalStrut_3.gridy = 1;
		BookingPanel.add(horizontalStrut_3, gbc_horizontalStrut_3);
		// 姓名欄位：JLabel
		JLabel BName = new JLabel("姓名");
		BName.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BName = new GridBagConstraints();
		gbc_BName.insets = new Insets(0, 0, 5, 5);
		gbc_BName.gridx = 0;
		gbc_BName.gridy = 2;
		BookingPanel.add(BName, gbc_BName);
		// 姓名欄位：JTextField
		BName_text = new JTextField();
		BName_text.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BName_text = new GridBagConstraints();
		gbc_BName_text.gridwidth = 5;
		gbc_BName_text.insets = new Insets(0, 0, 5, 0);
		gbc_BName_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_BName_text.gridx = 1;
		gbc_BName_text.gridy = 2;
		BookingPanel.add(BName_text, gbc_BName_text);
		BName_text.setColumns(10);
		// 電話欄位：JLabel
		JLabel BPhone = new JLabel("電話");
		BPhone.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BPhone = new GridBagConstraints();
		gbc_BPhone.insets = new Insets(0, 0, 5, 5);
		gbc_BPhone.gridx = 0;
		gbc_BPhone.gridy = 3;
		BookingPanel.add(BPhone, gbc_BPhone);
		// 電話欄位：JTextField
		BPhone_text = new JTextField();
		BPhone_text.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BPhone_text = new GridBagConstraints();
		gbc_BPhone_text.gridwidth = 5;
		gbc_BPhone_text.insets = new Insets(0, 0, 5, 0);
		gbc_BPhone_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_BPhone_text.gridx = 1;
		gbc_BPhone_text.gridy = 3;
		BookingPanel.add(BPhone_text, gbc_BPhone_text);
		BPhone_text.setColumns(10);
		// 航班欄位：JLabel
		JLabel BFlight = new JLabel("航班");
		BFlight.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BFlight = new GridBagConstraints();
		gbc_BFlight.insets = new Insets(0, 0, 5, 5);
		gbc_BFlight.anchor = GridBagConstraints.EAST;
		gbc_BFlight.gridx = 0;
		gbc_BFlight.gridy = 4;
		BookingPanel.add(BFlight, gbc_BFlight);
		// 航班欄位：JComboBox
		BFlight_comboBox = new JComboBox(flightStrAry);
		BFlight_comboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BFlight_comboBox = new GridBagConstraints();
		gbc_BFlight_comboBox.gridwidth = 5;
		gbc_BFlight_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_BFlight_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_BFlight_comboBox.gridx = 1;
		gbc_BFlight_comboBox.gridy = 4;
		BookingPanel.add(BFlight_comboBox, gbc_BFlight_comboBox);
		// 水平間隔
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.gridwidth = 6;
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 5;
		BookingPanel.add(horizontalStrut_4, gbc_horizontalStrut_4);
		// 提交表單：JButton
		JButton BSubmit = new JButton("送出");
		BSubmit.addActionListener(new SubmitListener());
		BSubmit.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_BSubmit = new GridBagConstraints();
		gbc_BSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_BSubmit.anchor = GridBagConstraints.EAST;
		gbc_BSubmit.gridx = 5;
		gbc_BSubmit.gridy = 6;
		BookingPanel.add(BSubmit, gbc_BSubmit);
		/**
		 * QPassengerPanel：查詢旅客的頁面，可以讓使用者進行旅客的查詢
		 */
		JPanel QPassengerPanel = new JPanel();
		contentPane.add(QPassengerPanel, "name_259276313752002");
		GridBagLayout gbl_QPassengerPanel = new GridBagLayout();
		gbl_QPassengerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_QPassengerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_QPassengerPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_QPassengerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		QPassengerPanel.setLayout(gbl_QPassengerPanel);
		// 回到上一頁(主頁面)
		JButton PBack_btn = new JButton("< 查詢旅客");
		PBack_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		PBack_btn.addActionListener(new BackHomeListener());
		GridBagConstraints gbc_PBack_btn = new GridBagConstraints();
		gbc_PBack_btn.anchor = GridBagConstraints.WEST;
		gbc_PBack_btn.insets = new Insets(0, 0, 5, 5);
		gbc_PBack_btn.gridx = 0;
		gbc_PBack_btn.gridy = 0;
		QPassengerPanel.add(PBack_btn, gbc_PBack_btn);
		// 水平間隔
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 7;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		QPassengerPanel.add(horizontalStrut, gbc_horizontalStrut);
		// 輸入旅客名字欄位：JTextField
		PQuery_text = new JTextField();
		PQuery_text.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_PQuery_text = new GridBagConstraints();
		gbc_PQuery_text.gridwidth = 6;
		gbc_PQuery_text.insets = new Insets(0, 0, 5, 5);
		gbc_PQuery_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_PQuery_text.gridx = 0;
		gbc_PQuery_text.gridy = 2;
		QPassengerPanel.add(PQuery_text, gbc_PQuery_text);
		PQuery_text.setColumns(10);
		// 提交查詢：JButton
		PQuery_btn = new JButton("送出");
		PQuery_btn.addActionListener(new QueryListener("Passenger"));
		PQuery_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_PQuery_btn = new GridBagConstraints();
		gbc_PQuery_btn.insets = new Insets(0, 0, 5, 0);
		gbc_PQuery_btn.gridx = 6;
		gbc_PQuery_btn.gridy = 2;
		QPassengerPanel.add(PQuery_btn, gbc_PQuery_btn);
		// 水平間隔
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.gridwidth = 7;
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_5.gridx = 0;
		gbc_horizontalStrut_5.gridy = 3;
		QPassengerPanel.add(horizontalStrut_5, gbc_horizontalStrut_5);
		// 查詢結果欄位：JLabel
		JLabel PResult = new JLabel("查詢結果");
		PResult.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_PResult = new GridBagConstraints();
		gbc_PResult.insets = new Insets(0, 0, 5, 0);
		gbc_PResult.gridwidth = 7;
		gbc_PResult.gridx = 0;
		gbc_PResult.gridy = 4;
		QPassengerPanel.add(PResult, gbc_PResult);
		// 水平間隔
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_6 = new GridBagConstraints();
		gbc_horizontalStrut_6.gridwidth = 7;
		gbc_horizontalStrut_6.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_6.gridx = 0;
		gbc_horizontalStrut_6.gridy = 5;
		QPassengerPanel.add(horizontalStrut_6, gbc_horizontalStrut_6);
		// 顯示查詢結果的 Panel：JScrollPane
		JScrollPane PResult_scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		QPassengerPanel.add(PResult_scrollPane, gbc_scrollPane);
		// 顯示查詢結果的欄位：JTextPane
		PResult_textPane = new JTextPane();
		PResult_textPane.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		PResult_textPane.setEditable(false);
		PResult_scrollPane.setViewportView(PResult_textPane);
		/**
		 * QFlightPanel：查詢航班的頁面，可以讓使用者進行航班的查詢
		 */
		JPanel QFlightPanel = new JPanel();
		contentPane.add(QFlightPanel, "name_259276348037181");
		GridBagLayout gbl_QFlightPanel = new GridBagLayout();
		gbl_QFlightPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_QFlightPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_QFlightPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_QFlightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		QFlightPanel.setLayout(gbl_QFlightPanel);
		// 回到上一頁(主頁面)
		JButton FBack_btn = new JButton("< 查詢航班");
		FBack_btn.addActionListener(new BackHomeListener());
		FBack_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_FBack_btn = new GridBagConstraints();
		gbc_FBack_btn.anchor = GridBagConstraints.WEST;
		gbc_FBack_btn.insets = new Insets(0, 0, 5, 5);
		gbc_FBack_btn.gridx = 0;
		gbc_FBack_btn.gridy = 0;
		QFlightPanel.add(FBack_btn, gbc_FBack_btn);
		// 水平間隔
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridwidth = 7;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 1;
		QFlightPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		// 選擇航班欄位：JComboBox
		FFlight_comboBox = new JComboBox(flightStrAry);
		FFlight_comboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_FFlight_comboBox = new GridBagConstraints();
		gbc_FFlight_comboBox.gridwidth = 6;
		gbc_FFlight_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_FFlight_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_FFlight_comboBox.gridx = 0;
		gbc_FFlight_comboBox.gridy = 2;
		QFlightPanel.add(FFlight_comboBox, gbc_FFlight_comboBox);
		// 提交查詢：JButton
		FSubmit_btn = new JButton("送出");
		FSubmit_btn.addActionListener(new QueryListener("Flight"));
		FSubmit_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GridBagConstraints gbc_FSubmit_btn = new GridBagConstraints();
		gbc_FSubmit_btn.insets = new Insets(0, 0, 5, 0);
		gbc_FSubmit_btn.gridx = 6;
		gbc_FSubmit_btn.gridy = 2;
		QFlightPanel.add(FSubmit_btn, gbc_FSubmit_btn);
		// 水平間隔
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.gridwidth = 7;
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 3;
		QFlightPanel.add(horizontalStrut_2, gbc_horizontalStrut_2);
		// 查詢結果欄位：JLabel
		JLabel FResult = new JLabel("查詢結果");
		FResult.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_FResult = new GridBagConstraints();
		gbc_FResult.insets = new Insets(0, 0, 5, 0);
		gbc_FResult.gridwidth = 7;
		gbc_FResult.gridx = 0;
		gbc_FResult.gridy = 4;
		QFlightPanel.add(FResult, gbc_FResult);
		// 顯示查詢結果的 Panel：JScrollPane
		JScrollPane FResult_scrollPane = new JScrollPane();
		GridBagConstraints gbc_FResult_scrollPane = new GridBagConstraints();
		gbc_FResult_scrollPane.gridwidth = 7;
		gbc_FResult_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_FResult_scrollPane.gridx = 0;
		gbc_FResult_scrollPane.gridy = 5;
		QFlightPanel.add(FResult_scrollPane, gbc_FResult_scrollPane);
		// 顯示查詢結果的欄位：JTextPane
		FResult_textPane = new JTextPane();
		FResult_textPane.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		FResult_textPane.setEditable(false);
		FResult_scrollPane.setViewportView(FResult_textPane);
	}
// 內部類別 ******************************************************
	/**
	 * 綁定事件：返回主畫面
	 */
	class BackHomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout)contentPane.getLayout();
			cardLayout.show(contentPane, "name_259276249528539");
		}
	}
	/**
	 * 綁定事件：提交旅客表單
	 */
	class SubmitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] strAry = submitBooking();
			if( strAry.length > 0 ){
				String[] PStrAry = new String[]{strAry[0], strAry[1]};
				String[] FStrAry = new String[]{strAry[2]};
				saveBooking(PStrAry, FStrAry);
				showAlert("訂位成功。");
			}
		}
	}
	/**
	 * 綁定事件：查詢旅客或班機
	 */
	class QueryListener implements ActionListener {
		private String mode;
		public QueryListener(String s){
			mode = s;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( mode == "Passenger" ){
				String q = PQuery_text.getText();
				if( q == null || q.isEmpty() ){
					showError("查詢不能為空。");
				}else{
					ArrayList<String> resultAry = queryPassenger(q);
					if( resultAry.size()>0 ){
						StringBuffer buf = new StringBuffer(); 
						for( int i=0, iLen=resultAry.size(); i<iLen; i++ ){
							buf.append(resultAry.get(i));
						}
						PResult_textPane.setText(buf.toString());
					}
				}
			}else if( mode == "Flight" ){
				String q = FFlight_comboBox.getSelectedItem().toString();
				System.out.println(q);
				ArrayList<String> resultAry = queryFlight(q);
				if( resultAry.size()>0 ){
					StringBuffer buf = new StringBuffer(); 
					for( int i=0, iLen=resultAry.size(); i<iLen; i++ ){
						buf.append(resultAry.get(i));
					}
					FResult_textPane.setText(buf.toString());
				}
			}
		}
	}
	/**
	 * 綁定事件：主畫面跳轉到子畫面
	 */
	class GoPanelListener implements ActionListener {
		private String panelName;
		public GoPanelListener(String s){
			panelName = s;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout)contentPane.getLayout();
			cardLayout.show(contentPane, panelName);
		}
	}
// 實體方法 ******************************************************
	/**
	 * 實作介面：IBooking
	 * 儲存：旅客的相關資訊與選擇的航班
	 */
	@Override
	public void saveBooking(String[] PStrAry, String[] FStrAry){
		TreeMap<Integer, String[]> PMap = getPessenger();
		TreeMap<Integer, String[]> FMap = getFlightMap();
		Integer PID = PMap.size()+1;
		Integer FID = FMap.size()+1;
		saveToPassengerMap(PID, PStrAry);
		saveToBookingMap(PID, FID);
		saveToFlightMap(FID, FStrAry);
	}
	/**
	 * 實作介面：IBooking
	 * 提交：旅客的相關資訊與選擇的航班
	 */
	@Override
	public String[] submitBooking(){
		String[] strAry = new String[3];
		strAry[0] = BName_text.getText();
		strAry[1] = BPhone_text.getText();
		strAry[2] = BFlight_comboBox.getSelectedItem().toString();
		if( strAry[0] == null || strAry[0].isEmpty() ){
			showError("姓名不能為空。");
			return new String[0];
		}
		if( strAry[1] == null || strAry[1].isEmpty() ){
			showError("電話不能為空。");
			return new String[0];
		}
		String msg = "姓名："+strAry[0]+"\n電話："+strAry[1]+"\n班機："+strAry[2]+"\n是否已填妥資料？";
		if( showConfirm(msg) != 1 ){
			return new String[0];
		}
		return strAry;
	}
	/**
	 * 實作介面：IPassengerQuery
	 * 查詢：符合 q 的旅客，有訂位哪些航班
	 */
	@Override
	public ArrayList<String> queryPassenger(String q){
		TreeMap<Integer, String[]> PMap = getPessenger();
		ArrayList<String> resultAry = new ArrayList<String>();
		if( PMap.size() == 0 ){
			showError("找不到該旅客；"+q+"。");
			return resultAry;
		}
		int num = 0;
		for( Map.Entry<Integer, String[]> entry : PMap.entrySet() ){
			Integer PID = entry.getKey();
			String[] valueAry = entry.getValue();
			if( valueAry[0].equals(q) ){
				num = 1;
				Integer FID = getFID(PID);
				String FName = getFName(FID);
				String item = "姓名："+valueAry[0]+"\n電話："+valueAry[1]+"\n班機："+FName+"\n\n";
				resultAry.add(item);
			}
		}
		if( num == 0 ){
			showError("找不到該旅客；"+q+"。");
			return resultAry;
		}
		return resultAry;
	}
	/**
	 * 實作介面：IFlightQuery
	 * 查詢：符合 q 的航班，有哪些旅客
	 */
	@Override
	public ArrayList<String> queryFlight(String q){
		TreeMap<Integer, String[]> FMap = getFlightMap();
		ArrayList<String> resultAry = new ArrayList<String>();
		if( FMap.size() == 0 ){
			showError("找不到該航班；"+q+"。");
			return resultAry;
		}
		int num = 0;
		for( Map.Entry<Integer, String[]> entry : FMap.entrySet() ){
			Integer FID = entry.getKey();
			String[] valueAry = entry.getValue();
			if( valueAry[0].equals(q) ){
				num = 1;
				Integer PID = getPID(FID);
				String[] FStrAry = getPValue(PID);
				String item = "姓名："+FStrAry[0]+"\n電話："+FStrAry[1]+"\n班機："+q+"\n\n";
				resultAry.add(item);
			}
		}
		if( num == 0 ){
			showError("找不到該航班；"+q+"。");
			return resultAry;
		}
		return resultAry;
	}
	/**
	 * GUI 彈出視窗：是否確認
	 */
	public int showConfirm(String msg){
		JDialog.setDefaultLookAndFeelDecorated(true); // 不套用作業系統外框
		int res = JOptionPane.showConfirmDialog(null, msg, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if( res == JOptionPane.YES_OPTION ){
			return 1;
		}else if( res == JOptionPane.NO_OPTION ){
			return 0;
		}
		return -1;
	}
	/**
	 * GUI 彈出視窗：發生錯誤
	 */
	public void showError(String msg){
		JDialog.setDefaultLookAndFeelDecorated(true); // 不套用作業系統外框
		JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * GUI 彈出視窗：提醒事項
	 */
	public void showAlert(String msg){
		JDialog.setDefaultLookAndFeelDecorated(true); // 不套用作業系統外框
		JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.PLAIN_MESSAGE);
	}
// 類別方法 ******************************************************
	/**
	 * 主程式
	 */
	public static void main(String[] args){
		/**
		 * 讓 GUI 組件與交互只允許在 EDT 上進行
		 * 以便 GUI 能及時響應使用者的輸入。
		 */
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				try{
					CGUI frame = new CGUI();
					frame.w.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
