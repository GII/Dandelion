/*******************************************************************************
 *   
 *   Copyright (C) 2015 Gervasio Varela <gervarela@picandocodigo.com>
 * 
 *   This file is part of Dandelion.
 *
 *   Dandelion is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Dandelion is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with Dandelion.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/
 
package org.usixml.model.aui.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.AbstractUserInterfaceModel;
import org.usixml.model.aui.Composition;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.EnumDataType;
import org.usixml.model.aui.data.PresentationSupport;
import org.usixml.model.aui.event.EnumTriggerType;
import org.usixml.model.aui.event.EventSupport;
import org.usixml.model.aui.event.TriggerSupport;



/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 * 
 *
 * Changelog:
 *      13-jul-2012 -- Initial version
 */
public class AUIJaxbConverter 
{

    
    public AbstractUserInterfaceModel convert(org.usixml.model.aui.xml.jaxb.UserInterfaceModel xmlUiModel)
    {
        
        org.usixml.model.aui.xml.jaxb.AbstractUIModel xmlAbstractUIModel = xmlUiModel.getAbstractUIModel();

        List<org.usixml.model.aui.xml.jaxb.AbstractInteractionUnit> xmlAuiUnits = xmlAbstractUIModel.getAbstractInteractionUnit();
        
        return new AbstractUserInterfaceModel(xmlUiModel.getUseCase(), convertAIUList(xmlAuiUnits));        
        
    }
    
    
    public ArrayList<AbstractInteractionUnit> convertAIUList(List<org.usixml.model.aui.xml.jaxb.AbstractInteractionUnit> xmlAuiUnits)
    {
        
        ArrayList<AbstractInteractionUnit> iUnitsList = new ArrayList<AbstractInteractionUnit>();
        
        for(org.usixml.model.aui.xml.jaxb.AbstractInteractionUnit xmlAiu : xmlAuiUnits) {
            
            iUnitsList.add(convertAIU(xmlAiu));
            
        }
        
        iUnitsList.trimToSize();
        
        return iUnitsList;
        
    }
    
    
    public AbstractInteractionUnit convertAIU(org.usixml.model.aui.xml.jaxb.AbstractInteractionUnit xmlAiu)
    {
        
        Set<Composition> aiuCompositions = convertCompositions(xmlAiu.getComposition());
        Set<PresentationSupport> aiuPresentationSupport = 
                new HashSet<PresentationSupport>(convertDataInputOutputSupportList(xmlAiu.getDataInputOutputSupport()));
        Set<EventSupport> aiuEventSupport = 
                new HashSet<EventSupport>(convertTriggerSupportList(xmlAiu.getTriggerSupport()));
        
        return new AbstractInteractionUnit(
                xmlAiu.getId(),
                xmlAiu.getName(),
                xmlAiu.getRole(),
                xmlAiu.getImportanceLevel().intValue(),
                xmlAiu.getState(),
                xmlAiu.getFrequency().doubleValue(),
                xmlAiu.getRepetitionFactor().intValue(),
                xmlAiu.getDomainReference(),
                aiuCompositions,
                aiuPresentationSupport,
                aiuEventSupport);
        
    }
    
    
    public Set<Composition> convertCompositions(List<org.usixml.model.aui.xml.jaxb.Composition> xmlCompositionList)
    {
        
        HashSet<Composition> compositions = new HashSet<Composition>(xmlCompositionList.size());
        
        
        for(org.usixml.model.aui.xml.jaxb.Composition comp : xmlCompositionList) {
            compositions.add(
                new Composition(
                        comp.getRationale(), 
                        comp.getRole(), 
                            convertAIUList(comp.getAbstractInteractionUnit())));
        }
        
        return compositions;
        
    }
    
    
    public Set<DataInputOutputSupport> convertDataInputOutputSupportList(List<org.usixml.model.aui.xml.jaxb.DataInputOutputSupport> xmlDioList)
    {
        
        HashSet<DataInputOutputSupport> dioList = new HashSet<DataInputOutputSupport>(xmlDioList.size());
        
        for(org.usixml.model.aui.xml.jaxb.DataInputOutputSupport xmlDio : xmlDioList) {
            dioList.add(
                    new DataInputOutputSupport(
                            xmlDio.getId(),
                            xmlDio.getMinCardinality().intValue(),
                            xmlDio.getMaxCardinality().intValue(),
                            xmlDio.getDefaultValue(),
                            convertEnumDataType(xmlDio.getDataType()),
                            xmlDio.getDataLength().intValue(),
                            xmlDio.getDataFormat(),
                            xmlDio.getRange(),
                            xmlDio.isIsDynamic(),
                            xmlDio.getPatternMatching(),
                            xmlDio.isInputSupport(),
                            xmlDio.isOutputSupport()
            ));
        }
        
        return dioList;
        
    }
    
    
    public EnumDataType convertEnumDataType(org.usixml.model.aui.xml.jaxb.EnumDataType xmlType)
    {
        
        switch(xmlType) {
            case ARRAY:
                return EnumDataType.ARRAY;
            case BOOLEAN:
                return EnumDataType.BOOLEAN;
            case DATE:
                return EnumDataType.DATE;
            case HOUR:
                return EnumDataType.HOUR;
            case INTEGER:
                return EnumDataType.INTEGER;
            case MEDIA_AUDIO:
                return EnumDataType.MEDIA_AUDIO;
            case MEDIA_IMAGE:
                return EnumDataType.MEDIA_IMAGE;
            case MEDIA_VIDEO:
                return EnumDataType.MEDIA_VIDEO;
            case NATURAL:
                return EnumDataType.NATURAL;
            case REAL:
                return EnumDataType.REAL;
            case SECRET:
                return EnumDataType.SECRET;
            case TEXT:
                return EnumDataType.TEXT;
            default:
                return null;
        }
        
    }
    
    
    public Set<TriggerSupport> convertTriggerSupportList(List<org.usixml.model.aui.xml.jaxb.TriggerSupport> xmlEventList)
    {
        
        HashSet<TriggerSupport> eventList = new HashSet<TriggerSupport>(xmlEventList.size());

        for(org.usixml.model.aui.xml.jaxb.TriggerSupport xmlEvent : xmlEventList) {
            
            eventList.add(
                    new TriggerSupport(
                            xmlEvent.getId(),
                            xmlEvent.getTriggerExpression(),
                            convertEnumTriggerType(xmlEvent.getTriggerType()),
                            xmlEvent.isRequiresConfirmation()!=null ? xmlEvent.isRequiresConfirmation() : false)
                    );
            
        }
        
        return eventList;
        
    }
    
    
    public EnumTriggerType convertEnumTriggerType(org.usixml.model.aui.xml.jaxb.EnumTriggerType xmlType)
    {
        
        switch(xmlType) {
            case NAVIGATION:
                return EnumTriggerType.NAVITAGION;
            case OPERATION:
                return EnumTriggerType.OPERATION;
            case OPERATION_NAVIGATION:
                return EnumTriggerType.OPERATION_NAVITAGION;
            default:
                return null;
        }
        
    }
    
}
