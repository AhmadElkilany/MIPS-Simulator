package proj;

import java.util.ArrayList;

public class Stages {
	public ArrayList<Long> decode(long instruction) {
		String temp = "11111";
		long fiveOnes = Long.parseLong(temp, 2);
		String t2 = "1111111111111111";// 16
		long sixteenOnes = Long.parseLong(t2, 2);
		String t3 = "111111111111111111111";
		long twentyOneOnes = Long.parseLong(t3, 2);
		String t4 = "11111111111111111111111111";
		long twentySixOnes = Long.parseLong(t4, 2);
		String t5 = "1111111111";
		long tenOnes = Long.parseLong(t5, 2);
		String t6 = "11111111111";
		long elevenOnes = Long.parseLong(t6, 2);
		ArrayList<Long> decoded = new ArrayList<>();
		if ((instruction >> 26) == 0) {// R-Type instructions
			long rs = (instruction >> 21) & fiveOnes;
			long rt = (instruction >> 16) & fiveOnes;
			long rd = (instruction >> 11) & fiveOnes;
			decoded.add(instruction >> 26);
			decoded.add(63 & instruction);
			decoded.add(rd);
			decoded.add(rs);
			decoded.add(rt);
		} else {
			if ((instruction >> 26) == 43 || (instruction >> 26) == 35) {// load or store
				long rs = (instruction >> 21) & fiveOnes;
				long rt = (instruction >> 16) & fiveOnes;
				// im.sw((instruction & sixteenOnes), RegisterFile.registers[(int)
				// rs].getRegValue(), rt);
				decoded.add(instruction >> 26);
				long x = sign_extend(instruction & sixteenOnes, 16);
				decoded.add(x);
				decoded.add(rs);
				decoded.add(rt);
			}

			if ((instruction >> 26) == 4) {// 000100
				long rs = ((instruction) >> 21) & fiveOnes;
				long rt = ((instruction) >> 16) & fiveOnes;
				long x = sign_extend(instruction & sixteenOnes, 16);
				decoded.add(instruction >> 26);
				decoded.add(rs);
				decoded.add(rt);
				decoded.add(x);
			}
			if ((instruction >> 26) == 57 || (instruction >> 26) == 58 || (instruction >> 26) == 56) {// add3,and3,mul3
				long rs = (instruction >> 21) & fiveOnes;
				long rt = (instruction >> 16) & fiveOnes;
				long rd = (instruction >> 11) & fiveOnes;
				long ro = (instruction >> 6) & fiveOnes;
				// result = alu.and3(ro, rt, rd, rs);
				decoded.add(instruction >> 26);
				decoded.add(ro);
				decoded.add(rt);
				decoded.add(rd);
				decoded.add(rs);
			}
			if ((instruction >> 26) == 55) {// 110111
				long rd = (instruction >> 21) & fiveOnes;
				long immediate = instruction & twentyOneOnes;
				// im.swI(rd, immediate);
				long x = sign_extend(immediate, 21);
				decoded.add(instruction >> 26);
				decoded.add(rd);

				decoded.add(x);
			}
			if ((instruction >> 28) == 4 && ((instruction >> 21) & fiveOnes) == 16) {
				long fs = (instruction >> 11) & fiveOnes;
				long fd = (instruction >> 6) & fiveOnes;
				// alu.move(fd, fs);
				decoded.add(instruction >> 28);
				decoded.add((instruction >> 21) & fiveOnes);
				decoded.add(fd);
				decoded.add(fs);
			}
			if ((instruction >> 26) == 2) {// j
				long immediate = instruction & twentySixOnes;
				decoded.add(instruction >> 26);
				long x = sign_extend(immediate, 26);
				decoded.add(x);
			}
			if ((instruction) >> 26 == 3) {// jal
				long immediate = instruction & twentySixOnes;
				decoded.add(instruction >> 26);
				long x = sign_extend(immediate, 26);
				decoded.add(x);
			}
			if ((instruction >> 26) == 61) {
				// decoded.add(instruction >> 26);
				long rs = (instruction >> 21) & fiveOnes;
				long immediate = (instruction >> 11) & tenOnes;
				long label = (instruction) & elevenOnes;
				decoded.add((instruction >> 26));
				decoded.add(rs);
				decoded.add(immediate);
				decoded.add(label);
			}

		}
		return decoded;
	}

