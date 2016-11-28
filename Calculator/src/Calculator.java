import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Created by akrasnov on 26.11.2016.
 */

public class Calculator {
     final static List<String> availableOperators = new ArrayList<String>() {{
      add("+");
      add("-");
      add("/");
      add("*");
    }};

    public static String calculateExpression(String expression) throws NotValidString, NotAvailableOperator,
            TooManyOperands, TooManyOperators, UnexpectedErrorInString {

        expression = normalizeString(expression);
        if (!validateExpression(expression)) throw new NotValidString(expression);

        List<String> operands = new ArrayList<String>(Arrays.asList(expression.split("-|/|\\*|\\+")));
        List<String> operators = new ArrayList<String>(Arrays.asList(expression.split("\\d+")));

        // Не могу в regex, который вернет только математические операторы.
        // Удаляем пустые строки по-другому.
        operators.removeAll(Arrays.asList("", null));
        //костыль, чтобы можно было работать с дробными числами
        operators.removeAll(Arrays.asList(".", null));
        operands.removeAll(Arrays.asList("", null));

        // подерживается только одна операция над двумя операндами
        // поэтому если найдено >2 операндов, или >1 оператора
        // ничего не считать, а вывести сообщение об ошибке.
        if (operands.size() > 2) throw new TooManyOperands(expression);
        if (operators.size() > 1) throw new TooManyOperators(expression);

        // чекнуть, что оператор доступен в списке операторов.
        for (String operator : operators){
            if (!availableOperators.contains(operator.replaceAll("\\s", ""))) {
                throw new NotAvailableOperator(operator);
            }
        }

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return engine.eval(expression).toString();
        } catch (ScriptException e) {
            throw new UnexpectedErrorInString(expression);
        }
    }

    private static String normalizeString (String expression) {
        return expression.replaceAll(" ", "");
    }
    private static boolean validateExpression(String expression) {
        /*
        Условия валидной строки:
        1. Строка начинается и кончается числом. Например, "2+2".
         */
        return Character.isDigit(expression.charAt(0)) && Character.isDigit(expression.charAt(expression.length()-1));
    }

}
