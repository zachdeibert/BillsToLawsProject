/* Copyright (c) 2014, Zach Deibert
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package deibert.zach.school.billstolaws;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import java.util.prefs.*;
import java.util.*;

/** License Window
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public final class License extends JFrame {
    /** Time the window was displayed (milliseconds)
     * @since 1.0
     * @see System.currentTimeMillis()
     */
    private long displayTime;
    /** Time it takes to read the license (milliseconds)
     * @since 1.0
     * @see System.currentTimeMillis()
     */
    private final long readingTime = 30000;
    /** If the scroll bar has reached the bottom
     * @since 1.0
     */
    private boolean hasFullyRead;
    /** The vertical scroll bar on the license
     * @since 1.0
     */
    private JScrollBar verticalScrollBar;
    /** File to cache license acceptence in
     * @since 1.0
     */
    private URI cacheFile;
    
    /** Default constructor
     * @since 1.0
     * @see initComponents()
     */
    public License() {
        initComponents();
        try {
            Preferences p = Preferences.userNodeForPackage(License.class);
            p.sync();
            if ( p.getBoolean("LicenseAccepted", false) ) {
                Main.instance.onLicenseAccepted();
                return;
            }
            /*cacheFile = License.class.getResource("License.cfg").toURI();
            FileReader file = new FileReader(new File(cacheFile));
            char buffer[] = new char[1];
            buffer[0] = '0';
            file.read(buffer, 0, 1);
            if ( buffer[0] == '1' ) {
                file.close();
                Main.instance.onLicenseAccepted();
                return;
            }
            file.close();*/
        } catch ( Exception ex ) {
            Logger.getLogger(License.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            InputStream file = License.class.getResourceAsStream("License.html");
            String html = "";
            Scanner in = new Scanner(file);
            for ( String tmp; in.hasNext(); html += tmp ) {
                tmp = in.nextLine();
            }
            in.close();
            file.close();
            jLabel1.setText(html);
        } catch ( Exception ex ) {
            Logger.getLogger(License.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            jLabel1.setText(String.format("Unable to load: %s", ex.getMessage()));
        }
        verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent event) {
                if ( verticalScrollBar.getValue() + verticalScrollBar.getModel().getExtent() >= verticalScrollBar.getMaximum() ) {
                    hasFullyRead = true;
                }
            }
        });
        setVisible(true);
        hasFullyRead = false;
        displayTime = System.currentTimeMillis();
    }

    /** NetBeans generated code
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("License Agreement");
        setAlwaysOnTop(true);
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jLabel1.setText("Loading...");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jScrollPane1.setViewportView(jLabel1);

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenseAccept(evt);
            }
        });

        jButton2.setText("Deny");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenseDeny(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Confirm the license action
     * @since 1.0
     * @param type The type of action taken ("deny" or "accept")
     * @return If the action was confirmed
     */
    private boolean licenseConfirm(String type) {
        type = String.format("Are you sure you want to %s?", type);
        int answer = JOptionPane.showConfirmDialog(this, type, "License Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        return answer == JOptionPane.YES_OPTION;
    }
    
    /** Deny the license
     * @since 1.0
     * @param evt The window event
     */
    private void licenseDeny(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenseDeny
        if ( licenseConfirm("deny") ) {
            System.exit(0);
        }
    }//GEN-LAST:event_licenseDeny

    /** Accept the license
     * @since 1.0
     * @param evt The window event
     */
    private void licenseAccept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenseAccept
        if ( System.currentTimeMillis() < displayTime + readingTime || !hasFullyRead ) {
            JOptionPane.showMessageDialog(this, "You should actually read it\n(You should not accept\nlicenses you do not read)", "License Confirm", JOptionPane.OK_OPTION);
        } else if ( licenseConfirm("accept") ) {
            setVisible(false);
            try {
                Preferences p = Preferences.userNodeForPackage(License.class);
                p.putBoolean("LicenseAccepted", true);
                p.sync();
                /*FileWriter file = new FileWriter(new File(cacheFile));
                char buffer[] = new char[1];
                buffer[0] = '1';
                file.write(buffer, 0, 1);
                file.close();*/
            } catch ( Exception ex ) {
                Logger.getLogger(License.class.getName()).log(Level.WARNING, null, ex);
            }
            Main.instance.onLicenseAccepted();
        }
    }//GEN-LAST:event_licenseAccept
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
