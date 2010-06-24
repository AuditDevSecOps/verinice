/*******************************************************************************
 * Copyright (c) 2010 Alexander Koderman <ak@sernet.de>.
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *     You should have received a copy of the GNU General Public 
 * License along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Alexander Koderman <ak@sernet.de> - initial API and implementation
 ******************************************************************************/
package sernet.verinice.iso27k.service;

import org.apache.log4j.Logger;

import sernet.hui.common.VeriniceContext;
import sernet.hui.common.connect.HUITypeFactory;
import sernet.hui.common.connect.Property;
import sernet.hui.common.connect.PropertyType;
import sernet.verinice.model.common.CnATreeElement;
import sernet.verinice.model.iso27k.ControlGroup;
import sernet.verinice.model.iso27k.IControl;

/**
 * 
 * Calculate maturity values and weights for controls and control groups.
 * 
 * @author koderman@sernet.de
 * @version $Rev$ $LastChangedDate$ 
 * $LastChangedBy$
 *
 */
public class ControlMaturityService {
    private static final Logger LOG = Logger.getLogger(ControlMaturityService.class);
    
    /**
     * Calculate accumulated maturity times weight of each control contained in this group.
     * @return the calculated maturity times the weights for each control
     */
    public Integer getWeightedMaturity(ControlGroup cg) {
        int maturity = 0;
        for (CnATreeElement child : cg.getChildren()) {
            if (child instanceof IControl) {
                IControl control = (IControl) child;
                maturity += getWeightedMaturity(control);
            }
            if (child instanceof ControlGroup) {
                ControlGroup control = (ControlGroup) child;
                maturity += getWeightedMaturity(control);
            }
        }
        return maturity;
    }
    
    public Double getMaturityByWeight(ControlGroup cg) {
        double result =0;
        if (getWeights(cg) != 0)
            result = ((double)getWeightedMaturity(cg)) / ((double)getWeights(cg));
        return result;
    }

    /**
     * Return sum of all weights of all controls contained in this group and subgroups.
     * @return combined weight
     */
    public Integer getWeights(ControlGroup cg) {
        int weight = 0;
        for (CnATreeElement child : cg.getChildren()) {
            if (child instanceof IControl) {
                IControl control = (IControl) child;
                weight += control.getWeight2();
            }
            if (child instanceof ControlGroup) {
                ControlGroup control = (ControlGroup) child;
                weight += getWeights(control);
            }
        }
        return weight;
    }
    
    /**
     * @return
     */
    public Integer getWeightedMaturity(IControl contr) {
        int value = contr.getMaturity() * contr.getWeight2();
        return value;
    }
    
    public Double getMaturityByWeight(IControl contr) {
        double result = ((double)getWeightedMaturity(contr)) / ((double)contr.getWeight2());
        return result;
    }

    /**
     * @param group
     * @return
     */
    public Double getMaxMaturityValue(ControlGroup group) {
        Double result = Double.valueOf(0);
        for (CnATreeElement child : group.getChildren()) {
            if (child instanceof IControl) {
                Double maturity = getMaxMaturityValue((IControl) child);
                if(maturity > result) {
                    result = maturity;
                }
            }
            if (child instanceof ControlGroup) {
                Double maturity = getMaxMaturityValue((ControlGroup) child);
                if(maturity > result) {
                    result = maturity;
                }
            }
        }
        return result;
    }
    
    private Double getMaxMaturityValue(IControl control) {
        HUITypeFactory hui = (HUITypeFactory) VeriniceContext.get(VeriniceContext.HUI_TYPE_FACTORY);
        PropertyType propertyType = hui.getPropertyType(control.getTypeId(), control.getMaturityPropertyId());
        return Double.valueOf(propertyType.getMaxValue());
    }
    
    /**
     * Returns the correct implementaiton state based on the maturity level of the <code>IControl.</code>
     * 
     * @param control
     * @return the implementation state as definied in the <code>IControl.IMPLEMENTED</code> constants.
     */
    public String getImplementationState(IControl control) {
        
        if (control.getMaturity() == Property.UNDEF) {
            return IControl.IMPLEMENTED_NOT_EDITED;
        }
        
        if (control.getMaturity() >= control.getThreshold2()) {
            return IControl.IMPLEMENTED_YES;
        }
        if (control.getMaturity() >= control.getThreshold1()) {
            return IControl.IMPLEMENTED_PARTLY;
        }
        return IControl.IMPLEMENTED_PARTLY;
    }
}


