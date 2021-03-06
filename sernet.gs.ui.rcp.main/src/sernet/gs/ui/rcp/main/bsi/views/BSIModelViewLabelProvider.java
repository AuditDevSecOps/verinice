/*******************************************************************************
 * Copyright (c) 2009 Alexander Koderman <ak[at]sernet[dot]de>.
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
 *     Alexander Koderman <ak[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package sernet.gs.ui.rcp.main.bsi.views;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import sernet.gs.ui.rcp.main.ImageCache;
import sernet.verinice.model.bsi.BausteinUmsetzung;
import sernet.verinice.model.bsi.IBSIStrukturElement;
import sernet.verinice.model.bsi.LinkKategorie;
import sernet.verinice.model.common.CnALink;
import sernet.verinice.model.common.CnATreeElement;

/**
 * Label provider fpr BSI model elements.
 * 
 * @author koderman[at]sernet[dot]de
 * 
 */
public class BSIModelViewLabelProvider extends LabelProvider {

    private static final Logger LOG = Logger.getLogger(BSIModelViewLabelProvider.class);
    
    public BSIModelViewLabelProvider() {
        super();
    }


    @Override
    public Image getImage(Object obj) {
        if(obj instanceof CnATreeElement) {
            Image image = CnAImageProvider.getCustomImage((CnATreeElement)obj);
            if(image!=null) {
                return image;
            }
        }
        
        if (obj instanceof BausteinUmsetzung) {         
            return ImageCache.getInstance().getImage(ImageCache.BAUSTEIN_UMSETZUNG);
        }

        else if (obj instanceof LinkKategorie) {
            return ImageCache.getInstance().getImage(ImageCache.LINKS);
        }

        else if (obj instanceof CnALink) {
            CnALink link = (CnALink) obj;
            return CnAImageProvider.getImage(link.getDependency());
        }

        CnATreeElement el = (CnATreeElement) obj;
        return CnAImageProvider.getImage(el);
    }

    @Override
    public String getText(Object obj) {
        try {
            if (obj == null) {
                return "<null>";
            }
    
            if (obj instanceof IBSIStrukturElement) {
                IBSIStrukturElement el = (IBSIStrukturElement) obj;
                CnATreeElement el2 = (CnATreeElement) obj;
                StringBuilder title = new StringBuilder();
                if (el.getKuerzel() != null && el.getKuerzel().length() > 0) {
                    title.append(el.getKuerzel()).append(" ");
                }
                title.append(el2.getTitle());
                if (LOG.isDebugEnabled()) {
                    appendIds(title, el2);
                }
                return title.toString();
            }
    
            else if (obj instanceof LinkKategorie) {
                return ((LinkKategorie) obj).getTitle();
            } else if (obj instanceof CnALink) {
                CnALink link = (CnALink) obj;
                return link.getTitle();
            }
    
            CnATreeElement el = (CnATreeElement) obj;
            StringBuilder title = new StringBuilder(el.getTitle());
            if (LOG.isDebugEnabled()) {
                appendIds(title, el);
            }
            return title.toString();
        } catch( Exception e ) {
            LOG.error("Error while getting label", e);
            return "";
        }
    }
    
    void appendIds(StringBuilder sb, CnATreeElement element) {
        sb.append(" (scope: ").append(element.getScopeId());
        sb.append(", uu: ").append(element.getUuid());
        sb.append(", ext: ").append(element.getExtId()).append(")");
    }

}
