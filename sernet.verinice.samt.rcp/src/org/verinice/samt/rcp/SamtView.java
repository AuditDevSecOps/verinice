/*******************************************************************************
 * Copyright (c) 2009 Daniel Murygin <dm[at]sernet[dot]de>.
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU Lesser General Public License for more details.
 *     You should have received a copy of the GNU Lesser General Public 
 * License along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Daniel <dm[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package org.verinice.samt.rcp;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;

import sernet.verinice.iso27k.rcp.ISMView;
import sernet.verinice.iso27k.rcp.JobScheduler;
import sernet.verinice.iso27k.rcp.action.HideEmptyFilter;

/**
 * 
 * 
 * @author Daniel Murygin <dm[at]sernet[dot]de> // TODO dm: Externalize Strings
 */
public class SamtView extends ISMView {

    private static final Logger LOG = Logger.getLogger(SamtView.class);

    /**
     * The ID of the view as specified by the extension.
     */
    public static final String ID = "org.verinice.samt.rcp.views.SamtView";

    /**
     * The constructor.
     */
    public SamtView() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see sernet.verinice.iso27k.rcp.ISMView#initData()
     */
    @Override
    protected void initData() {
        super.initData();
        WorkspaceJob initDataJob = new WorkspaceJob("Expanding Self Assessment Tree") {
            @Override
            public IStatus runInWorkspace(final IProgressMonitor monitor) {
                IStatus status = Status.OK_STATUS;
                try {
                    monitor.beginTask("Expanding Self Assessment Tree", IProgressMonitor.UNKNOWN);
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            expandAll();
                        }
                    });
                } catch (Exception e) {
                    LOG.error("Error while expanding tree", e); //$NON-NLS-1$
                    status = new Status(IStatus.ERROR, "sernet.gs.ui.rcp.main", "Error while expanding tree", e);
                } finally {
                    monitor.done();
                }
                return status;
            }
        };
        JobScheduler.scheduleInitJob(initDataJob);
    }

    /*
     * (non-Javadoc)
     * 
     * @see sernet.verinice.iso27k.rcp.ISMView#createHideEmptyFilter()
     */
    @Override
    protected HideEmptyFilter createHideEmptyFilter() {
        HideEmptyFilter filter = new HideEmptyFilter(viewer);
        filter.setHideEmpty(true);
        return filter;
    }

}