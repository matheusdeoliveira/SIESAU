package br.com.siesau.manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.siesau.entity.Cargo;
import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.CargoDao;

@SuppressWarnings("rawtypes")
@FacesConverter(forClass = Cargo.class)
public class CargoCoverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		CargoDao dao = new CargoDao(new Cargo());
		return dao.findByCode(Integer.parseInt(key));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object CargoOb) {
		if(CargoOb != null && CargoOb instanceof SituacaoAtend){
			Cargo cargo = (Cargo) CargoOb;
			
			return String.valueOf(cargo.getCdCargo());
		}
		return "";
	}

}
