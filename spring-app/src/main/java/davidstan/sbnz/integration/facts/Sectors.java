package davidstan.sbnz.integration.facts;

import java.util.LinkedList;
import java.util.List;

import davidstan.sbnz.integration.models.*;

public class Sectors {

	private Sector sector;
	private double std;
	private double volume;
	private double close;
	
	private List<Profiles> attributes;
	
	public Sectors(Sector sector, double std, double volume, double close) {
		this.sector = sector;
		this.std = std;
		this.volume = volume;
		this.close = close;
		
		this.attributes = new LinkedList<>();
	}
	
	

	public List<Profiles> getAttributes() {
		return attributes;
	}



	public void setAttributes(List<Profiles> attributes) {
		this.attributes = attributes;
	}



	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}
	
	
}
