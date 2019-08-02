package modelo.Vo;

public class InventarioInicialVo {

	private Integer id;
	private Integer idInventarioInicial;
	private String codigo;
	private String barras;
	private String descripcion;
	private Double cantidad;
	private Double costo;
	
	
	public Integer getIdInventarioInicial() {
		return idInventarioInicial;
	}

	public void setIdInventarioInicial(Integer idInventarioInicial) {
		this.idInventarioInicial = idInventarioInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getBarras() {
		return barras;
	}

	public void setBarras(String barras) {
		this.barras = barras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

}
