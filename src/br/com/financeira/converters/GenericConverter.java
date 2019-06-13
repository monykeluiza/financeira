package br.com.financeira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.financeira.entities.TipoLembrete;
import br.com.financeira.entities.Usuario;

@FacesConverter("genericConverter")
public class GenericConverter implements Converter {
    
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Object) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Usuario) {
        	Usuario entity= (Usuario) value;
        	if (entity != null && entity.getId() != null) {
               uiComponent.getAttributes().put( entity.getId().toString(), entity);
        		 return entity.getId().toString();
            }
        }
        if (value instanceof TipoLembrete) {
        	TipoLembrete entity= (TipoLembrete) value;
        	if (entity != null && entity.getId() != null) {
               uiComponent.getAttributes().put( entity.getId().toString(), entity);
        		 return entity.getId().toString();
            }
        }
        return "";
    }
}
