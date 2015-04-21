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
 
 package com.hi3project.dandelion.tools.specbuilder.gui;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.models.environment.Climate;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.environment.EnvironmentSituation;
import com.hi3project.dandelion.models.environment.EnvironmentType;
import com.hi3project.dandelion.models.environment.Lighting;
import com.hi3project.dandelion.models.environment.LightingType;
import com.hi3project.dandelion.models.environment.Motion;
import com.hi3project.dandelion.models.environment.Noise;
import com.hi3project.dandelion.models.environment.Space;
import com.hi3project.dandelion.models.environment.Visibility;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.scene.activity.AbilityRequisites;
import com.hi3project.dandelion.models.scene.activity.Activity;
import com.hi3project.dandelion.models.scene.activity.ActivityMode;
import com.hi3project.dandelion.models.scene.activity.ActivityStyle;
import com.hi3project.dandelion.models.scene.activity.ActivityType;
import com.hi3project.dandelion.models.user.CognitiveAbilities;
import com.hi3project.dandelion.models.user.ColourPerception;
import com.hi3project.dandelion.models.user.HearingAbilities;
import com.hi3project.dandelion.models.user.ICTAbilities;
import com.hi3project.dandelion.models.user.MotorAbilities;
import com.hi3project.dandelion.models.user.PhysicalProperties;
import com.hi3project.dandelion.models.user.Religion;
import com.hi3project.dandelion.models.user.Sex;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.models.user.VisionAbilities;
import com.hi3project.dandelion.uib.fio.specification.DefaultFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.IFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.DefaultFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.IFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.DefaultFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.IFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalShapeSelector;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalShapeSelector;
import com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalSpecificationsBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.DefaultFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.IFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.modality.selector.jfl.JFuzzyLogicModalitySelector;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.text.DecimalFormat;
import javax.swing.JTextField;


/**
 *
 * @author Gervasio Varela <gervasio.varela@udc.es>
 */
public class FIOSpecBuilderTool extends javax.swing.JFrame
{

    private IFIOSpecificationBuilder fioSpecBuilder = null;
    private DecimalFormat df = new DecimalFormat("#.###");
    
    
    
    /**
     * Creates new form FIOSpecBuilderTool
     */
    public FIOSpecBuilderTool()
    {
        initComponents();
        
        if (fioSpecBuilder == null) {
            this.fioSpecBuilder = instantiateFIOSpecBuilder();
            if (this.fioSpecBuilder == null) {
                System.exit(-1);
            }
        }
        
    }
    

    
    private final double asDouble(JTextField txt)
    {
        return Double.parseDouble(txt.getText());
    }
    
    private final int asInt(JTextField txt)
    {
        return Integer.parseInt(txt.getText());
    }
    
    private ColourPerception getColourPerception()
    {
        switch(userProfilePanel.comboColor.getSelectedIndex()) {
            case 0:
                return ColourPerception.FULL_COLOR;
            case 1:
                return ColourPerception.COLOR_BLIND;
            case 2:
                return ColourPerception.NONE;
            default:
                return ColourPerception.FULL_COLOR;
        }
    }
    
    
    private UserProfile createUserProfile()
    {
        
        PhysicalProperties physical = new PhysicalProperties(
                asDouble(userProfilePanel.txtHeight), 
                asDouble(userProfilePanel.txtWeight));
        
        VisionAbilities vision = new VisionAbilities(
                asDouble(userProfilePanel.txtAcuity),
                asDouble(userProfilePanel.txtField),
                getColourPerception());
        
        HearingAbilities hearing = new HearingAbilities(
                asDouble(userProfilePanel.txtHearing));
        
        CognitiveAbilities cognitive = new CognitiveAbilities(
                asDouble(userProfilePanel.txtLangRecept),
                asDouble(userProfilePanel.txtLangProd),
                asDouble(userProfilePanel.txtSigns),
                asDouble(userProfilePanel.txtAttention),
                asDouble(userProfilePanel.txtProcSpeed),
                asDouble(userProfilePanel.txtWorkMem),
                asDouble(userProfilePanel.txtLongMem));
        
        MotorAbilities motor = new MotorAbilities(
                asDouble(userProfilePanel.txtSpeechArtic),
                asDouble(userProfilePanel.txtFinger),
                asDouble(userProfilePanel.txtHand),
                asDouble(userProfilePanel.txtArm),
                asDouble(userProfilePanel.txtContact),
                asDouble(userProfilePanel.txtPinch),
                asDouble(userProfilePanel.txtClench),
                asDouble(userProfilePanel.txtHandEye));
        
        ICTAbilities ict = new ICTAbilities(
                asDouble(userProfilePanel.txtLiteracy),
                asDouble(userProfilePanel.txtAnxiety));
        
        return new UserProfile(
                userProfilePanel.txtName.getText(),
                asInt(userProfilePanel.txtAge),
                Sex.MALE,
                Religion.CATHOLIC,
                "spanish",
                "es",
                physical,
                vision, 
                hearing,
                cognitive,
                motor,
                ict);
                
    }
    
    
    
