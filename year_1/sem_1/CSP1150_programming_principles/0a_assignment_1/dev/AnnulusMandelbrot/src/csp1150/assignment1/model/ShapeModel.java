package csp1150.assignment1.model;

/**
 * This super class defines the shape model.
 * The shape's attributes are saved here,
 * and all calculation is handled by the methods
 * in this class.
 * 
 * @author Martin Ponce ID# 10371381
 * @version 5.1.0
 * @since 20141011
 */
public class ShapeModel {

	// declare constant grid size & samples
	protected final int GRIDSIZE = 444;
	protected final int SAMPLES = 100;
	
	// declare 2d array
	protected double[][] hits = new double[GRIDSIZE][GRIDSIZE];
	
	// these variables hold user input values
	protected double outRadius;
	protected double inRadius;
	
	// these variables hold the max-min values aka zoom values
	protected double maxX;
	protected double maxY;
	protected double minX;
	protected double minY;
	
	// these variables hold the calculated values
	private double areaApprox;
	protected double areaMonte;
	
	/**
	 * Default constructor for inheritance.
	 */
	public ShapeModel() {
		
	}
	
	/**
	 * This method sets the radius.
	 * 
	 * @param double r1 - The outer radius.
	 * @param double r2 - The inner radius.
	 */
	public final void setRadius(double outRadius, double inRadius) {
		
		// set incoming radius values to their respective variables
		this.outRadius = outRadius;
		this.inRadius = inRadius;
		
		// set incoming radius values to their respective max-min variables
		this.maxX = outRadius;
		this.minX = -outRadius;
		this.maxY = outRadius;
		this.minY = -outRadius;
		
		// do Area calculation
		calcApprox(this.maxX, this.maxY, this.minX, this.minY);
		
		// do Monte calculation
		calcMonte(this.maxX, this.maxY, this.minX, this.minY);
	}
	
	/**
	 * This method calculates the area using approximate estimation.
	 * 
	 * @param args unused
	 */
	protected final void calcApprox(double maxX, double maxY, double minX, double minY) {
		
		// declare counter
		int counter = 0;
		
		// iterate through columns
		for(int col = 0; col < GRIDSIZE - 1; col++) {
			
			// calculate column center
			double x = minX + (col + 0.5) * ((maxX - minX) / GRIDSIZE);
			
			// iterate through rows
			for(int row = 0; row < GRIDSIZE - 1; row++) {
				
				// calculate row center
				double y = minY + (row + 0.5) * ((maxY - minY) / GRIDSIZE);
				
				 // if test pass, set counter + 1
				if(isInside(x, y)) {
					counter++;
				}
			}
		}
		
		// set calculated area to areaApprox
		areaApprox = (maxX - minX) * (maxY - minY) * counter / Math.pow(GRIDSIZE, 2);
	}
	
	/**
	 * This method calculates the area using monte carlo estimation.
	 * Max-min values control the zoom of the image.
	 * 
	 * @param double maxX - The max X coordinate.
	 * @param double maxY - The max Y coordinate.
	 * @param double minX - The min X coordinate.
	 * @param double minY - The min Y coordinate.
	 */
	public final void calcMonte(double maxX, double maxY, double minX, double minY) {
		
		// declare counter
		double counter = 0.0;
		
		// declare arraySum
		double arraySum = 0.0;
		
		// iterate through columns
		for(int col = 0; col < GRIDSIZE - 1; col++) {
			
			// iterate through rows
			for(int row = 0; row < GRIDSIZE - 1; row++) {
				
				// reset current cell value to 0
				hits[col][row] = 0;
				
				// iterate through samples
				for(int i = 0; i < SAMPLES; i++) {
					
					// generate random scatter points per cell
					double x = minX + (col + Math.random()) * ((maxX - minX) / GRIDSIZE);
					double y = minY + (row + Math.random()) * ((maxY - minY) / GRIDSIZE);
					
					// if test pass, set counter + 1
					if(isInside(x, y)) {
						hits[col][row] = 1;
						arraySum++;
					}
				}
			}
		}
		
		// divide sum of array to samples
		arraySum = arraySum / SAMPLES;
		
		// add sum of array to counter
		counter = counter + arraySum;
		
		// calculate area
		areaMonte = (maxX - minX) * (maxY - minY) * counter / Math.pow(GRIDSIZE, 2);
	}
	
	/**
	 * This method checks if center of cell, or hitpoints are within shape perimeter.
	 * 
	 * @param double x - The x-axis value.
	 * @param double y - The y-axis value.
	 * @return boolean inside.
	 */
	protected boolean isInside(double x, double y) {
		
		// calculate test value
		double test = x * x + y * y;
		
		// declare boolean variable to be returned
		boolean inside;
		
		// if test value less than outer radius squared and greater than inner radius squared
		if(test < outRadius * outRadius && test > inRadius * inRadius) {
			
			// set boolean to true
			inside = true;
			
		// else set boolean value to false
		} else {
			inside = false;
		}
		
		// return boolean value
		return inside;
	}
	
	/**
	 * This method returns the area calculated using approximate estimation.
	 * 
	 * @param args unused
	 * @return double areaApprox
	 */
	public final double getAreaCalc() {
		return areaApprox;
	}
	
	/**
	 * This method returns the area calculated using monte carlo method.
	 * 
	 * @param args unused
	 * @return double areaMonte
	 */
	public final double getMonteCalc() {
		return areaMonte;
	}
	
	/**
	 * This method returns the hits array to be used for the hit view generator.
	 * 
	 * @param args unused
	 * @return double[][] hits.
	 */
	public final double[][] getHitsArray() {
		return hits;
	}
	
	/**
	 * This method returns the GRIDSIZE constant.
	 * 
	 * @param args unused
	 * @return int GRIDSIZE.
	 */
	public final int getGridSize() {
		return GRIDSIZE;
	}
	
	/**
	 * This method sets the max-min values,
	 * to be used for zooming in and out of the image.
	 * 
	 * @param double maxX
	 * @param double minX
	 * @param double maxY
	 * @param double minY
	 */
	public final void setMaxMins(double maxX, double minX, double maxY, double minY) {
		
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
	}
}