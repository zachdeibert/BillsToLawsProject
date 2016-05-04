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

/** Scrollbar automatic animator
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ScrollAnimator implements Runnable {
    /** The scroll bar to animate
     * @since 1.0
     */
    private final JScrollBar scrollbar;
    /** The current scrollbar value
     * @since 1.0
     */
    private double frame;
    
    /** The animator
     * @since 1.0
     */
    @Override
    public void run() {
        try {
            double max = scrollbar.getMaximum();
            if ( (frame += 0.001) > 1.0 ) {
                Thread.sleep(490);
                frame = 0;
                scrollbar.setValue((int) (frame * max));
                Thread.sleep(500);
            }
            scrollbar.setValue((int) (frame * max));
            // Limit framerate
            Thread.sleep(10);
            // Nimbus crashes without invokeLater recursion
            // No Nimbus == :(
            EventQueue.invokeLater(this);
        } catch ( Exception ex ) {
            Logger.getLogger(ScrollAnimator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Default Constructor
     * @since 1.0
     * @param bar The scroll bar object
     */
    public ScrollAnimator(JScrollBar bar) {
        scrollbar = bar;
        frame = 0;
        run();
    }
}
