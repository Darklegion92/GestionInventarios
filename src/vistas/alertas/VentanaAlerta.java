
package vistas.alertas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import principal.MaterialButton;
import recursos.componentes.AWTUtilities;

public class VentanaAlerta extends JDialog implements WindowListener, ActionListener{
	/**
	 * Se encarga de lanzar una ventana Informativa con mensaje para le usuario
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblIcono;
	private JLabel lblMensaje;
	private JPanel panelPrincipal;
	private JPanel panelBotones;
	
	private MaterialButton btnAceptar;
	private MaterialButton btnCancelar;
	
	private org.jdesktop.swingx.border.DropShadowBorder bordeFormulario;
	
	private GroupLayout layoutpanelPrincipal;
	private GroupLayout layuotpanelBotones;
	private GroupLayout layoutFormulario;
	
	private Timer tiempo = null;
	private TimerTask tarea;
	
	private int i = 32;
	
	private boolean respuesta;

	
	public VentanaAlerta(JFrame parent, boolean modal) {
		super(parent, modal);
	}

	private void initComponents(String tipo, int botones) {
		
		String rutaImagen = "";
		Color color = null;
		
		
		if(tipo.equals("INFORMACION")) {
			rutaImagen = "/recursos/img/informacion.png";
			color = new Color(0, 102, 153);
		}else if(tipo.equals("CORRECTO")) {
			rutaImagen = "/recursos/img/success.png";
			color = new Color(0, 102, 0);
		}else if(tipo.equals("ERROR")) {
			rutaImagen = "/recursos/img/error.png";
			color = new Color(153, 0, 0);
		}else if(tipo.equals("ADVERTENCIA")) {
			rutaImagen = "/recursos/img/warning.png";
			color = new Color(255, 153, 0);
		}
		
		panelPrincipal = new JPanel();
		panelBotones = new JPanel();
		
		lblIcono = new JLabel();
		lblMensaje = new JLabel();
		
		btnAceptar = new MaterialButton();
		btnCancelar = new MaterialButton();
		
		layoutpanelPrincipal = new GroupLayout(panelPrincipal);
		layuotpanelBotones = new GroupLayout(panelBotones);
		layoutFormulario = new GroupLayout(getContentPane());
		
		setUndecorated(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(layoutFormulario);
		addWindowListener(this);
		{
			bordeFormulario = new org.jdesktop.swingx.border.DropShadowBorder();
			bordeFormulario.setShowLeftShadow(true);
			bordeFormulario.setShowTopShadow(true);
			
			panelPrincipal.setBorder(bordeFormulario);
			panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
			panelPrincipal.setLayout(layoutpanelPrincipal);
			
			{
				lblIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				lblIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaImagen)));

				lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 20));
				lblMensaje.setText("ALERTA INFORMACIÓN");
				lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				
				layoutpanelPrincipal.setHorizontalGroup(layoutpanelPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layoutpanelPrincipal.createSequentialGroup().addContainerGap()
								.addGroup(layoutpanelPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lblMensaje, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
										.addComponent(lblIcono, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap())
						.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));
				layoutpanelPrincipal.setVerticalGroup(layoutpanelPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layoutpanelPrincipal.createSequentialGroup().addContainerGap()
								.addComponent(lblIcono, GroupLayout.PREFERRED_SIZE, 167,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lblMensaje, GroupLayout.PREFERRED_SIZE, 53,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
								.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
				
				
				
				panelBotones.setLayout(layuotpanelBotones);
				panelBotones.setBackground(color);
				{
					btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
					btnAceptar.setForeground(color);//color que se cambia
					btnAceptar.setText("ACEPTAR");
					btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					btnAceptar.setFont(new java.awt.Font("Roboto Medium", 1, 18));
					btnAceptar.addActionListener(this);
					
					if(botones == 1) {
						layuotpanelBotones.setHorizontalGroup(layuotpanelBotones.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layuotpanelBotones.createSequentialGroup().addContainerGap().addComponent(btnAceptar,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));
						layuotpanelBotones.setVerticalGroup(layuotpanelBotones.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(GroupLayout.Alignment.TRAILING,
										layuotpanelBotones.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap()));
					}else if(botones == 2) {
						btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
						btnCancelar.setForeground(color);//color que se cambia
						btnCancelar.setText("CANCELAR");
						btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
						btnCancelar.setFont(new java.awt.Font("Roboto Medium", 1, 18));
						btnCancelar.addActionListener(this);
						
						layuotpanelBotones.setHorizontalGroup(
								layuotpanelBotones.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layuotpanelBotones.createSequentialGroup().addContainerGap()
										.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 194,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));
						layuotpanelBotones.setVerticalGroup(layuotpanelBotones.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layuotpanelBotones.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layuotpanelBotones.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
					}
					
					
					
				
				}
			}
		}
	
		
		layoutFormulario.setHorizontalGroup(layoutFormulario.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layoutFormulario.setVerticalGroup(layoutFormulario.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		getRootPane().setDefaultButton(btnAceptar);
		btnAceptar.requestFocus();
		pack();
	}

	
/////////////Eventos/////////////////////////////////////////
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		tarea = new TimerTask() {
			@Override
			public void run() {
				if (i == 352) {
					tiempo.cancel();
				} else {
					Ubicar(i);
					i += 32;
					transparencia((float) i / 352);
				}
			}
		};
		tiempo = new java.util.Timer();
		tiempo.schedule(tarea, 0, 1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAceptar) {
			respuesta = true;
			cerrarVentana();
		}
		if(e.getSource() == btnCancelar) {
			respuesta = false;
			cerrarVentana();
		}
		
	}
	
////////Metodos Privados//////////////////
	
	/**
	 * se encarga de iniciar la tarea para el cierre de la vista
	 */
	private void cerrarVentana() {
		
		tarea = new TimerTask() {
			public void run() {
				if (i == 0) {
					cerrar();
				} else {
					Ubicar(i);
					i -= 32;
					transparencia((float) i / 352);
				}
			}
		};
		tiempo = new Timer();
		tiempo.schedule(tarea, 0, 2);
		
	}
	
	/**
	 * Se encarga de reiniciar los valores de la vista y ocultarla
	 */
	private void cerrar() {
		this.dispose();
		tiempo = null;
		tarea = null;
	}
	
	/**
	 * Define la transparencia de la vista
	 * @param trasp valor de la transparencia
	 */
	private void transparencia(float trasp) {
		AWTUtilities.setOpacity(this, trasp);
	}
	
	/**
	 * Procede a generar la ubicacion de la vista 
	 * @param y valor para ubicar en el plano horzontal
	 */
	private void Ubicar(int y) {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    int ancho = (int) d.getWidth();
		this.setLocation((ancho-panelPrincipal.getWidth())/2, y - 120);
	}
	
/////////Metodos Publicos////////////////////////////////////
	/**
	 * Se encarga de crear el tipo, los textos y los botones que van en la vista
	 * @param tipo tipo de alerta
	 * @param mensaje mensaje de la alerta
	 * @param botones cantidad de botones en la alerta
	 */
	public void crearMensaje(String tipo,String mensaje,int botones) {
		initComponents(tipo, botones);
		this.lblMensaje.setText(mensaje);
		AWTUtilities.setOpaque(this, false);	
		Ubicar(0);
		repaint();
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

}