    private Lighting getLighting()
    {
        
        LightingType ltype = LightingType.WARMLIGHT;
        switch(environmentProfilePanel.comboLight.getSelectedIndex()) {
            case 0: ltype = LightingType.SUNLIGHT; break;
            case 1: ltype = LightingType.WARMLIGHT; break;
            case 2: ltype = LightingType.COLDLIGHT; break;
            case 3: ltype = LightingType.SHADOW; break;
            case 4: ltype = LightingType.NO_LIGHT; break;
        }
        
        return new Lighting(asDouble(environmentProfilePanel.txtLighting1), ltype);
        
    }
    
    private EnvironmentType createEnvironmentType()
    {
        
        switch(environmentProfilePanel.comboEnvType.getSelectedIndex()) {
            case 0: return EnvironmentType.mobile;
            case 1: return EnvironmentType.stationary;
            default: return EnvironmentType.stationary;
        }
        
    }
    
    private EnvironmentSituation createEnvironmentSituation()
    {
        
        switch(environmentProfilePanel.comboSituation.getSelectedIndex()) {
            case 0: return EnvironmentSituation.indoor;
            case 1: return EnvironmentSituation.outdoor;
            default: return EnvironmentSituation.indoor;
        }
        
    }
    
    
    
    private EnvironmentProfile createEnvironmentProfile()
    {
        
        Noise noise = new Noise(asDouble(environmentProfilePanel.txtNoise));
        
        Climate climante = new Climate(
            asDouble(environmentProfilePanel.txtTemp),
            asDouble(environmentProfilePanel.txtHumidity),
            asDouble(environmentProfilePanel.txtWind));
        
        Visibility visibility = new Visibility(
                getLighting(), 
                asDouble(environmentProfilePanel.txtVisibility1),
                asDouble(environmentProfilePanel.txtContrast1));
        
       Motion motion = new Motion(
               asDouble(environmentProfilePanel.txtMotion),
               asDouble(environmentProfilePanel.txtVibration));
       
       Space space = new Space(asDouble(environmentProfilePanel.txtSpace));
       
       
        
        return new EnvironmentProfile(
                environmentProfilePanel.txtName.getText(),
                environmentProfilePanel.txtLocation.getText(),
                noise,
                climante,
                visibility,
                motion, 
                space, 
                createEnvironmentType(),
                createEnvironmentSituation());
        
    }
    
    
    private ActivityType createActivityType()
    {
        switch(sceneProfilePanel.comboActivityType.getSelectedIndex()) {
            case 0: return ActivityType.work;
            case 1: return ActivityType.daily;
            case 2: return ActivityType.fitness;
            case 3: return ActivityType.leisure;
            default: return ActivityType.work;
        }
    }
    
