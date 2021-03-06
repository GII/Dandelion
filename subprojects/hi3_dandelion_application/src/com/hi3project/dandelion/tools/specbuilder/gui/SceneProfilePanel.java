/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Gervasio Varela <gervasio.varela@udc.es>
 */
public class SceneProfilePanel extends javax.swing.JPanel
{

    /**
     * Creates new form UserProfilePanel
     */
    public SceneProfilePanel()
    {
        initComponents();
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

        scenePanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblLocation = new javax.swing.JLabel();
        txtUserCount = new javax.swing.JTextField();
        lblEnvType = new javax.swing.JLabel();
        comboActivityType = new javax.swing.JComboBox();
        lblSituation = new javax.swing.JLabel();
        comboActivityMode = new javax.swing.JComboBox();
        lblSituation1 = new javax.swing.JLabel();
        comboActivityStyle = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        scenePanel.setLayout(new java.awt.GridLayout(5, 2));

        lblName.setText("Name");
        scenePanel.add(lblName);

        txtName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtName.setText("scene 1");
        txtName.setPreferredSize(new java.awt.Dimension(100, 28));
        scenePanel.add(txtName);

        lblLocation.setText("User Count");
        scenePanel.add(lblLocation);

        txtUserCount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUserCount.setText("1");
        txtUserCount.setPreferredSize(new java.awt.Dimension(100, 28));
        scenePanel.add(txtUserCount);

        lblEnvType.setText("Activity Type");
        scenePanel.add(lblEnvType);

        comboActivityType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "WORK", "DAILY", "FITNESS", "LEISSURE" }));
        scenePanel.add(comboActivityType);

        lblSituation.setText("Activity Mode");
        scenePanel.add(lblSituation);

        comboActivityMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ON_THE_GO", "STATIONARY" }));
        scenePanel.add(comboActivityMode);

        lblSituation1.setText("Activity Style");
        scenePanel.add(lblSituation1);

        comboActivityStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INDIVIDUAL", "SOCIAL" }));
        scenePanel.add(comboActivityStyle);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        add(scenePanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox comboActivityMode;
    public javax.swing.JComboBox comboActivityStyle;
    public javax.swing.JComboBox comboActivityType;
    private javax.swing.JLabel lblEnvType;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSituation;
    private javax.swing.JLabel lblSituation1;
    private javax.swing.JPanel scenePanel;
    public javax.swing.JTextField txtName;
    public javax.swing.JTextField txtUserCount;
    // End of variables declaration//GEN-END:variables
}
