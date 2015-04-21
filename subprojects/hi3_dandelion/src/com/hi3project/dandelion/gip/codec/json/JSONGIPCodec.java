/*******************************************************************************
 *   
 *   Copyright (C) 2015 Mytech Ingenieria Aplicada <http://www.mytechia.com>
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
 
 package com.hi3project.dandelion.gip.codec.json;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.util.properties.ImageProperty;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.dandelion.util.properties.PropertyType;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.codec.binary.Base64;


/** Code/decode GIP Events using the JSON format.
 *
 * 
 * Example FOCUS event in JSON:
 * 
 * {
 *      id : 2,
 *      type : focus,
 *      interactionHints : 
 *          [{label : "priorty", value : 0.5},
 *          {label : "importance", value : 0.7}]
 * }
 * 
 * 
 * 
 * Example OUTPUT event in JSON:
 * 
 * {
 *      id : 6,
 *      type : output,
 *      interactionHints : 
 *          [{label : "importance", value : 0.7}]
 *      propertySet :
 *          [{label : "title", type : "string", value : "Titulo"},
 *           {label : "message", type : "string", value : "Mensaje"},
 *           {label : "checkbox", type : "boolean", value : "True"},
 *           {label : "image", type : "image/jpeg", image : "base64data"}]
 * }
 * 
 * 
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1

 Changelog:
      16-oct-2013 -- Initial version
 */
public class JSONGIPCodec implements IGIPCodec
{

    private static final String SRC_ID_GIP_EVENT = "sourceId";
    private static final String INTERACTION_ID_GIP_EVENT = "interactionId";
    private static final String TYPE_GIP_EVENT = "type";
    private static final String IH_GIP_EVENT = "interactionHints";
    private static final String PS_GIP_EVENT = "propertySet";    
    private static final String IH_NAME = "name";
    private static final String IH_LABEL = "label";
    private static final String IH_VALUE = "value";
    private static final String AVAILABLE_ITEMS_GIP_EVENT = "availableItems";
    private static final String SELECTED_ITEM_GIP_EVENT = "selectedItem";
    
    

    @Override
    public String codeGIPEvent(Event event) throws GIPEventCodeDecodeErrorException
    {
        
        JsonObject jsonGipEvent = new JsonObject(); 
        
        jsonGipEvent.addProperty(SRC_ID_GIP_EVENT, event.getSourceId());
        jsonGipEvent.addProperty(INTERACTION_ID_GIP_EVENT, event.getInteractionId());
        jsonGipEvent.addProperty(TYPE_GIP_EVENT, event.getType().toString());
        jsonGipEvent.add(IH_GIP_EVENT, codeInteractionHints(event.getFuzzyHints()));
        
        codeConcreteGIPEvent(jsonGipEvent, event);
        
        return jsonGipEvent.toString();
        
    }
    
    
    private void codeConcreteGIPEvent(JsonObject jsonGipEvent, Event event) throws GIPEventCodeDecodeErrorException
    {
        
        switch(event.getType()) {
            case action:                
                break;
            case focus:
                break;
            case input:
                codeDataEvent(jsonGipEvent, event);
                break;
            case output:
                codeDataEvent(jsonGipEvent, event);
                break;
            case selection:
                codeSelectionEvent(jsonGipEvent, event);
                break;
        }
        
    }


    @Override
    public Event decodeGIPEvent(String data) throws GIPEventCodeDecodeErrorException
    {
        
        try {
            
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonGipEvent = jsonParser.parse(data).getAsJsonObject();
            
            JsonElement idValue = jsonGipEvent.get(SRC_ID_GIP_EVENT);
            JsonElement dstValue = jsonGipEvent.get(INTERACTION_ID_GIP_EVENT);
            JsonElement typeValue = jsonGipEvent.get(TYPE_GIP_EVENT);
            JsonElement interactionHintsValue = jsonGipEvent.get(IH_GIP_EVENT);
            
            
            String eventId = decodeId(idValue);
            String dstId = decodeId(dstValue);
            EventType eventType = decodeType(typeValue);
            
            if (eventId != null && eventType != null) {
                
                ArrayList<FuzzyVariable> interactionHints = decodeInteractionHints(interactionHintsValue);
                
                return decodeConcreteGIPEvent(eventId, dstId,  eventType, interactionHints, jsonGipEvent);
                
            }
            else {
                throw new GIPEventCodeDecodeErrorException(data, "Unknown event id or type.");
            }
            
        }
        catch(JsonParseException ex) {
            throw new GIPEventCodeDecodeErrorException(data, "Parse error: "+ex.getLocalizedMessage());
        }
        
    }
    
    
    
