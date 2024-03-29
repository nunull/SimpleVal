/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2013 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <mseifert[at]error-reports.org>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erichseifert.gral.plots.legends;

import java.text.Format;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import de.erichseifert.gral.data.DataChangeEvent;
import de.erichseifert.gral.data.DataListener;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.Row;

/**
 * A legend implementation that displays items for all data values of all data
 * series that are added to the legend.
 */
public abstract class ValueLegend extends AbstractLegend
		implements DataListener {
	/** Version id for serialization. */
	private static final long serialVersionUID = -4274009997506638823L;

	/** Column index containing the labels. */
	private int labelColumn;
	/** Format for data to label text conversion. */
	private Format labelFormat;

	/**
	 * Initializes a new instance with default values.
	 */
	public ValueLegend() {
		labelColumn = 0;
	}

	@Override
	protected Iterable<Row> getEntries(DataSource source) {
		List<Row> items = new LinkedList<Row>();
		for (int rowIndex = 0; rowIndex < source.getRowCount(); rowIndex++) {
			Row row = new Row(source, rowIndex);
			items.add(row);
		}
		return items;
	}

	@Override
	protected String getLabel(Row row) {
		int col = getLabelColumn();
		Comparable<?> value = row.get(col);
		if (value == null) {
			return "";
		}

		// Formatting
		Format format = getLabelFormat();
		if ((format == null) && row.isColumnNumeric(col)) {
			format = NumberFormat.getInstance();
		}

		// Text to display
		String text = (format != null) ? format.format(value) : value.toString();
		return text;
	}

	@Override
	public void add(DataSource source) {
		super.add(source);
		source.addDataListener(this);
	}

	@Override
	public void remove(DataSource source) {
		super.remove(source);
		source.removeDataListener(this);
	}

	/**
	 * Method that is invoked when data has been added.
	 * This method is invoked by objects that provide support for
	 * {@code DataListener}s and should not be called manually.
	 * @param source Data source that has been changed.
	 * @param events Optional event object describing the data values that
	 *        have been added.
	 */
	public void dataAdded(DataSource source, DataChangeEvent... events) {
		dataChanged(source, events);
	}

	/**
	 * Method that is invoked when data has been updated.
	 * This method is invoked by objects that provide support for
	 * {@code DataListener}s and should not be called manually.
	 * @param source Data source that has been changed.
	 * @param events Optional event object describing the data values that
	 *        have been updated.
	 */
	public void dataUpdated(DataSource source, DataChangeEvent... events) {
		dataChanged(source, events);
	}

	/**
	 * Method that is invoked when data has been removed.
	 * This method is invoked by objects that provide support for
	 * {@code DataListener}s and should not be called manually.
	 * @param source Data source that has been changed.
	 * @param events Optional event object describing the data values that
	 *        have been removed.
	 */
	public void dataRemoved(DataSource source, DataChangeEvent... events) {
		dataChanged(source, events);
	}

	/**
	 * Method that is invoked when data has been added, updated, or removed.
	 * This method is invoked by objects that provide support for
	 * {@code DataListener}s and should not be called manually.
	 * @param source Data source that has been changed.
	 * @param events Optional event object describing the data values that
	 *        have been changed.
	 */
	private void dataChanged(DataSource source, DataChangeEvent... events) {
		invalidate();
	}

	/**
	 * Returns the index of the column that contains the labels for the values.
	 * @return Column index containing the labels.
	 */
	public int getLabelColumn() {
		return labelColumn;
	}

	/**
	 * Sets the index of the column that contains the labels for the values.
	 * @param labelColumn Column index containing the labels.
	 */
	public void setLabelColumn(int labelColumn) {
		this.labelColumn = labelColumn;
		invalidate();
		refresh();
	}

	/**
	 * Returns the format used to display data values.
	 * @return Format for data to label text conversion.
	 */
	public Format getLabelFormat() {
		return labelFormat;
	}

	/**
	 * Sets the format used to display data values.
	 * @param labelFormat Format for data to label text conversion.
	 */
	public void setLabelFormat(Format labelFormat) {
		this.labelFormat = labelFormat;
		invalidate();
		refresh();
	}
}
