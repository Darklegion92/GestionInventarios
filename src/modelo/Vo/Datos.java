package modelo.Vo;

public class Datos {

	private String codigo;
	private String descripcion;
	private Double invInicial;
	private Double invNuevo;
	private Double ajusteTotal;
	private Double costoUnidad;
	private Double total;
	private Boolean creado;
	private String familia;
	private String grupo;
	private String subgrupo;

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getInvInicial() {
		return invInicial;
	}

	public Double getInvNuevo() {
		return invNuevo;
	}

	public Double getAjusteTotal() {
		return ajusteTotal;
	}

	public Double getCostoUnidad() {
		return costoUnidad;
	}

	public Double getTotal() {
		return total;
	}

	public Boolean getCreado() {
		return creado;
	}

	public String getFamilia() {
		return familia;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setInvInicial(Double invInicial) {
		this.invInicial = invInicial;
	}

	public void setInvNuevo(Double invNuevo) {
		this.invNuevo = invNuevo;
	}

	public void setAjusteTotal(Double ajusteTotal) {
		this.ajusteTotal = ajusteTotal;
	}

	public void setCostoUnidad(Double costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCreado(Boolean creado) {
		this.creado = creado;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

}
