package uk.ac.abdn.fits.mvc.control.operator.map.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Overlay {
	
	private String type;
	private String title;
	private String content;
	private LatLon[][] paths;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LatLon[][] getPaths() {
		return paths;
	}
	public void setPaths(LatLon[][] paths) {
		this.paths = paths;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("type: ");
		sb.append(type);
		sb.append(" title: ");
		sb.append(title);
		sb.append(" content: ");
		sb.append(content);
		sb.append(" paths: ");
		sb.append("[");
		for(int i=0; i< paths[0].length; i++){
			sb.append(paths[0][i].toString());
			if(i!=paths[0].length-1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
		
	}
}
