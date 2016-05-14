package br.com.siesau.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
  
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Circle;
  
@ManagedBean
public class CirclesView implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 8590417733990901767L;
	private MapModel circleModel;
      
    @PostConstruct
    public void init() {
         
        circleModel = new DefaultMapModel();
  
        //Shared coordinates
        LatLng coord1 = new LatLng(-22.7714425, -43.5150466);
        LatLng coord2 = new LatLng(-22.757659,-43.465831);
        LatLng coord3 = new LatLng(-22.763722,-43.520133);
        LatLng coord4 = new LatLng(-22.7587873, -43.45788599999999);
 
        //Circle
        Circle circle1 = new Circle(coord1, 500);
        circle1.setStrokeColor("#d93c3c");
        circle1.setFillColor("#d93c3c");
        circle1.setFillOpacity(0.5);
 
        Circle circle2 = new Circle(coord4, 500);
        circle2.setStrokeColor("#00ff00");
        circle2.setFillColor("#00ff00");
        circle2.setStrokeOpacity(0.7);
        circle2.setFillOpacity(0.7);
        
        Circle circle3 = new Circle(coord2, 500);
        circle3.setStrokeColor("#d93c3c");
        circle3.setFillColor("#d93c3c");
        circle3.setFillOpacity(0.5);
 
        
        Circle circle4 = new Circle(coord3, 500);
        circle4.setStrokeColor("#00ff00");
        circle4.setFillColor("#00ff00");
        circle4.setFillOpacity(0.9);
 
 
        circleModel.addOverlay(circle1);
        circleModel.addOverlay(circle2);
        circleModel.addOverlay(circle3);
        circleModel.addOverlay(circle4);
    }
  
    public MapModel getCircleModel() {
        return circleModel;
    }
  
    public void onCircleSelect(OverlaySelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circle Selected", null));
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCircleModel(MapModel circleModel) {
		this.circleModel = circleModel;
	}
    
	
    
}