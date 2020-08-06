package ec.edu.monster.controlador;

import ec.edu.monster.modelo.PeperPerson;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.facades.PeperPersonFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.PF;

@Named("peperPersonController")
@SessionScoped
public class PeperPersonController implements Serializable {

    private String route = "/peperPerson/List.xhtml";
    private PeperPerson current;
    private DataModel items = null;
    private int createRequest = 0;
    @EJB
    private ec.edu.monster.facades.PeperPersonFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PeperPersonController() {

    }

    /**
     Cuando se crea el bean se inicializa la "Lista"
     */
    @PostConstruct
    public void init() {
        prepareList();
        getItems();
    }

    public PeperPerson getSelected() {
        if (current == null) {
            current = new PeperPerson();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PeperPersonFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    /**
     Esto no se usa, en vez de esto usar el método init()
     */
    public String prepareList() {
        recreateModel();
        return "List";
    }

    /*
        NO llamar desde la vista
    */
    public Boolean prepareView() {
        current = (PeperPerson) getItems().getRowData();
        
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        
        return true;
    }

    //Copiar en otros controladores !!!!!!!!
    // Este método reemplaza al prepareView
    public String setViewIndex(Object t) {
        
        getItems().setRowIndex((int) t);
        prepareView();
        return "";
    }
    //Este método reemplaza al prepareEdit
    public String setEditIndex(Object t) {
        
        getItems().setRowIndex((int) t);
        prepareEdit();
        return "";
    }
    
    public void prepareCreate() {
        current = new PeperPerson();
        selectedItemIndex = -1;
        createRequest = 0;
    }

    public void create() {
        try {
            System.out.println("Create request " + createRequest);
            if (createRequest != 0) {
                System.out.println("No reate");
                return;
            }

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeperPersonCreated"));
            current = null;
            createRequest++;
            recreateModel();
            getItems();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //return null;
        }
    }

    /*
        NO llamar desde la vista
    */
    public void prepareEdit() {
        current = (PeperPerson) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        //return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeperPersonUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PeperPerson) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeperPersonDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public PeperPerson getPeperPerson(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PeperPerson.class)
    public static class PeperPersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeperPersonController controller = (PeperPersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peperPersonController");
            return controller.getPeperPerson(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeperPerson) {
                PeperPerson o = (PeperPerson) object;
                return getStringKey(o.getPeperId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PeperPerson.class.getName());
            }
        }

    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

}
