package modelo;
/**
 * Enum con todos los valores de los documentales
 * @author Esteban Martínez
 * @author Sergio Fernández
 * @author Jose Manuel de Dios
 * @since 01/05/2020
 * @version 1.0
 */
public enum GeneroDocumental {
	
	NOTICIARIO("Noticiario"),
	HISTORICO("Histórico"),
	DOCUDRAMA("Docudrama"),
	FOTODOCUMENTAL("Fotodocumental"),
	NATURALEZA("Naturaleza"),
	BELICO("Bélico");
	
	private final String descripcion;
	
	GeneroDocumental (String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}