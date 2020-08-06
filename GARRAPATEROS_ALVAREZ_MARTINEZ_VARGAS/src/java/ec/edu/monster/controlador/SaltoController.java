package ec.edu.monster.controlador;

import ec.edu.monster.modelo.Salto;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.facades.SaltoFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("saltoController")
@SessionScoped
public class SaltoController implements Serializable {

    private Salto current;
    private int createRequest = 0;
    private DataModel items = null;
    @EJB
    private ec.edu.monster.facades.SaltoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public SaltoController() {
    }
     @PostConstruct
    public void init() {
        prepareList();
        getItems();
    }

    public Salto getSelected() {
        if (current == null) {
            current = new Salto();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SaltoFacade getFacade() {
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public Boolean prepareView() {
        current = (Salto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return true;
    }

    public String prepareCreate() {
        current = new Salto();
        selectedItemIndex = -1;
        return "Create";
    }

     public void create() {
        try {
            System.out.println("Create request " + createRequest);
            if (createRequest != 0) {
                System.out.println("No reate");
                return;
            }

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SaltoCreated"));
            current = null;
            createRequest++;
            recreateModel();
            getItems();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //return null;
        }
    }

public void prepareEdit() {
        current = (Salto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        //return "Edit";
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SaltoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Salto) getItems().getRowData();
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

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SaltoDeleted"));
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

    public Salto getSalto(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Salto.class)
    public static class SaltoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SaltoController controller = (SaltoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "saltoController");
            return controller.getSalto(getKey(value));
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
            if (object instanceof Salto) {
                Salto o = (Salto) object;
                return getStringKey(o.getIdSalto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Salto.class.getName());
            }
        }

    }

}
