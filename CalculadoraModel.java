public class CalculadoraModel {

    private double num1;
    private String operador;

    public void setNumero(double numero) {
        this.num1 = numero;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double calcular(double num2) {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0)
                    throw new ArithmeticException("Divisão por zero");
                return num1 / num2;
            default:
                return 0;
        }
    }
}