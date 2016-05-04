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
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/** Player base class
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class Player {
    /** The last turn played.
     * @since 1.0
     */
    public Turn lastTurn;
    /** The player's name
     * @since 1.0
     */
    public final String name;
    /** All of the player's bills
     * @since 1.0
     */
    public final LinkedList<Bill> bills;
    public int passedBills;
    public int proposedBills;
    public final Bill.House house;
    private static int representativesLeft = 435;
    private static int senatorsLeft = 100;
    
    /** Does the input cycle
     * @see lastTurn
     * @since 1.0
     */
    public abstract void doInputCycle();
    
    protected Player(String n) {
        Random rand = new Random();
        try {
            // All instances will be created very quickly.
            // Allow better randomization
            Thread.sleep(rand.nextInt(50));
        } catch ( Exception ex ) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        rand.setSeed(System.currentTimeMillis());
        name = n;
        bills = new LinkedList<>();
        passedBills = 0;
        proposedBills = 0;
        if ( rand.nextInt(535) < 100 ) {
            if ( senatorsLeft-- > 0 ) {
                house = Bill.House.Senate;
            } else if ( representativesLeft-- > 0 ) {
                house = Bill.House.HouseOfRepresentatives;
            } else {
                house = Bill.House.Senate;
                JOptionPane.showMessageDialog(new JFrame(), "Too many players!");
                System.exit(1);
            }
        } else {
            if ( representativesLeft-- > 0 ) {
                house = Bill.House.HouseOfRepresentatives;
            } else if ( senatorsLeft-- > 0 ) {
                house = Bill.House.Senate;
            } else {
                house = Bill.House.HouseOfRepresentatives;
                JOptionPane.showMessageDialog(new JFrame(), "Too many players!");
                System.exit(1);
            }
        }
    }
}
