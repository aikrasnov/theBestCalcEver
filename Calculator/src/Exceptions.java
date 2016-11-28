/**
 * Created by akrasnov on 28.11.2016.
 */

class TooManyOperands extends Exception {
    TooManyOperands(String invalidString) {
        super(String.format("Слишком много операндов (максимум 2): " + invalidString));
    }
}

class TooManyOperators extends Exception {
    TooManyOperators(String invalidString) {
        super(String.format("Слишком много операторов (максимум 1): " + invalidString));
    }
}

class NotValidString extends Exception{
    NotValidString(String invalidString) {
        super(String.format("Невалидная строка: " + invalidString));
    }
}

class NotAvailableOperator extends Exception {
    NotAvailableOperator(String invalidOperator) {
        super(String.format("Недоступный оператор: " + invalidOperator));
    }
}

class UnexpectedErrorInString extends Exception {
    UnexpectedErrorInString (String invalidString) {
        super(String.format("Ошибка в анализе строки: " + invalidString));
    }
}