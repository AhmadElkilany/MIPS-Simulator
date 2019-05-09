package proj;

public class InstructionsMemory {

	public int lw(long address) {
//		int n = DataMemory.memory[(int)base+(int)offset];
//		RegisterFile.registers[(int)destination].setRegValue(n);
		return DataMemory.memory[(int) address];
	}

	public void sw(int address, int value) {
		DataMemory.memory[address] = value;
		//DataMemory.memory[memoryLocation] = value.getRegValue();
		//System.out.println("done");
	}

	public void swI(long base, long immediate) {
		DataMemory.memory[(int)base] = (int)immediate;
	}

}
