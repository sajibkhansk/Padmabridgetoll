import java.util.Scanner;

public class Toll implements Database {
    public int Passenger;
    private int code;
    public int PaymentMethode;
    int lane;
    static boolean Success;
    String bit;
    Registry Enter1;
    Vehicle Enter;
    Scanner scn;

    static{
        Success = true;
    }

    public Toll(int Passenger, int PaymentMethode, int code, Registry Enter1, Vehicle Enter) {
        this.Passenger = Passenger;
        this.PaymentMethode = PaymentMethode;
        this.code = code;
        scn = new Scanner(System.in);
        lane = -1;
        bit = "";
        this.Enter1 = Enter1;
        this.Enter = Enter;
        PaymentPath();
        Entry();
        Bit();
    }

    public static void Opening() {
        System.out.print("***WHICH VEHICLE DO YOU HAVE*** \n 1. BUS \n 2. CNG \n 3. TRUCK \n " +
                "4. PRIVATE CAR\n 5. BIKE\n 6. MINI TRUCK\n 7. MICRO");
    }

    public static void Payment() {
        System.out.print("\n***How Do You want to  pay?***\n\t[1]ONLINE(20% Cashback only for Bkash customers.) " +
                "\t[2]MANUAL\n\nENTER YOUR PAYMENT METHOD [1] OR [2]? ");
    }

    public static void TollMessege(int code) {
        System.out.print("Amount of toll for your choice : ");
        if (code == 1) {
            System.out.println("(Bus) " + TollBUS + " Taka.");
        } else if (code == 3) {
            System.out.println("(Truck) " + TollTruck + "Taka.");
        } else if (code == 2) {
            System.out.println("(Cng) " + TollCNG + " Taka ");
        } else if (code == 6) {
            System.out.println("(Mini Truck) " + TollMINITRUCK + " Taka.");
        } else if (code == 4) {
            System.out.println("(Private Car) " + TollPRIVATECAR + " Taka");
        } else if (code == 7) {
            System.out.println("(Micro) " + TollMICRO + " Taka.");
        } else if (code == 5) {
            System.out.println("(Motor Bike) " + TollMOTORBIKE + " Taka.");
        }
    }

    public static void setSuccess(){
        Success = false;
    }

    private void PaymentPath() {
        if (PaymentMethode == 1) {
            double fine = Fine();
            OnlinePaid OnlinePayment1 = new OnlinePaid(code, fine);
            OnlinePaid.Printmsg();
            OnlinePayment1.discount();
            System.out.println(OnlinePayment1);
        } else {
            double fine = Fine();
            ManuallyPaid ManuallyPayment1 = new ManuallyPaid(code, fine);
            ManuallyPaid.Printmsg();
            ManuallyPayment1.CalculateReturn();
            System.out.println(ManuallyPayment1);
        }
    }

    private double Fine() {
        double fine = 0.0;
        if (code == 1 && Passenger > MaxPassengerBUS) {
            fine = (Passenger - MaxPassengerBUS) * FINE;
        } else if (code == 3 && Passenger > MaxPassengerTRUCK) {
            fine = (Passenger - MaxPassengerTRUCK) * FINE;
        } else if (code == 2 && Passenger > MaxPassengerCNG) {
            fine = (Passenger - MaxPassengerCNG) * FINE;
        } else if (code == 6 && Passenger > MaxPassengerMINITRUCK) {
            fine = (Passenger - MaxPassengerMINITRUCK) * FINE;
        } else if (code == 4 && Passenger > MaxPassengerPRIVATECAR) {
            fine = (Passenger - MaxPassengerPRIVATECAR) * FINE;
        } else if (code == 7 && Passenger > MaxPassengerMICRO) {
            fine = (Passenger - MaxPassengerMICRO) * FINE;
        } else if (code == 5 && Passenger > MaxPassengerBIKE) {
            fine = (Passenger - MaxPassengerBIKE) * FINE;
        }
        return fine;
    }

    private void Entry(){
        if(Success){
            if (code == 1) {
                Enter1.setCountBus();
                Enter1.setCountLane1();
                Enter.setBusPassenger(Passenger);
                Enter.setBusPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 3) {
                Enter1.setCountTruck();
                Enter1.setCountLane1();
                Enter.setTruckPassenger(Passenger);
                Enter.setTruckPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 2) {
                Enter1.setCountCng();
                Enter1.setCountLane2();
                Enter.setCngPassenger(Passenger);
                Enter.setCngPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 6) {
                Enter1.setCountMiniTruck();
                Enter1.setCountLane1();
                Enter.setMiniTruckPassenger(Passenger);
                Enter.setMiniTruckPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 4) {
                Enter1.setCountPrivateCar();
                Enter1.setCountLane2();
                Enter.setPrivateCarPassenger(Passenger);
                Enter.setPrivateCarPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 7) {
                Enter1.setCountMicro();
                Enter1.setCountLane2();
                Enter.setMicroPassenger(Passenger);
                Enter.setMicroPaymentMode(Vehicle.Mode(PaymentMethode));
            } else if (code == 5) {
                Enter1.setCountMotorBike();
                Enter1.setCountLane2();
                Enter.setMotorBikePassenger(Passenger);
                Enter.setMotorBikePaymentMode(Vehicle.Mode(PaymentMethode));
            }
        }
    }

    private int Lane(){
        if(code == 1 ){
            lane = 1;
        }else if(code == 2){
            lane = 2;
        }else if(code == 3){
            lane = 1;
        }else if(code == 4){
            lane = 2;
        }else if(code == 5){
            lane = 2;
        }else if(code == 6){
            lane = 1;
        }else if(code == 7){
            lane = 2;
        }
        return lane;
    }

    private void LoadEntry(){
        System.out.println("\nDo you want to see current situation? (Only for officials)\n [1] Yes \t [2] No");
        int opt = scn.nextInt();

        if(opt == 1){
            System.out.println(Enter1);
            System.out.println("\nDo you want to see details ?\n [1] YES\t[2] NO\n");
            int choice = scn.nextInt();

            if(choice == 1){
                System.out.println("Do you want to see specific vehicle details ?\n [1] Yes\t[2]No");
            }
            opt = scn.nextInt();

            if(opt == 2){
                Enter1.showDetails();
            }else{
                Enter.Choose();
            }
        }
    }

    private void Bit(){
        if(Success){
            Registry.setCounter();
            if(code == 1 ){
                bit = "BUS-01-";
                Enter.setBusCode(Registry.getCounter());
            }else if(code == 2){
                bit = "CNG-02-";
                Enter.setCngCode(Registry.getCounter());
            }else if(code == 3){
                bit = "TRUCK-01-";
                Enter.setTruckCode(Registry.getCounter());
            }else if(code == 4){
                bit = "PRIVATECAR-02-";
                Enter.setPrivateCarCode(Registry.getCounter());
            }else if(code == 5){
                bit = "MOTORBIKE-02-";
                Enter.setMotorBikeCode(Registry.getCounter());
            }else if(code == 6){
                bit = "MINITRUCK-01-";
                Enter.setMiniTruckCode(Registry.getCounter());
            }else if(code == 7){
                bit = "MICRO-02-";
                Enter.setMicroCode(Registry.getCounter());
            }
            Enter1.addVehicle(bit);
        }
    }

    public void Showlane(){
        if(Success){
            System.out.println("\nYour lane No : " + Lane());
            System.out.println();
            System.out.println("\nSlip serial : " + bit + Registry.getCounter());
        }else{
            System.out.println("\nPlease pay the right amount to proceed.");
        }
        LoadEntry();
    }

}
