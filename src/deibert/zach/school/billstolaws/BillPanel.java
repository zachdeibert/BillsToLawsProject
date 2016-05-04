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

/** Panel for displaying bill information
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class BillPanel extends JPanel {
    /** Default constructor
     * @since 1.0
     * @param b The bill to display
     */
    public BillPanel(Bill b) {
        initComponents();
        String state = "";
        Bill.House h = b.getCurrentHouse();
        String hn;
        switch ( h ) {
            case Senate:
                hn = "Senate";
                break;
            case HouseOfRepresentatives:
                hn = "House";
                break;
            case President:
                hn = "President";
                break;
            default:
                hn = "";
                break;
        }
        switch ( b.getCurrentStage() ) {
            case Idea:
                state = "Idea";
                break;
            case Introduction:
                state = "Introduction";
                break;
            case Committee:
                state = "Committee";
                break;
            case Fillibuster:
                state = "Fillibuster";
                break;
            case Rules:
                state = "Rules Committee";
                break;
            case Debate:
                state = "Debate";
                break;
            case ConferenceCommittee:
                state = "Conference Committee";
                break;
            case Veto:
                state = "Veto Override";
                break;
        }
        if ( h == Bill.House.President ) {
            state = "President";
        }
        String playerName = b.owner.name;
        while ( (new JLabel(playerName).getPreferredSize().width) > 128 ) {
            if ( playerName.endsWith("...") ) {
                playerName = playerName.substring(0, playerName.length() - 4) + "...";
            } else {
                playerName += "...";
            }
        }
        jLabel1.setText(String.format("<html><div style=\"width:128px\">%s%d<br />%s<br />%s<br />%s</div></html>",
            b.owner.house == Bill.House.HouseOfRepresentatives ? "HR" : "S",
            b.ID,
            playerName,
            hn,
            state));
    }

    /** NetBeans generated code
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(128, 64));
        setMinimumSize(new java.awt.Dimension(128, 64));
        setPreferredSize(new java.awt.Dimension(128, 64));

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