    private ActivityStyle createActivityStyle()
    {
        switch(sceneProfilePanel.comboActivityStyle.getSelectedIndex()) {
            case 0: return ActivityStyle.individual;
            case 1: return ActivityStyle.social;
            default: return ActivityStyle.individual;
        }
    }
    
    private ActivityMode createActivityMode()
    {
        switch(sceneProfilePanel.comboActivityMode.getSelectedIndex()) {
            case 0: return ActivityMode.on_the_go;
            case 1: return ActivityMode.stationary;
            default: return ActivityMode.stationary;
        }
    }
    
    private SceneProfile createSceneProfile()
    {
        
        Activity activity = new Activity(
            sceneProfilePanel.txtName.getText(),
            createActivityStyle(),
            createActivityType(),
            createActivityMode(), 
            new AbilityRequisites(0, 0, 0)); //TODO: Add this fields to the GUI
        
        return new SceneProfile(
                activity, 
                asInt(sceneProfilePanel.txtUserCount));
        
    }
    
    
    private IFIOSpecificationBuilder instantiateFIOSpecBuilder()
    {
        try {
            
            JFuzzyLogicModalitySelector fuzzyModalitySelector = new JFuzzyLogicModalitySelector();
            fuzzyModalitySelector.init();
            IFIOModalitySpecificationBuilder modalitySpecBuilder = new DefaultFIOModalitySpecificationBuilder(fuzzyModalitySelector);
            
            IFIOPhysicalShapeSelector shapeSelector = JFuzzyLogicFIOPhysicalShapeSelector.instantiate();
            IFIOPhysicalSpecificationBuilder physicalSpecBuilder = JFuzzyLogicFIOPhysicalSpecificationsBuilder.instantiate(shapeSelector);
            
            IFIOInteractionSpecificationBuilder interactionSpecbuilder = new DefaultFIOInteractionSpecificationBuilder();
            IFIOUsageSpecificationBuilder usageSpecBuilder = new DefaultFIOUsageSpecificationBuilder();
            
            return new DefaultFIOSpecificationBuilder(modalitySpecBuilder, physicalSpecBuilder, usageSpecBuilder);            
            
        } catch (ErrorLoadingFISException ex) {
            System.err.println("CRITICAL ERROR: ");
            ex.printStackTrace();
            return null;
        }
        
    }
    
    
    private void setDouble(JTextField txt, Double number)
    {
        
        txt.setText(this.df.format(number));
        
    }
    
    private void updateFIOSpecification(FIOSpecification fioSpec)
    {
        
        setDouble(fIOSpecificationPanel.txtSize, fioSpec.getPhysicalSpecification().getSize());
        setDouble(fIOSpecificationPanel.txtStatus, fioSpec.getPhysicalSpecification().getStatus());
        setDouble(fIOSpecificationPanel.txtDistance, fioSpec.getPhysicalSpecification().getDistance());
        
        
        setDouble(fIOSpecificationPanel.txtAge, fioSpec.getUsageSpecification().getRecommendedUserAge());
        setDouble(fIOSpecificationPanel.txtICTLiteracy, fioSpec.getUsageSpecification().getIctDifficulty());

        
        updateShapeList(fioSpec);
        
        updateModalityList(fioSpec);
        
        
    }
    
    private void updateShapeList(FIOSpecification fioSpec)
    {

        for(PhysicalShape ps : fioSpec.getPhysicalSpecification().getShapeList()) 
        {
            
            double granularity = ps.getGranularity();
            
            switch(ps.getShapeType()) {
                case button: 
                    setDouble(fIOSpecificationPanel.txtShapeButton, granularity);
                    break;
                case custom:
                    setDouble(fIOSpecificationPanel.txtShapeCustom, granularity);
                    break;
                case display:
                    setDouble(fIOSpecificationPanel.txtShapeDisplay, granularity);
                    break;
                case embedded:
                    setDouble(fIOSpecificationPanel.txtShapeEmbedded, granularity);
                    break;
                case keyboard:
                    setDouble(fIOSpecificationPanel.txtShapeKeyboard, granularity);
                    break;
                case remote:
                    setDouble(fIOSpecificationPanel.txtShapeRemote, granularity);
                    break;
                case surface:
                    setDouble(fIOSpecificationPanel.txtShapeSurface, granularity);
                    break;
                case toy:
                    setDouble(fIOSpecificationPanel.txtShapeToy, granularity);
                    break;
                case unknown:
                    setDouble(fIOSpecificationPanel.txtShapeUnknown, granularity);
                    break;
            }
            
        }
        
    }
    
