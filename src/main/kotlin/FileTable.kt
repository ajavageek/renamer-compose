import androidx.compose.runtime.Composable
import java.awt.Color.LIGHT_GRAY
import java.awt.Color.YELLOW
import java.io.File
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.table.AbstractTableModel
import javax.swing.table.TableCellRenderer

class FileTable(model: AbstractTableModel) : JTable(model) {

    private val highlightRenderer = TableCellRenderer { table: JTable, _: Any,
                                                        _: Boolean, _: Boolean,
                                                        row: Int, _: Int ->
        JLabel().apply {
            val actual = table.getValueAt(row, 0) as String
            text = table.getValueAt(row, 1) as String
            if (actual != text) {
                isOpaque = true
                background = YELLOW
            }
        }
    }

    init {
        setShowGrid(true)
        setGridColor(LIGHT_GRAY)
    }

    override fun getCellRenderer(row: Int, column: Int): TableCellRenderer = when (column) {
        0 -> super.getCellRenderer(row, column)
        else -> highlightRenderer
    }
}

class FileTableModel(var files: List<File>, var candidates: List<File>): AbstractTableModel() {

    override fun getRowCount() = files.size
    override fun getColumnCount() = 2

    override fun getColumnName(column: Int) = when (column) {
        0 -> "Name"
        else -> "Candidate name"
    }

    override fun getValueAt(row: Int, column: Int): String {
        return when (column) {
            0 -> files[row].name
            else -> candidates[row].name
        }
    }
}