package delphi;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import delphi.ExpressionsParser.ExpressionContext;
import delphi.ExpressionsParser.ParExpressionContext;

public class Hello {
	public static void main(String[] args) {
		CodePointCharStream stream = CharStreams.fromString("(42)");

		ExpressionsLexer lexer = new ExpressionsLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExpressionsParser parser = new ExpressionsParser(tokens);
		ExpressionsParser.ExpressionContext expression = parser.expression();

		MyVisitor visitor = new MyVisitor();
		double result = visitor.visit(expression);
		System.out.println(result);
	}
}

class MyVisitor extends ExpressionsBaseVisitor<Double> {
	@Override
	public Double visitExpression(ExpressionContext ctx) {
		// TODO Geht das nicht auch anders, als die Kinder zu zählen?
		int childCount = ctx.getChildCount();
		if (childCount == 1) {
			String number = ctx.getChild(0).getText();
			return Double.valueOf(number);
		} else if (childCount == 2) {
			return -ctx.getChild(1).accept(this);
		} else if (childCount == 3) {
			Double left = ctx.getChild(0).accept(this);
			String operator = ctx.getChild(1).getText();
			Double right = ctx.getChild(2).accept(this);

			switch (operator) {
			case "+":
				return left + right;
			case "-":
				return left - right;
			case "*":
				return left * right;
			case "/":
				return left / right;
			}
		}
		throw new AssertionError(ctx.toString());
	}

	@Override
	public Double visitParExpression(ParExpressionContext ctx) {
		ParseTree child = ctx.getChild(1);
		System.out.println(child.getText());
		return child.accept(this);
	}
}
