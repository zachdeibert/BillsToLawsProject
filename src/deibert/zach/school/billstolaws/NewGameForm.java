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
import java.awt.*;
import java.util.logging.*;

/** New game dialog window
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class NewGameForm extends JFrame {
    /** If the game is creates
     * @since 1.0
     */
    private boolean isCreated;
    /** The parent window
     * @since 1.0
     */
    private final GameWindow gameWin;
    
    /** Default constructor
     * @since 1.0
     * @param parent The parent game window
     */
    public NewGameForm(GameWindow parent) {
        initComponents();
        isCreated = false;
        gameWin = parent;
        setVisible(true);
    }

    /** NetBeans generated code
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Game");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                centerOnScreen(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                quitGame(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("How many players will play?");

        jLabel2.setText("Number of human players:");

        jLabel3.setText("Number of robotic players:");

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGame(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        jFormattedTextField1.setText("1");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
        jFormattedTextField2.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFormattedTextField1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitGame(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_quitGame
        if ( !isCreated ) {
            System.exit(0);
        }
    }//GEN-LAST:event_quitGame

    private void centerOnScreen(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_centerOnScreen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - getWidth()) / 2);
        int y = (int) ((dim.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }//GEN-LAST:event_centerOnScreen

    private void startGame(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGame
        int humans, robots;
        try {
            long tmp;
            tmp = (long) jFormattedTextField1.getValue();
            humans = (int) tmp;
            tmp = (long) jFormattedTextField2.getValue();
            robots = (int) tmp;
        } catch ( Exception ex ) {
            humans = 1;
            robots = 1;
        }
        int players = humans + robots;
        if ( players < 2 ) {
            JOptionPane.showMessageDialog(this, "There must be at least two players.\nAdd a robot player if you are lonely.");
            return;
        }
        if ( players > 535 ) {
            JOptionPane.showMessageDialog(this, "Thare may only be up to 535 players.\nStart multiple games if you must.");
            return;
        }
        if ( players > 10 ) {
            if ( JOptionPane.showConfirmDialog(this, "You have more than 10 plnayers.\nThis is not recommended\n(It will take a while to play),\nbut you may continue if you want")
                != JOptionPane.OK_OPTION ) {
                return;
            }
        }
        GameData.players = new Player[players];
        for ( int i = 0; i < humans; i++ ) {
            String name = JOptionPane.showInputDialog(this, String.format("What is player %d's name?", i + 1));
            if ( name.isEmpty() ) {
                name = String.format("Player %d", i + 1);
            }
            GameData.players[i] = HumanPlayer.create(name);
        }
        for ( int i = 0; i < robots; i++ ) {
            GameData.players[i + humans] = RoboticPlayer.create(i + humans);
        }
        GameData.turn = -1;
        isCreated = true;
        setVisible(false);
        dispose();
        gameWin.StartGameLoop();
    }//GEN-LAST:event_startGame

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
