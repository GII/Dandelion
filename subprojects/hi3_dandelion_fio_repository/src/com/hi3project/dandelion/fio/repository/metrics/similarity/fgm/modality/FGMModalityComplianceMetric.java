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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.modality;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicInteractionComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.FGMFuzzySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.exception.NotSameNumberOfAttributesException;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.Collection;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-feb-2015
 */
public class FGMModalityComplianceMetric
{
    
    
    private final JFuzzyLogicModalitySimilarity FA;
    private final JFuzzyLogicModalitySimilarity FB;
    

    
    public FGMModalityComplianceMetric() throws ErrorLoadingFISException
    {
        this.FA = JFuzzyLogicModalitySimilarity.instantiate();
        this.FB = JFuzzyLogicModalitySimilarity.instantiate();
    }
    
    
    
    public ModalitySimilarity calcSimilarity(ModalitySpecification fioSpecs, FIODescription fioDesc)
    {
        
        double modalitySimilarity = 0, similarity = 0;
        
        
        Collection<InteractionCapability> interactionCapabilities = 
                fioDesc.getInteractionCapabilities();
        for(InteractionCapability ic : interactionCapabilities) {
            modalitySimilarity = calcModalityAdequateness(fioSpecs, ic.getModality());
            if (modalitySimilarity > similarity) {
                similarity = modalitySimilarity;
            }
        }
              
        return new ModalitySimilarity(similarity);
        
    }
    
    
    public ModalityInteractionSimilarity calcSimilarity(
            ModalitySpecification fioSpecs, 
            InteractionSpecification interSpecs, 
            FIODescription fioDesc)
    {
        
        BasicInteractionComplianceMetric interactionMetric =
                new BasicInteractionComplianceMetric();
        
        ModalityInteractionSimilarity similarity = new ModalityInteractionSimilarity(
                new InteractionSimilarity(0), null, 0);
        double interactionSim, modalitySim;
        
        for(InteractionCapability ic : fioDesc.getInteractionCapabilities()) {
            //for each IC, we calc its similitude with interaction and modality specifications
            interactionSim = interactionMetric.calcInteractionCapabilitiySupport(ic, interSpecs.getInteractionSupport());
            modalitySim = calcModalityAdequateness(fioSpecs, ic.getModality());
            
            //we save the best combination of interaction+modality
            ModalityInteractionSimilarity newSimilariry = new ModalityInteractionSimilarity(
                    new InteractionSimilarity(interactionSim), ic, modalitySim);
            if (newSimilariry.getValue() > similarity.getValue()) {
                similarity = newSimilariry;
            }
            
        }
        
        return similarity;
        
    }
    
    
    
    private double calcModalityAdequateness(ModalitySpecification fioSpecs, Modality fioModality)
    {
    
        double adquateness = 0;
        
        for(Modality m : fioSpecs.getModalities()) {
            if (m.getType() == fioModality.getType()) {

                if (m.getGranularity() > fioModality.getGranularity()) {
                    //the scenario SUPPORT for the modalitiy is greater that the requirements of the FIO
                    adquateness = 1.0 - (fioModality.getGranularity() * 0.01); //simple heuristic to give a bonus to modalities "ease to use"
                    break;
                }
                else {
                    //the requirements are greater  that the support --> calc the fuzzy difference
                    FA.setGranularity(m.getGranularity());
                    Variable[] FAAttributes = new Variable[1];
                    FAAttributes[0] = FA.getGranularityVariable();

                    FB.setGranularity(fioModality.getGranularity());
                    Variable[] FBAttributes = new Variable[1];
                    FBAttributes[0] = FB.getGranularityVariable();

                    FGMFuzzySimilarity fuzzySimilarity = new FGMFuzzySimilarity(FAAttributes, FBAttributes);
                    try {
                        adquateness = fuzzySimilarity.similarityMinimum(new double[]{1});
                        adquateness -= ((fioModality.getGranularity() - m.getGranularity()) * 0.05); //simple heuristic to penalize complex modalities
                        if (adquateness <= 0.2) adquateness = 0.0; //below 0.2 --> modality is not supported for the scenario
                        break;
                    } catch (NotSameNumberOfAttributesException ex) {
                        adquateness = 0;
                    }
                }                
            }
        }
        
        return adquateness;
        
    }

}
