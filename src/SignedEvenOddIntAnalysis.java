import java.util.*;

import soot.G;
import soot.Body;
import soot.IntegerType;
import soot.Local;
import soot.Unit;
import soot.Value;
import soot.jimple.AddExpr;
import soot.jimple.BinopExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.IfStmt;
import soot.jimple.IntConstant;
import soot.jimple.MulExpr;
import soot.jimple.Stmt;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardBranchedFlowAnalysis;

public class SignedEvenOddIntAnalysis 
			extends ForwardBranchedFlowAnalysis<Map<Local, OddEvenwithSignLattice.Element>>{

	private Map<Local, OddEvenwithSignLattice.Element> entryFlow;
	private Map<Local, OddEvenwithSignLattice.Element> newFlow;
	
	public SignedEvenOddIntAnalysis(UnitGraph g){
		super(g);
		entryFlow = new HashMap<Local, OddEvenwithSignLattice.Element>();
		newFlow = new HashMap<Local, OddEvenwithSignLattice.Element>();
		Body b = g.getBody();
		for (Local l : b.getLocals()) {
			if(l.getType() instanceof IntegerType){
				entryFlow.put(l, OddEvenwithSignLattice.TOP);
				newFlow.put(l, OddEvenwithSignLattice.TOP);				
			}
		}
		G.v().out.println("Analyzing "+ g.getBody().getMethod().getName());
		doAnalysis();
		G.v().out.println("-----------");
	}
	
	protected void flowThrough(Map<Local, OddEvenwithSignLattice.Element> src, Unit unit,
			List<Map<Local, OddEvenwithSignLattice.Element>> fallOut, 
				List<Map<Local, OddEvenwithSignLattice.Element>> branchOuts) {
			// TODO Auto-generated method stub
		Stmt s = (Stmt) unit;
		Map<Local, OddEvenwithSignLattice.Element> out = new HashMap<Local, OddEvenwithSignLattice.Element>(src);
		Map<Local, OddEvenwithSignLattice.Element> outBrance = new HashMap<Local, OddEvenwithSignLattice.Element>(src);

		if(s instanceof IfStmt){

		}else if (s instanceof DefinitionStmt) {
			DefinitionStmt defStmt = (DefinitionStmt)s;
			if(defStmt.getLeftOp().getType() instanceof IntegerType){
				handleAssignment(defStmt, out);
			}
		}

		for(Iterator<Map<Local, OddEvenwithSignLattice.Element>> it = fallOut.iterator(); it.hasNext();){
			copy(out, it.next());
		}
		for (Iterator<Map<Local, OddEvenwithSignLattice.Element>> it = branchOuts.iterator(); it.hasNext(); ) {
			copy(outBrance, it.next());
		}
		
		G.v().out.println(unit.toString() + " // " + out.toString());
	}
	
	
	private void handleAssignment(DefinitionStmt assignStmt, Map<Local, OddEvenwithSignLattice.Element> out) {
		G.v().out.println("in handle assignment");
		if(assignStmt.getLeftOp() instanceof Local){
			Local left = (Local)assignStmt.getLeftOp();
			if(out.containsKey(left)){
				if(assignStmt.getRightOp() instanceof IntConstant){
					IntConstant right = (IntConstant)assignStmt.getRightOp();
					G.v().out.println(OddEvenwithSignLattice.OfInt(right.value).toString());
					out.put(left, OddEvenwithSignLattice.OfInt(right.value));
				}else if (assignStmt.getRightOp() instanceof Local && out.containsKey((Local)assignStmt.getRightOp())) {
					out.put(left, out.get((Local)assignStmt.getRightOp()));
				}else if (assignStmt.getRightOp() instanceof BinopExpr) {
					BinopExpr expr = (BinopExpr) assignStmt.getRightOp();
					Value op1Value = expr.getOp1();
					Value op2Value = expr.getOp2();
					
					OddEvenwithSignLattice.Element v1 = OddEvenwithSignLattice.TOP;
					OddEvenwithSignLattice.Element v2 = OddEvenwithSignLattice.TOP;
					if(op1Value instanceof Local && out.containsKey((Local) op1Value)){
						v1 = out.get((Local) op1Value);
					}else if (op1Value instanceof IntConstant) {
						IntConstant n = (IntConstant) op1Value;
						v1 = OddEvenwithSignLattice.OfInt(n.value);
					}
					if(op2Value instanceof Local && out.containsKey((Local) op2Value)){
						v2 = out.get((Local) op2Value);
					}else if (op2Value instanceof IntConstant) {
						IntConstant n = (IntConstant) op2Value;
						v2 = OddEvenwithSignLattice.OfInt(n.value);
					}
					
					OddEvenwithSignLattice.Element result = OddEvenwithSignLattice.TOP;
					if(expr instanceof AddExpr){
						result = OddEvenwithSignLattice.add(v1, v2);
					}else if (expr instanceof MulExpr) {
						result = OddEvenwithSignLattice.multi(v1, v2);
					}
					out.put(left, result);
				}else {
					out.put(left, OddEvenwithSignLattice.TOP);
				}
			}
		}
	}
	
	protected void copy(Map<Local, OddEvenwithSignLattice.Element> src, 
						Map<Local, OddEvenwithSignLattice.Element> dest) {
		// TODO Auto-generated method stub
		dest.clear();
		dest.putAll(src);
	}

	@Override
	protected Map<Local, OddEvenwithSignLattice.Element> entryInitialFlow() {
		// TODO Auto-generated method stub
		return new HashMap<Local, OddEvenwithSignLattice.Element>(entryFlow);
	}

	protected void merge(Map<Local, OddEvenwithSignLattice.Element> src1,
						Map<Local, OddEvenwithSignLattice.Element> src2,
						Map<Local, OddEvenwithSignLattice.Element> dest) {
		// TODO Auto-generated method stub
		copy(src1, dest);
		for (Map.Entry<Local, OddEvenwithSignLattice.Element> entry : src2.entrySet()) {
			Local key = entry.getKey();
			OddEvenwithSignLattice.Element value = entry.getValue();
			if (dest.containsKey(key)) {
				dest.put(key, value.meet(dest.get(key)));
			} else {
				dest.put(key, value);
			}
			
		}
	}

	@Override
	protected Map<Local, OddEvenwithSignLattice.Element> newInitialFlow() {
		// TODO Auto-generated method stub
		return new HashMap<Local, OddEvenwithSignLattice.Element>(newFlow);
	}
		
	
}
