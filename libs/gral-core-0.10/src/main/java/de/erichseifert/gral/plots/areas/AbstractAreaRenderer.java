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
package de.erichseifert.gral.plots.areas;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.io.Serializable;
import java.util.List;

import de.erichseifert.gral.plots.DataPoint;
import de.erichseifert.gral.util.GeometryUtils;
import de.erichseifert.gral.util.MathUtils;

/**
 * <p>Abstract class that renders an area in two-dimensional space.</p>
 * <p>Functionality includes:</p>
 * <ul>
 *   <li>Punching data points out of the area's shape</li>
 *   <li>Administration of settings</li>
 * </ul>
 */
public abstract class AbstractAreaRenderer implements AreaRenderer, Serializable {
	/** Version id for serialization. */
	private static final long serialVersionUID = -9064749128190128428L;

	/** Gap between points and the area. */
	private double gap;
	/** Decides whether the shape of the gap between points and the area is
	 * rounded. */
	private boolean gapRounded;
	/** Paint to fill the area. */
	private Paint color;

	/**
	 * Initializes a new instance with default settings.
	 */
	public AbstractAreaRenderer() {
		gap = 0.0;
		gapRounded = false;
		color = Color.GRAY;
	}

	/**
	 * Returns the shape of an area from which the shapes of the specified
	 * points are subtracted.
	 * @param shape Shape of the area.
	 * @param dataPoints Data points on the line.
	 * @return Punched shape.
	 */
	protected Shape punch(Shape shape, List<DataPoint> dataPoints) {
		double size = getGap();
		if (!MathUtils.isCalculatable(size) || size == 0.0) {
			return shape;
		}

		boolean rounded = isGapRounded();

		// Subtract shapes of data points from the area to yield gaps.
		Area punched = new Area(shape);
		for (DataPoint p : dataPoints) {
			punched = GeometryUtils.punch(punched, size, rounded,
				p.position.getPoint2D(), p.shape);
		}
		return punched;
	}

	@Override
	public double getGap() {
		return gap;
	}

	@Override
	public void setGap(double gap) {
		this.gap = gap;
	}

	@Override
	public boolean isGapRounded() {
		return gapRounded;
	}

	@Override
	public void setGapRounded(boolean gapRounded) {
		this.gapRounded = gapRounded;
	}

	@Override
	public Paint getColor() {
		return color;
	}

	@Override
	public void setColor(Paint color) {
		this.color = color;
	}
}
