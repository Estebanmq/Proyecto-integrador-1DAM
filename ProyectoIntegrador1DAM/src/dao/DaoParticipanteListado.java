package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.FiltroParticipanteListado;
import modelo.ListaParticipante;
import modelo.Pais;
/**
 * Clase que obtiene los participantes para mostrar en pantalla
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 */
public class DaoParticipanteListado {
	
	/**
	 * Query a ejecutar 
	 */
	private String query;

	/**
	 * Conexión a la BBDD
	 * @see java.sql.Connection
	 */
	private Connection conn;
	
	/**
	 * PreparedStatement para ejecutar comandos SQL ya precompilados
	 * @see java.sql.PreparedStatement
	 */
	private PreparedStatement ps;
	
	/**
	 * ResultSet para almacenar el resultado de la sentencia SQL
	 * @see java.sql.ResultSet
	 */
	private ResultSet rs;
	
	/**
	 * Método constructor al que se le pasa la conexión a utilizar en la instancia
	 * 
	 * @param conn Conexión con la base de datos a utilizar 
	 */
	public DaoParticipanteListado(Connection conn) {
		this.setConn(conn);
	}

	/**
	 * Método que obtiene de BD la lista de participantes
	 * 
	 * @return Colección de ListaParticipante
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public ArrayList<ListaParticipante> obtenerListaParticipantes() throws ClassNotFoundException, SQLException {
		
		ListaParticipante listaParticipante = new ListaParticipante();
		ArrayList<ListaParticipante> arrayList = new ArrayList<ListaParticipante>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
		// Monta la query a ejecutar
		this.setQuery("select CODIGO, NOMBRE, NACIONALIDAD from PARTICIPANTE order by CODIGO");

		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaParticipante = new ListaParticipante(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaParticipante);
		}
		
		return arrayList;
		
	}

	/**
	 * 
	 * Método que obtiene de BD la lista de participantes en función del filtro recibido por parámetros
	 * 
	 * @param filtro Filtro del listado de participantes
	 * @return Colección de ListaParticipante
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public ArrayList<ListaParticipante> obtenerListaParticipantes(FiltroParticipanteListado filtro) throws ClassNotFoundException, SQLException {
		
		ListaParticipante listaParticipante = new ListaParticipante();
		ArrayList<ListaParticipante> arrayList = new ArrayList<ListaParticipante>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
												// Monta la query a ejecutar
		if (filtro.isDirector() && filtro.isInterprete()) {
			this.setQuery("SELECT codigo, nombre, nacionalidad FROM participante");
		} else if (filtro.isDirector() && !filtro.isInterprete()) {
			this.setQuery("SELECT participante.codigo, participante.nombre, participante.nacionalidad FROM director INNER JOIN participante ON director.codigo = participante.codigo");
		} else {
			this.setQuery("SELECT participante.codigo, participante.nombre, participante.nacionalidad FROM interprete INNER JOIN participante ON interprete.codigo = participante.codigo");
		}
		query = query.concat(" WHERE TRUE");
			
		if (!filtro.getNombre().isEmpty()) {
			query = query.concat(" AND participante.nombre = '").concat(filtro.getNombre()).concat("'");			
		}
		if (filtro.getPais() != 0) {
			query = query.concat(" AND participante.nacionalidad = ").concat(filtro.getPais().toString());
		}
		if (!filtro.isSexoFemenino() || !filtro.isSexoMasculino()) {
			query = query.concat(" AND participante.sexo = ");
			if (filtro.isSexoFemenino()) {
				query = query.concat("'FEMENINO'");				
			} else {
				query = query.concat("'MASCULINO'");				
			}			
		}
		query = query.concat(" order by PARTICIPANTE.CODIGO");
		
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaParticipante = new ListaParticipante(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaParticipante);
		}
		
		return arrayList;
		
	}
	
	/**
	 * 
	 * Método que obtiene de BD la lista de países existentes en base de datos
	 * 
	 * @see HashMap
	 * @see Pais
	 * 
	 * @param conn Conexión
	 * @return Colección de países
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public HashMap<Integer, String> obtenerPaises(Connection conn) throws ClassNotFoundException, SQLException {
		
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>(); 
		
									// 	Instancia al DAO y obtiene el array de países
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		arrayPaises = daoPaisMantenimiento.obtenerListaPaises();
		
		for (Pais p : arrayPaises) {
			listaPaises.put(p.getCodigo(), p.getDescripcion());
		}

		return listaPaises;

	}

	// GETTERS & SETTERS
	private String getQuery() {
		return query;
	}

	private void setQuery(String query) {
		this.query = query;
	}

	private Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
	}

	private PreparedStatement getPs() {
		return ps;
	}

	private void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	private ResultSet getRs() {
		return rs;
	}

	private void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
