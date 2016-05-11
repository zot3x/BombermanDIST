package HighscoreTest;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ColumnRenderer extends DefaultTableCellRenderer
{
	Color backcolor, forcolor;

	public ColumnRenderer(Color backround, Color forground) {
		super();
		backcolor = backround;
		forcolor = forground;
	}

	public Component getTableCellRendererComponent
	(JTable table, Object value, boolean selected,
			boolean arg, int row, int column)
	{
		setHorizontalAlignment(SwingConstants.CENTER);
		Component cell = super.getTableCellRendererComponent(table, value, selected, arg, row, column);

		cell.setBackground(backcolor);
		cell.setForeground(forcolor);
		return cell;
	}
}
