package cl.nooc.superheroes.modelo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SuperRespuesta{

	@SerializedName("SuperRespuesta")
	private List<SuperRespuestaItem> superRespuesta;

	public List<SuperRespuestaItem> getSuperRespuesta(){
		return superRespuesta;
	}

	public void setSuperRespuesta(List<SuperRespuestaItem> superRespuesta) {
		this.superRespuesta = superRespuesta;
	}

	@Override
 	public String toString(){
		return 
			"SuperRespuesta{" + 
			"superRespuesta = '" + superRespuesta + '\'' + 
			"}";
		}
}