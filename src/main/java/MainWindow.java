package src.main.java;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JTextField fieldAmount;
	private ArrayList<Currency> currencies = Currency.init();
	
	public MainWindow() {
		super("Przelicznik Walut");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 260);
		setLocationRelativeTo(null);
		setResizable( false );
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JLabel lblConvert = new JLabel("Waluta: "); //$NON-NLS-1$
		lblConvert.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConvert.setBounds(0, 14, 92, 10);
		contentPane.add(lblConvert);
	
		final JComboBox<String> comboBoxCountry1 = new JComboBox<String>();
		comboBoxCountry1.setBounds(147, 7, 240, 28);
		populate(comboBoxCountry1, currencies);
		contentPane.add(comboBoxCountry1);
		
		JLabel lblTo = new JLabel("Na:"); //$NON-NLS-1$
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(66, 54, 26, 10);
		contentPane.add(lblTo);
		
		final JComboBox<String> comboBoxCountry2 = new JComboBox<String>();
		comboBoxCountry2.setBounds(147, 47, 240, 28);
		populate(comboBoxCountry2, currencies);
		contentPane.add(comboBoxCountry2);
		
		JLabel lblAmount = new JLabel("Kwota:"); //$NON-NLS-1$
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(23, 108, 69, 10);
		contentPane.add(lblAmount);
		 
		fieldAmount = new JTextField();
		fieldAmount.setText("0.00");
		fieldAmount.setBounds(147, 101, 103, 29);
		contentPane.add(fieldAmount);
		fieldAmount.setColumns(10);
		fieldAmount.setDocument(new JTextFieldLimit(8));
     	
		final JLabel lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setBounds(147, 188, 428, 38);
		contentPane.add(lblResult);
		
		JButton btnConvert = new JButton("PRZELICZ"); //$NON-NLS-1$
		btnConvert.setBounds(147, 142, 129, 38);
		btnConvert.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	String nameCurrency1 = comboBoxCountry1.getSelectedItem().toString();
	        	String nameCurrency2 = comboBoxCountry2.getSelectedItem().toString();
	        	String result;
	        	String formattedPrice;
	        	String formattedAmount; 
	        	Double price;
	        	Double amount = 0.0;
	        	DecimalFormat format = new DecimalFormat("#0.00");
	        	
	        	try {
	        		amount = Double.parseDouble( fieldAmount.getText() ) ;
	        	} catch (NumberFormatException e) {
	        	    e.printStackTrace();
	        	    amount = 0.0;
	        	}
	        	
	        	price = convert(nameCurrency1, nameCurrency2, currencies, amount);

	        	formattedPrice = format.format(price);
	        	formattedAmount = format.format(amount);
	        	
	        	result = formattedAmount + " " + nameCurrency1 + " = " + formattedPrice + " " + nameCurrency2;
	        	lblResult.setText(result);	        	
	        }
	    });		
		contentPane.add(btnConvert);
	}
	
	public static void populate(JComboBox<String> comboBox, ArrayList<Currency> currencies) {
		for (Integer i = 0; i < currencies.size(); i++) {
			comboBox.addItem( currencies.get(i).getName() );
		}		
	}
	
	public static Double convert(String currency1, String currency2, ArrayList<Currency> currencies, Double amount) {
		String shortNameCurrency2 = null;
		Double exchangeValue;
		Double price = 0.0;
		
		for (Integer i = 0; i < currencies.size(); i++) {
			if (currencies.get(i).getName() == currency2) {
				shortNameCurrency2 = currencies.get(i).getShortName();
				break;
			}
		}
		
		if (shortNameCurrency2 != null) {
			for (Integer i = 0; i < currencies.size(); i++) {
				if (currencies.get(i).getName() == currency1) {
					exchangeValue = currencies.get(i).getExchangeValues().get(shortNameCurrency2);
					price = Currency.convert(amount, exchangeValue);
					break;
				}
			}
		}
		
		return price;
	}
}