package tomasulo;

public class Instruction {

    int opCode, destOp, sourceOp1, sourceOp2, RstoInstructionMatcher;

    public Instruction(){}

    public Instruction(int opCode, int destOp, int sourceOp1, int sourceOp2, int RstoInstructionMatcher){

        this.opCode = opCode;
        this.destOp = destOp;
        this.sourceOp1 = sourceOp1;
        this.sourceOp2 = sourceOp2;
        this.RstoInstructionMatcher = RstoInstructionMatcher;
    }

    public int getRstoInstructionMatcher() {
        return RstoInstructionMatcher;
    }

    public void setRstoInstructionMatcher(int RstoInstructionMatcher) {
        this.RstoInstructionMatcher = RstoInstructionMatcher;
    }

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public int getDestOp() {
        return destOp;
    }

    public void setDestOp(int destOp) {
        this.destOp = destOp;
    }

    public int getSourceOp1() {
        return sourceOp1;
    }

    public void setSourceOp1(int sourceOp1) {
        this.sourceOp1 = sourceOp1;
    }

    public int getSourceOp2() {
        return sourceOp2;
    }

    public void setSourceOp2(int sourceOp2) {
        this.sourceOp2 = sourceOp2;
    }

    @Override
    public String toString() {
        return "Instruction{" + "opCode=" + opCode + ", destOp=" + destOp + ", sourceOp1=" + sourceOp1 + ", sourceOp2=" + sourceOp2 + '}';
    }


}
