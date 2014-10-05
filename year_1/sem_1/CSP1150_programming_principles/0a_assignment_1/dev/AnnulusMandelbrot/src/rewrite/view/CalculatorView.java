package rewrite.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CalculatorView extends JFrame {
	
	private CalculatorFrame theFrame;
	private PanelGridLayout panelLeft;
	private PanelGridLayout panelRight;
	private PanelGridBagLayout panelAnnulusCalc;
	private PanelGridBagLayout panelAnnulusBorder;

	public CalculatorView() {
		
		// create frame
		this.theFrame = new CalculatorFrame();
		
		// create panelLeft and add to the frame
		this.theFrame.getContentPane().add(this.panelLeft = new PanelGridLayout(2, 1, 20, 5));
		
		// set panelLeft layout
		this.panelLeft.setLayout(this.panelLeft.theGridLayout);
		
		// create panelRight and add to the frame
		this.theFrame.getContentPane().add(this.panelRight = new PanelGridLayout(2, 1, 20, 5));
		
		// set panelRight layout
		this.panelRight.setLayout(this.panelRight.theGridLayout);
		
		// create panelAnnulusCalc container
		this.panelLeft.add(this.panelAnnulusCalc = new PanelGridBagLayout());
		
		// set panelAnnulusCalc layout to gbl
		this.panelAnnulusCalc.setLayout(this.panelAnnulusCalc.gbl_panel);
		
		// create panelAnnulusBorder (use overloading constructor)
		this.panelAnnulusBorder = new PanelGridBagLayout(20, 20, 10, 0, 0, 0);

		// create panelAnnulusBorder (use overloading constructor) and add to container
		this.panelAnnulusCalc.add(this.panelAnnulusBorder, this.panelAnnulusBorder.gbc_panel);

		createBorder(this.panelAnnulusBorder, "Annulus Calculator");
		
		this.panelAnnulusCalc.add(this.panelAnnulusBorder, this.panelAnnulusBorder.gbc_panel);
		
		this.panelAnnulusBorder.setLayout(this.panelAnnulusBorder.gbl_panel);
		
		
		// **DEBUGGING**
		JPanel panelMandelbrotCalc = new JPanel();
		panelMandelbrotCalc.setBackground(Color.ORANGE);
		panelLeft.add(panelMandelbrotCalc);
		JPanel panelAnnulusImage = new JPanel();
		panelAnnulusImage.setBackground(Color.ORANGE);
		panelRight.add(panelAnnulusImage);
		JPanel panelMandelbrotImage = new JPanel();
		panelMandelbrotImage.setBackground(Color.ORANGE);
		panelRight.add(panelMandelbrotImage);
		// **END DEBUG**
	}
	
	private void createBorder(JPanel thePanel, String borderTitle) {
		thePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), borderTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
}
