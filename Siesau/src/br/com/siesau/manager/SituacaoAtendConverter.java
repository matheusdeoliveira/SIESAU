package br.com.siesau.manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.persistence.SituacaoAtendDao;

@SuppressWarnings("rawtypes")
@FacesConverter(forClass = SituacaoAtend.class)
public class SituacaoAtendConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		SituacaoAtendDao dao = new SituacaoAtendDao(new SituacaoAtend());
		return dao.findByCode(Integer.parseInt(key));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object SituacaoAtendOb) {
		if(SituacaoAtendOb != null && SituacaoAtendOb instanceof SituacaoAtend){
			SituacaoAtend situacaoAtend = (SituacaoAtend) SituacaoAtendOb;
			
			return String.valueOf(situacaoAtend.getCdSitatend());
		}
		return "";
	}

}

