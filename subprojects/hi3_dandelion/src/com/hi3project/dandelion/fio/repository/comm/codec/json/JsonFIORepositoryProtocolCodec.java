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
 
 package com.hi3project.dandelion.fio.repository.comm.codec.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType;
import static com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType.DeregisterFIOAction;
import static com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType.FIOEntryResponse;
import static com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType.FindFIOByIdAction;
import static com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.DeregisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.FindFIOByIdAction;
import com.hi3project.dandelion.fio.repository.comm.action.QueryFIOsByDistanceAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.response.FIOEntryResponse;
import com.hi3project.dandelion.fio.repository.comm.response.FIOQueryResponse;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCodec;
import com.hi3project.dandelion.util.requestresponse.RequestResponseMessage;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;


/** Provides cocrete code/decode techniques for transfering FIO repository 
 * messages over a network
 * 
 * 
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class JsonFIORepositoryProtocolCodec implements IRequestResponseCodec
{
    
    
    private final Gson gson;

    
    public JsonFIORepositoryProtocolCodec()
    {
        this.gson = new Gson();
    }
    

    

    @Override
    public String codeRequestResponseMessage(RequestResponseMessage msg) throws MessageCodeDecodeErrorException
    {
        
        FIORepositoryMessage fioRepoMsg = null;
        try {
            fioRepoMsg = (FIORepositoryMessage) msg;
        }
        catch(ClassCastException ex) {
            throw new MessageCodeDecodeErrorException();
        }
        
        switch(fioRepoMsg.getType()) {
            
            case FIOEntryResponse:
                return codeFIOEntryResponse(fioRepoMsg);
            case FIOQueryReponse:
                return codeFIOQueryResponse(fioRepoMsg);
            case DeregisterFIOAction:
                return codeDeregisterFIOAction(fioRepoMsg);
            case RegisterFIOAction:
                return codeRegisterFIOAction(fioRepoMsg);
            case FindFIOByIdAction:
                return codeFindFIOByIdAction(fioRepoMsg);
            case QueryFIOByDistanceAction:
                return codeQueryFIOsByDistanceAction(fioRepoMsg);
            default:
                throw new MessageCodeDecodeErrorException("Unsupported message type.");
                
        }
        
    }

    @Override
    public RequestResponseMessage decodeRequestResponseMessage(String data) throws MessageCodeDecodeErrorException
    {
        
        JsonParser jsonParser = new JsonParser();
        
        JsonElement msgJson = jsonParser.parse(data);
        
        JsonPrimitive typeJson = msgJson.getAsJsonObject().getAsJsonPrimitive("type");
        
        String typeStr = typeJson.getAsString();
        
        if (typeStr.equals(FIORepositoryMessageType.FIOEntryResponse.toString())) {
            return decodeFIOEntryResponse(msgJson);
        }
        else if (typeStr.equals(FIORepositoryMessageType.DeregisterFIOAction.toString())) {
            return decodeDeregisterFIOAction(msgJson);
        }
        else if (typeStr.equals(FIORepositoryMessageType.RegisterFIOAction.toString())) {
            return decodeRegisterFIOAction(msgJson);
        }
        else if (typeStr.equals(FIORepositoryMessageType.FindFIOByIdAction.toString())) {
            return decodeFindFIOByIdAction(msgJson);
        }
        else if (typeStr.equals(FIORepositoryMessageType.QueryFIOByDistanceAction.toString())) {
            return decodeQueryFIOsByDistanceAction(msgJson);
        }
        else if (typeStr.equals(FIORepositoryMessageType.FIOQueryReponse.toString())) {
            return decodeFIOQueryResponse(msgJson);
        }
        else {
            throw new MessageCodeDecodeErrorException("Unsupoprted message type.");
        }
        
    }
    
    
    
    private String codeFIOEntryResponse(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((FIOEntryResponse) msg);
        
    }
    
    private String codeFIOQueryResponse(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((FIOQueryResponse) msg);
        
    }
    
    private FIOEntryResponse decodeFIOEntryResponse(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, FIOEntryResponse.class);
    }
    
    
    private String codeDeregisterFIOAction(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((DeregisterFIOAction) msg);
        
    }
    
    private DeregisterFIOAction decodeDeregisterFIOAction(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, DeregisterFIOAction.class);
    }
    
    
    private String codeRegisterFIOAction(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((RegisterFIOAction) msg);
        
    }
    
    private RegisterFIOAction decodeRegisterFIOAction(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, RegisterFIOAction.class);
    }
    
    
    private String codeFindFIOByIdAction(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((FindFIOByIdAction) msg);
        
    }
    
    private FindFIOByIdAction decodeFindFIOByIdAction(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, FindFIOByIdAction.class);
    }
    
    
    private String codeQueryFIOsByDistanceAction(FIORepositoryMessage msg)
    {
        
        return this.gson.toJson((QueryFIOsByDistanceAction) msg);
        
    }
    
    private QueryFIOsByDistanceAction decodeQueryFIOsByDistanceAction(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, QueryFIOsByDistanceAction.class);
    }
    
    
    private FIOQueryResponse decodeFIOQueryResponse(JsonElement msgJson)
    {
        return this.gson.fromJson(msgJson, FIOQueryResponse.class);
    }
    

}
