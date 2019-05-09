package proj;

public class others {
//	public static boolean beq(Register a, Register b, int immediate) {
//		ALU al = new ALU();
//		long x = al.subtract(-1, a.getRegValue(), b.getRegValue());
//		if (x == 0) {
//			return true;
//		}
//		return false;
//	}
	public void beq (long source1 , long source2, long immediate)
	{
		if (source1==source2)
		{
			
		}
	}
	
	public void move(long destination, long source) {
		Register src = RegisterFile.registers[(int) source];
		// Register dest = RegisterFile.registers[destination];
		RegisterFile.registers[(int) destination].setRegValue(src.getRegValue());
	}

}
