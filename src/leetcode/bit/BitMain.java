package leetcode.bit;

public class BitMain {

    /**
     * leetcode 2595. 奇偶位数     位掩码 0x55555555 AND       Integer.bitCount 统计 1的个数
     * 利用位掩码0x55555555（二进制 0101⋯01），与 n计算 AND，即可取出所有偶数下标比特，然后用库函数统计其中1的个数。
     * 我们把 0x55555555取反（二进制 1010⋯10），与 n计算 AND，即可取出所有奇数下标比特，然后用库函数统计其中1的个数
     */
    public int[] evenOddBit(int n) {
        final int MASK = 0x55555555;
        return new int[]{Integer.bitCount(n & MASK), Integer.bitCount(n & ~MASK)};
    }
}
