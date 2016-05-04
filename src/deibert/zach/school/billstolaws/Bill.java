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
import java.util.*;

/** Bill data
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Bill {
    public enum House {
        HouseOfRepresentatives,
        Senate,
        President
    }
    public enum Stage {
        Idea,
        Introduction,
        Committee,
        Fillibuster,
        Rules,
        Debate,
        ConferenceCommittee,
        Veto
    }
    
    public final int ID;
    public final Player owner;
    private static int HouseOfRepresentativesBillCount = 0;
    private static int SenateBillCount = 0;
    private static final JFrame displayPanel = new JFrame();
    //<editor-fold defaultstate="collapsed" desc="Flags to determine where the bill is">
    private static final int FLAG_DEFAULT =                                     0b00000000000000000000;
    private static final int FLAG_KILLED =                                      0b10000000000000000000;
    private static final int FLAG_IDEA =                                        0b01000000000000000000;
    private static final int FLAG_PASSED =                                      0b00110000000000000000;
    private static final int FLAG_SENATE_INTRODUCED =                           0b00000000000000000001;
    private static final int FLAG_SENATE_COMMITTEE =                            0b00000000000000000010;
    private static final int FLAG_SENATE_COMMITTEE_FAVORABLY =                  0b00000000000000000110;
    private static final int FLAG_SENATE_COMMITTEE_UNFAVORABLY =                0b00000000000000001010;
    private static final int FLAG_SENATE_FILLIBUSTER =                          0b00000000000000010000;
    private static final int FLAG_SENATE_APPROVED =                             0b00000000000000100000;
    private static final int FLAG_HOUSE_INTRODUCED =                            0b00000000000001000000;
    private static final int FLAG_HOUSE_COMMITTEE =                             0b00000000000010000000;
    private static final int FLAG_HOUSE_COMMITTEE_FAVORABLY =                   0b00000000000110000000;
    private static final int FLAG_HOUSE_COMMITTEE_UNFAVORABLY =                 0b00000000001010000000;
    private static final int FLAG_HOUSE_RULES_COMMITTEE =                       0b00000000010000000000;
    private static final int FLAG_HOUSE_APPROVED =                              0b00000000100000000000;
    private static final int FLAG_CONFERENCE_COMMITTEE =                        0b00000001000000000000;
    private static final int FLAG_PRESIDENTIAL_ACTION =                         0b00001000000000000000;
    private static final int FLAG_PRESIDENT_SIGNED =                            0b00111010000000000000;
    private static final int FLAG_PRESIDENT_VETOED =                            0b00001100000000000000;
    private static final int FLAG_PRESIDENT_POCKET_VETOED =                     0b10001000000000000000;
    private static final int FLAG_SENATE_VETO_OVERRIDE =                        0b00010000000000000000;
    private static final int FLAG_HOUSE_VETO_OVERRIDE =                         0b00100000000000000000;
    private int flags =                                                         FLAG_DEFAULT;
    //</editor-fold>
    
    public Bill(Player p) {
        switch ( p.house ) {
            case HouseOfRepresentatives:
                ID = ++HouseOfRepresentativesBillCount;
                break;
            case Senate:
                ID = ++SenateBillCount;
                break;
            default:
                ID = -1;
                break;
        }
        owner = p;
    }
    
    public House getCurrentHouse() {
        switch ( owner.house ) {
            case Senate:
                if ( (flags & FLAG_SENATE_APPROVED) == FLAG_DEFAULT ) {
                    return House.Senate;
                } else if ( (flags & FLAG_HOUSE_APPROVED) == FLAG_DEFAULT ) {
                    return House.HouseOfRepresentatives;
                } else if ( (flags & FLAG_PRESIDENTIAL_ACTION) == FLAG_DEFAULT ) {
                    return House.President;
                } else if ( (flags & FLAG_SENATE_VETO_OVERRIDE)  == FLAG_DEFAULT ) {
                    return House.Senate;
                } else {
                    return House.HouseOfRepresentatives;
                }
            case HouseOfRepresentatives:
                if ( (flags & FLAG_HOUSE_APPROVED) == FLAG_DEFAULT ) {
                    return House.HouseOfRepresentatives;
                } else if ( (flags & FLAG_SENATE_APPROVED) == FLAG_DEFAULT ) {
                    return House.Senate;
                } else if ( (flags & FLAG_PRESIDENTIAL_ACTION) == FLAG_DEFAULT ) {
                    return House.President;
                } else if ( (flags & FLAG_HOUSE_VETO_OVERRIDE)  == FLAG_DEFAULT ) {
                    return House.HouseOfRepresentatives;
                } else {
                    return House.Senate;
                }
            default:
                return owner.house;
        }
    }
    
    public Stage getCurrentStage() {
        switch ( getCurrentHouse() ) {
            case HouseOfRepresentatives:
                if ( (flags & FLAG_IDEA) == FLAG_DEFAULT ) {
                    return Stage.Idea;
                } else if ( (flags & FLAG_HOUSE_INTRODUCED) == FLAG_DEFAULT ) {
                    return Stage.Introduction;
                } else if ( (flags & FLAG_HOUSE_COMMITTEE) == FLAG_DEFAULT ) {
                    return Stage.Committee;
                } else if ( (flags & FLAG_HOUSE_RULES_COMMITTEE) == FLAG_DEFAULT ) {
                    return Stage.Rules;
                } else if ( (flags & FLAG_HOUSE_APPROVED) == FLAG_DEFAULT ) {
                    return Stage.Debate;
                } else if ( (flags & FLAG_CONFERENCE_COMMITTEE) == FLAG_DEFAULT ) {
                    return Stage.ConferenceCommittee;
                } else {
                    return Stage.Veto;
                }
            case Senate:
                if ( (flags & FLAG_IDEA) == FLAG_DEFAULT ) {
                    return Stage.Idea;
                } else if ( (flags & FLAG_SENATE_INTRODUCED) == FLAG_DEFAULT ) {
                    return Stage.Introduction;
                } else if ( (flags & FLAG_SENATE_COMMITTEE) == FLAG_DEFAULT ) {
                    return Stage.Committee;
                } else if ( (flags & FLAG_SENATE_FILLIBUSTER) != FLAG_DEFAULT ) {
                    return Stage.Fillibuster;
                } else if ( (flags & FLAG_SENATE_APPROVED) == FLAG_DEFAULT ) {
                    return Stage.Debate;
                } else if ( (flags & FLAG_CONFERENCE_COMMITTEE) == FLAG_DEFAULT ) {
                    return Stage.ConferenceCommittee;
                } else {
                    return Stage.Veto;
                }
            default: // case President:
                return Stage.Veto;
        }
    }
    
    public void nextStage() {
        if ( (flags & FLAG_KILLED) != FLAG_DEFAULT || (flags & FLAG_PASSED) != FLAG_DEFAULT ) {
            return;
        }
        switch ( owner.house ) {
            case Senate:
                if ( (flags & FLAG_IDEA) == FLAG_DEFAULT ) {
                    CreateBill();
                } else if ( (flags & FLAG_SENATE_INTRODUCED) == FLAG_DEFAULT ) {
                    SenateIntro();
                } else if ( (flags & FLAG_SENATE_COMMITTEE) == FLAG_DEFAULT ) {
                    SenateCommittee();
                } else if ( (flags & FLAG_SENATE_FILLIBUSTER) != FLAG_DEFAULT ) {
                    SenateFillibuster();
                } else if ( (flags & FLAG_SENATE_APPROVED) == FLAG_DEFAULT ) {
                    SenateDebate();
                } else if ( (flags & FLAG_HOUSE_INTRODUCED) == FLAG_DEFAULT ) {
                    HouseIntro();
                } else if ( (flags & FLAG_HOUSE_COMMITTEE) == FLAG_DEFAULT ) {
                    HouseCommittee();
                } else if ( (flags & FLAG_HOUSE_RULES_COMMITTEE) == FLAG_DEFAULT ) {
                    HouseRulesCommittee();
                } else if ( (flags & FLAG_HOUSE_APPROVED) == FLAG_DEFAULT ) {
                    HouseDebate();
                } else if ( (flags & FLAG_CONFERENCE_COMMITTEE) == FLAG_DEFAULT ) {
                    ConferenceCommittee();
                } else if ( (flags & FLAG_PRESIDENTIAL_ACTION) == FLAG_DEFAULT ) {
                    PresidentialAction();
                } else if ( (flags & FLAG_PRESIDENT_VETOED) != FLAG_DEFAULT ) {
                    // Override in Congress
                    if ( (flags & FLAG_SENATE_VETO_OVERRIDE) == FLAG_DEFAULT ) {
                        SenateOverride();
                    } else if ( (flags & FLAG_HOUSE_VETO_OVERRIDE) == FLAG_DEFAULT ) {
                        HouseOverride();
                    } else {
                        // Pass
                        flags |= FLAG_PASSED;
                    }
                } else {
                    // Pass
                    flags |= FLAG_PASSED;
                }
                break;
            case HouseOfRepresentatives:
                if ( (flags & FLAG_IDEA) == FLAG_DEFAULT ) {
                    CreateBill();
                } else if ( (flags & FLAG_HOUSE_INTRODUCED) == FLAG_DEFAULT ) {
                    HouseIntro();
                } else if ( (flags & FLAG_HOUSE_COMMITTEE) == FLAG_DEFAULT ) {
                    HouseCommittee();
                } else if ( (flags & FLAG_HOUSE_RULES_COMMITTEE) == FLAG_DEFAULT ) {
                    HouseRulesCommittee();
                } else if ( (flags & FLAG_HOUSE_APPROVED) == FLAG_DEFAULT ) {
                    HouseDebate();
                } else if ( (flags & FLAG_SENATE_INTRODUCED) == FLAG_DEFAULT ) {
                    SenateIntro();
                } else if ( (flags & FLAG_SENATE_COMMITTEE) == FLAG_DEFAULT ) {
                    SenateCommittee();
                } else if ( (flags & FLAG_SENATE_FILLIBUSTER) != FLAG_DEFAULT ) {
                    SenateFillibuster();
                } else if ( (flags & FLAG_SENATE_APPROVED) == FLAG_DEFAULT ) {
                    SenateDebate();
                } else if ( (flags & FLAG_CONFERENCE_COMMITTEE) == FLAG_DEFAULT ) {
                    ConferenceCommittee();
                } else if ( (flags & FLAG_PRESIDENTIAL_ACTION) == FLAG_DEFAULT ) {
                    PresidentialAction();
                } else if ( (flags & FLAG_PRESIDENT_VETOED) != FLAG_DEFAULT ) {
                    // Override in Congress
                    if ( (flags & FLAG_HOUSE_VETO_OVERRIDE) == FLAG_DEFAULT ) {
                        HouseOverride();
                    } else if ( (flags & FLAG_SENATE_VETO_OVERRIDE) == FLAG_DEFAULT ) {
                        SenateOverride();
                    } else {
                        // Pass
                        flags |= FLAG_PASSED;
                    }
                } else {
                    // Pass
                    flags |= FLAG_PASSED;
                }
                break;
        }
        if ( (flags & FLAG_KILLED) != FLAG_DEFAULT ) {
            String hn;
            switch ( owner.house ) {
                case Senate:
                    hn = "S";
                    break;
                case HouseOfRepresentatives:
                    hn = "HR";
                    break;
                default:
                    hn = "";
                    break;
            }
            ShowMessage(String.format("%s%d died.", hn, ID));
            owner.bills.remove(this);
        } else if ( (flags & FLAG_PASSED) != FLAG_DEFAULT ) {
            ShowMessage("Your bill has been passed!");
            owner.passedBills++;
            owner.bills.remove(this);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Stage Handlers">
    private void CreateBill() {
        // 33%  Idea from Citizen
        // 33%  Idea from Interest Group
        // 34%  Idea from Lawmaker
        switch ( DetermineChances(new int[] {33, 33, 34}) ) {
            case 0:
                ShowMessage("You got an idea for a new bill from a citizen");
                break;
            case 1:
                ShowMessage("You got an idea for a new bill from an interest group");
                break;
            case 2:
                ShowMessage("You got an idea for a new bill from a lawmaker");
                break;
        }
        owner.proposedBills++;
        flags |= FLAG_IDEA;
    }
    
    private void SenateIntro() {
        // 100% Introduce
        ShowMessage("You were recognized, then you read the bill into record.");
        flags |= FLAG_SENATE_INTRODUCED;
    }
    
    private void SenateCommittee() {
        // First time:
        //  75%  Kill
        //  15%  Report Favorably
        //  10%  Report Unfavorably
        // Second time:
        //  75%  Report Favorably
        //  15%  Report Unfavorably
        //  10%  Kill
        ShowMessage("Your bill was assigned to a committee by leadership consent");
        switch ( DetermineChances(new int[] {75, 15, 10}) ) {
            case 0:
                if ( owner.house == House.Senate ) {
                    ShowMessage("Your bill died in Committee");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("Your bill was reported favorably");
                    flags |= FLAG_SENATE_COMMITTEE_FAVORABLY;
                }
                break;
            case 1:
                if ( owner.house == House.Senate ) {
                    ShowMessage("Your bill was reported favorably");
                    flags |= FLAG_SENATE_COMMITTEE_FAVORABLY;
                } else {
                    ShowMessage("Your bill was reported unfavorably");
                    flags |= FLAG_SENATE_COMMITTEE_UNFAVORABLY;
                }
                break;
            case 2:
                if ( owner.house == House.Senate ) {
                    ShowMessage("Your bill was reported unfavorably");
                    flags |= FLAG_SENATE_COMMITTEE_UNFAVORABLY;
                } else {
                    ShowMessage("Your bill died in Committee");
                    flags |= FLAG_KILLED;
                }
                break;
        }
    }
    
    private void SenateFillibuster() {
        // 100% Remove Fillibuster
        ShowMessage("Your bill is still in fillibuster.");
        flags ^= FLAG_SENATE_FILLIBUSTER;
    }
    
    private void SenateDebate() {
        // 10%  Fillibuster
        // 65%  Agree with Committee
        // 25%  Disagree with Committee
        switch ( DetermineChances(new int[] {10, 65, 25}) ) {
            case 0:
                ShowMessage("Your bill was fillibustered during debate.");
                flags |= FLAG_SENATE_FILLIBUSTER;
                break;
            case 1:
                if ( (flags & (FLAG_SENATE_COMMITTEE_FAVORABLY ^ FLAG_SENATE_COMMITTEE)) != FLAG_DEFAULT ) {
                    ShowMessage("After debating, the Senate decided to agree with the Committee's decision of passing the bill");
                    flags |= FLAG_SENATE_APPROVED;
                } else {
                    ShowMessage("After debating, the Senate decided to agree with the Committee's decision of not passing the bill");
                    flags |= FLAG_KILLED;
                }
                break;
            case 2:
                if ( (flags & (FLAG_SENATE_COMMITTEE_FAVORABLY ^ FLAG_SENATE_COMMITTEE)) != FLAG_DEFAULT ) {
                    ShowMessage("After debating, the Senate decided not to pass the bill");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("After debating, the Senate decided to pass the bill");
                    flags |= FLAG_SENATE_APPROVED;
                }
                break;
        }
    }
    
    private void HouseIntro() {
        // 100% Introduce
        ShowMessage("You dropped the bill into the hopper.");
        flags |= FLAG_HOUSE_INTRODUCED;
    }
    
    private void HouseCommittee() {
        // First time:
        //  75%  Kill
        //  15%  Report Favorably
        //  10%  Report Unfavorably
        // Second time:
        //  75%  Report Favorably
        //  15%  Report Unfavorably
        //  10%  Kill
        ShowMessage("Your bill was assigned to a committee by the Speaker");
        switch ( DetermineChances(new int[] {75, 15, 10}) ) {
            case 0:
                if ( owner.house == House.HouseOfRepresentatives ) {
                    ShowMessage("Your bill died in Committee");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("Your bill was reported favorably");
                    flags |= FLAG_HOUSE_COMMITTEE_FAVORABLY;
                }
                break;
            case 1:
                if ( owner.house == House.HouseOfRepresentatives ) {
                    ShowMessage("Your bill was reported favorably");
                    flags |= FLAG_HOUSE_COMMITTEE_FAVORABLY;
                } else {
                    ShowMessage("Your bill was reported unfavorably");
                    flags |= FLAG_HOUSE_COMMITTEE_UNFAVORABLY;
                }
                break;
            case 2:
                if ( owner.house == House.HouseOfRepresentatives ) {
                    ShowMessage("Your bill was reported unfavorably");
                    flags |= FLAG_HOUSE_COMMITTEE_UNFAVORABLY;
                } else {
                    ShowMessage("Your bill died in Committee");
                    flags |= FLAG_KILLED;
                }
                break;
        }
    }
    
    private void HouseRulesCommittee() {
        // 100% Advance to Debate
        ShowMessage("The rules for debate were decided at the Rules Committee");
        flags |= FLAG_HOUSE_RULES_COMMITTEE;
    }
    
    private void HouseDebate() {
        // 75%  Agree with Committee
        // 25%  Disagree with Committee
        switch ( DetermineChances(new int[] {75, 25}) ) {
            case 0:
                if ( (flags & (FLAG_HOUSE_COMMITTEE_FAVORABLY ^ FLAG_HOUSE_COMMITTEE)) != FLAG_DEFAULT ) {
                    ShowMessage("After debating, the House decided to agree with the Committee's decision of passing the bill");
                    flags |= FLAG_HOUSE_APPROVED;
                } else {
                    ShowMessage("After debating, the House decided to agree with the Committee's decision of not passing the bill");
                    flags |= FLAG_KILLED;
                }
                break;
            case 1:
                if ( (flags & (FLAG_HOUSE_COMMITTEE_FAVORABLY ^ FLAG_HOUSE_COMMITTEE)) != FLAG_DEFAULT ) {
                    ShowMessage("After debating, the House decided not to pass the bill");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("After debating, the House decided to pass the bill");
                    flags |= FLAG_HOUSE_APPROVED;
                }
                break;
        }
    }
    
    private void ConferenceCommittee() {
        // 100% Agree on bill
        ShowMessage("Both the Senate and the House agreed on the Conference Report from the Conference Committee");
        flags |= FLAG_CONFERENCE_COMMITTEE;
    }
    
    private void PresidentialAction() {
        // 25%  Sign
        // 50%  Veto
        // 25%  Pocket Veto
        switch ( DetermineChances(new int[] {25, 50, 25}) ) {
            case 0:
                ShowMessage("The President signed your bill!");
                flags |= FLAG_PRESIDENT_SIGNED;
                break;
            case 1:
                ShowMessage("The President vetoed your bill");
                flags |= FLAG_PRESIDENT_VETOED;
                break;
            case 2:
                ShowMessage("The President pocket vetoed your bill");
                flags |= FLAG_PRESIDENT_POCKET_VETOED;
                break;
        }
    }
    
    private void SenateOverride() {
        // 85%  Agree with Committee
        // 15%  Disagree with Committee
        switch ( DetermineChances(new int[] {85, 15}) ) {
            case 0:
                if ( (flags & FLAG_SENATE_COMMITTEE_FAVORABLY) != FLAG_DEFAULT ) {
                    ShowMessage("The Senate voted over 2/3 to override the veto");
                    flags |= FLAG_SENATE_VETO_OVERRIDE;
                } else {
                    ShowMessage("The Senate did not vote over 2/3 to override the veto");
                    flags |= FLAG_KILLED;
                }
                break;
            case 1:
                if ( (flags & FLAG_SENATE_COMMITTEE_FAVORABLY) != FLAG_DEFAULT ) {
                    ShowMessage("The Senate did not vote over 2/3 to override the veto");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("The Senate voted over 2/3 to override the veto");
                    flags |= FLAG_SENATE_VETO_OVERRIDE;
                }
                break;
        }
    }
    
    private void HouseOverride() {
        // 85%  Agree with Committee
        // 15%  Disagree with Committee
        switch ( DetermineChances(new int[] {85, 15}) ) {
            case 0:
                if ( (flags & FLAG_HOUSE_COMMITTEE_FAVORABLY) != FLAG_DEFAULT ) {
                    ShowMessage("The House voted over 2/3 to override the veto");
                    flags |= FLAG_HOUSE_VETO_OVERRIDE;
                } else {
                    ShowMessage("The House did not vote over 2/3 to override the veto");
                    flags |= FLAG_KILLED;
                }
                break;
            case 1:
                if ( (flags & FLAG_HOUSE_COMMITTEE_FAVORABLY) != FLAG_DEFAULT ) {
                    ShowMessage("The House did not vote over 2/3 to override the veto");
                    flags |= FLAG_KILLED;
                } else {
                    ShowMessage("The House voted over 2/3 to override the veto");
                    flags |= FLAG_HOUSE_VETO_OVERRIDE;
                }
                break;
        }
    }
    //</editor-fold>
    
    private int DetermineChances(int chances[]) {
        Random gen = new Random();
        int max = 0;
        for ( int c : chances ) {
            max += c;
        }
        int res = gen.nextInt(max);
        int n = 0;
        for ( int i = 0, t = 0; i < chances.length; i++ ) {
            if ( res >= t && res < t + chances[i] ) {
                n = i;
                break;
            }
            t += chances[i];
        }
        return n;
    }
    
    private void ShowMessage(String msg) {
        JOptionPane.showOptionDialog(displayPanel, msg, String.format("%s's turn", owner.name), JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {"OK"}, "OK");
    }
}
