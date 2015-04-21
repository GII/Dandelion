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
 
 package com.hi3project.dandelion.application.history;

import com.hi3project.dandelion.models.user.UserProfile;
import java.util.Date;


/** Indicates that the application was adapted to the specified user during
 * the specified time period
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public class UserHistoryEvent extends HistoryEvent
{
    
    private final UserProfile user;

    
    public UserHistoryEvent(UserProfile user, Date startDate, Date endDate)
    {
        super(startDate, endDate);
        this.user = user;
    }

    public UserProfile getUser()
    {
        return user;
    }

    @Override
    public String toString()
    {
        
        StringBuilder sb = new StringBuilder("UH - ");
        
        sb.append(getStartDate().toString());
        sb.append(" | ");
        sb.append(getEndDate().toString());
        sb.append(" | ");
        sb.append(getUser().getName());
        
        return sb.toString();
        
    }

    @Override
    public HistoryEventType getType()
    {
        return HistoryEventType.user;
    }
    
    

}
