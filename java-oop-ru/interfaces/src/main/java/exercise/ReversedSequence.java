package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;

    public ReversedSequence(String str) {
        this.str = new StringBuilder(str).reverse().toString();
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.subSequence(start, end);
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }
}
// END
