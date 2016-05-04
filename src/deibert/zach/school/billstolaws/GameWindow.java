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
import java.util.*;
import deibert.zach.school.billstolaws.tiles.Splash;
import deibert.zach.school.billstolaws.tiles.Tile;
import deibert.zach.school.billstolaws.tiles.TileChangeAction;

/** Game window
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public final class GameWindow extends JFrame {
    public static Runnable closeHandler;
    private boolean scrollbarsFixed = false;

    /** Default constructor
     * @since 1.0
     */
    public GameWindow() {
        initComponents();
        closeHandler = new Runnable() {
            @Override
            public void run() {
                displayScores(null);
            }
        };
        NewGameForm form = new NewGameForm(this);
    }
    /** Switches the tile class
     * @since 1.0
     * @param t The new class
     */
    private void SwitchTile(final Tile t) {
        t.init(new TileChangeAction() {
            @Override
            public void handle(final Tile tile) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                jPanel6.removeAll();
                                jPanel6.add(tile);
                                jPanel6.repaint();
                                RepaintBills();
                                RepaintPlayers();
                                if  ( !scrollbarsFixed ) {
                                    jSplitPane1.setDividerLocation(jSplitPane1.getMinimumDividerLocation());
                                    jSplitPane3.setDividerLocation(jSplitPane3.getMaximumDividerLocation());
                                    scrollbarsFixed = true;
                                }
                            }
                        });
                        SwitchTile(tile);
                    }
                });
            }
        });
    }
    /** Repaints the bill information panel
     * @since 1.0
     */
    private void RepaintBills() {
        jPanel2.removeAll();
        ArrayList<Bill> bills = new ArrayList<>();
        for ( Player p : GameData.players ) {
            for ( Bill b : p.bills ) {
                bills.add(b);
            }
        }
        Collections.sort(bills, new Comparator() {
            @Override
            public int compare(Object t, Object t1) {
                Bill b = (Bill) t;
                Bill b1 = (Bill) t1;
                if ( b == null || b1 == null ) {
                    return 0;
                }
                // 'a' for Senate and 'b' for House
                // Ensures Senate bills are before House bills
                return String.format("%s%d", b.owner.house == Bill.House.HouseOfRepresentatives ? "b" : "a", b.ID).compareTo(
                    String.format("%s%d", b1.owner.house == Bill.House.HouseOfRepresentatives ? "b" : "a", b1.ID));
            }
        });
        for ( Bill b : bills ) {
            jPanel2.add(new BillPanel(b));
        }
        jPanel2.repaint();
        jSplitPane1.setDividerLocation(jSplitPane1.getDividerLocation());
    }
    private void RepaintPlayers() {
        jPanel3.removeAll();
        for ( Player p : GameData.players ) {
            jPanel3.add(new PlayerPanel(p));
        }
        jPanel3.repaint();
        jSplitPane1.setDividerLocation(jSplitPane1.getDividerLocation());
    }
    
    /** Starts the game loop
     * @since 1.0
     */
    public void StartGameLoop() {
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
        jPanel6.setLayout(new BoxLayout(jPanel6, BoxLayout.Y_AXIS));
        SwitchTile(new Splash());
        new ScrollAnimator(jScrollPane1.getHorizontalScrollBar());
        new ScrollAnimator(jScrollPane2.getVerticalScrollBar());
        jSplitPane1.setDividerLocation(0.0);
        jSplitPane3.setDividerLocation(1.0);
        setVisible(true);
    }

    /** NetBeans generated code
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bills to Laws");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                displayScores(evt);
            }
        });

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel1.setText("Living Bills:");
        jLabel1.setMinimumSize(new java.awt.Dimension(74, 64));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1513, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(jPanel1);

        jLabel3.setText("Loading...");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(1088, Short.MAX_VALUE))
        );

        jSplitPane3.setLeftComponent(jPanel6);

        jLabel4.setText("Players:");
        jLabel4.setMaximumSize(new java.awt.Dimension(132, 18));
        jLabel4.setMinimumSize(new java.awt.Dimension(132, 18));
        jLabel4.setPreferredSize(new java.awt.Dimension(132, 18));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1516, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1108, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane3.setRightComponent(jPanel7);

        jSplitPane1.setRightComponent(jSplitPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayScores(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_displayScores
        String scores = "<html><table><tr><th colspan=\"2\">Scores:</th></tr>";
        int lastPlace = 0;
        int place = 0;
        int lastScore = Integer.MAX_VALUE;
        ArrayList<Player> players = new ArrayList<>();
        players.addAll(Arrays.asList(GameData.players));
        Collections.sort(players, new Comparator() {
            @Override
            public int compare(Object t, Object t1) {
                Player p = (Player) t;
                Player p1 = (Player) t1;
                if ( p == null || p1 == null ) {
                    return 0;
                }
                return Integer.compare(p1.passedBills, p.passedBills);
            }
        });
        for ( Player p : players ) {
            if ( p.passedBills < lastScore ) {
                lastPlace = place + 1;
            }
            place++;
            scores += String.format("<tr><td rowspan=\"2\">%d.</td><td>%s</td></tr><tr><td>%d Bill(s) Passed (%d%%)</td></tr>",
                lastPlace, p.name, p.passedBills, (p.passedBills * 100) / (p.proposedBills == 0 ? 1 : p.proposedBills));
        }
        scores += "<tr><th colspan=\"2\">Thanks for playing!</th></tr></table></html>";
        JOptionPane.showMessageDialog(this, scores, "Final Scores", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }//GEN-LAST:event_displayScores


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    // End of variables declaration//GEN-END:variables
}
