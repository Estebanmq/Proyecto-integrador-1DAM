package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaBaja;
/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de películas y del acceso a la base de datos para consultar y eliminar películas
 * @author Esteban Martínez
 * @since 16/05/2020
 * @version 1.0
 */
public class CtrlPeliculaBaja implements ActionListener{
	
	/**
	 * Ventana DialogoBajaPelicula 
	 */
	private DialogoPeliculaBaja dialogoBajaPelicula;
	
	/**
	 * Atributo para trabajar con los datos de peliculas
	 */
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	/**
	 * Método constructor del control para dar de baja películas
	 */
	public CtrlPeliculaBaja() {
		
		//Creación de la ventana de baja de películas
		dialogoBajaPelicula = new DialogoPeliculaBaja();
		
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoBajaPelicula.getBtnBuscar().addActionListener(this);
		dialogoBajaPelicula.getBtnEliminar().addActionListener(this);
		
//		try {
//			
//		} catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
		dialogoBajaPelicula.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de baja de películas
	 * @param ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoPeliculaMantenimiento daoPeliculaMantenimiento = new DaoPeliculaMantenimiento();
		switch (e.getActionCommand()) {
		case "btnBuscar":
			int cod = Integer.parseInt(dialogoBajaPelicula.getFieldCodPeli().getText());
			try {
				Pelicula p = new Pelicula(daoPeliculaMantenimiento.buscarPeli(cod));
				System.out.format("%s\n", p.toString());
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnEliminar":
			System.out.format("%s\n", "Boton de eliminar");
			break;
		case "btnAceptar":
			System.out.format("%s\n", "Boton de aceptar");
			break;
		case "btnCancelar":
			dialogoBajaPelicula.dispose();
			break;
		}
		
	}
	
	public DialogoPeliculaBaja getDialogoBajaPelicula() {
		return dialogoBajaPelicula;
	}

	public void setDialogoAltaPelicula(DialogoPeliculaBaja dialogoBajaPelicula) {
		this.dialogoBajaPelicula = dialogoBajaPelicula;
	}


}
