package vistas;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import controladores.Coordinador;

public class VentanaSplash extends JDialog {

	/**
	* 
	*/
	private static final long serialVersionUID = 3562717898205004202L;
	private JLabel splash;
	private JLabel porcentaje;
	private JLabel porcentaje2;
	private JProgressBar barraCarga;
	private JLabel lblSuperLogo;

	private Coordinador miCoordinador;

	public VentanaSplash(JFrame parent, boolean modal) {
		super(parent,modal);
		iniciarComponentes();
		setSize(395, 200);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		iniciarHilo();
	}

	private void iniciarHilo() {
		Thread hilo = new Thread(new Runnable() { // le damos cuerpo a la interfaz
			int x = 0;
			String cad = ".";

			@Override
			public void run() {
				try {
					while (x <= 100) {
						barraCarga.setValue(x);
						porcentaje.setText(x + "%");
						porcentaje2.setText(cad + x + "%");
						x += 2;
						cad += ".";// le voy sumando un punto
						Thread.sleep(100);

					}
					miCoordinador.cerrarVentanaSplash();
				} catch (Exception e) {
					System.out.print("Exepcion " + e.getMessage());
				}

			}
		});
		hilo.start();

	}

	private void iniciarComponentes() {
		splash = new JLabel("Versión De Prueba...............");
		porcentaje = new JLabel("%0");
		porcentaje2 = new JLabel("%0");

		splash.setFont(new Font("Tahoma", Font.PLAIN, 18));
		splash.setBounds(49, 11, 155, 32);
		splash.setForeground(new java.awt.Color(255, 255, 255));
		getContentPane().add(splash);

		porcentaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		porcentaje.setBounds(206, 23, 46, 14);
		porcentaje.setForeground(new java.awt.Color(255, 255, 255));
		getContentPane().add(porcentaje);

		barraCarga = new JProgressBar();
		barraCarga.setBounds(26, 144, 229, 32);
		getContentPane().add(barraCarga);

		porcentaje2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		porcentaje2.setBounds(26, 182, 264, 14);

		getContentPane().add(porcentaje2);

		lblSuperLogo = new javax.swing.JLabel();
		lblSuperLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSuperLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/img/superLogo.png")));
		lblSuperLogo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		getContentPane().add(lblSuperLogo);
		lblSuperLogo.setBounds(0, 0, 395, 195);
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	

}
