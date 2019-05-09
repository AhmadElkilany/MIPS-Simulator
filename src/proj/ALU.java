package proj;

public class ALU {
	public int add(long source1, long source2) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal + tani);
		return (int) (source1 + source2);

	}

	public int and(long source1, long source2) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal & tani);
		return (int) (source1 & source2);
	}

	public int or(long source1, long source2) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal | tani);
		return (int) (source1 | source2);
	}

	public int subtract(long source1, long source2) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		if(destination != -1)
//			RegisterFile.registers[(int) destination].setRegValue(awal - tani);
		return (int) (source1 - source2);

	}

	public int xor(long source1, long source2) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal ^ tani);
		return (int) (source1 ^ source2);
	}

	public int add3(long source1, long source2, long source3) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		int talet = RegisterFile.registers[(int) source3].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal + tani + talet);
		return (int) (source1 + source2 + source3);
	}

	public int and3(long source1, long source2, long source3) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		int talet = RegisterFile.registers[(int) source3].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal & tani & talet);
		return (int) (source1 & source2 & source3);
	}

	public int mul3(long source1, long source2, long source3) {
//		int awal = RegisterFile.registers[(int) source1].getRegValue();
//		int tani = RegisterFile.registers[(int) source2].getRegValue();
//		int talet = RegisterFile.registers[(int) source3].getRegValue();
//		RegisterFile.registers[(int) destination].setRegValue(awal * tani * talet);
		return (int) (source1 * source1 * source1);
	}

	public void move(long destination, long source) {
		Register src = RegisterFile.registers[(int) source];
		// Register dest = RegisterFile.registers[destination];
		RegisterFile.registers[(int) destination].setRegValue(src.getRegValue());
	}

}