    private Event decodeConcreteGIPEvent(
            String id, String dstId, EventType type, ArrayList<FuzzyVariable> interactionHints, JsonObject jsonGipEvent) 
            throws GIPEventCodeDecodeErrorException
    {

        switch(type) {
            case action:
                return createEvent(id, dstId, type, interactionHints);
            case focus:
                return createEvent(id, dstId, type, interactionHints);
            case input:
                return createDataEvent(id, dstId, type, interactionHints, jsonGipEvent);
            case output:
                return createDataEvent(id, dstId, type, interactionHints, jsonGipEvent);
            case selection:
                return createSelectionEvent(id, dstId, type, interactionHints, jsonGipEvent);
            default:
                throw new GIPEventCodeDecodeErrorException(jsonGipEvent.toString(), "Unknown event type");
        }
        
    }
    
    
    private Event createEvent(String id, String dstId, EventType type, ArrayList<FuzzyVariable> interactionHints)
    {
        Event event = new Event(id, dstId, type);
        event.setFuzzyHints(interactionHints);
        return event;
    }
    
    
    private Event createDataEvent(
            String id, String dstId, EventType type, ArrayList<FuzzyVariable> interactionHints, JsonObject jsonGipEvent)
            throws GIPEventCodeDecodeErrorException
    {
        
        JsonElement jsonPS = jsonGipEvent.get(PS_GIP_EVENT);
        
        ArrayList<Property> propertySet = decodePropertySet(jsonPS);
        
        if (propertySet != null)
            return new DataEvent(id, dstId, type, propertySet);
        else 
            throw new GIPEventCodeDecodeErrorException(jsonGipEvent.toString(), "Error parsing Property Set section.");
        
    }
    
    
    private void codeDataEvent(JsonObject jsonGipEvent, Event event) throws GIPEventCodeDecodeErrorException
    {
        
        if (event instanceof DataEvent) {
            
            DataEvent dataEvent = (DataEvent) event;
            JsonArray jsonPropertySet = codePropertySet(dataEvent.getPropertySet());
            
            jsonGipEvent.add(PS_GIP_EVENT, jsonPropertySet);
            
        }
        else {
            throw new GIPEventCodeDecodeErrorException(event, "GIP event coding error: Incompatible type.");
        }            
        
    }
    
    
    private void codeSelectionEvent(JsonObject jsonGipEvent, Event event) throws GIPEventCodeDecodeErrorException
    {
        
        if (event instanceof SelectionEvent) {
            
            SelectionEvent selectEvent = (SelectionEvent) event;
            JsonArray jsonAvailableItems = codePropertySet(selectEvent.getAvailableItems());
            JsonObject jsonSelectedItem = codeProperty(selectEvent.getSelectedItem());
            
            jsonGipEvent.add(AVAILABLE_ITEMS_GIP_EVENT, jsonAvailableItems);
            jsonGipEvent.add(SELECTED_ITEM_GIP_EVENT, jsonSelectedItem);
            
        }
        else {
            throw new GIPEventCodeDecodeErrorException(event, "GIP event coding error: Incompatible type.");
        }
        
        
    }
    
    
    private Event createSelectionEvent(
            String id, String dstId, EventType type, ArrayList<FuzzyVariable> interactionHints, JsonObject jsonGipEvent) 
            throws GIPEventCodeDecodeErrorException
    {

            JsonElement jsonAI = jsonGipEvent.get(AVAILABLE_ITEMS_GIP_EVENT);
            JsonElement jsonSI = jsonGipEvent.get(SELECTED_ITEM_GIP_EVENT);

            ArrayList<Property> availabeItems = decodePropertySet(jsonAI);
            Property selectedItem = decodeProperty(jsonSI.getAsJsonObject());

            return new SelectionEvent(id, dstId, availabeItems, selectedItem);
            
    }
    
    
    private String decodeId(JsonElement idValue) throws GIPEventCodeDecodeErrorException
    {
        try {
            return jsonValueAsString(idValue);
        }
        catch(UnsupportedOperationException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
    }
    
    
    private EventType decodeType(JsonElement typeValue) throws GIPEventCodeDecodeErrorException
    {
        try {
            
            String type = typeValue.getAsString();
            
            return EventType.valueOf(type);
            
        }
        catch(IllegalArgumentException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
        catch(UnsupportedOperationException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
    }
    
    
    private ArrayList<FuzzyVariable> decodeInteractionHints(JsonElement interactionHints)
            throws GIPEventCodeDecodeErrorException
    {
        
        try {
            
            JsonArray ihsValue = interactionHints.getAsJsonArray();
            ArrayList<FuzzyVariable> interactionHintsResult = new ArrayList<FuzzyVariable>(ihsValue.size());
            
            JsonObject ihObject;
            String name, label, value;
            for(JsonElement ihv : ihsValue) {
                ihObject = ihv.getAsJsonObject();
                name = jsonValueAsString(ihObject.get(IH_NAME));
                label = jsonValueAsString(ihObject.get(IH_LABEL));
                value = jsonValueAsString(ihObject.get(IH_VALUE));
                interactionHintsResult.add(new FuzzyVariable(name, label, Double.valueOf(value)));
            }
            
            return interactionHintsResult;
            
        }
        catch(UnsupportedOperationException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
        
    }
    
    
    private JsonArray codeInteractionHints(Collection<FuzzyVariable> interactionHints)
    {
        
        JsonArray jsonInteractionHints = new JsonArray();
        
        for(FuzzyVariable fv : interactionHints) {
            JsonObject iHint = new JsonObject();
            iHint.addProperty(IH_NAME, fv.getName());
            iHint.addProperty(IH_LABEL, fv.getValueLabel());
            iHint.addProperty(IH_VALUE, fv.getValue());
            jsonInteractionHints.add(iHint);
        }
        
        return jsonInteractionHints;
        
    }
    
    
    private ArrayList<Property> decodePropertySet(JsonElement propertySet)
            throws GIPEventCodeDecodeErrorException
    {
        
        try {
            
            JsonArray psValues = propertySet.getAsJsonArray();
            ArrayList<Property> propertySetResult = new ArrayList<Property>(psValues.size());
            
            for(JsonElement psV : psValues) {
                propertySetResult.add(decodeProperty(psV.getAsJsonObject()));
            }
            
            return propertySetResult;
            
        }
        catch(IllegalArgumentException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
        catch(UnsupportedOperationException ex) {
            throw new GIPEventCodeDecodeErrorException(ex);
        }
        
    }
    
    
    private Property decodeProperty(JsonObject jsonProperty)
            throws UnsupportedOperationException, GIPEventCodeDecodeErrorException
    {

        String label = jsonValueAsString(jsonProperty.get(IH_LABEL));
        String type = jsonValueAsString(jsonProperty.get(TYPE_GIP_EVENT));
        String value = jsonValueAsString(jsonProperty.get(IH_VALUE));
        if (type.equals("imageProperty")) {
            return new ImageProperty(label, value);
        }
        else {
            return new Property(PropertyType.valueOf(type), label, value);
        }
        
    }
    
    
    private JsonArray codePropertySet(Collection<Property> propertySet)
    {
        
        JsonArray jsonPropertySet = new JsonArray();
        
        for(Property p : propertySet) {
            jsonPropertySet.add(codeProperty(p));
        }
        
        return jsonPropertySet;
        
    }
    
    
    private JsonObject codeProperty(Property property)
    {
        
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(IH_LABEL, property.getLabel());
        jsonProperty.addProperty(TYPE_GIP_EVENT, property.getType().name());
        jsonProperty.addProperty(IH_VALUE, property.getValue());
        return jsonProperty;
        
    }
    
    
    private String jsonValueAsString(JsonElement value) throws GIPEventCodeDecodeErrorException
    {
        
        JsonPrimitive primitive = value.getAsJsonPrimitive();
        
        if (primitive.isString()) {
            return primitive.getAsString();
        }
        else if (primitive.isBoolean()) {
            return (Boolean.toString(primitive.getAsBoolean()));
        }
        else if (primitive.isNumber()) {
            return (Double.toString(primitive.getAsDouble()));
        }
        else {
            throw new GIPEventCodeDecodeErrorException("", "Unable to decode JsonValue.");
        }
        
    }

    
    
}
