package tomasulo;
public class
ReservationStation extends Object{

    public String Op, Qj, Qk, Vj, Vk, Busy, Disp;
    int RstoInstructionMatcher;

    public ReservationStation(){}

    public ReservationStation(String Busy, String Op, String Vj, String Vk, String Qj, String Qk, String Disp, int RstoInstructionMatcher){
        this.Busy = Busy;
        this.Op = Op;
        this.Vj = Vj;
        this.Vk = Vk;
        this.Qj = Qj;
        this.Qk = Qk;
        this.Disp = Disp;
        this.RstoInstructionMatcher = RstoInstructionMatcher;

    }

    public int getRstoInstructionMatcher() {
        return RstoInstructionMatcher;
    }

    public void setRstoInstructionMatcher(int RstoInstructionMatcher) {
        this.RstoInstructionMatcher = RstoInstructionMatcher;
    }

    public String getOp() {
        return Op;
    }

    public void setOp(String Op) {
        this.Op = Op;
    }

    public String getQj() {
        return Qj;
    }

    public void setQj(String Qj) {
        this.Qj = Qj;
    }

    public String getQk() {
        return Qk;
    }

    public void setQk(String Qk) {
        this.Qk = Qk;
    }

    public String getVj() {
        return Vj;
    }

    public void setVj(String Vj) {
        this.Vj = Vj;
    }

    public String getVk() {
        return Vk;
    }

    public void setVk(String Vk) {
        this.Vk = Vk;
    }

    public String getBusy() {
        return Busy;
    }

    public void setBusy(String Busy) {
        this.Busy = Busy;
    }

    public String getDisp() {
        return Disp;
    }

    public void setDisp(String Disp) {
        this.Disp = Disp;
    }



    @Override
    public String toString() {
        return  Busy + "        "  +  Op + "      " + Vj + "        " + Vk  + "       " + Qj + "      " + Qk + "       " + Disp;
    }



}

