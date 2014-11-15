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
public class Main{
// 實體變數 ******************************************************
	private JFrame w;
	private JPanel contentPane;
	private JComboBox BPComboBox;
	private JComboBox BFComboBox;
	private JComboBox PPComboBox;
	private JComboBox FFComboBox;
	private JTextField BSeat_text;
	private JTextField PName_text;
	private JTextField FName_text;
	private JTextPane PResult_textPane;
	private JTextPane FResult_textPane;
	private TreeMap<String, Passenger> passengers = new TreeMap<String, Passenger>();
	private TreeMap<String, SpecificFlight> flights = new TreeMap<String, SpecificFlight>();
// 建構子 ******************************************************
	/**
	 * CGUI 建構子：產生 Main
	 */
	public Main(){
		/**
		 * 初始化 JFrame
		 */
		w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setTitle("Booking Application");
        w.setBounds(100, 100, 600, 400);
		w.setResizable(false);
		/**
		 * 產生 CardLayout 的 JPanel
		 * 一共有四張卡片：HomePanel、BookingPanel、QPassengerPanel、QFlightPanel
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		w.setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		// 初始化四張卡片的GUI
		initHomePage();
		initBooking();
		initQPassenger();
		initQFlight();
		initAddPassenger();
		initAddFlight();
	}
	/**
	 * HomePanel：主頁面，可以讓使用者選擇五種操作
	 */
	public void initHomePage(){
		JPanel HomePanel = new JPanel();
		HomePanel.setBackground(new Color(245,245,245));
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
		// 新增旅客
		JButton AddPassenger_btn = new JButton("新增旅客");
		AddPassenger_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		AddPassenger_btn.addActionListener(new GoPanelListener("name_573053561848675"));
		// 新增航班
		JButton AddFlight_btn = new JButton("新增航班");
		AddFlight_btn.addActionListener(new GoPanelListener("name_573109199200504"));
		AddFlight_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		// 作者
		JLabel lblPowered = new JLabel("Powered By Tzu-Hsieb Wang, 2014");
		lblPowered.setHorizontalAlignment(SwingConstants.CENTER);
		lblPowered.setForeground(Color.LIGHT_GRAY);
		lblPowered.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		GroupLayout gl_HomePanel = new GroupLayout(HomePanel);
		gl_HomePanel.setHorizontalGroup(
			gl_HomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HomePanel.createSequentialGroup()
					.addGroup(gl_HomePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_HomePanel.createSequentialGroup()
							.addGap(149)
							.addComponent(lblPowered, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_HomePanel.createSequentialGroup()
							.addGap(207)
							.addComponent(Booking_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_HomePanel.createSequentialGroup()
							.addGap(89)
							.addGroup(gl_HomePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_HomePanel.createSequentialGroup()
									.addComponent(QPassenager_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(66)
									.addComponent(QFlight_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_HomePanel.createSequentialGroup()
									.addComponent(AddPassenger_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(66)
									.addComponent(AddFlight_btn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_HomePanel.setVerticalGroup(
			gl_HomePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_HomePanel.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addGroup(gl_HomePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(AddPassenger_btn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddFlight_btn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(Booking_btn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_HomePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(QFlight_btn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(QPassenager_btn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(lblPowered, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		HomePanel.setLayout(gl_HomePanel);
	}
	/**
	 * BookingPanel：旅客訂位的頁面，可以讓使用者進行航班的訂位
	 */
	public void initBooking(){
		JPanel BookingPanel = new JPanel();
		BookingPanel.setBackground(new Color(245,245,245));
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
		// 旅客欄位：JLabel
		JLabel PLabel = new JLabel("旅客");
		PLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_PLabel = new GridBagConstraints();
		gbc_PLabel.anchor = GridBagConstraints.EAST;
		gbc_PLabel.insets = new Insets(0, 0, 5, 5);
		gbc_PLabel.gridx = 0;
		gbc_PLabel.gridy = 2;
		BookingPanel.add(PLabel, gbc_PLabel);
		// 旅客欄位：JComboBox
		BPComboBox = new JComboBox();
		BPComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_BPComboBox = new GridBagConstraints();
		gbc_BPComboBox.gridwidth = 5;
		gbc_BPComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_BPComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_BPComboBox.gridx = 1;
		gbc_BPComboBox.gridy = 2;
		BookingPanel.add(BPComboBox, gbc_BPComboBox);
		// 航班欄位：JLabel
		JLabel FLabel = new JLabel("航班");
		FLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_FLabel = new GridBagConstraints();
		gbc_FLabel.insets = new Insets(0, 0, 5, 5);
		gbc_FLabel.anchor = GridBagConstraints.EAST;
		gbc_FLabel.gridx = 0;
		gbc_FLabel.gridy = 3;
		BookingPanel.add(FLabel, gbc_FLabel);
		// 航班欄位：JComboBox
		BFComboBox = new JComboBox();
		BFComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_BFComboBox = new GridBagConstraints();
		gbc_BFComboBox.gridwidth = 5;
		gbc_BFComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_BFComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_BFComboBox.gridx = 1;
		gbc_BFComboBox.gridy = 3;
		BookingPanel.add(BFComboBox, gbc_BFComboBox);
		// 座位欄位：JLabel
		JLabel SeatLabel = new JLabel("座位");
		SeatLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_SeatLabel = new GridBagConstraints();
		gbc_SeatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_SeatLabel.gridx = 0;
		gbc_SeatLabel.gridy = 4;
		BookingPanel.add(SeatLabel, gbc_SeatLabel);
		// 座位欄位：JTextField
		BSeat_text = new JTextField();
		BSeat_text.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		BSeat_text.addKeyListener(new KeyAdapter(){
			// 讓使用者只能輸入數字
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if( keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9 ){
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_BSeat_text = new GridBagConstraints();
		gbc_BSeat_text.gridwidth = 5;
		gbc_BSeat_text.insets = new Insets(0, 0, 5, 0);
		gbc_BSeat_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_BSeat_text.gridx = 1;
		gbc_BSeat_text.gridy = 4;
		BookingPanel.add(BSeat_text, gbc_BSeat_text);
		BSeat_text.setColumns(10);
		// 水平間隔
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.gridwidth = 6;
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 5;
		BookingPanel.add(horizontalStrut_4, gbc_horizontalStrut_4);
		// 提交表單：JButton
		JButton BSubmit = new JButton("送出");
		BSubmit.addActionListener(new SubmitListener());
		BSubmit.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_BSubmit = new GridBagConstraints();
		gbc_BSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_BSubmit.anchor = GridBagConstraints.EAST;
		gbc_BSubmit.gridx = 5;
		gbc_BSubmit.gridy = 6;
		BookingPanel.add(BSubmit, gbc_BSubmit);
	}
	/**
	 * QPassengerPanel：查詢旅客的頁面，可以讓使用者進行旅客的查詢
	 */
	public void initQPassenger(){
		JPanel QPassengerPanel = new JPanel();
		QPassengerPanel.setBackground(new Color(245,245,245));
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
		// 選擇航班欄位：JComboBox
		PPComboBox = new JComboBox();
		PPComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_PPComboBox = new GridBagConstraints();
		gbc_PPComboBox.gridwidth = 6;
		gbc_PPComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_PPComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_PPComboBox.gridx = 0;
		gbc_PPComboBox.gridy = 2;
		QPassengerPanel.add(PPComboBox, gbc_PPComboBox);
		// 提交查詢：JButton
		JButton PQuery_btn = new JButton("送出");
		PQuery_btn.addActionListener(new QueryListener("Passenger"));
		PQuery_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
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
		PResult_textPane.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		PResult_textPane.setEditable(false);
		PResult_scrollPane.setViewportView(PResult_textPane);
	}
	/**
	 * QFlightPanel：查詢航班的頁面，可以讓使用者進行航班的查詢
	 */
	public void initQFlight(){
		JPanel QFlightPanel = new JPanel();
		QFlightPanel.setBackground(new Color(245,245,245));
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
		FFComboBox = new JComboBox();
		FFComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_FFComboBox = new GridBagConstraints();
		gbc_FFComboBox.gridwidth = 6;
		gbc_FFComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_FFComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_FFComboBox.gridx = 0;
		gbc_FFComboBox.gridy = 2;
		QFlightPanel.add(FFComboBox, gbc_FFComboBox);
		// 提交查詢：JButton
		JButton FSubmit_btn = new JButton("送出");
		FSubmit_btn.addActionListener(new QueryListener("Flight"));
		FSubmit_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
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
		FResult_textPane.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		FResult_textPane.setEditable(false);
		FResult_scrollPane.setViewportView(FResult_textPane);
	}
	/**
	 * initAddPassenger：新增旅客的頁面，可以讓使用者進行旅客資訊的新增
	 */
	public void initAddPassenger(){	
		JPanel AddPassengerPanel = new JPanel();
		AddPassengerPanel.setBackground(new Color(245,245,245));
		contentPane.add(AddPassengerPanel, "name_573053561848675");
		GridBagLayout gbl_AddPassengerPanel = new GridBagLayout();
		gbl_AddPassengerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_AddPassengerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_AddPassengerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_AddPassengerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		AddPassengerPanel.setLayout(gbl_AddPassengerPanel);
		// 回到上一頁(主頁面)
		JButton AddPBack_btn = new JButton("< 新增旅客");
		AddPBack_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		AddPBack_btn.addActionListener(new BackHomeListener());
		GridBagConstraints gbc_AddPBack_btn = new GridBagConstraints();
		gbc_AddPBack_btn.gridwidth = 2;
		gbc_AddPBack_btn.insets = new Insets(0, 0, 5, 5);
		gbc_AddPBack_btn.gridx = 0;
		gbc_AddPBack_btn.gridy = 0;
		AddPassengerPanel.add(AddPBack_btn, gbc_AddPBack_btn);
		// 水平間隔
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 6;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		AddPassengerPanel.add(horizontalStrut, gbc_horizontalStrut);
		// 輸入旅客姓名欄位：JLabel
		JLabel label = new JLabel("旅客姓名");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		AddPassengerPanel.add(label, gbc_label);
		// 輸入旅客姓名欄位：JTextField
		PName_text = new JTextField();
		PName_text.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_PName_text = new GridBagConstraints();
		gbc_PName_text.insets = new Insets(0, 0, 5, 0);
		gbc_PName_text.gridwidth = 5;
		gbc_PName_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_PName_text.gridx = 1;
		gbc_PName_text.gridy = 2;
		AddPassengerPanel.add(PName_text, gbc_PName_text);
		PName_text.setColumns(10);
		// 水平間隔
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridwidth = 6;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 3;
		AddPassengerPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		// 確認名稱：JButton
		JButton AddP_btn = new JButton("確認");
		AddP_btn.addActionListener(new AddListener("Passenger"));
		AddP_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_AddP_btn = new GridBagConstraints();
		gbc_AddP_btn.anchor = GridBagConstraints.EAST;
		gbc_AddP_btn.gridx = 5;
		gbc_AddP_btn.gridy = 4;
		AddPassengerPanel.add(AddP_btn, gbc_AddP_btn);
	}
	/**
	 * initAddFlight：新增航班的頁面，可以讓使用者進行航班資訊的新增
	 */
	public void initAddFlight(){
		JPanel AddFlightPanel = new JPanel();
		AddFlightPanel.setBackground(new Color(245,245,245));
		contentPane.add(AddFlightPanel, "name_573109199200504");
		GridBagLayout gbl_AddFlightPanel = new GridBagLayout();
		gbl_AddFlightPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_AddFlightPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_AddFlightPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_AddFlightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		AddFlightPanel.setLayout(gbl_AddFlightPanel);
		// 回到上一頁(主頁面)
		JButton AddFBack_btn = new JButton("< 新增航班");
		AddFBack_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		AddFBack_btn.addActionListener(new BackHomeListener());
		GridBagConstraints gbc_AddFBack_btn = new GridBagConstraints();
		gbc_AddFBack_btn.gridwidth = 2;
		gbc_AddFBack_btn.insets = new Insets(0, 0, 5, 5);
		gbc_AddFBack_btn.gridx = 0;
		gbc_AddFBack_btn.gridy = 0;
		AddFlightPanel.add(AddFBack_btn, gbc_AddFBack_btn);
		// 水平間隔
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridwidth = 6;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 1;
		AddFlightPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		// 輸入航班名稱欄位：JLabel
		JLabel label = new JLabel("航班名稱");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		AddFlightPanel.add(label, gbc_label);
		// 輸入航班名稱欄位：JTextField
		FName_text = new JTextField();
		FName_text.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_FName_text = new GridBagConstraints();
		gbc_FName_text.insets = new Insets(0, 0, 5, 0);
		gbc_FName_text.gridwidth = 5;
		gbc_FName_text.fill = GridBagConstraints.HORIZONTAL;
		gbc_FName_text.gridx = 1;
		gbc_FName_text.gridy = 2;
		AddFlightPanel.add(FName_text, gbc_FName_text);
		FName_text.setColumns(10);
		// 水平間隔
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 6;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 3;
		AddFlightPanel.add(horizontalStrut, gbc_horizontalStrut);
		// 確認名稱：JButton
		JButton AddF_btn = new JButton("確認");
		AddF_btn.addActionListener(new AddListener("SpecificFlight"));
		AddF_btn.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		GridBagConstraints gbc_AddF_btn = new GridBagConstraints();
		gbc_AddF_btn.anchor = GridBagConstraints.EAST;
		gbc_AddF_btn.gridx = 5;
		gbc_AddF_btn.gridy = 4;
		AddFlightPanel.add(AddF_btn, gbc_AddF_btn);
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
	 * 綁定事件：新增旅客 | 新增航班
	 */
	class AddListener implements ActionListener {
		private String mode;
		public AddListener(String s){
			mode = s;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( mode == "Passenger" ){
				String name = PName_text.getText();
				if( name == null || name.isEmpty() ){
					showError("旅客姓名不能為空。");
				}else if( passengers.containsKey(name) ){
					showError("已經有該旅客姓名了。");
				}else{
					Passenger passenger = new Passenger(name);
					passengers.put(name, passenger);
					showAlert("新增成功。");
				}
			}else if( mode == "SpecificFlight" ){
				String name = FName_text.getText();
				if( name == null || name.isEmpty() ){
					showError("航班名稱不能為空。");
				}else if( flights.containsKey(name) ){
					showError("已經有該航班名稱了。");
				}else{
					SpecificFlight flight = new SpecificFlight(name);
					flights.put(name, flight);
					showAlert("新增成功。");
				}
			}
		}
	}
	/**
	 * 綁定事件：旅客訂位 makeBooking
	 */
	class SubmitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String SeatNum =  BSeat_text.getText();
			String PName = BPComboBox.getSelectedItem().toString();
			String FName = BFComboBox.getSelectedItem().toString();
			if( SeatNum == null || SeatNum.isEmpty() ){
				showError("座位號碼不能為空。");
			}else{
				Passenger passenger = passengers.get(PName);
				SpecificFlight flight = flights.get(FName);
				passenger.makeBooking(flight, Integer.valueOf(SeatNum));
				passengers.put(PName, passenger);
				showAlert("定位成功。");
				passenger.getAllBookingOfPassenger();
				flight.getAllBookingOfSpecificFlight();
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
				String PName = PPComboBox.getSelectedItem().toString();
				Passenger passenger = passengers.get(PName);
				ArrayList<String> resultAry = passenger.getAllBookingOfPassenger();
				if( resultAry.size()==0 ){
					showError("該旅客尚未訂位。");
				}else{
					StringBuffer buf = new StringBuffer();
					for( int i=0, iLen=resultAry.size(); i<iLen; i++ ){
						buf.append(resultAry.get(i));
					}
					PResult_textPane.setText(buf.toString());
				}
			}else if( mode == "Flight" ){
				String FName = FFComboBox.getSelectedItem().toString();
				SpecificFlight flight = flights.get(FName);
				ArrayList<String> resultAry = flight.getAllBookingOfSpecificFlight();
				if( resultAry.size()==0 ){
					showError("該航班未被訂位。");
				}else{
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
			Object[] Pkeys = passengers.keySet().toArray();
			Object[] Fkeys = flights.keySet().toArray();
			switch(panelName){
				case "name_259276278482402": // 旅客訂位頁面
					if( Pkeys.length==0 ){
						showError("沒有旅客，請新增旅客才可以訂位。");
						return;
					}
					if( Fkeys.length==0 ){
						showError("沒有航班，請新增航班才可以訂位。");
						return;
					}
					BPComboBox.removeAllItems();
					for( Map.Entry<String, Passenger> entry : passengers.entrySet() ){
						String key = entry.getKey();
						BPComboBox.addItem(key);
					}
					BFComboBox.removeAllItems();
					for( Map.Entry<String, SpecificFlight> entry : flights.entrySet() ){
						String key = entry.getKey();
						BFComboBox.addItem(key);
					}
					break;
				case "name_259276313752002": // 查詢旅客頁面
					if( Pkeys.length==0 ){
						showError("沒有旅客，請新增旅客。");
						return;
					}
					PPComboBox.removeAllItems();
					for( Map.Entry<String, Passenger> entry : passengers.entrySet() ){
						String key = entry.getKey();
						PPComboBox.addItem(key);
					}
					break;
				case "name_259276348037181": // 查詢航班頁面
					if( Fkeys.length==0 ){
						showError("沒有航班，請新增航班。");
						return;
					}
					FFComboBox.removeAllItems();
					for( Map.Entry<String, SpecificFlight> entry : flights.entrySet() ){
						String key = entry.getKey();
						FFComboBox.addItem(key);
					}
					break;
				default:
					break;
			}
			CardLayout cardLayout = (CardLayout)contentPane.getLayout();
			cardLayout.show(contentPane, panelName);
		}
	}
// 實體方法 ******************************************************
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
					Main frame = new Main();
					frame.w.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
