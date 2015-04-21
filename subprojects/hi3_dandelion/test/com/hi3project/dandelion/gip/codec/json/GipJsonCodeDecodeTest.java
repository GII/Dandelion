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

import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.dandelion.util.properties.PropertyType;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Gervasio Varela
 */
public class GipJsonCodeDecodeTest
{
    
    private JSONGIPCodec jsonCodec;
    
    public GipJsonCodeDecodeTest()
    {
        jsonCodec = new JSONGIPCodec();
    }
    

    @BeforeClass
    public static void setUpClass()
    {
    }
    

    @AfterClass
    public static void tearDownClass()
    {
    }
    

    @Before
    public void setUp()
    {
    }
    

    @After
    public void tearDown()
    {
    }


    @Test
    public void testCodeActionEvent()
    {
        
        Event actionEvent = new Event("1", "1", EventType.action);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("relevance", "low", 4));
        ihs.add(new FuzzyVariable("color", "red" ,10));
        actionEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(actionEvent);
            String expected = "{\"sourceId\":\"1\",\"interactionId\":\"1\",\"type\":\"action\",\"interactionHints\":[{\"name\":\"relevance\",\"label\":\"low\",\"value\":4.0},{\"name\":\"color\",\"label\":\"red\",\"value\":10.0}]}";
            assertEquals(expected, codedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
                
    }
    
    
    @Test
    public void testCodeFocusEvent()
    {
        
        Event actionEvent = new Event("1", "1", EventType.focus);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("importance", "medium" , 7));
        actionEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(actionEvent);
            assertEquals("{\"sourceId\":\"1\",\"interactionId\":\"1\",\"type\":\"focus\",\"interactionHints\":[{\"name\":\"importance\",\"label\":\"medium\",\"value\":7.0}]}", codedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
                
    }
    
    
    @Test
    public void testCodeInputEvent()
    {
        
        ArrayList<Property> propertySet = new ArrayList<Property>();
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2"));
        
        DataEvent dataEvent = new DataEvent("1", "1", EventType.input, propertySet);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("relevance", "low", 3));
        dataEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(dataEvent);
            assertEquals("{\"sourceId\":\"1\",\"interactionId\":\"1\",\"type\":\"input\",\"interactionHints\":[{\"name\":\"relevance\",\"label\":\"low\",\"value\":3.0}],\"propertySet\":[{\"label\":\"NUMBER_PROPERTY\",\"type\":\"numberProperty\",\"value\":\"2\"}]}", codedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
                
    }
    
    
    @Test
    public void testCodeOutputEvent()
    {
        
        ArrayList<Property> propertySet = new ArrayList<Property>();
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2"));
        propertySet.add(new Property(PropertyType.booleanProperty, "BOOL_PROPERTY", "true"));
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2.78"));
        
        DataEvent dataEvent = new DataEvent("3", "1", EventType.output, propertySet);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("size", "small", 3));
        ihs.add(new FuzzyVariable("relevance", "low", 3));
        dataEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(dataEvent);
            String expected = "{\"sourceId\":\"3\",\"interactionId\":\"1\",\"type\":\"output\",\"interactionHints\":[{\"name\":\"size\",\"label\":\"small\",\"value\":3.0},{\"name\":\"relevance\",\"label\":\"low\",\"value\":3.0}],\"propertySet\":[{\"label\":\"NUMBER_PROPERTY\",\"type\":\"numberProperty\",\"value\":\"2\"},{\"label\":\"BOOL_PROPERTY\",\"type\":\"booleanProperty\",\"value\":\"true\"},{\"label\":\"NUMBER_PROPERTY\",\"type\":\"numberProperty\",\"value\":\"2.78\"}]}";
            assertEquals(expected, codedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
                
    }
        
    
    @Test
    public void testCodeSelectionEvent()
    {
        
        ArrayList<Property> availableItems = new ArrayList<Property>();
        availableItems.add(new Property(PropertyType.stringProperty, "1", "1"));
        availableItems.add(new Property(PropertyType.stringProperty, "2", "2"));
        availableItems.add(new Property(PropertyType.stringProperty, "3", "3"));
        
        Property selectedItem = new Property(PropertyType.stringProperty, "1", "1");
        
        SelectionEvent selectEvent = new SelectionEvent("2", "1", availableItems, selectedItem);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("size", "big", 33));
        selectEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(selectEvent);
            String expected = "{\"sourceId\":\"2\",\"interactionId\":\"1\",\"type\":\"selection\",\"interactionHints\":[{\"name\":\"size\",\"label\":\"big\",\"value\":33.0}],\"availableItems\":[{\"label\":\"1\",\"type\":\"stringProperty\",\"value\":\"1\"},{\"label\":\"2\",\"type\":\"stringProperty\",\"value\":\"2\"},{\"label\":\"3\",\"type\":\"stringProperty\",\"value\":\"3\"}],\"selectedItem\":{\"label\":\"1\",\"type\":\"stringProperty\",\"value\":\"1\"}}";
            assertEquals(expected, codedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
                
    }
    
    
    @Test
    public void testDecodeActionEvent()
    {
        
        Event actionEvent = new Event("1", "1", EventType.action);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("relevance", "low", 4));
        ihs.add(new FuzzyVariable("color", "red", 10));
        actionEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(actionEvent);
            Event decodedEvent = this.jsonCodec.decodeGIPEvent(codedEvent);
            assertEquals(actionEvent, decodedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            ex.printStackTrace();
            fail();            
        }
        
    }
    
    
    @Test
    public void testDecodeFocusEvent()
    {
        
        Event focusEvent = new Event("2","1", EventType.focus);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("color", "black", 9));
        focusEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(focusEvent);
            Event decodedEvent = this.jsonCodec.decodeGIPEvent(codedEvent);
            assertEquals(focusEvent, decodedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            ex.printStackTrace();
            fail();            
        }
        
    }
    
    
    @Test
    public void testDecodeInputEvent()
    {
        
        ArrayList<Property> propertySet = new ArrayList<Property>();
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2"));
        
        DataEvent dataEvent = new DataEvent("1", "1", EventType.input, propertySet);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("relevance", "low", 3));
        dataEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(dataEvent);
            Event decodedEvent = this.jsonCodec.decodeGIPEvent(codedEvent);
            assertEquals(dataEvent, decodedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
        
    }
    
    
    @Test
    public void testDecodeOutputEvent()
    {
        
        ArrayList<Property> propertySet = new ArrayList<Property>();
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2"));
        propertySet.add(new Property(PropertyType.numberProperty, "NUMBER_PROPERTY", "2.78"));
        
        DataEvent dataEvent = new DataEvent("5", "1", EventType.output, propertySet);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("color", "yellow", 3));
        dataEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(dataEvent);
            Event decodedEvent = this.jsonCodec.decodeGIPEvent(codedEvent);
            assertEquals(decodedEvent, dataEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
        
    }
    
    
    @Test
    public void testDecodeSelectionEvent()
    {
        
        ArrayList<Property> availableItems = new ArrayList<Property>();
        availableItems.add(new Property(PropertyType.stringProperty, "1", "1"));
        availableItems.add(new Property(PropertyType.stringProperty, "3", "3"));
        
        Property selectedItem = new Property(PropertyType.stringProperty, "1", "1");
        
        SelectionEvent selectEvent = new SelectionEvent("2", "1", availableItems, selectedItem);
        ArrayList<FuzzyVariable> ihs = new ArrayList<FuzzyVariable>(2);        
        ihs.add(new FuzzyVariable("size", "medium", 5));
        selectEvent.setFuzzyHints(ihs);
        
        try {
            String codedEvent = this.jsonCodec.codeGIPEvent(selectEvent);
            Event decodedEvent = this.jsonCodec.decodeGIPEvent(codedEvent);
            assertEquals(selectEvent, decodedEvent);
        } catch (GIPEventCodeDecodeErrorException ex) {
            fail();
        }
        
    }
    
    
    
}