	@SuppressWarnings("unused")
	public long execute(ArrayList<Long> decodedInst) {
		ALU alu = new ALU();
		// RegisterFile rf = new RegisterFile();
		long res = 0;
		if (decodedInst.get(0) == 0) {
			String y = decodedInst.get(2) + "";
			String yy = decodedInst.get(3) + "";
			String yyy = decodedInst.get(4) + "";
			int x = Integer.parseInt(y);
			int xx = Integer.parseInt(yy);
			System.out.println("xx" + xx);
			int xxx = Integer.parseInt(yyy);
			System.out.println("xxx" + xxx);
			if (decodedInst.get(1) == 32) {// add
				res = alu.add(RegisterFile.registers[xx].getRegValue(), RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}

			if (decodedInst.get(1) == 36) {// and
				res = alu.and(RegisterFile.registers[xx].getRegValue(), RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}

			if (decodedInst.get(1) == 37) {// or
				res = alu.or(RegisterFile.registers[xx].getRegValue(), RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}

			if (decodedInst.get(1) == 34) {// sub
				res = alu.subtract(RegisterFile.registers[xx].getRegValue(), RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}
			if (decodedInst.get(1) == 38) {// xor
				res = alu.xor(RegisterFile.registers[xx].getRegValue(), RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}

		} else {
			if (decodedInst.get(0) == 4) {
				String s = decodedInst.get(1) + "";
				String ss = decodedInst.get(2) + "";
				int x = Integer.parseInt(s);
				int xx = Integer.parseInt(ss);
				res = alu.subtract(RegisterFile.registers[x].getRegValue(), RegisterFile.registers[xx].getRegValue());
			}
			if (decodedInst.get(0) == 61) {
				String s = decodedInst.get(1) + "";
				String ss = decodedInst.get(2) + "";
				int x = RegisterFile.registers[Integer.parseInt(s)].getRegValue();
				int xx = Integer.parseInt(ss);
				res = alu.subtract(x, xx);
			}

			if (decodedInst.get(0) == 43) {// store
				String y = decodedInst.get(3) + "";
				int x = Integer.parseInt(y);
				res = alu.add(decodedInst.get(1), RegisterFile.registers[x].getRegValue());
				// res = decodedInst.get(1) + decodedInst.get(3);
				// im.sw((instruction & sixteenOnes), RegisterFile.registers[(int)
				// rs].getRegValue(), rt);
			}
			if (decodedInst.get(0) == 35) {// load
				String y = decodedInst.get(2) + "";
				int x = Integer.parseInt(y);
				res = alu.add(decodedInst.get(1), RegisterFile.registers[x].getRegValue());
			}
			if (decodedInst.get(0) == 56 || decodedInst.get(0) == 57 || decodedInst.get(0) == 58) {
				String s = decodedInst.get(2) + "";
				int x = Integer.parseInt(s);
				String ss = decodedInst.get(3) + "";
				int xx = Integer.parseInt(ss);
				String sss = decodedInst.get(4) + "";
				int xxx = Integer.parseInt(sss);
				if (decodedInst.get(0) == 56)
					res = alu.add3(RegisterFile.registers[x].getRegValue(), RegisterFile.registers[xx].getRegValue(),
							RegisterFile.registers[xxx].getRegValue());
				if (decodedInst.get(0) == 57)
					res = alu.and3(RegisterFile.registers[x].getRegValue(), RegisterFile.registers[xx].getRegValue(),
							RegisterFile.registers[xxx].getRegValue());
				if (decodedInst.get(0) == 58)
					res = alu.mul3(RegisterFile.registers[x].getRegValue(), RegisterFile.registers[xx].getRegValue(),
							RegisterFile.registers[xxx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[x].getRegName() + " "
						+ RegisterFile.registers[x].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xx].getRegName() + " "
						+ RegisterFile.registers[xx].getRegValue());
				System.out.println("Execution stage " + RegisterFile.registers[xxx].getRegName() + " "
						+ RegisterFile.registers[xxx].getRegValue());
			}

		}

		return res;

	}

	public int memoryAccess(ArrayList<Long> decodedInst, int address) {
		// lw $t0 , 3 2 ( $s3 )
		InstructionsMemory im = new InstructionsMemory();
		int res = 0;
		if (decodedInst.get(0) == 35) {// load
			res = im.lw(address);
			System.out.println("In memory" + " " + DataMemory.memory[address]);
			return res;
		}
		if (decodedInst.get(0) == 43) {
			String s = decodedInst.get(2) + "";
			int x = Integer.parseInt(s);
			im.sw(address, RegisterFile.registers[x].getRegValue());
			System.out.println("In memory" + " " + DataMemory.memory[address]);
			System.out.println("Memory stage " + RegisterFile.registers[x].getRegName() + " "
					+ RegisterFile.registers[x].getRegValue());
			return -1;
		}

		if (decodedInst.get(0) == 55) {
			String s = decodedInst.get(1) + "";
			int x = Integer.parseInt(s);
			im.swI(RegisterFile.registers[x].getRegValue(), decodedInst.get(2));
			System.out.println("In memory" + " " + DataMemory.memory[RegisterFile.registers[x].getRegValue()]);
			System.out.println("Memory stage " + RegisterFile.registers[x].getRegName() + " "
					+ RegisterFile.registers[x].getRegValue());
			return -1;
		}

		return -1;

	}

	@SuppressWarnings("unused")
	public void writeBack(ArrayList<Long> decodedInst, int memOutput, int executeOutput, int curAddress) {
		if (memOutput == -1) {
			if (decodedInst.get(0) == 0) {// opcode = 0
				String s = decodedInst.get(2) + "";
				int x = Integer.parseInt(s);
				RegisterFile.registers[x].setRegValue(executeOutput);
				System.out.println("writeBack stage " + RegisterFile.registers[x].getRegName() + " "
						+ RegisterFile.registers[x].getRegValue());
			} else {
				if ((decodedInst.get(0) == 56) || (decodedInst.get(0) == 57) || (decodedInst.get(0) == 58)) {// add3,mul3,and3
					String s = decodedInst.get(1) + "";
					int x = Integer.parseInt(s);
					RegisterFile.registers[x].setRegValue(executeOutput);
					System.out.println("wrrite back stage" + RegisterFile.registers[x].getRegName() + " "
							+ RegisterFile.registers[x].getRegValue());
				} else {
					if (decodedInst.get(0) == 4 && decodedInst.get(1) == 16) {
						others o = new others();
						String s = decodedInst.get(2) + "";
						String ss = decodedInst.get(3) + "";
						int x = Integer.parseInt(s);
						int xx = Integer.parseInt(ss);
						o.move(decodedInst.get(2), decodedInst.get(3));
						System.out.println("write back moved " + decodedInst.get(3) + " to " + decodedInst.get(2));
						System.out.println("After move " + RegisterFile.registers[x].getRegName() + " "
								+ RegisterFile.registers[x].getRegValue());
					} else {
						if (decodedInst.get(0) == 3) {
							RegisterFile.registers[31].setRegValue(curAddress);
							System.out.println("Just saved address " + curAddress);
						}
					}
				}
			}
		} else {
			if (decodedInst.get(0) == 35) {
				String s = decodedInst.get(1) + "";
				int x = Integer.parseInt(s);
				System.out.println("x value" + " " + x);
				RegisterFile.registers[x].setRegValue(memOutput);
				System.out.println("wrrite back stage" + RegisterFile.registers[x].getRegName() + " "
						+ RegisterFile.registers[x].getRegValue());
			}
		}
	}

	@SuppressWarnings("unused")
	public long fetch(int inst) {
		long res = 0;
		long full = Main.allInstructions.get(inst);
		long x = full >> 26;
		if (x == 0) {
			System.out.println("R-Type");
			res = x;
		}
		if (x == 4 || x == 35 || x == 43) {
			System.out.println("I-Type");
			res = x;
		}
		if (x == 2 || x == 3) {
			System.out.println("J-Type");
			res = x;
		}
		if (x == 56 || x == 57 || x == 58) {
			System.out.println("Tri-Type");
			res = x;
		}
		if(x == 61) {
			System.out.println("BranchImmediate-Type");
			res = x;
		}
		if(x == 55) {
			System.out.println("StoreImmediate-Type");
			res = x;
		}
		if(x == 16 || x == 17 || x == 18 || x == 19) {
			System.out.println("Coprosessor-Type");
			res = x;
		}

		return full;
	}

	public static long sign_extend(long val, long bits) {
		long shift = 32 - bits; // int always has 32 bits in Java
		long s = val << shift;
		return s >> shift;
	}

}
