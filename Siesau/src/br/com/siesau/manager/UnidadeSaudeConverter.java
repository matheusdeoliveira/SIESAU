package br.com.siesau.manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.siesau.entity.SituacaoAtend;
import br.com.siesau.entity.UnidadeSaude;
import br.com.siesau.persistence.UnidadeSaudeDao;

@SuppressWarnings("rawtypes")
@FacesConverter(forClass = UnidadeSaude.class)
public class UnidadeSaudeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		UnidadeSaudeDao dao = new UnidadeSaudeDao(new UnidadeSaude());
		return dao.findByCode(Integer.parseInt(key));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object unidadeSaudeOb) {
		if(unidadeSaudeOb != null && unidadeSaudeOb instanceof SituacaoAtend){
			UnidadeSaude situacaoAtend = (UnidadeSaude) unidadeSaudeOb;
			
			return String.valueOf(situacaoAtend.getCdUnidsaude());
		}
		return "";
	}

}
