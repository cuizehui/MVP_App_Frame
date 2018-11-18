package cn.nela.tools;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class RectTool {

    /**
     * 最简单以宽高来等分
     * @param rect
     * @param totalNum
     * @return
     */
    public static List<Rect> caclSubViewRect(Rect rect, int totalNum) {
        List<Rect> l = new ArrayList<>();
        if (totalNum == 0) {
            return l;
        }
        int row = 0;
        int column = 0;
        if (totalNum <= 2) {
            row = 1;
            column = totalNum;
        } else if (totalNum <= 4) {
            row = 2;
            column = 2;
        } else if (totalNum <= 8) {
            row = 2;
            column = 4;
        } else if (totalNum <= 12) {
            row = 2;
            column = 6;
        } else if (totalNum <= 15) {
            row = 3;
            column = 5;
        } else if (totalNum <= 18) {
            row = 3;
            column = 6;
        } else if (totalNum <= 21) {
            row = 3;
            column = 7;
        }

        int perWidth = rect.width() / column;
        int perHeight = rect.height() / row;
        for (int i = 0; i < totalNum; i++) {
            l.add(new Rect(perWidth * (i % column), perHeight * (i / column), perWidth * (i % column + 1), perHeight * (i / column + 1)));
        }
        return l;
    }

}
