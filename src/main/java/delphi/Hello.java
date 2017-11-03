package delphi;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import delphi.ExpressionsParser.BinaryContext;
import delphi.ExpressionsParser.NegateContext;
import delphi.ExpressionsParser.NumberContext;
import delphi.ExpressionsParser.ParenthesizedContext;

public class Hello {
	public static void main(String[] args) {
		CodePointCharStream stream = CharStreams.fromString("(1+2)*(3+4)");

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
	public Double visitNumber(NumberContext ctx) {
		String number = ctx.NUMBER().getText();
		return Double.valueOf(number);
	}

	@Override
	public Double visitNegate(NegateContext ctx) {
		return -ctx.expression().accept(this);
	}

	@Override
	public Double visitBinary(BinaryContext ctx) {
		Double left = ctx.left.accept(this);
		String operator = ctx.operator.getText();
		Double right = ctx.right.accept(this);

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
		throw new AssertionError(ctx.toString());
	}

	@Override
	public Double visitParenthesized(ParenthesizedContext ctx) {
		return ctx.expression().accept(this);
	}
}
