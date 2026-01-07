package epi.util;

public class MatrixXYOffset {
    public Integer x;
    public Integer y;
    public Integer offset;

    public MatrixXYOffset(Integer x, Integer y, Integer offset) {
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.hashCode = java.util.Objects.hash(x, y, offset);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof MatrixXYOffset)) return false;
        MatrixXYOffset that = (MatrixXYOffset) o;
        return this.x.equals(that.x) && this.y.equals(that.y) && this.offset.equals(that.offset);
    }

    private final int hashCode;

    @Override
    public int hashCode() {
        return hashCode;
    }
}
