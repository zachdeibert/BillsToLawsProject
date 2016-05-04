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
package deibert.zach.school.billstolaws.tiles;
import deibert.zach.school.billstolaws.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;
import java.util.logging.*;

public class GameTile extends Tile {
    private static HashMap<Bill.Stage, HashMap<Bill.House, ImageIcon>> tileIcons;
    private static boolean initialized = false;
    private final Runnable action;
    private final Bill.Stage s;
    private final Bill.House h;
    private TileChangeAction act;
    
    public static void loadResources() {
        if ( initialized ) {
            return;
        }
        try {
            tileIcons = new HashMap<>();
            for ( Bill.Stage stage : Bill.Stage.values() ) {
                HashMap<Bill.House, ImageIcon> map = new HashMap<>();
                for ( Bill.House house : Bill.House.values() ) {
                    map.put(house, new ImageIcon(ImageIO.read(GameTile.class.getResource(String.format("%s.%s.png", house.toString(), stage.toString())))));
                }
                tileIcons.put(stage, map);
            }
        } catch ( Exception ex ) {
            Logger.getLogger(GameTile.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        initialized = true;
    }
    
    public GameTile(Runnable code, Bill.Stage stage, Bill.House house) {
        if ( !initialized ) {
            loadResources();
        }
        action = code;
        s = stage;
        h = house;
    }
    
    @Override
    public void init(TileChangeAction actionHandler) {
        try {
            act = actionHandler;
            final ImageIcon icon = tileIcons.get(s).get(h);
            //JLabel l = new JLabel(icon);
            //l.setHorizontalAlignment(JLabel.CENTER);
            //l.setVerticalAlignment(JLabel.CENTER);
            //setLayout(new BorderLayout());
            //add(l, BorderLayout.CENTER);
            setLayout(new BorderLayout());
            add(new JLabel(icon) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image img = icon.getImage();
                    int x = (getWidth() - img.getWidth(this)) / 2;
                    int y = (getWidth() - img.getHeight(this)) / 2;
                    g.drawImage(img, x, y, this);
                }
            });
            setVisible(true);
        } catch ( Exception ex ) {
            Logger.getLogger(GameTile.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                action.run();
                act.handle(new TurnManager());
            }
        });
    }
}
