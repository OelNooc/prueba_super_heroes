package cl.nooc.superheroes.modelo;

import com.google.gson.annotations.SerializedName;

public class Work{

	@SerializedName("occupation")
	private String occupation;

	@SerializedName("base")
	private String base;

	public String getOccupation(){
		return occupation;
	}

	public String getBase(){
		return base;
	}

	@Override
 	public String toString(){
		return 
			"Work{" + 
			"occupation = '" + occupation + '\'' + 
			",base = '" + base + '\'' + 
			"}";
		}
}