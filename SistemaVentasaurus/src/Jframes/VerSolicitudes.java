package Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Conexion;
import Datos.Entrada;
import Datos.SolicitudDevolucion;
import Interfaz.MenuCliente;
import Jframes.ModernScrollBarUI;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

public class VerSolicitudes extends JFrame {
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	MenuCliente cliente= new MenuCliente();
	private JTextField textField;
	
	public static void run(boolean vacio,boolean checkbox) {
		try {
			VerSolicitudes frame = new VerSolicitudes(vacio,checkbox);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public VerSolicitudes(boolean vacio,boolean checkbox) {
        setTitle("Ver Solicitudes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        List<String> opciones = new ArrayList<>();
        
        SolicitudDevolucion aux= new SolicitudDevolucion(0,"","",0,"",0,0,null);
        
		LinkedList<SolicitudDevolucion> solicitudes;
		
		if (checkbox) {
			solicitudes = aux.traerSolicitudes();
		} else {
			solicitudes= aux.traerTodas();
		}
        
        for (SolicitudDevolucion solicitud : solicitudes) {
        	JRadioButton rdbtnNewRadioButton = new JRadioButton(solicitud.toString());
            //checkBox.setIcon(new ImageIcon(DevolverEntradas.class.getResource("imagen.png"))); // Ruta a tu icono de selección
            //checkBox.setSelectedIcon(new ImageIcon(DevolverEntradas.class.getResource("chequeado.png"))); // Ruta a tu icono de selección seleccionado
            
            //checkBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            //listPanel.add(rdbtnNewRadioButton);
        }
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        panel.add(scrollPane, BorderLayout.CENTER);
        
        JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("New radio item");
        scrollPane.setViewportView(rdbtnmntmNewRadioItem);
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Sadsa");
        rdbtnmntmNewRadioItem.add(rdbtnNewRadioButton);
        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        LinkedList<Integer> ids= new LinkedList<Integer>();
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		boolean codigo=false;
        		SolicitudDevolucion elegida= null;
        		Component[] componentes = listPanel.getComponents();
                for (Component componente : componentes) {
                    if (componente instanceof JRadioButton) {
                    	JRadioButton checkBox = (JRadioButton) componente;
                        if (checkBox.isSelected()) {
                        	for (SolicitudDevolucion solicitud : solicitudes) {
								if(solicitud.toString().equals(checkBox.getText())) {
									elegida=solicitud;
								}
							}
                            // Realiza la lógica correspondiente para el checkbox seleccionado
                            //System.out.println("Checkbox seleccionado: " + elegidas);
                            //ids.add(Integer.parseInt(Character.toString(checkBox.getText().charAt(0))));
                            //aber+=Character.toString(checkBox.getText().charAt(0))+",";
                            
                        }
                    }
                }
               /*if(aber.length()>0) {
                	//VentanaError.run("No has seleccionado entradas");
                	//dispose();
            	   aber=aber.substring(0, aber.length() - 1);
                	//cliente.SolicitudDeDevolucion();
                }*/
                if(elegida!=null) {
                		dispose();
                		Devolviendo.run(elegidas,dni);
                } else {
                	VentanaError.run("No se han seleccionado entradas");
                }
                //System.out.println(aber);
                
               // Devolviendo.run(ids);
        	}
        });
        buttonPanel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Volver");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        		cliente.SolicitudDeDevolucion();
        	}
        });
        buttonPanel.add(btnNewButton_1);

        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("");
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("  Seleccionar Devolución:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel_2);
        
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel);
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(0, 0, 312, 21);
        panel_2.add(textField);
        textField.setColumns(10);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(255, 255, 255));
        btnBuscar.setBounds(309, 0, 177, 20);
        panel_2.add(btnBuscar);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar solo pendientes");
        chckbxNewCheckBox.setSelected(true);
        chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(chckbxNewCheckBox.isSelected()) {
        			dispose();
        			VerSolicitudes.run(dni, true);
        		} else {
        			dispose();
        			VerSolicitudes.run(dni, false);
        		}
        	}
        });
        if (checkbox) {
        	chckbxNewCheckBox.setSelected(true);
        }
        JLabel relleno= new JLabel("<html><br></html>");
        panel_1.add(chckbxNewCheckBox);
        panel_1.add(relleno);
        
        setVisible(true);
    }

}

class ModernScrollBarUI extends BasicScrollBarUI {
    private final Dimension d = new Dimension();

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(new Color(240, 240, 240));
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(new Color(200, 200, 200));
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }

    @Override
    protected void configureScrollBarColors() {
        LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground");
        thumbHighlightColor = UIManager.getColor("ScrollBar.thumbHighlight");
        thumbLightShadowColor = new ColorUIResource(new Color(200, 200, 200));
        thumbDarkShadowColor = new ColorUIResource(new Color(200, 200, 200));
        thumbColor = UIManager.getColor("ScrollBar.thumb");
        trackColor = UIManager.getColor("ScrollBar.track");
        trackHighlightColor = UIManager.getColor("ScrollBar.trackHighlight");
    }
}

