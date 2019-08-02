package modelo.Vo;

public class InventarioNuevoVo {
	private Integer id;
	private Integer idInventarioNuevo;
	private String codigo;
	private String familia;
	private String grupo;
	private String subgrupo;
	private String descripcion;
	private Double cantidadInicial;
	private Double cantidadNueva;
	private Double cantidadAjustada;
	private Double costoUnidad;
	private boolean creado;
	
	
	public boolean isCreado() {
		return creado;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

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
