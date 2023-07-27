import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JTextField txtMoneda;
	private JLabel label;
	
	public enum Moneda{
		pesos_dollar,
		pesos_euro,
		pesos_esterlina,
		pesos_yen,
		pesos_bolivar,
		dollar_pesos,
		euro_pesos,
		esterlina_pesos,
		yen_pesos,
		bolivar_pesos
	}
	
	public double dollar = 55.82;
	public double euro = 61.86;
	public double esterlina = 72.16;
	public double yen = 0.40;
	public double bolivar = 1.90;
	
	public double valorImput = 0.00;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Conversor de monedas");
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(138, 10, 180, 23);
		frame.getContentPane().add(lblNewLabel);
		
		txtMoneda = new JTextField();
		txtMoneda.setToolTipText("");
		txtMoneda.setForeground(new Color(0, 0, 0));
		txtMoneda.setBounds(10, 92, 144, 19);
		frame.getContentPane().add(txtMoneda);
		txtMoneda.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Moneda local ($)");
		lblNewLabel_1.setFont(new Font("Modern No. 20", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 69, 117, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		btn = new JButton("convertir");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(161, 146, 117, 21);
		frame.getContentPane().add(btn);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 146, 144, 21);
		frame.getContentPane().add(cmb);
		
		label = new JLabel("00.00");
		label.setBounds(177, 95, 180, 13);
		frame.getContentPane().add(label);
	}
	
	public void Convertir() {
		if(Validar(txtMoneda.getText())) {
		
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			
			switch (moneda) {
			case pesos_dollar:
				pesosAMoneda(dollar);
				break;
			case pesos_euro:
				pesosAMoneda(euro);
				break;
			case pesos_esterlina:
				pesosAMoneda(esterlina);
				break;
			case pesos_yen:
				pesosAMoneda(yen);
				break;
			case pesos_bolivar:
				pesosAMoneda(bolivar);
				break;
			case dollar_pesos:
				MonedasAPesos(dollar);
				break;
			case euro_pesos:
				MonedasAPesos(euro);
				break;
			case esterlina_pesos:
				MonedasAPesos(esterlina);
				break;
			case yen_pesos:
				MonedasAPesos(yen);
				break;
			case bolivar_pesos:
				MonedasAPesos(bolivar);
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
		}
	
		}
	}
	
	public void pesosAMoneda(double moneda) {
		double res = valorImput / moneda;
		label.setText(Redondear(res));
	}
	
	public void MonedasAPesos(double moneda) {
		double res = valorImput * moneda;
		label.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorImput = x;
			return true;
		}catch(NumberFormatException e) {
			label.setText("Solo es permitido Num!!");
		}
		return false;
	}
	
	
}
