package modelo.Vo;

public class InventarioNuevoVo {
	private Integer id;
	private Integer idInventarioNuevo;
	private String codigo;
	private String descripcion;
	private Double cantidadInicial;
	private Double cantidadNueva;
	private Double cantidadAjustada;
	private Double costoUnidad;

	public Double getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(Double cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public Double getCantidadNueva() {
		return cantidadNueva;
	}

	public void setCantidadNueva(Double cantidadNueva) {
		this.cantidadNueva = cantidadNueva;
	}

	public Double getCantidadAjustada() {
		return cantidadAjustada;
	}

	public void setCantidadAjustada(Double cantidadAjustada) {
		this.cantidadAjustada = cantidadAjustada;
	}

	public Double getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(Double costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdInventarioNuevo() {
		return idInventarioNuevo;
	}

	public void setIdInventarioNuevo(Integer idInventarioNuevo) {
		this.idInventarioNuevo = idInventarioNuevo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
