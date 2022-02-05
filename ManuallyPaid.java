import java.util.Scanner;

public class ManuallyPaid implements Database {
    double amount;
    int code;
    Scanner scn;
    double returned;
    String CountNotes;
    int[] note;
    double tmp;
    double paid;
    public double fine;

    public ManuallyPaid(int code, double fine) {
        scn = new Scanner(System.in);
        this.code = code;
        this.amount = 0;
        returned = 0.0;
        CountNotes = "";
        note = new int[9];
        tmp = 0.0;
        paid = 0.0;
        this.fine = fine;
    }

    public static void Printmsg() {
        System.out.print("\nEnter your amount : ");
    }

    public void CalculateReturn() {
        if(fine > 0.0){
            System.out.println("///Note :Fined " + fine + " taka.(Passenger limit exceeded.)///" +
                    "\n///New amount to be pay (Fine added): " + CalculateTotal() + " taka.");
        }
        amount = scn.nextInt();
        if (code == 1) {
            paid = TollBUS + fine;
            returned = amount - TollBUS;
        } else if (code == 3) {
            paid = TollTruck + fine;
            returned = amount - TollTruck;
        } else if (code == 2) {
            paid = TollCNG + fine;
            returned = amount - TollCNG;
        } else if (code == 6) {
            paid = TollMINITRUCK + fine;
            returned = amount - TollMINITRUCK;
        } else if (code == 4) {
            paid = TollPRIVATECAR + fine;
            returned = amount - TollPRIVATECAR;
        } else if (code == 7) {
            paid = TollMICRO + fine;
            returned = amount - TollMICRO;
        } else if (code == 5) {
            paid = TollMOTORBIKE + fine;
            returned = amount - TollMOTORBIKE;
        }

        if(returned < 0 ){
            paid = 0.0;
            System.out.println("!!Insufficient amount of money.!!\n");
            Toll.setSuccess();
        }else{
            tmp = returned;
            calculateNotes();
            Registry.setTotalTaka(paid);
        }
    }

    private void calculateNotes() {
        while (returned != 0) {
            if (returned >= 1000.00) {
                returned -= 1000.00;
                note[0]++;
            } else if (returned >= 500.00) {
                returned -= 500.00;
                note[1]++;
            } else if (returned >= 100.00) {
                returned -= 100.00;
                note[2]++;
            } else if (returned >= 50.00) {
                returned -= 50.00;
                note[3]++;
            } else if (returned >= 20.00) {
                returned -= 20.00;
                note[4]++;
            } else if (returned >= 10.00) {
                returned -= 10.00;
                note[5]++;
            } else if (returned >= 5.00) {
                returned -= 5.00;
                note[6]++;
            } else if (returned >= 2.00) {
                returned -= 2.00;
                note[7]++;
            } else if (returned >= 1.00) {
                returned -= 1.00;
                note[8]++;
            }
        }
    }

    private double CalculateTotal(){
        if (code == 1) {
            paid = TollBUS + fine;
        } else if (code == 3) {
            paid = TollTruck + fine;
        } else if (code == 2) {
            paid = TollCNG + fine;
        } else if (code == 6) {
            paid = TollMINITRUCK + fine;
        } else if (code == 4) {
            paid = TollPRIVATECAR + fine;
        } else if (code == 7) {
            paid = TollMICRO + fine;
        } else if (code == 5) {
            paid = TollMOTORBIKE + fine;
        }
        return paid;
    }

    public String toString() {
        return "Paid : " + paid + " taka\n\n1000 Tk Note ----- " + note[0] + "\n500 Tk Note  ----- " + note[1]
                + "\n100 Tk Note  ----- " + note[2] + "\n50 Tk Note   ----- " + note[3] + "\n20 Tk Note   ----- " + note[4] +
                "\n10 Tk Note   ----- " + note[5] + "\n5 Tk Note    ----- " + note[6] +
                "\n2 Tk Note    ----- " + note[7] + "\n1 Tk Note    ----- " + note[8] + "\n-----------------------------" +
                "\nReturned Taka --- " + tmp + " taka\n";
    }

}






