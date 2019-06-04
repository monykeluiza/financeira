package br.com.financeira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
//        if (value instanceof EixoTecnologico) {
//        	EixoTecnologico entity= (EixoTecnologico) value;
//        	if (entity != null && entity.getId() != null) {
//               uiComponent.getAttributes().put( entity.getId().toString(), entity);
//        		 return entity.getId().toString();
//            }
//        }
        
        
        return "";
    }
}
