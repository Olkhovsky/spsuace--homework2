package ru.spsuace.homework2.objects;

/**
 * Опишите иммутабельное комплексное число, с геттерами и методами toString()
 * Так же дополните класс методами, чтобы эти объекты можно было использовать в HashMap
 * Плюс реализуйте любой метод (операцию) над комплексными числами (на ваш выбор)
 */
public class ComplexNumber {
    private final  double realPart;
    private final double complexPart;

    public ComplexNumber(Double realPart, Double complexPart) {
        this.realPart = realPart;
        this.complexPart = complexPart;
    }

    public double getRalPart() {
        return realPart;
    }


    public double getComplexPart() {
        return complexPart;
    }

    @Override
    public String toString() {
        char sign;
        if (complexPart > 0) {
            sign = '+';
        } else {
            sign = '-';
        }
        return "" + realPart + sign + Math.abs(complexPart) + 'i';
    }

    @Override
    public int hashCode() {
        String s = toString();
        return  s.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof ComplexNumber) {
            ComplexNumber number = (ComplexNumber)o;
            if ((number.getRalPart() == realPart) && (number.getComplexPart() == complexPart)) {
                return  true;
            }
        }
        return  false;
    }
}
