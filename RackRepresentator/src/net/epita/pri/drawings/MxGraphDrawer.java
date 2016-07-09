package net.epita.pri.drawings;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.mxgraph.shape.mxRectangleShape;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class MxGraphDrawer implements IDrawer {
	private JPanel container;
	private mxGraph graph;
	private mxGraphComponent graphComponent;
	private Object box;
	
	public MxGraphDrawer(JPanel container) {
		this.container = container;
		graph = new mxGraph();
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(200, 200));
		container.add(graphComponent);
	}

	@Override
	public void add(IDrawable drawable) {
	}

	@Override
	public void remove(IDrawable drawable) {
	}

	@Override
	public void draw() {
		graph.getModel().beginUpdate();
		Object parent = graph.getDefaultParent();
		mxRectangleShape shape = new mxRectangleShape();
		String style = mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_CYLINDER;
		style += ";" + mxConstants.STYLE_FILLCOLOR + "=#ff0000";
		box = graph.insertVertex(parent, null, "TEST", 300, 80, 100, 50, style);
		graph.getModel().endUpdate();
	}

	@Override
	public void handleEvent(int eventId, Object data) {
		Object[] obj = { box };
		graph.removeCells(obj);
	}
}