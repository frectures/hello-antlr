package delphi;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Hello {
	public static void main(String[] args) {
		CodePointCharStream stream = CharStreams.fromString("123");

		ExpressionsLexer lexer = new ExpressionsLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExpressionsParser parser = new ExpressionsParser(tokens);
		ExpressionsParser.ExpressionContext expression = parser.expression();

		MyVisitor visitor = new MyVisitor();
		visitor.visit(expression);
	}
}

class MyVisitor extends ExpressionsBaseVisitor<Void> {
	@Override
	public Void visitExpression(ExpressionsParser.ExpressionContext ctx) {
		System.out.println("visit " + ctx.getText());
		return super.visitExpression(ctx);
	}
}
