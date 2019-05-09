package proj;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Long> allInstructions;
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ALU alu = new ALU();
		RegisterFile r = new RegisterFile();
		DataMemory dm = new DataMemory();
		Stages sts = new Stages();
		InstructionsMemory im = new InstructionsMemory();
		long result = 1;
		// long num = (long) 4160749568;
		Scanner sc = new Scanner(System.in);
		allInstructions = new ArrayList<>();
		while(true) {
			String in = sc.nextLine();
			if(in==null || in.equals(""))
				break;
			allInstructions.add(Long.parseLong(in,2));
		}
		long fetched = 0;
		long executed = 0;
		long memoryAccessed = 0;
		RegisterFile.registers[5].setRegValue(5);
		DataMemory.memory[2] = 10;
		for(int i=0;i<allInstructions.size();i++) {
			fetched = sts.fetch(i);
			ArrayList<Long> decoded = sts.decode(fetched);
			if(decoded.get(0) == 2) {
				String s = decoded.get(1)+"";
				int x = Integer.parseInt(s);
				i = x;
				i--;
				continue;
			}
			executed = sts.execute(decoded);
			if(executed == 0 && (decoded.get(0)==4 || decoded.get(0)==61)) {
				System.out.println("ifffff");
				String s = decoded.get(3)+"";
				int x = Integer.parseInt(s);
				i = x;
				i--;
				continue;
			}
			memoryAccessed = sts.memoryAccess(decoded, (int)executed);
			System.out.println("executed"+executed+" "+"instruction number"+i);
			System.out.println("memory accessed"+memoryAccessed+" "+"instruction number"+i);
			sts.writeBack(decoded, (int)memoryAccessed, (int)executed,i);
			if(decoded.get(0) == 3) {
				String s = decoded.get(1)+"";
				int x = Integer.parseInt(s);
				i = x;
				i--;
				continue;
			}
		}
//		String ins = sc.nextLine();
//		String temp = "11111";
//		long fiveOnes = Long.parseLong(temp, 2);
//		String t2 = "1111111111111111";//16
//		long sixteenOnes = Long.parseLong(t2, 2);
//		String t3 = "111111111111111111111";
//		long twentyOneOnes = Long.parseLong(t3);
//		long instruction = Long.parseLong(ins, 2);
//		RegisterFile.registers[5].setRegValue(5);
//		if ((instruction >> 26) == 0) {// R-Type instructions
//			if ((63 & instruction) == 32) {
//				long x = instruction >> 16;
//				long y = instruction >> 11;
//				long z = instruction >> 6;
//				result = alu.add(x & fiveOnes, y & fiveOnes, z & fiveOnes);
//			}
//			if ((63 & instruction) == 36) {
//				long x = instruction >> 16;
//				long y = instruction >> 11;
//				long z = instruction >> 6;
//				result = alu.and(x & fiveOnes, y & fiveOnes, z & fiveOnes);
//			}
//			if ((63 & instruction) == 37) {
//				long x = instruction >> 16;
//				long y = instruction >> 11;
//				long z = instruction >> 6;
//				result = alu.or(x & fiveOnes, y & fiveOnes, z & fiveOnes);
//			}
//			if ((63 & instruction) == 34) {
//				long x = instruction >> 16;
//				long y = instruction >> 11;
//				long z = instruction >> 6;
//				result = alu.subtract(x & fiveOnes, y & fiveOnes, z & fiveOnes);
//			}
//			if ((63 & instruction) == 38) {
//				long x = instruction >> 16;
//				long y = instruction >> 11;
//				long z = instruction >> 6;
//				result = alu.xor(x & fiveOnes, y & fiveOnes, z & fiveOnes);
//			}
//		} else {
//			if ((instruction >> 26) == 43) {
//				long rs = (instruction >> 21) & fiveOnes;
//				long rt = (instruction >> 16) & fiveOnes;
//				im.sw((instruction & sixteenOnes), RegisterFile.registers[(int) rs].getRegValue(), rt);
//			}
//
//			if ((instruction >> 26) == 35) {
//				long rs = (instruction >> 21) & fiveOnes;
//				long rt = (instruction >> 16) & fiveOnes;
//				im.lw((instruction & sixteenOnes), RegisterFile.registers[(int) rs].getRegValue(), rt);
//			}
//
//			if ((instruction >> 26) == 4) {//000100
//				// branch equal lamma tet3emel
//			}
//			if((instruction >> 26) == 56) {//111000
//				long rs = (instruction >> 21) & fiveOnes;
//				long rt = (instruction >> 16) & fiveOnes;
//				long rd = (instruction >> 11) & fiveOnes;
//				long ro = (instruction >> 6) & fiveOnes;
//				result = alu.add3(ro, rt, rd, rs);
//			}
//			if((instruction >> 26) == 57) {//111001
//				long rs = (instruction >> 21) & fiveOnes;
//				long rt = (instruction >> 16) & fiveOnes;
//				long rd = (instruction >> 11) & fiveOnes;
//				long ro = (instruction >> 6) & fiveOnes;
//				result = alu.and3(ro, rt, rd, rs);
//			}
//			if((instruction >> 26) == 58) {//111010
//				long rs = (instruction >> 21) & fiveOnes;
//				long rt = (instruction >> 16) & fiveOnes;
//				long rd = (instruction >> 11) & fiveOnes;
//				long ro = (instruction >> 6) & fiveOnes;
//				result = alu.mul3(ro, rt, rd, rs);
//			}
//			if((instruction >> 26) == 55) {//110111
//				long rd = (instruction >> 21) & fiveOnes;
//				long immediate = instruction & twentyOneOnes;
//				im.swI(rd, immediate);
//			}
//			if((instruction >> 28) == 4 && ((instruction >> 21) & fiveOnes) == 16) {
//				long fs = (instruction >> 11) & fiveOnes;
//				long fd = (instruction >> 6) & fiveOnes;
//				alu.move(fd, fs);
//			}
//			
//		}
//		System.out.println(DataMemory.memory[8]);
//		// 101011001010100000000000000
//		// load 100011 store 101011
//		// 10101100101010100000000000000000 store
		// 11011100101000000000000000000001 swI
//		sc.close();
		//10001100100010100000000000000010 //load
		//00000000101000010001100000100000 add
		//00000000101000010001100000100010 sub
		//00000000101000010001100000100100 and
		//00000000101000010001100000100101 or
		//00000000101000010001100000100110 xor
		//11100000101000010001100000100110 add3
		//11100100101000010001100100100110 and3
		//11101000101000010001100100100110 mul3
		//01000010000000000010100110000000 move
		//00001100000000000000000000000100 jal
		//00001000000000000000000000000100 j
		//00010000101000100000000000000100 beq
		//00000000101000010001100000100101 beqi
	}

}
