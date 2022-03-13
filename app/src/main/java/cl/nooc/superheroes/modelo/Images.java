package cl.nooc.superheroes.modelo;

import com.google.gson.annotations.SerializedName;

public class Images{

	@SerializedName("md")
	private String md;

	@SerializedName("sm")
	private String sm;

	@SerializedName("xs")
	private String xs;

	@SerializedName("lg")
	private String lg;

	public String getMd(){
		return md;
	}

	public String getSm(){
		return sm;
	}

	public String getXs(){
		return xs;
	}

	public String getLg(){
		return lg;
	}

	@Override
 	public String toString(){
		return 
			"Images{" + 
			"md = '" + md + '\'' + 
			",sm = '" + sm + '\'' + 
			",xs = '" + xs + '\'' + 
			",lg = '" + lg + '\'' + 
			"}";
		}
}