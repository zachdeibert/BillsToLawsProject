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
import deibert.zach.school.billstolaws.uim.Switch;
import java.util.logging.*;
import javax.swing.*;

/** Entry point when launching from a jar
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public final class Main {
    /** Instance of Main class for static method access
     * @since 1.0
     */
    public static Main instance;
    /** Reference to a License window
     * @since 1.0
     */
    private License license;
    /** Reference to a Game window
     * @since 1.0
     */
    private GameWindow gameWin;
    
    /** Forces default constructor to be private
     * @since 1.0
     */
    private Main() {}
    
    /** Entry method
     * @since 1.0
     * @param args The command-line arguments or null when launched from applet
     */
    public static void main(String args[]) {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable thrwbl) {
                    StackTraceElement[] stack = thrwbl.getStackTrace();
                    Logger.getLogger(stack[0].getClassName()).log(Level.SEVERE, String.format("Uncaught Exception in Thread %s (%d)", thread.getName(), thread.getId()), thrwbl);
                }
            });
            instance = new Main();
            instance.license = new License();
        } catch ( Exception ex ) {
            Logger.getLogger(Main.class.getName()).log(Level.OFF, ex.getMessage(), ex);
        }
    }
    
    /** License accepted callback
     * @since 1.0
     */
    public void onLicenseAccepted() {
        if ( license != null ) {
            license.dispose();
        }
        new Switch(new Runnable() {
            @Override
            public void run() {
                gameWin = new GameWindow();
            }
        });
    }
}
