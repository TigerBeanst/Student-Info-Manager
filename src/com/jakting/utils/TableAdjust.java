package com.jakting.utils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
手动调整表头宽度
 */
public class TableAdjust {
    public static void setSomeColumnSize(JTable table, int[] i, int preferedWidth, int maxWidth, int minWidth) {
        TableColumnModel cm = table.getColumnModel();
        if (i.length == 0) {
            return;
        }
        for (int j = 0; j < i.length; j++) {
            TableColumn column = cm.getColumn(i[j]);
            column.setPreferredWidth(preferedWidth);
            column.setMaxWidth(maxWidth);
            column.setMinWidth(minWidth);
        }
    }

    public static void setOneColumnSize(JTable table, int i, int preferedWidth, int maxWidth, int minWidth) {
        TableColumnModel cm = table.getColumnModel();
        TableColumn column = cm.getColumn(i);
        column.setPreferredWidth(preferedWidth);
        column.setMaxWidth(maxWidth);
        column.setMinWidth(minWidth);
    }
}
