import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class anaPencere {

	private JFrame frame;
	private JTextField txtIP;
	private JTextField txtOlusturX;
	private JTextField txtRobotAd;
	private JTextField txtOlusturY;
	private JTextField txtOlusturZ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					anaPencere window = new anaPencere();
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
	public anaPencere() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	ArrayList<Robot> robotlar = new ArrayList<Robot>();
	Thread konumOkuyucu;

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 262);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Robot Olu\u015Ftur",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(23, 0, 246, 206);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("IP :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(66, 38, 18, 14);
		panel.add(lblNewLabel);

		JLabel lblRobotTipi = new JLabel("Robot Tipi :");
		lblRobotTipi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRobotTipi.setBounds(21, 66, 65, 14);
		panel.add(lblRobotTipi);

		JLabel lblRobotIsmi = new JLabel("Robot \u0130smi :");
		lblRobotIsmi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRobotIsmi.setBounds(17, 104, 74, 14);
		panel.add(lblRobotIsmi);

		JLabel lblKonum = new JLabel("Konum :");
		lblKonum.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKonum.setBounds(40, 137, 56, 14);
		panel.add(lblKonum);

		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setBounds(105, 35, 95, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);

		txtOlusturX = new JTextField();
		txtOlusturX.setText("0");
		txtOlusturX.setColumns(10);
		txtOlusturX.setBounds(105, 132, 25, 20);
		panel.add(txtOlusturX);

		txtRobotAd = new JTextField();
		txtRobotAd.setText("\u0130lk Robot");
		txtRobotAd.setColumns(10);
		txtRobotAd.setBounds(105, 101, 95, 20);
		panel.add(txtRobotAd);

		final JComboBox<Object> cmbRobotTip = new JComboBox<Object>();
		cmbRobotTip.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Kenaf", "P3AT" }));
		cmbRobotTip.setBounds(105, 64, 95, 20);
		panel.add(cmbRobotTip);

		txtOlusturY = new JTextField();
		txtOlusturY.setText("0");
		txtOlusturY.setColumns(10);
		txtOlusturY.setBounds(140, 132, 25, 20);
		panel.add(txtOlusturY);

		txtOlusturZ = new JTextField();
		txtOlusturZ.setText("0");
		txtOlusturZ.setColumns(10);
		txtOlusturZ.setBounds(175, 132, 25, 20);
		panel.add(txtOlusturZ);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Robot S\u00FCr",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0,
						255)));
		panel_1.setBounds(296, 0, 246, 206);
		frame.getContentPane().add(panel_1);

		JLabel lblRobotSe = new JLabel("Robot Se\u00E7 :");
		lblRobotSe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRobotSe.setBounds(17, 31, 65, 14);

		panel_1.add(lblRobotSe);
		final JLabel lblX = new JLabel("0");
		lblX.setBounds(95, 180, 33, 14);
		panel_1.add(lblX);

		final JLabel lblY = new JLabel("0");
		lblY.setBounds(129, 180, 33, 14);
		panel_1.add(lblY);

		final JLabel lblZ = new JLabel("0");
		lblZ.setBounds(170, 180, 33, 14);
		panel_1.add(lblZ);

		final JComboBox<Robot> cmbRobotSec = new JComboBox<Robot>();
		cmbRobotSec.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				final Robot secilen = robotlar.get(cmbRobotSec
						.getSelectedIndex());
				if (konumOkuyucu != null) {
					konumOkuyucu.stop();
				}
				konumOkuyucu = new Thread(new Runnable() {
					public void run() {
						while (true) {
							System.out.println(secilen.x);

							lblX.setText(secilen.x);
							lblY.setText(secilen.y);
							lblZ.setText(secilen.z);
						}
					}
				});
				konumOkuyucu.start();

			}
		});
		cmbRobotSec.setBounds(105, 28, 131, 20);
		panel_1.add(cmbRobotSec);

		JToggleButton btnOlustur = new JToggleButton("OLU\u015ETUR");
		btnOlustur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				final Robot r;
				String ip = txtIP.getText();
				String konum = txtOlusturX.getText() + ","
						+ txtOlusturY.getText() + "," + txtOlusturZ.getText();
				String isim = txtRobotAd.getText();
				if (cmbRobotTip.getSelectedItem().toString().equals("Kenaf")) {
					r = new Kenaf(ip, isim, konum);

				} else {
					r = new P3AT(ip, isim, konum);

				}

				// r.x = lblX;
				// r.y = lblY;
				// r.z = lblZ;

				robotlar.add(r);

				/*
				 * Thread t = new Thread(new Runnable() {
				 * 
				 * @Override public void run() { r.hareketEt();
				 * 
				 * } }); t.start();
				 */

				cmbRobotSec.addItem(r);

			}
		});
		btnOlustur.setBounds(58, 172, 121, 23);
		panel.add(btnOlustur);

		JButton btnSol = new JButton("<");
		btnSol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Robot secilen = robotlar.get(cmbRobotSec.getSelectedIndex());
				secilen.hareketEt(3);

			}
		});
		btnSol.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSol.setBounds(48, 98, 47, 32);
		panel_1.add(btnSol);

		JButton btnSag = new JButton(">");
		btnSag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Robot secilen = robotlar.get(cmbRobotSec.getSelectedIndex());
				secilen.hareketEt(2);
			}
		});
		btnSag.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSag.setBounds(183, 98, 53, 32);
		panel_1.add(btnSag);

		JButton btnDur = new JButton("DUR");
		btnDur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Robot secilen = robotlar.get(cmbRobotSec.getSelectedIndex());
				secilen.hareketEt(5);
			}
		});
		btnDur.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDur.setBounds(105, 98, 68, 32);
		panel_1.add(btnDur);

		JLabel label = new JLabel("Konum :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(38, 180, 56, 14);
		panel_1.add(label);

		JButton btnYukari = new JButton("^");
		btnYukari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Robot secilen = robotlar.get(cmbRobotSec.getSelectedIndex());
				secilen.hareketEt(1);

			}
		});
		btnYukari.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnYukari.setBounds(115, 62, 47, 32);
		panel_1.add(btnYukari);

		JButton btnAsagi = new JButton("v");
		btnAsagi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Robot secilen = robotlar.get(cmbRobotSec.getSelectedIndex());
				secilen.hareketEt(4);
			}
		});
		btnAsagi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAsagi.setBounds(115, 132, 47, 32);
		panel_1.add(btnAsagi);

	}
}
