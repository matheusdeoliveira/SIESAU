package br.com.siesau.manager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.siesau.entity.Especialidade;
import br.com.siesau.persistence.EspecialidadeDao;

@SuppressWarnings("rawtypes")
@FacesConverter("funcionarioConverter")
public class FuncionarioConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		EspecialidadeDao dao = new EspecialidadeDao(new Especialidade());
		return dao.findByCode(Integer.parseInt(value));
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object especialidadeOb) {
		if (especialidadeOb != null && especialidadeOb instanceof Especialidade) {
			Especialidade especialidade = (Especialidade) especialidadeOb;

			return String.valueOf(especialidade.getCdEspec());
		}
		return "";
	}
}
