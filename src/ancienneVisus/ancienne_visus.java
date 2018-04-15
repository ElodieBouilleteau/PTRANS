package ancienneVisus;

import processing.core.PApplet;

public class ancienne_visus {

	public void showRectangularVisualization( ) {
		VisuRect.setT1(this.t1);
		VisuRect.setT3(this.t3);
		VisuRect.setWidth(this.width);
		VisuRect.setHeight(this.height);
		VisuRect.setCP(this.CP);
		VisuRect.setCM(this.CM);
		VisuRect.setAlpha1(alpha1);
		VisuRect.setSavePath(savePath);
		VisuRect.setSave(save);
		PApplet.main("pqmethodvisu.model.VisuRect");
	}
	
	public void showCircularVisualization() {
		VisuCircle.setT1(this.t1);
		VisuCircle.setT3(this.t3);
		VisuCircle.setWidth(this.width);
		VisuCircle.setHeight(this.height);
		VisuCircle.setCP(this.CP);
		VisuCircle.setCM(this.CM);
		VisuCircle.setAlpha1(alpha1);
		VisuCircle.setSavePath(savePath);
		VisuCircle.setSave(save);
		System.out.println("test");
		//papplet = new VisuCircle();
		//((VisuCircle) papplet).show();
		PApplet.main("pqmethodvisu.model.VisuCircle");
	}
	
	/*test*/
	public PApplet getPApplet()
	{
		return papplet;
	}
	
	public void showBlackRectangularVisualization( ) {
		VisuRectBlack.setT1(this.t1);
		VisuRectBlack.setT3(this.t3);
		VisuRectBlack.setWidth(this.width);
		VisuRectBlack.setHeight(this.height);
		VisuRectBlack.setCP(this.CP);
		VisuRectBlack.setCM(this.CM);
		VisuRectBlack.setAlpha1(alpha1);
		VisuRectBlack.setSavePath(savePath);
		VisuRectBlack.setSave(save);
		PApplet.main("pqmethodvisu.model.VisuRect");
	}
	
	public void showBlackCircularVisualization() {
		VisuCircleBlack.setT1(this.t1);
		VisuCircleBlack.setT3(this.t3);
		VisuCircleBlack.setWidth(this.width);
		VisuCircleBlack.setHeight(this.height);
		VisuCircleBlack.setCP(this.CP);
		VisuCircleBlack.setCM(this.CM);
		VisuCircleBlack.setAlpha1(alpha1);
		VisuCircleBlack.setSavePath(savePath);
		VisuCircleBlack.setSave(save);
		PApplet.main("pqmethodvisu.model.VisuCircleBlack");
	}
	
}
