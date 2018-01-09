public final class FNVHash {

    public static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;

    public FNVHash() {}

    public static int hash32(final byte[] k, int length, int rvStart) {
        int rv = rvStart;
        for(int i = 0; i < length; i++) {
            rv ^= k[i];
            rv *= FNV_32_PRIME;
        }
        return rv == FNV_32_INIT ? 0 : rv;
    }
}