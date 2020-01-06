package com.github.dima767.springshell.commons;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.TableModel;
import org.springframework.shell.table.TableModelBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Tables {

    private Tables() {
    }

    public static TableModel singleObjectDetailsTableModelOf(List<Pair<String, Object>> rows) {
        //TODO input check
        var builder = new TableModelBuilder();
        rows.forEach(row -> {
            builder.addRow()
                    .addValue(row.getLeft())
                    .addValue(row.getRight());
        });
        return builder.build();
    }

    public static TableModel objectListWithHeadersTableModelOf(List<Pair<String, Object>> headerData, Iterable<?> data) {
        LinkedHashMap<String, Object> header = new LinkedHashMap<>();
        headerData.forEach(it -> {
            header.put(it.getLeft(), it.getRight());
        });
        return new BeanListTableModel(data, new LinkedHashMap<>(header));
    }

    public static TableModel objectListWithHeadersTableModelOf(Set<String> headerRow, List<List<Object>> data) {

        //TODO input check
        var builder = new TableModelBuilder();
        //Build header row
        builder.addRow();
        headerRow.forEach(header -> {
            builder.addValue(header);
        });

        //Add data rows
        data.forEach(row -> {
            builder.addRow();
            row.forEach(item -> {
                builder.addValue(item);
            });
        });
        return builder.build();
    }
}
