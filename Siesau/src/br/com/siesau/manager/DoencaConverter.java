package br.com.siesau.manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.siesau.entity.Doenca;
import br.com.siesau.persistence.DoencaDao;

@SuppressWarnings("rawtypes")
@FacesConverter("doencaConverter")
public class DoencaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		DoencaDao dao = new DoencaDao(new Doenca());
		return dao.findByCode(Integer.parseInt(key));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object DoencaOb) {
		if (DoencaOb != null && DoencaOb instanceof Doenca) {
			Doenca doenca = (Doenca) DoencaOb;

			return String.valueOf(doenca.getCdDoenca());
		}
		return "";
	}

}
