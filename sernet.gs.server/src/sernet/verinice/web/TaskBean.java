/*******************************************************************************
 * Copyright (c) 2010 Daniel Murygin.
 *
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Daniel Murygin <dm[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package sernet.verinice.web;

import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.richfaces.component.html.HtmlExtendedDataTable;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

import sernet.gs.web.Util;
import sernet.hui.common.VeriniceContext;
import sernet.verinice.interfaces.bpm.ITask;
import sernet.verinice.interfaces.bpm.ITaskService;
import sernet.verinice.model.iso27k.Control;

/**
 * JSF managed bean for view and edit Tasks, template: todo/task.xhtml
 * 
 * @author Daniel Murygin <dm[at]sernet[dot]de>
 */
public class TaskBean {

    private static final Logger LOG = Logger.getLogger(TaskBean.class);
    
    public static final String BOUNDLE_NAME = "sernet.verinice.web.TaskMessages";

    private EditBean editBean;
    
    List<ITask> taskList;
    
    ITask selectedTask;
    
    private HtmlExtendedDataTable table;
    
    private Selection selection = new SimpleSelection();
    
    /**
     * @return
     */
    public List<ITask> loadTasks() {   
        return taskList = getTaskService().getTaskList();
    }
    
    public void openTask() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("openTask() called ...");
        }
        try {
            Iterator<Object> iterator = getSelection().getKeys();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                table.setRowKey(key);
                if (table.isRowAvailable()) {
                    setSelectedTask( (ITask) table.getRowData());
                }
                getEditBean().setUuid(getSelectedTask().getControlUuid());
                getEditBean().setTitle(getSelectedTask().getControlTitle());
                getEditBean().setTypeId(Control.TYPE_ID);
                getEditBean().init();
            }
        } catch (Throwable t) {
            LOG.error("Error while opening task", t);
        }
    }
    
    public EditBean getEditBean() {
        return editBean;
    }

    public void setEditBean(EditBean editBean) {
        this.editBean = editBean;
    }

    public List<ITask> getTaskList() {
        if(this.taskList==null) {
            this.taskList = loadTasks();
        }
        return taskList;
    }

    public void setTaskList(List<ITask> taskList) {     
        this.taskList = taskList;
    }

    public ITask getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(ITask selectedTask) {
        this.selectedTask = selectedTask;
    }

    public HtmlExtendedDataTable getTable() {
        return table;
    }

    public void setTable(HtmlExtendedDataTable table) {
        this.table = table;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
    
    private ITaskService getTaskService() {
        return (ITaskService) VeriniceContext.get(VeriniceContext.TASK_SERVICE);
    }
    
    public void english() {
        Util.english();
    }
    
    public void german() {
        Util.german();
    }
    
}