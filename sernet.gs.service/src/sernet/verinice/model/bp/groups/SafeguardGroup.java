/*******************************************************************************
 * Copyright (c) 2017 Sebastian Hagedorn.
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
 *     Sebastian Hagedorn sh[at]sernet.de - initial API and implementation
 ******************************************************************************/
package sernet.verinice.model.bp.groups;

import sernet.verinice.model.bp.IBpGroup;
import sernet.verinice.model.bp.elements.Safeguard;
import sernet.verinice.model.common.CnATreeElement;
import sernet.verinice.model.iso27k.Group;

/**
 * 
 * @author Sebastian Hagedorn sh[at]sernet.de
 */
public class SafeguardGroup extends Group<Safeguard> implements IBpGroup {
    
    private static final long serialVersionUID = -6689926582876183791L;
    
    public static final String TYPE_ID = "bp_safeguard_group";
    
    private static final String PROP_DESC = "bp_safeguard_group_objectbrowser_content"; //$NON-NLS-1$
    
    private static final String PROP_NAME = "bp_safeguard_group_name"; //$NON-NLS-1$
    
    public static final String[] CHILD_TYPES = new String[] {Safeguard.TYPE_ID};
    
    protected SafeguardGroup() {}
    
    public SafeguardGroup(CnATreeElement parent) {
        super(parent);
        init();
    }

    @Override
    public String getTypeId() {
        return TYPE_ID;
    }
    
    @Override
    public String[] getChildTypes() {
        return CHILD_TYPES;
    }  
    
    @Override
    public String getTitle() {
        return getEntity().getPropertyValue(PROP_NAME);
    }
    
    @Override
    public void setTitel(String title) {
        getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_NAME), title);
    }

}