    public void updateModalityList(FIOSpecification fioSpec)
    {
        
        for(Modality m : fioSpec.getModalitySpecification().getModalities()) {
            
            double granularity = m.getGranularity();
            
            switch(m.getType()) {
                case gesture:
                    setDouble(fIOSpecificationPanel.txtModGesture, granularity);
                    break;
                case keyboard:
                    setDouble(fIOSpecificationPanel.txtModKeyboard, granularity);
                    break;
                case sound_production:
                    setDouble(fIOSpecificationPanel.txtModSoundProd, granularity);
                    break;
                case speech_recognition:
                    setDouble(fIOSpecificationPanel.txtModSpeech, granularity);
                    break;
                case speech_production:
                    setDouble(fIOSpecificationPanel.txtModSpeechProd, granularity);
                    break;
                case symbol:
                    setDouble(fIOSpecificationPanel.txtModSymbol, granularity);
                    break;
                case touch:
                    setDouble(fIOSpecificationPanel.txtModTouch, granularity);
                    break;
                case wimp:
                    setDouble(fIOSpecificationPanel.txtModWimp, granularity);
                    break;
            }
            
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        userProfilePanel = new com.hi3project.dandelion.tools.specbuilder.gui.UserProfilePanel();
        sceneProfilePanel = new com.hi3project.dandelion.tools.specbuilder.gui.SceneProfilePanel();
        environmentProfilePanel = new com.hi3project.dandelion.tools.specbuilder.gui.EnvironmentProfilePanel();
        sperator = new javax.swing.JSeparator();
        fIOSpecificationPanel = new com.hi3project.dandelion.tools.specbuilder.gui.FIOSpecificationPanel();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        userProfilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("User Profile"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(userProfilePanel, gridBagConstraints);

        sceneProfilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Scene Profile"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(sceneProfilePanel, gridBagConstraints);

        environmentProfilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Environment Profile"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(environmentProfilePanel, gridBagConstraints);

        sperator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(sperator, gridBagConstraints);

        fIOSpecificationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FIO Specification", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(fIOSpecificationPanel, gridBagConstraints);

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(btnUpdate, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUpdateActionPerformed
    {//GEN-HEADEREND:event_btnUpdateActionPerformed
        
        UserProfile userProfile = createUserProfile();
        
        EnvironmentProfile envProfile = createEnvironmentProfile();
        
        SceneProfile sceneProfile = createSceneProfile();
        
        FIOSpecification fioSpecification =
                this.fioSpecBuilder.buildQuery(null, null, sceneProfile, userProfile, envProfile);
        
        updateFIOSpecification(fioSpecification);
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FIOSpecBuilderTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FIOSpecBuilderTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FIOSpecBuilderTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FIOSpecBuilderTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FIOSpecBuilderTool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private com.hi3project.dandelion.tools.specbuilder.gui.EnvironmentProfilePanel environmentProfilePanel;
    private com.hi3project.dandelion.tools.specbuilder.gui.FIOSpecificationPanel fIOSpecificationPanel;
    private com.hi3project.dandelion.tools.specbuilder.gui.SceneProfilePanel sceneProfilePanel;
    private javax.swing.JSeparator sperator;
    private com.hi3project.dandelion.tools.specbuilder.gui.UserProfilePanel userProfilePanel;
    // End of variables declaration//GEN-END:variables
}
