package Jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import Jframes.ModernScrollBarUI;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DevolverEntradas extends JFrame {
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
    public DevolverEntradas(int id) {
        setTitle("Selección de entradas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        List<String> opciones = new ArrayList<>();
        // Crear una lista de opciones
        String sql="SELECT id,localidad,precio,concierto,c_devolucion FROM `vw_entrada` WHERE id not in (SELECT id_entrada FROM detalle_devolucion) AND id_cliente=?";
        String[] datos = new String[5]; 
		LinkedList<Entrada> entradas =new LinkedList<Entrada>();
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, id);
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

        for (String opcion : opciones) {
            JCheckBox checkBox = new JCheckBox(opcion);
            checkBox.setFocusPainted(false);
            checkBox.setForeground(new Color(50, 50, 50));
            //checkBox.setIcon(new ImageIcon(DevolverEntradas.class.getResource("imagen.png"))); // Ruta a tu icono de selección
            //checkBox.setSelectedIcon(new ImageIcon(DevolverEntradas.class.getResource("chequeado.png"))); // Ruta a tu icono de selección seleccionado
            checkBox.setBackground(new Color(240, 240, 240));
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
        
        JButton btnNewButton = new JButton("New button");
        buttonPanel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("New button");
        buttonPanel.add(btnNewButton_1);

        getContentPane().add(panel);
        
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("");
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("  Selección de entradas a devolver:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
    	DevolverEntradas frame = new DevolverEntradas(2);
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

