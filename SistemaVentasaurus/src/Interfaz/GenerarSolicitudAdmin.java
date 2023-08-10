package Interfaz;

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

import java.sql.Connection;
import java.sql.PreparedStatement;

import Datos.Conexion;
import Datos.Entrada;
import Interfaz.MenuAdmin;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class GenerarSolicitudAdmin extends JFrame {
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	MenuAdmin admin= new MenuAdmin();
	private JTextField txtCdigoDeDevolucin;
	
	public static void run(int id, boolean checkbox) {
		try {
			GenerarSolicitudAdmin frame = new GenerarSolicitudAdmin(id, checkbox);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public GenerarSolicitudAdmin(int dni, boolean checkbox) {
        setTitle("Selección de entradas"+dni);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
		setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        List<String> opciones = new ArrayList<>();
        // Crear una lista de opciones
        String sql="";
        if (checkbox) {
        	sql="SELECT id,localidad,precio,concierto,c_devolucion FROM `vw_entrada` WHERE id_cliente in (SELECT id FROM cliente WHERE dni=?) AND id IN (SELECT id_entrada FROM detalle_devolucion WHERE detalle_devolucion.id_devolucion NOT IN (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='aprobada'));";
        } else {
        	sql="SELECT id,localidad,precio,concierto,c_devolucion FROM `vw_entrada` WHERE id not in (SELECT id_entrada FROM detalle_devolucion) AND id_cliente in (SELECT id FROM cliente WHERE dni=?)AND c_devolucion>0;";
        }String[] datos = new String[5]; 
		LinkedList<Entrada> entradas =new LinkedList<Entrada>();
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, dni);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				datos[0]= result.getString(1); //id
				datos[1]= result.getString(2); //localidad
				datos[2]= result.getString(3); //precio
				datos[3]= result.getString(4); //concierto
				datos[4]= result.getString(5); //codigo
				entradas.add(new Entrada(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],datos[4]));
				opciones.add(entradas.getLast().toString());
			}
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
		}
        // Crear un panel para la lista de opciones con scroll
		
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JLabel relleno= new JLabel("<html><br></html>");
        
        if (entradas.isEmpty()) {
        	
			JLabel error= new JLabel("   El cliente con dni "+dni+" no posee entradas aptas para devolver");
	        error.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        error.setHorizontalAlignment(SwingConstants.LEFT);
	        
	        listPanel.add(error);
			//VentanaError.run("Este dni no tiene entradas disponibles para devolver");
			//cliente.SolicitudDeDevolucion();
			//dispose();
		}
        for (String opcion : opciones) {
            JCheckBox checkBox = new JCheckBox(opcion);
            checkBox.setFocusPainted(false);
            checkBox.setForeground(new Color(50, 50, 50));
            //checkBox.setIcon(new ImageIcon(DevolverEntradas.class.getResource("imagen.png"))); // Ruta a tu icono de selección
            //checkBox.setSelectedIcon(new ImageIcon(DevolverEntradas.class.getResource("chequeado.png"))); // Ruta a tu icono de selección seleccionado
            checkBox.setBackground(new Color(240, 240, 240));
            if (checkbox && Integer.parseInt(entradas.get(opciones.indexOf(opcion)).getCodigoDevolucion()) <0) {
            	checkBox.setEnabled(false);
            }
            //checkBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            listPanel.add(checkBox);
        }
        

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        panel.add(scrollPane, BorderLayout.CENTER);
        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        LinkedList<Integer> ids= new LinkedList<Integer>();
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		boolean codigo=false;
        		LinkedList<Entrada> elegidas = new LinkedList<Entrada>();
        		Component[] componentes = listPanel.getComponents();
                for (Component componente : componentes) {
                    if (componente instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) componente;
                        if (checkBox.isSelected()) {
                        	for (Entrada entrada : entradas) {
								if(entrada.toString().equals(checkBox.getText())) {
									elegidas.add(entrada);
								} if (txtCdigoDeDevolucin.getText().equals(entrada.getCodigoDevolucion())) {
									codigo=true;
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
                if(!elegidas.isEmpty()) {
                	if (!codigo) {
                		VentanaError.run("Codigo incorrecto");
                	} else {	
                		dispose();
                		DevolviendoAdmin.run(elegidas,dni);
                	}
                } else {
                	VentanaError.run("No se han seleccionado entradas");
                }
                //System.out.println(aber);
                
               // Devolviendo.run(ids);
        	}
        });
        buttonPanel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Volver");
        btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        		MenuAdmin.principal();
        	}
        });
        buttonPanel.add(btnNewButton_1);

        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("");
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("  Selección de entradas a devolver:");
        lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel_2);
        
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel);
        
        txtCdigoDeDevolucin = new JTextField();
        txtCdigoDeDevolucin.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        txtCdigoDeDevolucin.setText("  Código de devolución");
        panel_1.add(txtCdigoDeDevolucin);
        txtCdigoDeDevolucin.setColumns(10);
        JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar no disponibles");
        chckbxNewCheckBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(chckbxNewCheckBox.isSelected()) {
        			dispose();
        			GenerarSolicitudAdmin.run(dni, true);
        		} else {
        			dispose();
        			GenerarSolicitudAdmin.run(dni, false);
        		}
        	}
        });
        if (checkbox) {
        	chckbxNewCheckBox.setSelected(true);
        }
        
        panel_1.add(chckbxNewCheckBox);
        panel_1.add(relleno);
        setVisible(true);
    }

}
