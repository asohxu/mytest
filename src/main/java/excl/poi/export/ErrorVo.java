package excl.poi.export;

/**
 * 错误信息实体
 * @author huang_kangjie
 * @create 2018-04-27 11:43
 */
public class ErrorVo {
    /**
     * 行号
     */
    private int row;

    /**
     * 列号
     */
    private int cell;

    /**
     * 批注内容
     */
    private String text;

    public ErrorVo() {
    }

    public ErrorVo(int row, int cell, String text) {
        this.row = row;
        this.cell = cell;
        this.text = text;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
