package br.com.financeira.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addFatalMessage() {
    	String msg = "Ocorreu um erro ao processar a requisiçãoo. Por favor, tente recarregar a página. Caso o erro persista, contate o administrador do sistema";
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("Sucesso", facesMsg);
    }
    
    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage("Mensagem", facesMsg);
    }
    
    public static void addSuccessMessage(String msg, String fiedlId) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(fiedlId, facesMsg);  
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

//    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
//        String theId = JsfUtil.getRequestParameter(requestParameterName);
//        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
//    }
    
    public static void setListenerValidadao(Boolean validacao, String message){    	
        PrimeFaces context = PrimeFaces.current();
        
        if (!validacao){
        	JsfUtil.addErrorMessage(message);
        }
        context.ajax().addCallbackParam("validacao", validacao);  
    
    }
    
    public static String getIP(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String ip = request.getLocalAddr();  
	    return ip;
	}
    
    public static void closeModal(String idModal) {
    	PrimeFaces.current().executeScript("PF('" + idModal + "').hide()");
    }
    
    public static void showModal(String idModal) {
    	PrimeFaces.current().executeScript("PF('" + idModal + "').show()");
    }
    
    public static StreamedContent convertFichier(byte[] bytes, String fileName) {
	    InputStream is = new ByteArrayInputStream(bytes);
	    StreamedContent file = new DefaultStreamedContent(is, "application/pdf",fileName);
	    return file;
	}
    
}