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
package deibert.zach.school.billstolaws.uim;
import javax.swing.*;
import java.awt.*;

public class Switch extends JFrame {
    private final Select select;
    private Work work;
    private final Runnable action;
    
    public Switch(Runnable act) {
        action = act;
        select = new Select(this);
        work = new Work();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - 320) / 2);
        int y = (int) ((dim.getHeight() - 240) / 2);
        setLocation(x, y);
        setPreferredSize(new Dimension(320, 240));
        setMinimumSize(new Dimension(320, 240));
        setMaximumSize(new Dimension(320, 240));
        setTitle("UIM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(select);
        setVisible(true);
    }
    
    public void selectClass(final String className) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                add(work);
                setVisible(true);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            EventQueue.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        UIManager.setLookAndFeel(className);
                                        remove(select);
                                        remove(work);
                                        work = new Work();
                                        add(work);
                                        setVisible(true);
                                    } catch ( Exception ex ) {
                                        
                                    }
                                }
                            });
                            Thread.sleep(1000);
                            finish();
                        } catch ( Exception ex ) {
                            finish();
                        }
                    }
                }.start();
            }
        });
    }
    
    public void finish() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dispose();
                action.run();
            }
        });
    }
}
