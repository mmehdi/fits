package uk.ac.abdn.fits.mvc.control.operator.map.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapJsonString {
	
	private int zoom;
	private int tilt;

	private String mapTypeId;
	private LatLon center;
	private Overlay[] overlays;
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	public int getTilt() {
		return tilt;
	}
	public void setTilt(int tilt) {
		this.tilt = tilt;
	}
	public String getMapTypeId() {
		return mapTypeId;
	}
	public void setMapTypeId(String mapTypeId) {
		this.mapTypeId = mapTypeId;
	}
	public LatLon getCenter() {
		return center;
	}
	public void setCenter(LatLon center) {
		this.center = center;
	}
	public Overlay[] getOverlays() {
		return overlays;
	}
	public void setOverlays(Overlay[] overlays) {
		this.overlays = overlays;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("zoom: ");
		sb.append(zoom);
		sb.append(" tilt: ");
		sb.append(tilt);
		sb.append(" mapTypeId: ");
		sb.append( mapTypeId);
		sb.append(" center: ");
		sb.append(center.toString());
		sb.append(" overlays: [");
		for(int i=0; i<overlays.length;i++){
			sb.append(overlays[i].toString());
			if(i!=overlays.length-1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
