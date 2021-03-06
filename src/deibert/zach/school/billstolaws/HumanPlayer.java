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

/** Human player class
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class HumanPlayer extends Player {
    private JFrame displayFrame;
    
    @Override
    public void doInputCycle() {
        Object[] Options = new Object[bills.size() + 1];
        for ( int i = 0; i < Options.length - 1; i++ ) {
            Bill b = bills.get(i);
            Options[i] = String.format("%s%d", house == Bill.House.HouseOfRepresentatives ? "HR" : "S", b.ID);
        }
        Options[Options.length - 1] = "New";
        lastTurn = new Turn();
        lastTurn.billID = JOptionPane.showOptionDialog(displayFrame, "Which bill do you want to work on this turn?",
                String.format("%s's Turn", name),
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options, Options[Options.length - 1]);
        if ( lastTurn.billID == Options.length - 1 ) {
            bills.add(new Bill(this));
        }
        if ( lastTurn.billID < 0 ) {
            GameWindow.closeHandler.run();
        }
    }

    public static Player create(String name) {
        HumanPlayer instance = new HumanPlayer(name);
        instance.displayFrame = new JFrame();
        return instance;
    }
    
    private HumanPlayer(String name) {
        super(name);
    }
}
